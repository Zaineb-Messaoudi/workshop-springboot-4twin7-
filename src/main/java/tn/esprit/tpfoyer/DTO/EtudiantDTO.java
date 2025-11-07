package tn.esprit.tpfoyer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {
    public String nomEt;
    public String prenomEt;
    public Long cin;
    public String ecole;
    public String dateNaissance;
}
