package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universitecontroller")
public class UniversiteController {
    @Autowired
    IUniversiteService universiteService;

    @PostMapping("/addUniversite")
    Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addOrUpdateUniversite(universite);
    }

    @PutMapping("/updateuniversite")
    Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.addOrUpdateUniversite(universite);
    }

    @DeleteMapping("/deleteuniversite/{idUniversite}")
    void deleteUniversite(@PathVariable Long idUniversite) {
        universiteService.deleteUniversite(idUniversite);
    }

    @GetMapping("/findalluniversite")
    List<Universite> findAllUniversite() {
        return universiteService.getAllUniversites();
    }

    @GetMapping("/getuniversitebyid/{idUniversite}")
    Universite getUniversiteById(@PathVariable Long idUniversite) {
        return universiteService.getUniversite(idUniversite);
    }
}
