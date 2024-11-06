package com;

import com.model.Client;
import com.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class StartClientApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartClientApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(ClientRepository repository) {
       return args -> {

           repository.save(new Client("Juan","Perez", 24,new GregorianCalendar(2000, Calendar.DECEMBER, 2)));
           repository.save(new Client("Jose","Ramirez",26, new GregorianCalendar(1988, Calendar.OCTOBER, 12)));
           repository.save(new Client("Pedro","Juarez", 14,new GregorianCalendar(2010, Calendar.JANUARY, 22)));
        };
    }



}