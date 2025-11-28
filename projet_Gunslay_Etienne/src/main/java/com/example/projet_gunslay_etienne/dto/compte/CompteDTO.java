package com.example.projet_gunslay_etienne.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteDTO {

    private String numeroCompte;
    private String typeCompte;
    private BigDecimal solde;
    private LocalDate dateOuverture;
    private Long clientId;

    private BigDecimal decouvert;
    private BigDecimal tauxRemuneration;
}
