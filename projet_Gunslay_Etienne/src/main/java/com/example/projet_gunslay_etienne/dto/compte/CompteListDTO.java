package com.example.projet_gunslay_etienne.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteListDTO {

    private String numeroCompte;
    private String typeCompte;
    private BigDecimal solde;
    private Long clientId;
}
