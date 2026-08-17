package com.ryanteles.pedido_api.exception;

public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException (String mensagem){
        super(mensagem);
    }
}
