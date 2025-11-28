package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.audit.AuditResultDTO;
import com.example.projet_gunslay_etienne.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/comptes")
    public AuditResultDTO auditerTousLesComptes() {
        return auditService.auditerTousLesComptes();
    }
}
