package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.IFoyerService;

import java.util.List;

@RestController
@RequestMapping("/foyercontroller")
public class FoyerController {
    @Autowired
    IFoyerService foyerService;

    @PostMapping("/addFoyer")
    Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @PutMapping("/updatefoyer")
    Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @DeleteMapping("/deletefoyer/{idFoyer}")
    void deleteFoyer(@PathVariable Long idFoyer) {
        foyerService.deleteFoyer(idFoyer);
    }

    @GetMapping("/findallfoyer")
    List<Foyer> findAllFoyer() {
        return foyerService.findAll();
    }

    @GetMapping("/getfoyerbyid/{idFoyer}")
    Foyer getFoyerById(@PathVariable Long idFoyer) {
        return foyerService.findById(idFoyer);
    }
}
