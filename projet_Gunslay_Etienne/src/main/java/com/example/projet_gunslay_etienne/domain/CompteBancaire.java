package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "comptes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte", nullable = false, unique = true)
    private String numeroCompte;

    @Column(name = "solde", nullable = false)
    private BigDecimal solde;

    @Column(name = "date_ouverture", nullable = false)
    private LocalDate dateOuverture;
}

