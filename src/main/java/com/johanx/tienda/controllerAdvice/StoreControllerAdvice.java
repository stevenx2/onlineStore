package com.johanx.tienda.controllerAdvice;

import com.johanx.tienda.runtimeException.ResourceAlreadyExists;
import com.johanx.tienda.runtimeException.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * controlador de errores
 */
@RestControllerAdvice
public class StoreControllerAdvice {


    /**
     * excepción lanzada cuando el recurso no existe
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException e){
        Map<String,Object> header = getHeaderHttp(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(header);
    }


    /**
     * captura excepción cuando se viola la integridad de la base de datos,
     * como repetir valor en columnas únicas
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataBaseConflict(DataIntegrityViolationException e){
        Map<String,Object> header = getHeaderHttp(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(header);
    }


    /***
     * excepción lanzada cuando se manda a crear un objeto que ya existe en la base de datos
     */
    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<?> resourceAlreadyExists(ResourceAlreadyExists e){
        Map<String,Object> header = getHeaderHttp(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(header);
    }


    /**
     * este metodo genera el cuerpo del error de la peticion http
     * @param msg el mensaje de error
     * @return un Map que contiene el cuerpo del error
     */
    private Map<String, Object> getHeaderHttp(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", msg);
        map.put("timestamp", LocalDate.now());
        return map;
    }

}
