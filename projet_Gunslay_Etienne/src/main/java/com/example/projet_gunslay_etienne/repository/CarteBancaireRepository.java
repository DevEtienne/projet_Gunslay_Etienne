package com.example.projet_gunslay_etienne.repository;

import com.example.projet_gunslay_etienne.domain.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {

    List<CarteBancaire> findByCompteCourant_NumeroCompte(String numeroCompte);
}
