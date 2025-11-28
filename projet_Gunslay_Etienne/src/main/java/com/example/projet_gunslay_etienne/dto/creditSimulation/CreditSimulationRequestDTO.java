package com.example.projet_gunslay_etienne.dto.creditSimulation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditSimulationRequestDTO {

    @NotNull
    @Min(1)
    private BigDecimal montant;

    @Min(1)
    private int dureeMois;

    @NotNull
    private BigDecimal tauxInteretAnnuel;

    @NotNull
    private BigDecimal tauxAssuranceAnnuel;

    private Long clientId;
}
