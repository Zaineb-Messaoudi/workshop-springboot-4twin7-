package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservationcontroller")
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @PostMapping("/addReservation")
    Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }

    @PutMapping("/updatereservation")
    Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }

    @DeleteMapping("/deletereservation/{idReservation}")
    void deleteReservation(@PathVariable String idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    @GetMapping("/findallreservation")
    List<Reservation> findAllReservation() {
        return reservationService.findAll();
    }

    @GetMapping("/getreservationbyid/{idReservation}")
    Reservation getReservationById(@PathVariable String idReservation) {
        return reservationService.findById(idReservation);
    }
}