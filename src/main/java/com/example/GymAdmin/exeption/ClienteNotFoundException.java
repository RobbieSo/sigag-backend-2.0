package com.example.GymAdmin.exeption;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensaje) {
        super(mensaje);
    }
}