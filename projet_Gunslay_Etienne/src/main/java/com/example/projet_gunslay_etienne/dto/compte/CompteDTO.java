package com.example.projet_gunslay_etienne.dto.compte;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteDTO {

    private String numeroCompte;
    private String typeCompte;        // COURANT / EPARGNE
    private BigDecimal solde;
    private LocalDate dateOuverture;

    // Champs spécifiques
    private BigDecimal decouvert;        // seulement si COURANT
    private BigDecimal tauxRemuneration; // seulement si EPARGNE
}
