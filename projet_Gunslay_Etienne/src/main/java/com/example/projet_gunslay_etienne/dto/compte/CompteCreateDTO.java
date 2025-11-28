package com.example.projet_gunslay_etienne.dto.compte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteCreateDTO {

    @NotBlank
    private String numeroCompte;

    @NotNull
    private BigDecimal soldeInitial;

    @NotBlank
    private String typeCompte;

    private BigDecimal decouvert;

    private BigDecimal tauxRemuneration;

    @NotNull
    private Long clientId;
}
