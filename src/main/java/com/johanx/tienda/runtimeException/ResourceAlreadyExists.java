package com.johanx.tienda.runtimeException;

/**
 * excepción usada cuando el usuario por cualquier razón manda a guardar
 * un objeto con id en el json y ya existe un registro con esa identificación
 */
public class ResourceAlreadyExists extends RuntimeException {
    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
