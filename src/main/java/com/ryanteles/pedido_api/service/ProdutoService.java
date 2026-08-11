package com.ryanteles.pedido_api.service;

import com.ryanteles.pedido_api.entity.Produto;
import com.ryanteles.pedido_api.exception.ProdutoNotFoundException;
import com.ryanteles.pedido_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Produto buscarPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(
                ()-> new ProdutoNotFoundException("Produto não encontrado!")
        );
    }

    public void deletarPorId (Long id){
       produtoRepository.findById(id).orElseThrow(()-> new ProdutoNotFoundException("Produto não encontrado!"));
       produtoRepository.deleteById(id);
    }
}
