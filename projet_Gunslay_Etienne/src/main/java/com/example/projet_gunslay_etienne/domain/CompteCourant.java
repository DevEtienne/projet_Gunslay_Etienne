package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("COURANT")
@Getter
@Setter
@NoArgsConstructor
public class CompteCourant extends CompteBancaire {

    @Column(name = "decouvert")
    private BigDecimal decouvert;

    @OneToMany(mappedBy = "compteCourant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarteBancaire> cartes = new ArrayList<>();
}
