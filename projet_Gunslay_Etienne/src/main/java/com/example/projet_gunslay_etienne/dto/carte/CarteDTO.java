package com.example.projet_gunslay_etienne.dto.carte;

import com.example.projet_gunslay_etienne.domain.enums.TypeCarte;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteDTO {

    private Long id;
    private TypeCarte typeCarte;
    private boolean active;
    private String numeroCompte;
}
