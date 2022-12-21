package com.example.reto.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String nombre, String apellido, Integer edad,
    String fecha_nac, String fecha_mu) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("Nombre", nombre);
            map.put("Apellido", apellido);
            map.put("Edad", edad);
            map.put("fecha_nac", fecha_nac);
            map.put("fecha_muerte", fecha_mu);

            return new ResponseEntity<Object>(map,status);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("isSuccess",false);
            map.put("message", e.getMessage());
            map.put("data", null);
            return new ResponseEntity<Object>(map,status);
        }
    }
}
