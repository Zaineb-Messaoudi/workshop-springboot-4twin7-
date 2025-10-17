package tn.esprit.tpfoyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    List<Bloc> findAllBloc() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/getblocbyid/{idBloc}")
    Bloc getBlocById(@PathVariable Long idBloc) {
        return blocService.getBlocById(idBloc);
    }
}
