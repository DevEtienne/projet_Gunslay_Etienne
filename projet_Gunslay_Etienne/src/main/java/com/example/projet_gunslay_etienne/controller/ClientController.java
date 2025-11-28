package com.example.projet_gunslay_etienne.controller;

import com.example.projet_gunslay_etienne.dto.ClientCreateDTO;
import com.example.projet_gunslay_etienne.dto.ClientDTO;
import com.example.projet_gunslay_etienne.dto.ClientListDTO;
import com.example.projet_gunslay_etienne.dto.ClientUpdateDTO;
import com.example.projet_gunslay_etienne.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientListDTO> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@Valid @RequestBody ClientCreateDTO dto) {
        return clientService.create(dto);
    }

    @PutMapping("/{id}")
    public ClientDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ClientUpdateDTO dto) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
