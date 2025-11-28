package com.example.projet_gunslay_etienne.dto.virement;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirementResponseDTO {

    private String compteDebiteurNumero;
    private String compteReceveurNumero;
    private BigDecimal montant;
    private String statut; // "SUCCES"
    private String message;
}
