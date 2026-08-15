package com.ryanteles.pedido_api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message = "O nome não pode está vazio!")
    private String nome;

    @NotBlank(message = "Email obrigatório!")
    @Email(message = "Email invalido!")
    private String email;

    @NotBlank(message = "O telefone não pode está vazio!")
    private String telefone;

}
