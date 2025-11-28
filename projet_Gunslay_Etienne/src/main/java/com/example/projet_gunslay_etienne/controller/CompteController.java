package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.compte.CompteDTO;
import com.example.projet_gunslay_etienne.dto.compte.CompteListDTO;
import com.example.projet_gunslay_etienne.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public List<CompteListDTO> getAll() {
        return compteService.findAll();
    }

    @GetMapping("/{numero}")
    public CompteDTO getByNumero(@PathVariable String numero) {
        return compteService.getByNumero(numero);
    }

    @PostMapping("/{numero}/credit")
    public CompteDTO credit(@PathVariable String numero,
                            @RequestParam BigDecimal montant) {
        return compteService.credit(numero, montant);
    }

    @PostMapping("/{numero}/debit")
    public CompteDTO debit(@PathVariable String numero,
                           @RequestParam BigDecimal montant) {
        return compteService.debit(numero, montant);
    }
}
