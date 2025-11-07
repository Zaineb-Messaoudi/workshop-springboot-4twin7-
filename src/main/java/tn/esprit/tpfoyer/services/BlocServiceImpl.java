package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.DTO.BlocDTO;
import tn.esprit.tpfoyer.DTO.BlocMapper;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.repositories.BlocRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{


    BlocRepository blocRepository;

    BlocMapper mapper;

    @Override
    public Bloc addOrUpdateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc getBlocById(long id) {
        return blocRepository.findById(id).get();
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public void deleteBloc(long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public BlocDTO getBloc(long id) {
        Bloc bloc = blocRepository.findById(id).orElseThrow(null);
        return mapper.toDTO(bloc);
    }
}

