package com.foodexpress.microservice.Exception.Exceptions;

/**
 * Captura errores relacionados a la falta de recursos
 *
 * @author  Leandro
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
