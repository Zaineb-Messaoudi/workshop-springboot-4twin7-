package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.DTO.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiantcontroller")
public class EtudiantController {
    @Autowired
    IEtudiantService etudiantService;

    @PostMapping("/addEtudiant")
    Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @PutMapping("/updateetudiant")
    Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @DeleteMapping("/deleteetudiant/{idEtudiant}")
    void deleteEtudiant(@PathVariable Long idEtudiant) {
        etudiantService.deleteEtudiantById(idEtudiant);
    }

    @GetMapping("/findalletudiant")
    List<Etudiant> findAllEtudiant() {
        return etudiantService.findAll();
    }

    @GetMapping("/getetudiantbyid/{idEtudiant}")
    Etudiant getEtudiantById(@PathVariable Long idEtudiant) {
        return etudiantService.findById(idEtudiant);
    }

    @GetMapping("/{idEtudiant}/etudiantDetails")
    EtudiantDTO getEtudiantDTO(@PathVariable Long idEtudiant) { return etudiantService.getEtudiantDTOById(idEtudiant); }
}
