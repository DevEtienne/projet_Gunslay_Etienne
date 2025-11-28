package com.example.projet_gunslay_etienne.domain;

import com.example.projet_gunslay_etienne.domain.enums.TypeCarte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cartes_bancaires")
@Getter
@Setter
@NoArgsConstructor
public class CarteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_carte", nullable = false)
    private TypeCarte typeCarte;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_courant_id") // pas nullable = false
    private CompteCourant compteCourant;
}

