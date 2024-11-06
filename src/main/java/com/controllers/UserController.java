package com.controllers;

import com.model.Client;
import com.model.ClientDTO;

import com.model.ClientList;
import com.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private ClientService service;

    @RequestMapping(value = "/creacliente", method = RequestMethod.POST)
    public ResponseEntity<String> newUser(@RequestBody ClientDTO cliente) {
        try {
            service.creacliente(cliente);
            return new ResponseEntity<String>("Cliente creado satisfactoriamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/kpidelientes", method= RequestMethod.GET)
    @GetMapping("/kpidelientes")
    public ResponseEntity<String> kpidelientes() {

        double promedio = service.promedioedadclientes();
        double desviacion = service.calcularDesviacionEstandar(promedio);
        return new ResponseEntity<String>("El promedio de edad de los clientes es de "+promedio + "Y la desviacion estandar emtre las edades de los clientes es de "+ desviacion, HttpStatus.OK);
    }

    @RequestMapping(value = "/listclientes", method= RequestMethod.GET)
    @GetMapping("/listclientes")
    public ResponseEntity<List<ClientList>> findAll() {
        return new ResponseEntity<List<ClientList>>(service.listClientes(),HttpStatus.OK);
    }

}
