package com.ryanteles.pedido_api.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String mensagem){
        super(mensagem);
    }

}
