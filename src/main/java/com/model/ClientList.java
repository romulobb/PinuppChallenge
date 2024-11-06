package com.model;

import java.util.Objects;

public class ClientList {

    private String nombre;
    private String apellido;
    private int edad;
    private String fechanacimiento;
    private String fechafall;

    // avoid this "No default constructor for entity"
    public ClientList() {
    }

    public ClientList(String name, String author, int edad, String  fechanacimiento) {
        this.nombre = name;
        this.apellido = author;
        this.edad=edad;
        this.fechanacimiento = fechanacimiento;
    }

    public ClientList(String name, String author, int edad, String  fechanacimiento, String  fechafall) {
        this.nombre = name;
        this.apellido = author;
        this.edad=edad;
        this.fechanacimiento = fechanacimiento;
        this.fechafall=fechafall;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getFechafall() {
        return fechafall;
    }

    public void setFechafall(String fechafall) {
        this.fechafall = fechafall;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Nombre'" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", fecha Nacimiento=" + fechanacimiento +
                ", fecha probable fallecimineto="+ fechafall+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientList)) return false;
        ClientList that = (ClientList) o;
        return getEdad() == that.getEdad() && Objects.equals(getNombre(), that.getNombre()) && Objects.equals(getApellido(), that.getApellido()) && Objects.equals(getFechanacimiento(), that.getFechanacimiento()) && Objects.equals(getFechafall(), that.getFechafall());
    }


}
