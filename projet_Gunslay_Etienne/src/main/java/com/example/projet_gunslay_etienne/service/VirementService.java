package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.dto.VirementRequestDTO;
import com.example.projet_gunslay_etienne.dto.virement.VirementResponseDTO;
import com.example.projet_gunslay_etienne.repository.CompteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class VirementService {

    private final CompteBancaireRepository compteRepository;
    private final CompteService compteService;

    @Transactional
    public VirementResponseDTO effectuerVirement(VirementRequestDTO request) {

        String debiteurNumero = request.getCompteDebiteurNumero();
        String receveurNumero = request.getCompteReceveurNumero();
        BigDecimal montant = request.getMontant();

        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Le montant du virement doit être strictement positif.");
        }

        if (debiteurNumero.equals(receveurNumero)) {
            throw new RuntimeException("Le compte débiteur et le compte receveur doivent être différents.");
        }

        // Vérifier l'existence des comptes
        CompteBancaire compteDebiteur = compteRepository.findByNumeroCompte(debiteurNumero)
                .orElseThrow(() -> new RuntimeException("Compte débiteur introuvable : " + debiteurNumero));

        CompteBancaire compteReceveur = compteRepository.findByNumeroCompte(receveurNumero)
                .orElseThrow(() -> new RuntimeException("Compte receveur introuvable : " + receveurNumero));

        // Utiliser les règles de debit / credit déjà existantes
        compteService.debit(compteDebiteur.getNumeroCompte(), montant);
        compteService.credit(compteReceveur.getNumeroCompte(), montant);

        return VirementResponseDTO.builder()
                .compteDebiteurNumero(debiteurNumero)
                .compteReceveurNumero(receveurNumero)
                .montant(montant)
                .statut("SUCCES")
                .message("Virement effectué avec succès.")
                .build();
    }
}
