package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Bloc;

import java.util.List;

public interface IBlocService {

    Bloc addOrUpdateBloc(Bloc bloc);

    Bloc getBlocById(long id);

    List<Bloc> getAllBlocs();

    void deleteBloc(long id);

}
