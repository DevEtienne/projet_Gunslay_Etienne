package com.example.projet_gunslay_etienne.dto.creditSimulation;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditSimulationResultDTO {

    private BigDecimal montant;
    private int dureeMois;
    private BigDecimal tauxInteretAnnuel;
    private BigDecimal tauxAssuranceAnnuel;
    private Long clientId;

    private BigDecimal mensualiteHorsAssurance;
    private BigDecimal mensualiteAssurance;
    private BigDecimal mensualiteTotale;

    private BigDecimal coutTotalInterets;
    private BigDecimal coutTotalAssurance;
    private BigDecimal coutTotal;
}
