package com.ryanteles.pedido_api.repository;

import com.ryanteles.pedido_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
