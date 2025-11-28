package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.CarteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.dto.carte.CarteCreateDTO;
import com.example.projet_gunslay_etienne.dto.carte.CarteDTO;
import org.springframework.stereotype.Component;

@Component
public class CarteMapper {

    public CarteDTO toDto(CarteBancaire carte) {
        if (carte == null) return null;

        return CarteDTO.builder()
                .id(carte.getId())
                .typeCarte(carte.getTypeCarte())
                .active(carte.isActive())
                .numeroCompte(carte.getCompteCourant().getNumeroCompte())
                .build();
    }

    public CarteBancaire fromCreateDto(CarteCreateDTO dto, CompteCourant compteCourant) {
        CarteBancaire carte = new CarteBancaire();
        carte.setTypeCarte(dto.getTypeCarte());
        carte.setActive(dto.getActive() != null ? dto.getActive() : true);
        carte.setCompteCourant(compteCourant);
        return carte;
    }
}
