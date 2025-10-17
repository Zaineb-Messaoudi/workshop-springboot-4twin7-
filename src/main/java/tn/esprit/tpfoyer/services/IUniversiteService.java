package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite getUniversite(Long id);
    Universite addOrUpdateUniversite(Universite universite);
    void deleteUniversite(Long id);
    List<Universite> getAllUniversites();
}
