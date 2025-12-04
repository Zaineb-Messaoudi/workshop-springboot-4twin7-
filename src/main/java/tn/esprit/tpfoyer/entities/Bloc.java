package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class Bloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloc")
    private Long idBloc;

    @Column(name = "nom_bloc")
    private String nomBloc;

    @Column(name = "capacite_bloc")
    private Long capaciteBloc;

    @ManyToOne
    private Foyer foyer;

    @JsonIgnore
    @OneToMany(mappedBy="bloc")
    private Set<Chambre> chambres;
}
