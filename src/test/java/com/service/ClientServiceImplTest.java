package com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Client;
import com.model.ClientDTO;
import com.model.ClientList;
import com.repositories.ClientRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientServiceImplTest extends TestCase {

    private static final ObjectMapper om = new ObjectMapper();

    @Mock
    private TestRestTemplate restTemplate;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientServiceImpl clientService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        clientService = new ClientServiceImpl();
        clientService.setUserRepository(clientRepository);

    }

    @Test
    public void creacliente_OK() {
        Client expectedClient =new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2));
        when(clientRepository.save(expectedClient)).thenReturn(expectedClient);

        ClientDTO cliDto= new ClientDTO("Juan","Perez",24,"2000-12-2");
        Client actualClient = clientService.creacliente(cliDto);
        assertEquals(actualClient, expectedClient);
    }
    @Test
    public void creacliente_fecha_nacimiento_Not_OK() {
        Client expectedClient =new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2));
        when(clientRepository.save(expectedClient)).thenReturn(expectedClient);

        ClientDTO cliDto= new ClientDTO("Juan","Perez",24,"2-12-2000");
        Client actualClient = clientService.creacliente(cliDto);
        assertNotSame(actualClient, expectedClient);
    }
    @Test
    public void creacliente_fecha_nacimiento_distinta() {
        Client expectedClient =new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2));
        when(clientRepository.save(expectedClient)).thenReturn(expectedClient);

        ClientDTO cliDto= new ClientDTO("Juan","Perez",24,"2022-12-12");
        Client actualClient = clientService.creacliente(cliDto);
        assertNotSame(actualClient, expectedClient);
    }
    @Test
    public void promedioedadclientes_OK() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        listaClientes.add(new Client("Pepe","Florez", 33,new GregorianCalendar(1990, Calendar.NOVEMBER, 10)));
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double actual = clientService.promedioedadclientes();
        double expected = 28.5;
        assertEquals(actual,expected);
    }

    @Test
    public void promedioedadclientes_cliente_unico() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double actual = clientService.promedioedadclientes();
        double expected = 24;
        assertEquals(actual,expected);
    }

    @Test
    public void promedioedadclientes_Lista_Vacia() {
        List<Client> listaClientes = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double actual = clientService.promedioedadclientes();
        double expected = 0;
        assertEquals(actual,expected);
    }

    @Test
    public void calcularDesviacionEstandar_OK() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        listaClientes.add(new Client("Pepe","Florez", 33,new GregorianCalendar(1990, Calendar.NOVEMBER, 10)));
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double media = clientService.promedioedadclientes();
        double desv =clientService.calcularDesviacionEstandar(media);
        assertEquals(desv,4.5);

    }

    @Test
    public void calcularDesviacionEstandar_Cliente_Unico() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double media = clientService.promedioedadclientes();
        double desv =clientService.calcularDesviacionEstandar(media);
        assertEquals(desv,0.0);
    }

    @Test
    public void calcularDesviacionEstandar_lista_vacia() {
        List<Client> listaClientes = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(listaClientes);
        double media = clientService.promedioedadclientes();
        double desv =clientService.calcularDesviacionEstandar(media);
        assertEquals(desv,0.0);
    }

    @Test
    public void listClientes_OK() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        listaClientes.add(new Client("Pepe","Florez", 33,new GregorianCalendar(1990, Calendar.NOVEMBER, 10)));
        when(clientRepository.findAll()).thenReturn(listaClientes);

        List<ClientList> expected = new ArrayList<>();
        expected.add(new ClientList("Juan","Perez",24,"2000-12-02","2080-12-02"));
        expected.add(new ClientList("Pepe","Florez",33,"1990-11-10","2070-11-10"));

        List<ClientList> actual=clientService.listClientes();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void listClientes_not_OK() {
        List<Client> listaClientes = new ArrayList<>();
        listaClientes.add(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
        listaClientes.add(new Client("Pepe","Florez", 33,new GregorianCalendar(1990, Calendar.NOVEMBER, 10)));
        when(clientRepository.findAll()).thenReturn(listaClientes);

        List<ClientList> expected = new ArrayList<>();
        expected.add(new ClientList("Juan","Perez",24,"2000-12-02","2080-12-02"));

        List<ClientList> actual=clientService.listClientes();

        assertFalse(expected.equals(actual));
    }

}