package com.example.projet_gunslay_etienne.dto.carte;

import com.example.projet_gunslay_etienne.domain.enums.TypeCarte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteCreateDTO {

    @NotBlank
    private String numeroCompte;

    @NotNull
    private TypeCarte typeCarte;

    private Boolean active;
}
