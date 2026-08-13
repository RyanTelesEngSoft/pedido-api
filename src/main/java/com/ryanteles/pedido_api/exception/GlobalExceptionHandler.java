package com.ryanteles.pedido_api.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<String> tratarProdutoNotFoundException(ProdutoNotFoundException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>>tratarValidacao(MethodArgumentNotValidException exception){
        var erros = exception.getBindingResult().getFieldErrors();
        Map<String, String> campos = new HashMap<>();

        for (var erro : erros) {
            campos.put(erro.getField(), erro.getDefaultMessage());
       }
        return ResponseEntity.status(400).body(campos);
   }
}
