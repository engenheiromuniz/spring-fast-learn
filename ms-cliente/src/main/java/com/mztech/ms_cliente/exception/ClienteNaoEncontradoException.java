package com.mztech.ms_cliente.exception;


public class ClienteNaoEncontradoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    // 
    public ClienteNaoEncontradoException(Long id) {
        super("Cliente com ID " + id + " não foi encontrado.");
    }
}
