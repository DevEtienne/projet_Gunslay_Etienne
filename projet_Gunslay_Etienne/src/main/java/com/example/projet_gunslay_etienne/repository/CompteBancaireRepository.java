package com.example.projet_gunslay_etienne.repository;

import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {

    java.util.Optional<CompteBancaire> findByNumeroCompte(String numeroCompte);

    void deleteByNumeroCompte(String numeroCompte);

    List<CompteBancaire> findByClient_Id(Long clientId);

    void deleteAllByClient_Id(Long clientId);
}
