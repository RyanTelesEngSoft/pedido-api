package com.ryanteles.pedido_api.controller;

import com.ryanteles.pedido_api.dto.ProdutoRequestDTO;
import com.ryanteles.pedido_api.dto.ProdutoResponseDTO;
import com.ryanteles.pedido_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO produto){
        ProdutoResponseDTO produtoCriado = produtoService.salvar(produto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/").buildAndExpand(produtoCriado.getId()).toUri();
        return ResponseEntity.created(location).body(produtoCriado);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar(){
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public  ProdutoResponseDTO atualizar( @PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produto){
       return produtoService.atualizar(id, produto);
    }
}
