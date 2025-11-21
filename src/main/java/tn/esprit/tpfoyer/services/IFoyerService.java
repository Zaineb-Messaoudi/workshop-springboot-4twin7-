package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdateFoyer(Foyer foyer);
    List<Foyer> findAll();
    Foyer findById(Long id);
    void deleteFoyer(Long id);
    Foyer addBlocetFoyerAssocie (Foyer foyer);
    void assignBlocToFoyer(Long idFoyer, Long idBloc);
    void unassignBlocFromFoyer(Long idBloc);
}
