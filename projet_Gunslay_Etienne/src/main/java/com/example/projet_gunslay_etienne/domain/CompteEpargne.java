package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("EPARGNE")
@Getter
@Setter
@NoArgsConstructor
public class CompteEpargne extends CompteBancaire {

    // taux de rémunération en %
    private BigDecimal tauxRemuneration;
}
