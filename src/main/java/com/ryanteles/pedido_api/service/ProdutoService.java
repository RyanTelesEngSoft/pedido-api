package com.ryanteles.pedido_api.service;

import com.ryanteles.pedido_api.dto.ProdutoRequestDTO;
import com.ryanteles.pedido_api.dto.ProdutoResponseDTO;
import com.ryanteles.pedido_api.entity.Produto;
import com.ryanteles.pedido_api.exception.ProdutoNotFoundException;
import com.ryanteles.pedido_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO produtoResponseDTO(Produto produto){
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(produto.getId());
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setPreco(produto.getPreco());
        produtoResponseDTO.setEstoque(produto.getEstoque());
        return produtoResponseDTO;
    }

    public Produto salvar(ProdutoRequestDTO produto){

        if(produto == null){
            throw new IllegalArgumentException("Voce não pode criar um produto nulo");
        }

        Produto produtoEntity = new Produto();

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setPreco(produto.getPreco());
        produtoEntity.setEstoque(produto.getEstoque());

        return produtoRepository.save(produtoEntity);
    }

    public List<ProdutoResponseDTO> listar(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosResponseDTO = new ArrayList<>();
        for( Produto produto : produtos){
            produtosResponseDTO.add(produtoResponseDTO(produto));
        }
        return produtosResponseDTO;
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        Produto produto = produtoOptional.orElseThrow(
                ()-> new ProdutoNotFoundException("Produto não encontrado!")
        );
        return produtoResponseDTO(produto);
    }

    public void deletarPorId (Long id){
       produtoRepository.findById(id).orElseThrow(()-> new ProdutoNotFoundException("Produto não encontrado!"));
       produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoAtualizado){
      Produto produtoExistente =  produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado!"));

            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setEstoque(produtoAtualizado.getEstoque());
            produtoRepository.save(produtoExistente);
            return produtoResponseDTO(produtoExistente);
    }
}
