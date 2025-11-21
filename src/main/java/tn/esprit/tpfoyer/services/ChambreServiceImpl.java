package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService{

    final ChambreRepository chambreRepository;
    final ReservationRepository reservationRepository;

    @Override
    public Chambre findById(long id) {
        return chambreRepository.findById(id).get();
    }

    @Override
    public List<Chambre> findAll() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addOrUpdateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
        chambreRepository.deleteById(id);
    }




}
