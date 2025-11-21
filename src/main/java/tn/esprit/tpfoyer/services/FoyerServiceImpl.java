package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {
    final FoyerRepository foyerRepository;
    final BlocRepository blocRepository;

    @Override
    public Foyer addOrUpdateFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public List<Foyer> findAll() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer findById(Long id) {
        return foyerRepository.findById(id).get();
    }

    @Override
    public void deleteFoyer(Long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public Foyer addBlocetFoyerAssocie(Foyer foyer) {
        Foyer f = foyerRepository.save(foyer);
        foyer.getBlocs().forEach(bloc -> {
            bloc.setFoyer(f);
            blocRepository.save(bloc);
        });
        return f;
    }

    @Override
    public void assignBlocToFoyer(Long idFoyer, Long idBloc) {
        Bloc b = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé"));
        Foyer f = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé"));
        b.setFoyer(f);
        blocRepository.save(b);;
    }

    @Override
    public void unassignBlocFromFoyer(Long idBloc) {
        Bloc b = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé"));
        b.setFoyer(null);  // désaffectation
        blocRepository.save(b);
    }


}
