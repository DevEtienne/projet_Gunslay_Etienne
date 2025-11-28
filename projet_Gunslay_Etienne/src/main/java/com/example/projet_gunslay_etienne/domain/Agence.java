package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agences")
@Getter
@Setter
@NoArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 5)
    @Pattern(regexp = "^[A-Za-z0-9]{5}$")
    @Column(name = "numero_agence", length = 5, nullable = false, unique = true)
    private String numeroAgence;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "agence")
    private List<Conseiller> conseillers = new ArrayList<>();
}
