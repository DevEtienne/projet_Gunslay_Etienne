package com.example.projet_gunslay_etienne.dto.conseiller;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConseillerDTO {

    private Long id;
    private String nom;
    private String prenom;

    private Long agenceId;
    private String numeroAgence;
}
