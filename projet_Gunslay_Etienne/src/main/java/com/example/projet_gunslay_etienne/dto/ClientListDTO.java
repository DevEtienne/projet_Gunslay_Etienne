package com.example.projet_gunslay_etienne.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientListDTO {

    private Long id;
    private String nom;
    private String prenom;
}

