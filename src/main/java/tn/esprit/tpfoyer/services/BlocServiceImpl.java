package tn.esprit.tpfoyer.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.DTO.BlocDTO;
import tn.esprit.tpfoyer.DTO.BlocMapper;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.repositories.BlocRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BlocServiceImpl implements IBlocService{


    final BlocRepository blocRepository;
    final BlocMapper mapper;

    @Override
    public Bloc addOrUpdateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc getBlocById(long id) {
        return blocRepository.findById(id).get();
    }

    @Override
    @Transactional
    @Scheduled(fixedDelay = 10000)
    public List<Bloc> getAllBlocs() throws InterruptedException{
        List<Bloc> blocs = blocRepository.findAll();
        Thread.sleep(5000);
        for (Bloc bloc:blocs) {
            System.out.println(bloc);
            log.info("bloc : "+bloc);
        }
        return blocs;
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

    @Override
    public List<Bloc> getBlocsNonAffectes() {
        return blocRepository.findByFoyerIsNull();
    }

    @Override
    public List<Bloc> getBlocsCapaciteGT30() {
        return blocRepository.findByCapaciteBlocGreaterThan(30L);
    }

    @Override
    public List<Bloc> getBlocsNomStartsWithA() {
        return blocRepository.findByNomBlocStartingWith("A");
    }

    @Override
    public List<Bloc> getBlocsNomStartsWithAAndCapaciteGT30() {
        return blocRepository.findByNomBlocStartingWithAndCapaciteBlocGreaterThan("A", 30L);
    }

}

