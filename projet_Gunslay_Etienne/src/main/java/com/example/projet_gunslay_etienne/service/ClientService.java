package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.dto.ClientDTO;
import com.example.projet_gunslay_etienne.mapper.ClientMapper;
import com.example.projet_gunslay_etienne.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));
        return clientMapper.toDto(client);
    }

    public ClientDTO create(ClientDTO dto) {
        Client entity = clientMapper.toEntity(dto);
        entity.setId(null);
        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    public ClientDTO update(Long id, ClientDTO dto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setAdresse(dto.getAdresse());
        existing.setCodePostal(dto.getCodePostal());
        existing.setVille(dto.getVille());
        existing.setTelephone(dto.getTelephone());

        Client saved = clientRepository.save(existing);
        return clientMapper.toDto(saved);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}

