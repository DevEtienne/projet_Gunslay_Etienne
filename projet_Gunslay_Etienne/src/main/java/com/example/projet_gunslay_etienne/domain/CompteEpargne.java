package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EPARGNE")
@Getter
@Setter
@NoArgsConstructor
public class CompteEpargne extends CompteBancaire {

    @Column(name = "taux_remuneration")
    private BigDecimal tauxRemuneration;

    public CompteEpargne(Long id, String numeroCompte, BigDecimal solde, LocalDate dateOuverture, BigDecimal tauxRemuneration) {
        super(id, numeroCompte, solde, dateOuverture);
        this.tauxRemuneration = tauxRemuneration;
    }
}
