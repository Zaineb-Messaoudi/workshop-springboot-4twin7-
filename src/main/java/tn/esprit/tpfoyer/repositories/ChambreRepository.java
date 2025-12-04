package tn.esprit.tpfoyer.repositories;

import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByTypeC(TypeChambre typeC);

    Chambre findByNumeroChambre(Long numeroChambre);

    @Query("SELECT c FROM Chambre c JOIN c.reservations r JOIN r.etudiants e WHERE e.cin = :cin")
    Chambre findChambreByEtudiantCin(@Param("cin") Long cin);

}
