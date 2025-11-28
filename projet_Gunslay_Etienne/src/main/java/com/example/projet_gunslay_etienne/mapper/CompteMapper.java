package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.Client;
import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CompteEpargne;
import com.example.projet_gunslay_etienne.dto.CompteDTO;
import com.example.projet_gunslay_etienne.dto.CompteListDTO;
import com.example.projet_gunslay_etienne.dto.compte.CompteCreateDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CompteMapper {

    public CompteListDTO toListDto(CompteBancaire compte) {
        if (compte == null) return null;

        return CompteListDTO.builder()
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(getTypeCompte(compte))
                .solde(compte.getSolde())
                .clientId(compte.getClient().getId())
                .build();
    }

    public CompteDTO toDto(CompteBancaire compte) {
        if (compte == null) return null;

        CompteDTO.CompteDTOBuilder builder = CompteDTO.builder()
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(getTypeCompte(compte))
                .solde(compte.getSolde())
                .dateOuverture(compte.getDateOuverture())
                .clientId(compte.getClient().getId());

        if (compte instanceof CompteCourant cc) {
            builder.decouvert(cc.getDecouvert());
        } else if (compte instanceof CompteEpargne ce) {
            builder.tauxRemuneration(ce.getTauxRemuneration());
        }

        return builder.build();
    }

    public CompteBancaire fromCreateDto(CompteCreateDTO dto, Client client) {
        String type = dto.getTypeCompte().toUpperCase();

        if ("COURANT".equals(type)) {
            CompteCourant cc = new CompteCourant();
            cc.setNumeroCompte(dto.getNumeroCompte());
            cc.setSolde(dto.getSoldeInitial());
            cc.setDateOuverture(LocalDate.now());
            cc.setClient(client);
            cc.setDecouvert(dto.getDecouvert() != null ? dto.getDecouvert() : BigDecimal.valueOf(1000));
            return cc;
        } else if ("EPARGNE".equals(type)) {
            CompteEpargne ce = new CompteEpargne();
            ce.setNumeroCompte(dto.getNumeroCompte());
            ce.setSolde(dto.getSoldeInitial());
            ce.setDateOuverture(LocalDate.now());
            ce.setClient(client);
            ce.setTauxRemuneration(dto.getTauxRemuneration() != null ? dto.getTauxRemuneration() : BigDecimal.valueOf(3.0));
            return ce;
        }

        throw new RuntimeException("Type de compte inconnu : " + dto.getTypeCompte());
    }

    private String getTypeCompte(CompteBancaire compte) {
        if (compte instanceof CompteCourant) return "COURANT";
        if (compte instanceof CompteEpargne) return "EPARGNE";
        return "INCONNU";
    }
}
