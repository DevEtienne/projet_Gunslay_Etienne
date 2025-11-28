package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.VirementRequestDTO;
import com.example.projet_gunslay_etienne.dto.virement.VirementResponseDTO;
import com.example.projet_gunslay_etienne.service.VirementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virements")
@RequiredArgsConstructor
public class VirementController {

    private final VirementService virementService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public VirementResponseDTO effectuerVirement(@Valid @RequestBody VirementRequestDTO request) {
        return virementService.effectuerVirement(request);
    }
}
