package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Chambre;

import java.util.List;

public interface IChambreService {

    Chambre findById(long id);
    List<Chambre> findAll();
    Chambre addOrUpdateChambre(Chambre chambre);
    void deleteChambre(long id);
}
