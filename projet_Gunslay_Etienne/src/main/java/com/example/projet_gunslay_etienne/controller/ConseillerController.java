package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerCreateDTO;
import com.example.projet_gunslay_etienne.dto.conseiller.ConseillerDTO;
import com.example.projet_gunslay_etienne.service.ConseillerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conseillers")
@RequiredArgsConstructor
public class ConseillerController {

    private final ConseillerService conseillerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConseillerDTO create(@Valid @RequestBody ConseillerCreateDTO dto) {
        return conseillerService.create(dto);
    }

    @GetMapping
    public List<ConseillerDTO> getAll() {
        return conseillerService.findAll();
    }

    // Détail d'un conseiller
    @GetMapping("/{id}")
    public ConseillerDTO getById(@PathVariable Long id) {
        return conseillerService.findById(id);
    }

    @PostMapping("/{conseillerId}/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affecterClient(
            @PathVariable Long conseillerId,
            @PathVariable Long clientId
    ) {
        conseillerService.affecterClient(conseillerId, clientId);
    }
}
