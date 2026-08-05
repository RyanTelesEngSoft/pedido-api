package com.ryanteles.pedido_api.repository;

import com.ryanteles.pedido_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
