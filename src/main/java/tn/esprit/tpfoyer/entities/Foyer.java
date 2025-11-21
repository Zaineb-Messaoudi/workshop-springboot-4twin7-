package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foyer")
    private Long idFoyer;

    @Column(name = "nom_foyer")
    private String nomFoyer;

    @Column(name = "capacite_foyer")
    private Long capaciteFoyer;

    @OneToMany(mappedBy="foyer", cascade=CascadeType.ALL)
    private Set<Bloc> blocs;

    @OneToOne(mappedBy = "foyer")
    private Universite universite;
}

