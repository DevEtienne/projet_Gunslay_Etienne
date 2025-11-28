package com.example.projet_gunslay_etienne.repository;

import com.example.projet_gunslay_etienne.domain.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
}
