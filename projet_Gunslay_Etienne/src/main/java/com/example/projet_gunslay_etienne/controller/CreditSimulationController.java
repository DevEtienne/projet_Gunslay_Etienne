package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.creditSimulation.CreditSimulationRequestDTO;
import com.example.projet_gunslay_etienne.dto.creditSimulation.CreditSimulationResultDTO;
import com.example.projet_gunslay_etienne.service.CreditSimulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulations")
@RequiredArgsConstructor
public class CreditSimulationController {

    private final CreditSimulationService creditSimulationService;

    @PostMapping("/credit")
    @ResponseStatus(HttpStatus.OK)
    public CreditSimulationResultDTO simulerCredit(@Valid @RequestBody CreditSimulationRequestDTO request) {
        return creditSimulationService.simuler(request);
    }
}
