package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CompteEpargne;
import com.example.projet_gunslay_etienne.dto.audit.AuditCompteDTO;
import com.example.projet_gunslay_etienne.dto.audit.AuditResultDTO;
import com.example.projet_gunslay_etienne.repository.CompteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditService {

    private static final BigDecimal SEUIL_DEBIT = BigDecimal.valueOf(5000);

    private final CompteBancaireRepository compteRepository;

    public AuditResultDTO auditerTousLesComptes() {

        List<CompteBancaire> comptes = compteRepository.findAll();

        List<AuditCompteDTO> nonConformes = new ArrayList<>();

        BigDecimal totalDebiteur = BigDecimal.ZERO;
        BigDecimal totalCrediteur = BigDecimal.ZERO;

        for (CompteBancaire compte : comptes) {
            BigDecimal solde = compte.getSolde();

            // cumul des soldes créditeurs / débiteurs
            if (solde.compareTo(BigDecimal.ZERO) < 0) {
                totalDebiteur = totalDebiteur.add(solde);
            } else {
                totalCrediteur = totalCrediteur.add(solde);
            }

            boolean conforme = isConforme(compte);

            if (!conforme) {
                nonConformes.add(toAuditCompteDTO(compte, conforme));
            }
        }

        return AuditResultDTO.builder()
                .seuilDebit(SEUIL_DEBIT)
                .nombreTotalComptes(comptes.size())
                .nombreComptesNonConformes(nonConformes.size())
                .totalSoldeDebiteur(totalDebiteur)
                .totalSoldeCrediteur(totalCrediteur)
                .comptesNonConformes(nonConformes)
                .build();
    }

    public boolean isConforme(CompteBancaire compte) {
        // solde < -5000 => non conforme
        BigDecimal limite = SEUIL_DEBIT.negate(); // -5000
        return compte.getSolde().compareTo(limite) >= 0;
    }

    private AuditCompteDTO toAuditCompteDTO(CompteBancaire compte, boolean conforme) {

        Client client = compte.getClient();

        String typeCompte;
        if (compte instanceof CompteCourant) {
            typeCompte = "COURANT";
        } else if (compte instanceof CompteEpargne) {
            typeCompte = "EPARGNE";
        } else {
            typeCompte = "INCONNU";
        }

        return AuditCompteDTO.builder()
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(typeCompte)
                .solde(compte.getSolde())
                .clientId(client != null ? client.getId() : null)
                .clientNom(client != null ? client.getNom() : null)
                .clientPrenom(client != null ? client.getPrenom() : null)
                .conforme(conforme)
                .seuilDebit(SEUIL_DEBIT)
                .build();
    }
}
