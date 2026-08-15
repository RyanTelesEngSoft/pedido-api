package com.ryanteles.pedido_api.service;

import com.ryanteles.pedido_api.dto.ClienteRequestDTO;
import com.ryanteles.pedido_api.dto.ClienteResponseDTO;
import com.ryanteles.pedido_api.entity.Cliente;
import com.ryanteles.pedido_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public ClienteResponseDTO clienteResponseDTO(Cliente cliente){
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setNome(cliente.getNome());
        clienteResponseDTO.setEmail(cliente.getEmail());
        clienteResponseDTO.setTelefone(cliente.getTelefone());
        return clienteResponseDTO;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO){
        if(clienteRequestDTO == null){
            throw new IllegalArgumentException(" O Cliente não pode está vazio!");
        }

        Cliente clienteEntity = new Cliente();
       clienteEntity.setNome(clienteRequestDTO.getNome());
       clienteEntity.setEmail(clienteRequestDTO.getEmail());
       clienteEntity.setTelefone(clienteRequestDTO.getTelefone());
       clienteRepository.save(clienteEntity);
       return clienteResponseDTO(clienteEntity);

    }

    public List<ClienteResponseDTO> listar(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> clientesResponseDTO = new ArrayList<>();
        for(Cliente cliente : clientes){
            clientesResponseDTO.add(clienteResponseDTO(cliente));
        }
        return clientesResponseDTO;
    }

    
}
