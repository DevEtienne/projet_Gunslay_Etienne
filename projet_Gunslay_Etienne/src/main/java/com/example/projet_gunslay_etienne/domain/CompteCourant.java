package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("COURANT")
@Getter
@Setter
@NoArgsConstructor
public class CompteCourant extends CompteBancaire {

    @Column(name = "decouvert")
    private BigDecimal decouvert;

    public CompteCourant(Long id, String numeroCompte, BigDecimal solde, LocalDate dateOuverture, BigDecimal decouvert) {
        super(id, numeroCompte, solde, dateOuverture);
        this.decouvert = decouvert;
    }
}
