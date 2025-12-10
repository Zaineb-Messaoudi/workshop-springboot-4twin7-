package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.DTO.BlocDTO;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.IBlocService;

import java.util.List;

@RestController
@RequestMapping("/bloccontroller")
public class BlocController {
    @Autowired
    IBlocService blocService;

    @PostMapping("/addBloc")
    Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }

    @PutMapping("/updatebloc")
    Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }

    @DeleteMapping("/deletebloc/{idBloc}")
    void deleteBloc(@PathVariable Long idBloc) {
        blocService.deleteBloc(idBloc);
    }

    @GetMapping("/findallbloc")
    List<Bloc> findAllBloc() throws InterruptedException {
        return blocService.getAllBlocs();
    }

    @GetMapping("/getblocbyid/{idBloc}")
    Bloc getBlocById(@PathVariable Long idBloc) {
        return blocService.getBlocById(idBloc);
    }

    @GetMapping("/{idBloc}")
    BlocDTO getBlocDTO(@PathVariable Long idBloc) {return blocService.getBloc(idBloc);}

    @GetMapping("/nonAffectes")
    public List<Bloc> getBlocsNonAffectes() {
        return blocService.getBlocsNonAffectes();
    }

    @GetMapping("/capaciteSup30")
    public List<Bloc> getBlocsCapaciteSup30() {
        return blocService.getBlocsCapaciteGT30();
    }

    @GetMapping("/nomStartsA")
    public List<Bloc> getBlocsNomStartsWithA() {
        return blocService.getBlocsNomStartsWithA();
    }

    @GetMapping("/nomStartsA-capaciteSup30")
    public List<Bloc> getBlocsNomStartsWithAAndCapaciteSup30() {
        return blocService.getBlocsNomStartsWithAAndCapaciteGT30();
    }

}
