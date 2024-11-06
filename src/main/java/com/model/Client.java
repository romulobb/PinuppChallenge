package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Objects;

import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private Calendar fechanacimiento;

    // avoid this "No default constructor for entity"
    public Client() {
    }

    public Client(String name, String author, int edad, Calendar  fechanacimiento) {
        this.nombre = name;
        this.apellido = author;
        this.edad=edad;
        this.fechanacimiento = fechanacimiento;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "Nombre'" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", fecha Nacimiento=" + fechanacimiento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return edad == client.edad  && Objects.equals(nombre, client.nombre) && Objects.equals(apellido, client.apellido) && Objects.equals(fechanacimiento, client.fechanacimiento);
    }


}
