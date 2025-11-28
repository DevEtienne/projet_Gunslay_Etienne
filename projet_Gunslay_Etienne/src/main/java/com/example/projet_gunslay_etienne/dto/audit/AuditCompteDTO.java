package com.example.projet_gunslay_etienne.dto.audit;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditCompteDTO {

    private String numeroCompte;
    private String typeCompte;
    private BigDecimal solde;

    private Long clientId;
    private String clientNom;
    private String clientPrenom;

    private boolean conforme;
    private BigDecimal seuilDebit;
}
