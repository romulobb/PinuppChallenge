package com.service;


import com.error.ClientDateInvalid;
import com.model.Client;
import com.model.ClientDTO;
import com.model.ClientList;
import com.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import com.util.validators;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private List<Client> listacli;

    @Autowired
    public void setUserRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client creacliente(ClientDTO cliente) {

        try {
            Calendar cal = validators.validateDate(cliente.getFechanacimiento());
            Client clienteSave= new Client(cliente.getNombre(),cliente.getApellido(),cliente.getEdad(),cal);
            return clientRepository.save(clienteSave);
        } catch (ParseException e) {
            throw new ClientDateInvalid();
        }
    }

    @Override
    public double promedioedadclientes() {
        listacli = clientRepository.findAll();

        if (listacli.isEmpty()){
            return 0;

        }
        double resp=0;
        for(Client cli:listacli){
            resp=resp+cli.getEdad();
        }
        resp=resp/listacli.size();
        return resp;
    }

    public double calcularDesviacionEstandar(double media) {
        if ((media==0) ||(listacli.isEmpty())){
            return 0;
        }

        // Calcular la suma de los cuadrados de las diferencias con la media
        double sumaCuadrados = 0;
        for (Client  cli : listacli) {
            sumaCuadrados += Math.pow(cli.getEdad() - media, 2);
        }

        // Calcular la desviación estándar (para una población completa)
        return Math.sqrt(sumaCuadrados / listacli.size());
    }

    @Override
    public List<ClientList> listClientes() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double mediaedad=promedioedadclientes();
        int esperanzaVida=new Double(mediaedad + (80 - mediaedad)).intValue();

        List<ClientList> rta = new ArrayList<ClientList>();
        for (Client  cli : listacli) {
            ClientList clist=new ClientList();
            clist.setNombre(cli.getNombre());
            clist.setApellido(cli.getApellido());
            clist.setEdad(cli.getEdad());
            clist.setFechanacimiento(sdf.format(cli.getFechanacimiento().getTime()));
            Calendar fechafall=cli.getFechanacimiento();
            fechafall.add(Calendar.YEAR,esperanzaVida);
            clist.setFechafall(sdf.format(fechafall.getTime()));
            rta.add(clist);
        }
        return rta;

    }
}