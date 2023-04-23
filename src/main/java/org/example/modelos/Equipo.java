package org.example.modelos;

public class Equipo {
    private String nombre;

    private String descripcion;

    public Equipo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }
}
