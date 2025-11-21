package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService{
    final ChambreRepository chambreRepository;
    final ReservationRepository reservationRepository;
    @Override
    public Reservation findById(String id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation addOrUpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Chambre createChambreWithReservation(Chambre chambre, Reservation reservation) {
        // Generate ID for reservation
        reservation.setIdReservation(UUID.randomUUID().toString());

        // Initialize the reservations set if null
        if (chambre.getReservations() == null) {
            chambre.setReservations(new HashSet<>());
        }

        // Add reservation to chambre
        chambre.getReservations().add(reservation);

        // Save reservation first
        reservationRepository.save(reservation);

        // Save chambre with reservation
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre assignChambre(Long chambreId, String reservationId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (chambre.getReservations() == null) {
            chambre.setReservations(new HashSet<>());
        }

        chambre.getReservations().add(reservation);
        return chambreRepository.save(chambre);
    }
    @Override
    public Chambre unassignChambre(Long chambreId, String reservationId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (chambre.getReservations() != null) {
            chambre.getReservations().remove(reservation);
        }

        return chambreRepository.save(chambre);
    }

}
