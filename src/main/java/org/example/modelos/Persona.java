package org.example.modelos;

public class Persona {

    private String nombre;

    private Integer puntaje;

    private int cantAciertos;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.cantAciertos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAciertos() {
        return puntaje;
    }

    public int getCantAciertos() {
        return cantAciertos;
    }

    public void sumarPuntos(Integer puntajeASumar) {
        this.puntaje += puntajeASumar;
    }

    public void agregarAcierto(){
        this.cantAciertos++;
    }
}
