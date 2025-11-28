package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CompteEpargne;
import com.example.projet_gunslay_etienne.dto.compte.CompteDTO;
import com.example.projet_gunslay_etienne.dto.compte.CompteListDTO;
import com.example.projet_gunslay_etienne.mapper.CompteMapper;
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
    private final CompteMapper compteMapper;

    public List<CompteListDTO> findAll() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::toListDto)
                .toList();
    }

    public CompteDTO getByNumero(String numeroCompte) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + numeroCompte));

        return compteMapper.toDto(compte);
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
}
