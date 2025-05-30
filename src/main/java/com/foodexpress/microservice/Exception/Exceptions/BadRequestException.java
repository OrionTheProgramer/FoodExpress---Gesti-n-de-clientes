package com.foodexpress.microservice.Exception.Exceptions;

/**
 * Cuando el servidor no puede procesar una solicitud (400)
 *
 * @author Leandro
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
