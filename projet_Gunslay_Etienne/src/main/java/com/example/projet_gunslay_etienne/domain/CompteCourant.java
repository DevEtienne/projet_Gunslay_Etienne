package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("COURANT")
@Getter
@Setter
@NoArgsConstructor
public class CompteCourant extends CompteBancaire {

    // autorisation de découvert
    private BigDecimal decouvert;
}
