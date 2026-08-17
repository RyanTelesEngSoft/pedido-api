package com.ryanteles.pedido_api.service;

import com.ryanteles.pedido_api.dto.ClienteRequestDTO;
import com.ryanteles.pedido_api.dto.ClienteResponseDTO;
import com.ryanteles.pedido_api.entity.Cliente;
import com.ryanteles.pedido_api.exception.ClienteNotFoundException;
import com.ryanteles.pedido_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ClienteResponseDTO buscarPorId (Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente = clienteOptional.orElseThrow(
                ()-> new ClienteNotFoundException("Cliente não encontrado")
        );
        return clienteResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteAtualizado){
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(()-> new ClienteNotFoundException("Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        clienteRepository.save(clienteExistente);
        return clienteResponseDTO(clienteExistente);
    }

    public void deletar(Long id){
        clienteRepository.findById(id).orElseThrow(()-> new ClienteNotFoundException("Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }
}
