package com.ryanteles.pedido_api.controller;

import com.ryanteles.pedido_api.dto.ClienteRequestDTO;
import com.ryanteles.pedido_api.dto.ClienteResponseDTO;
import com.ryanteles.pedido_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar (@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteCriado = clienteService.salvar(clienteRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCriado.getId()).toUri();
        return ResponseEntity.created(location).body(clienteCriado);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar(){
       return clienteService.listar();
    }
}
