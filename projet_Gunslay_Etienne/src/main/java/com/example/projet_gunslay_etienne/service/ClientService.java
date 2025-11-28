package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CarteBancaire;
import com.example.projet_gunslay_etienne.dto.client.ClientCreateDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientListDTO;
import com.example.projet_gunslay_etienne.dto.client.ClientUpdateDTO;
import com.example.projet_gunslay_etienne.mapper.ClientMapper;
import com.example.projet_gunslay_etienne.repository.ClientRepository;
import com.example.projet_gunslay_etienne.repository.CompteBancaireRepository;
import com.example.projet_gunslay_etienne.repository.CarteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final CompteBancaireRepository compteRepository;
    private final CarteBancaireRepository carteRepository;
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

        clientMapper.updateEntityFromDto(dto, entity);

        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    /**
     * Règle métier :
     * - suppression d'un client =>
     *   -> suppression de tous les comptes associés
     *   -> désactivation des cartes bancaires associées aux comptes courants
     */
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));

        List<CompteBancaire> comptes = compteRepository.findByClient_Id(id);

        for (CompteBancaire compte : comptes) {
            if (compte instanceof CompteCourant cc) {

                List<CarteBancaire> cartes = cc.getCartes();
                for (CarteBancaire carte : cartes) {
                    carte.setActive(false);
                    carte.setCompteCourant(null);
                    carteRepository.save(carte);
                }
            }
        }

        compteRepository.deleteAll(comptes);

        clientRepository.delete(client);
    }
}
