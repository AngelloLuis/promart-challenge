package com.example.reto.controller;

import com.example.reto.dto.ClienteDTO;
import com.example.reto.model.Cliente;
import com.example.reto.service.api.ClienteServiceAPI;
import com.example.reto.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente/api/v1/")
@CrossOrigin("*")
public class ClienteRestController {
    @Autowired
    private ClienteServiceAPI clienteServiceAPI;

/*    @GetMapping(value = "/allfech")
    public ResponseEntity<Object>  getAll() throws Exception {

        List<ClienteDTO> clientes = clienteServiceAPI.getAll();
        for(ClienteDTO cliente: clientes){
            ResponseHandler.generateResponse(HttpStatus.OK, cliente.getNombre(),cliente.getApellido(),cliente.getEdad(), cliente.getFecha_nac(), "12-03-2080");
        }

        //return clienteServiceAPI.getAll();

        return clienteServiceAPI.getAll();
    }*/

    @GetMapping(value = "/all")
    public List<ClienteDTO> getAll() throws Exception {
        return clienteServiceAPI.getAll();
    }

    //@GetMapping(value = "/kpideclientes")
    @RequestMapping(value = "/kpideclientes", method = RequestMethod.GET, produces = { "application/json" })
    public String getKpiClientes() throws Exception {
        Integer suma = 0;
        List<ClienteDTO> cliente = clienteServiceAPI.getAll();
        //cliente.forEach();
        for ( ClienteDTO client: cliente ){
            suma += client.getEdad();
        }

        int n = cliente.size();
        Double desv = 0.0;
        double sum = 0.0;
        double standardDeviation = 0.0;
        double mean = 0.0;
        double res = 0.0;
        double sq = 0.0;

        for (int i = 0; i < cliente.size(); i++) {
            sum = sum + cliente.get(i).getEdad();
        }

        for (int i = 0; i < cliente.size(); i++) {
            standardDeviation
                    = standardDeviation + Math.pow((cliente.get(i).getEdad() - mean), 2);

        }

        sq = standardDeviation / n;
        res = Math.sqrt(sq);

        System.out.println(suma);
        return "{\"promedio_edad\": " +suma + "," +
                "\"desv_standar\": \"" +res+
                "\"}";
    }

    @GetMapping(value = "/find/{id}")
    public ClienteDTO find(@PathVariable String id) throws Exception {
        return clienteServiceAPI.get(id);
    }

    @PostMapping(value = "/save/{id}")
    public ResponseEntity<String> save(@RequestBody Cliente cliente, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            id = clienteServiceAPI.save(cliente);
        } else {
            clienteServiceAPI.save(cliente, id);
        }
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable String id) throws Exception {
        ClienteDTO persona = clienteServiceAPI.get(id);
        if (persona != null) {
            clienteServiceAPI.delete(id);
        } else {
            return new ResponseEntity<ClienteDTO>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<ClienteDTO>(persona, HttpStatus.OK);
    }
}
