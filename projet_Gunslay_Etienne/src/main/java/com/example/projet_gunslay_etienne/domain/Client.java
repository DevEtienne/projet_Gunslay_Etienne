package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "code_postal", nullable = false, length = 10)
    private String codePostal;

    @Column(name = "ville", nullable = false, length = 100)
    private String ville;

    @Column(name = "telephone", nullable = false, length = 20)
    private String telephone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conseiller_id")
    private Conseiller conseiller;
}
