package com.example.projet_gunslay_etienne.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientUpdateDTO {

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

