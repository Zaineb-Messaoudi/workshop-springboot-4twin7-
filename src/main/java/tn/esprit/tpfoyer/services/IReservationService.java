package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation findById(String id);
    List<Reservation> findAll();
    void deleteReservation(String id);
    Reservation addOrUpdateReservation(Reservation reservation);

}
