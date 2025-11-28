package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CompteEpargne;
import com.example.projet_gunslay_etienne.dto.CompteDTO;
import com.example.projet_gunslay_etienne.dto.CompteListDTO;
import com.example.projet_gunslay_etienne.dto.compte.CompteCreateDTO;
import com.example.projet_gunslay_etienne.mapper.CompteMapper;
import com.example.projet_gunslay_etienne.repository.ClientRepository;
import com.example.projet_gunslay_etienne.repository.CompteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompteService {

    private final CompteBancaireRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;

    public List<CompteListDTO> findAll() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::toListDto)
                .toList();
    }

    public CompteDTO findByNumero(String numeroCompte) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + numeroCompte));
        return compteMapper.toDto(compte);
    }

    public CompteDTO create(CompteCreateDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + dto.getClientId()));

        CompteBancaire compte = compteMapper.fromCreateDto(dto, client);
        CompteBancaire saved = compteRepository.save(compte);
        return compteMapper.toDto(saved);
    }

    public CompteDTO credit(String numeroCompte, BigDecimal montant) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + numeroCompte));

        compte.setSolde(compte.getSolde().add(montant));
        CompteBancaire saved = compteRepository.save(compte);
        return compteMapper.toDto(saved);
    }

    public CompteDTO debit(String numeroCompte, BigDecimal montant) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + numeroCompte));

        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);

        if (compte instanceof CompteCourant cc) {
            BigDecimal limite = cc.getDecouvert().negate(); // ex : -1000
            if (nouveauSolde.compareTo(limite) < 0) {
                throw new RuntimeException("Découvert dépassé pour le compte " + numeroCompte);
            }
        } else if (compte instanceof CompteEpargne) {
            if (nouveauSolde.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Solde insuffisant sur le compte épargne " + numeroCompte);
            }
        }

        compte.setSolde(nouveauSolde);
        CompteBancaire saved = compteRepository.save(compte);
        return compteMapper.toDto(saved);
    }

    public void deleteByNumero(String numeroCompte) {
        compteRepository.deleteByNumeroCompte(numeroCompte);
    }
}
