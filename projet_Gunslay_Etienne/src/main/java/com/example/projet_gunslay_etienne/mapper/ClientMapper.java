package com.example.projet_gunslay_etienne.mapper;


import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.dto.ClientDTO;
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

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        return Client.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .adresse(dto.getAdresse())
                .codePostal(dto.getCodePostal())
                .ville(dto.getVille())
                .telephone(dto.getTelephone())
                .build();
    }
}
