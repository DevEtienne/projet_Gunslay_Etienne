package com.example.projet_gunslay_etienne.mapper;

import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.domain.CompteEpargne;
import com.example.projet_gunslay_etienne.dto.compte.CompteDTO;
import com.example.projet_gunslay_etienne.dto.compte.CompteListDTO;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public CompteListDTO toListDto(CompteBancaire compte) {
        if (compte == null) return null;

        String type = getTypeCompte(compte);

        return CompteListDTO.builder()
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(type)
                .solde(compte.getSolde())
                .build();
    }

    public CompteDTO toDto(CompteBancaire compte) {
        if (compte == null) return null;

        String type = getTypeCompte(compte);

        CompteDTO.CompteDTOBuilder builder = CompteDTO.builder()
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(type)
                .solde(compte.getSolde())
                .dateOuverture(compte.getDateOuverture());

        if (compte instanceof CompteCourant cc) {
            builder.decouvert(cc.getDecouvert());
        } else if (compte instanceof CompteEpargne ce) {
            builder.tauxRemuneration(ce.getTauxRemuneration());
        }

        return builder.build();
    }

    private String getTypeCompte(CompteBancaire compte) {
        if (compte instanceof CompteCourant) return "COURANT";
        if (compte instanceof CompteEpargne) return "EPARGNE";
        return "INVALID";
    }
}
