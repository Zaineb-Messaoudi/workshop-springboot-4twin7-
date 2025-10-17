package tn.esprit.tpfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.repositories.UnivarsiteRepository;

import java.util.List;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    UnivarsiteRepository univarsiteRepository;
    @Override
    public Universite getUniversite(Long id) {
        return univarsiteRepository.findById(id).get();
    }

    @Override
    public Universite addOrUpdateUniversite(Universite universite) {
        return univarsiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(Universite universite) {
        univarsiteRepository.delete(universite);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return univarsiteRepository.findAll();
    }
}
