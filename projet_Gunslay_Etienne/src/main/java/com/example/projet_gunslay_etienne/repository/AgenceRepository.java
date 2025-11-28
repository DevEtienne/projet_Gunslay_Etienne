package com.example.projet_gunslay_etienne.repository;

import com.example.projet_gunslay_etienne.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

    Optional<Agence> findByNumeroAgence(String numeroAgence);
}
