package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.carte.CarteCreateDTO;
import com.example.projet_gunslay_etienne.dto.carte.CarteDTO;
import com.example.projet_gunslay_etienne.service.CarteBancaireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartes")
@RequiredArgsConstructor
public class CarteBancaireController {

    private final CarteBancaireService carteService;

    @GetMapping
    public List<CarteDTO> getAll() {
        return carteService.findAll();
    }

    @GetMapping("/{id}")
    public CarteDTO getById(@PathVariable Long id) {
        return carteService.findById(id);
    }

    @GetMapping("/compte/{numeroCompte}")
    public List<CarteDTO> getByCompte(@PathVariable String numeroCompte) {
        return carteService.findByNumeroCompte(numeroCompte);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarteDTO create(@Valid @RequestBody CarteCreateDTO dto) {
        return carteService.create(dto);
    }

    @PostMapping("/{id}/activer")
    public CarteDTO activer(@PathVariable Long id) {
        return carteService.activer(id);
    }

    @PostMapping("/{id}/desactiver")
    public CarteDTO desactiver(@PathVariable Long id) {
        return carteService.desactiver(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carteService.delete(id);
    }
}
