package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.DTO.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.repositories.EtudiantRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{
    EtudiantRepository etudiantRepository;
    @Override
    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id).get();
    }

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addOrUpdateEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiantById(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public EtudiantDTO getEtudiantDTOById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));
        return convertToDTO(etudiant);
    }

    @Override
    public EtudiantDTO convertToDTO(Etudiant etudiantDetails) {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setCin(etudiantDetails.getCin());
        etudiantDTO.setNomEt(etudiantDetails.getNomEt());
        etudiantDTO.setPrenomEt(etudiantDetails.getPrenomEt());
        etudiantDTO.setCin(etudiantDetails.getCin());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        etudiantDTO.setDateNaissance(etudiantDetails.getDateNaissance().format(formatter));
        return etudiantDTO;
    }
}
