package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.dto.client.ClientCreateDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientListDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientUpdateDTO;
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

    public List<ClientListDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toListDto)
                .toList();
    }

    public ClientDTO findById(Long id) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));
        return clientMapper.toDto(entity);
    }

    public ClientDTO create(ClientCreateDTO dto) {
        Client entity = clientMapper.fromCreateDto(dto);
        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    public ClientDTO update(Long id, ClientUpdateDTO dto) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));

        clientMapper.updateEntity(entity, dto);

        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
