package org.example.modelos;

import java.util.ArrayList;
import java.util.List;

public class Fase {
    private List<Ronda> rondas;

    private int numeroFase;

    public Fase(int numeroFase) {
        this.rondas = new ArrayList<>();
        this.numeroFase = numeroFase;
    }

    public int getNumeroFase() {
        return numeroFase;
    }

    public void agregarRonda(Ronda r1) {
        for (Ronda r : this.rondas){
            if(r.getNroRonda() == r1.getNroRonda()){
                throw new RuntimeException("La ronda ya existe");
            }
        }
        this.rondas.add(r1);
    }
    public Ronda getRonda(int numeroRonda){
        Ronda ronda = null;

        for(Ronda r : this.rondas){
            if(r.getNroRonda() == numeroRonda){
                ronda = r;
            }
        }
        if (ronda == null) {
            throw new RuntimeException("La fase no existe");
        }
        return ronda;
    }
}
