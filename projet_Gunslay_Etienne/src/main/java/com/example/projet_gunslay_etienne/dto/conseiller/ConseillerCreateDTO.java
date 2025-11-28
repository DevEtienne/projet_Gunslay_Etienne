package com.example.projet_gunslay_etienne.dto.conseiller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConseillerCreateDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    private Long agenceId; // agence à laquelle il appartient
}

