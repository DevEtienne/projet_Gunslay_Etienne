package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.dto.client.ClientCreateDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientListDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

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

    public void updateEntityFromDto(ClientUpdateDTO dto, Client entity) {
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setAdresse(dto.getAdresse());
        entity.setCodePostal(dto.getCodePostal());
        entity.setVille(dto.getVille());
        entity.setTelephone(dto.getTelephone());
    }
}
