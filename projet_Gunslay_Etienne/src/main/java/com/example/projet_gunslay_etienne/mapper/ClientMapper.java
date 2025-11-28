package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.dto.ClientCreateDTO;
import com.example.projet_gunslay_etienne.dto.ClientDTO;
import com.example.projet_gunslay_etienne.dto.ClientListDTO;
import com.example.projet_gunslay_etienne.dto.ClientUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // =========  ENTITY → DTO  =========

    public ClientDTO toDto(Client entity) {
        if (entity == null) return null;

        return ClientDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .adresse(entity.getAdresse())
                .codePostal(entity.getCodePostal())
                .ville(entity.getVille())
                .telephone(entity.getTelephone())
                .build();
    }

    public ClientListDTO toListDto(Client entity) {
        if (entity == null) return null;

        return ClientListDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .build();
    }


    // =========  DTO → ENTITY  =========

    public Client fromCreateDto(ClientCreateDTO dto) {
        return Client.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .adresse(dto.getAdresse())
                .codePostal(dto.getCodePostal())
                .ville(dto.getVille())
                .telephone(dto.getTelephone())
                .build();
    }

    public void updateEntity(Client entity, ClientUpdateDTO dto) {
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setAdresse(dto.getAdresse());
        entity.setCodePostal(dto.getCodePostal());
        entity.setVille(dto.getVille());
        entity.setTelephone(dto.getTelephone());
    }
}
