package com.example.projet_gunslay_etienne.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirementRequestDTO {

    @NotBlank
    private String compteDebiteurNumero;

    @NotBlank
    private String compteReceveurNumero;

    @NotNull
    private BigDecimal montant;
}
