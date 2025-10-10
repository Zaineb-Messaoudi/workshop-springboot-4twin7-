package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}
