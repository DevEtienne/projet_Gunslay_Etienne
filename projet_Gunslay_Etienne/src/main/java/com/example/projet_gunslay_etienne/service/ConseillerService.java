package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Agence;
import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.domain.Conseiller;
import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerCreateDTO;
import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerDTO;
import com.example.projet_gunslay_etienne.mapper.ConseillerMapper;
import com.example.projet_gunslay_etienne.repository.AgenceRepository;
import com.example.projet_gunslay_etienne.repository.ClientRepository;
import com.example.projet_gunslay_etienne.repository.ConseillerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConseillerService {

    private static final int MAX_CLIENTS_PAR_CONSEILLER = 10;

    private final ConseillerRepository conseillerRepository;
    private final AgenceRepository agenceRepository;
    private final ClientRepository clientRepository;
    private final ConseillerMapper conseillerMapper;

    public ConseillerDTO create(ConseillerCreateDTO dto) {
        Agence agence = agenceRepository.findById(dto.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence non trouvée : " + dto.getAgenceId()));

        Conseiller entity = conseillerMapper.fromCreateDto(dto, agence);
        Conseiller saved = conseillerRepository.save(entity);

        return conseillerMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<ConseillerDTO> findAll() {
        return conseillerRepository.findAll()
                .stream()
                .map(conseillerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ConseillerDTO findById(Long id) {
        Conseiller entity = conseillerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseiller non trouvé : " + id));

        return conseillerMapper.toDto(entity);
    }

    /**
     * Affecte un client à un conseiller en respectant la règle :
     * "Chaque conseiller a la responsabilité au plus de 10 clients."
     */
    public void affecterClient(Long conseillerId, Long clientId) {

        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new RuntimeException("Conseiller non trouvé : " + conseillerId));

        long nbClients = clientRepository.countByConseiller_Id(conseillerId);

        if (nbClients >= MAX_CLIENTS_PAR_CONSEILLER) {
            throw new RuntimeException("Ce conseiller gère déjà le maximum de "
                    + MAX_CLIENTS_PAR_CONSEILLER + " clients.");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + clientId));

        client.setConseiller(conseiller);
        clientRepository.save(client);
    }
}
