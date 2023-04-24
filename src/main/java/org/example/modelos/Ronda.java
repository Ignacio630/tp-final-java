package org.example.modelos;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private int nroRonda;

    private List<Partido> partidos;

    public Ronda(int nroRonda) {
        this.nroRonda = nroRonda;
        this.partidos = new ArrayList<>();
    }

    public void agregarPartido(Partido partido)  {
        for(Partido p : this.partidos){
            if (p.igual(partido)){
                throw new RuntimeException("El partido ya existe");
            }
        }
        this.partidos.add(partido);
    }

    public int getNroRonda() {
        return nroRonda;
    }

    public Partido obtenerPartido(String equipo1, String equipo2) {
        Partido partido = null;

        for(Partido p : (this.partidos)){
            if (p.juegan(equipo1,equipo2)){
                partido = p;
            }
        }

        if (partido == null){
            throw new RuntimeException("El partido no existe");
        }
        return partido;
    }
}
