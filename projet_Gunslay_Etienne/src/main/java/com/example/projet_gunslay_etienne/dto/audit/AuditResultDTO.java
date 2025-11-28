package com.example.projet_gunslay_etienne.dto.audit;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditResultDTO {

    private BigDecimal seuilDebit;                // 5000
    private int nombreTotalComptes;               // tous
    private int nombreComptesNonConformes;        // ceux qui dépassent le seuil
    private BigDecimal totalSoldeDebiteur;        // somme des soldes négatifs
    private BigDecimal totalSoldeCrediteur;       // somme des soldes positifs ou nuls

    private List<AuditCompteDTO> comptesNonConformes;
}
