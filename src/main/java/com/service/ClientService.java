package com.service;

import com.model.Client;
import com.model.ClientDTO;
import com.model.ClientList;

import java.text.ParseException;
import java.util.List;


public interface ClientService {


    List<ClientList> listClientes();

    Client creacliente(ClientDTO cliente) throws ParseException;

    double promedioedadclientes();

    double calcularDesviacionEstandar(double media);
}
