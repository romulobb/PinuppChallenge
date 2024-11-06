package com.model;

public class ClientDTO {

    private String nombre;


    private String apellido;
    private int edad;
    private String fechanacimiento;

    // avoid this "No default constructor for entity"
    public ClientDTO() {
    }

    public ClientDTO(String name, String author, int edad, String  fechanacimiento) {
        this.nombre = name;
        this.apellido = author;
        this.edad=edad;
        this.fechanacimiento = fechanacimiento;
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


    @Override
    public String toString() {
        return "Cliente{" +
                "Nombre'" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", fecha Nacimiento=" + fechanacimiento +
                '}';
    }

}
