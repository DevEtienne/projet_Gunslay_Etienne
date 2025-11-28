package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.Agence;
import com.example.projet_gunslay_etienne.domain.Conseiller;
import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerCreateDTO;
import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerDTO;
import org.springframework.stereotype.Component;

@Component
public class ConseillerMapper {

    public ConseillerDTO toDto(Conseiller entity) {
        if (entity == null) return null;

        Agence agence = entity.getAgence();

        return ConseillerDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .agenceId(agence != null ? agence.getId() : null)
                .numeroAgence(agence != null ? agence.getNumeroAgence() : null)
                .build();
    }

    public Conseiller fromCreateDto(ConseillerCreateDTO dto, Agence agence) {
        Conseiller conseiller = new Conseiller();
        conseiller.setNom(dto.getNom());
        conseiller.setPrenom(dto.getPrenom());
        conseiller.setAgence(agence);
        return conseiller;
    }
}
