package tn.esprit.tpfoyer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChambreReservationDTO {
    private Chambre chambre;
    private Reservation reservation;
}
