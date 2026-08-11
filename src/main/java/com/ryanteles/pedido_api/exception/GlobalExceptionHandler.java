package com.ryanteles.pedido_api.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<String> tratarProdutoNotFoundException(ProdutoNotFoundException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }
}
