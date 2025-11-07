package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.DTO.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant findById(Long id);
    List<Etudiant> findAll();
    Etudiant addOrUpdateEtudiant(Etudiant etudiant);
    void deleteEtudiantById(Long id);
    EtudiantDTO getEtudiantDTOById(Long id);
    EtudiantDTO convertToDTO(Etudiant etudiantDetails);
}
