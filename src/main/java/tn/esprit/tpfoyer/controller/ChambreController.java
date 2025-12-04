package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.TypeChambre;
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

    @GetMapping("/type/{type}")
    public List<Chambre> findByType(@PathVariable TypeChambre type) {
        return chambreService.findByType(type);
    }

    @GetMapping("/numero/{numero}")
    public Chambre findByNumero(@PathVariable Long numero) {
        return chambreService.findByNumero(numero);
    }

    @GetMapping("/byEtudiantCin/{cin}")
    public Chambre findChambreByEtudiantCin(@PathVariable Long cin) {
        return chambreService.findChambreByEtudiantCin(cin);
    }


}
