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

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id){
       return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO cliente){
        return clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eletar (@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
