package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.IChambreService;

import java.util.List;


@RestController
@RequestMapping("/chambrecontroller")
public class ChambreController {
    @Autowired
    IChambreService chambreService;

    @PostMapping("/addchambre")
    Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }

    @PostMapping("/updatechambre")
    Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }

    @DeleteMapping("/deletechambre/{idChambre}")
    void deleteChambre(@PathVariable Long idChambre) {
        chambreService.deleteChambre(idChambre);
    }

    @GetMapping("/findallchambre")
    List<Chambre> findAllChambre() {
        return chambreService.findAll();
    }

    @GetMapping("/findchambrebyid/{idChambre}")
    Chambre findChambreById(@PathVariable Long idChambre) {
        return chambreService.findById(idChambre);
    }


}
