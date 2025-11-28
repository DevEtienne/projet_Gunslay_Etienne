package com.example.projet_gunslay_etienne.dto.compte;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteListDTO {

    private String numeroCompte;
    private String typeCompte;   // COURANT / EPARGNE
    private BigDecimal solde;
}