package com.ryanteles.pedido_api.service;

import com.ryanteles.pedido_api.entity.Produto;
import com.ryanteles.pedido_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("Voce não pode criar um produto nulo");
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
}
