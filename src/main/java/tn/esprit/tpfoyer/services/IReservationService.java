package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation findById(String id);
    List<Reservation> findAll();
    void deleteReservation(String id);
    Reservation addOrUpdateReservation(Reservation reservation);
    Chambre createChambreWithReservation(Chambre chambre, Reservation reservation);
    Chambre assignChambre(Long chambreId, String reservationId);
    Chambre unassignChambre(Long chambreId, String reservationId);

}
