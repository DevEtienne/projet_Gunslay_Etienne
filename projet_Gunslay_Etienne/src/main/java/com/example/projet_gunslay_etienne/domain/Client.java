package com.example.projet_gunslay_etienne.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 50)
    private String nom;

    @NotBlank @Size(max = 50)
    private String prenom;

    @NotBlank @Size(max = 255)
    private String adresse;

    @NotBlank @Size(max = 10)
    private String codePostal;

    @NotBlank @Size(max = 100)
    private String ville;

    @NotBlank @Size(max = 20)
    private String telephone;
}
