package com.example.projet_gunslay_etienne.repository;

import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {

    Optional<CompteBancaire> findByNumeroCompte(String numeroCompte);

    void deleteByNumeroCompte(String numeroCompte);
}
