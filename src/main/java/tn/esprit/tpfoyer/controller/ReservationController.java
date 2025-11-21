package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.DTO.ChambreReservationDTO;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.IChambreService;
import tn.esprit.tpfoyer.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservationcontroller")
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @Autowired
    IChambreService chambreService;

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

    @PostMapping("/createChambreWithReservation")
    public Chambre createChambreWithReservation(@RequestBody ChambreReservationDTO dto) {
        return reservationService.createChambreWithReservation(dto.getChambre(), dto.getReservation());
    }

    @PutMapping("/assignChambre/{idChambre}/{idReservation}")
    public Chambre assignChambre(@PathVariable Long idChambre,
                                  @PathVariable String idReservation) {
        return reservationService.assignChambre(idChambre, idReservation);
    }

    @PutMapping("/unassignChambre/{idChambre}/{idReservation}")
    public Chambre unassignChambre(@PathVariable Long idChambre,
                                     @PathVariable String idReservation) {
        return reservationService.unassignChambre(idChambre, idReservation);
    }

}