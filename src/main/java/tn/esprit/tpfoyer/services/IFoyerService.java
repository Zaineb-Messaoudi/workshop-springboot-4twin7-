package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdateFoyer(Foyer foyer);
    List<Foyer> findAll();
    Foyer findById(Long id);
    void deleteFoyer(Long id);
}
