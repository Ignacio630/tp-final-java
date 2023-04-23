package org.example.utilidades;

import org.example.modelos.Equipo;
import org.example.modelos.Fase;
import org.example.modelos.Partido;
import org.example.modelos.Ronda;

import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    List<Fase> fases;

    List<Equipo> equipos;

    public LectorCSV() {
        this.fases = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    public List<Fase> getFases() {
        return fases;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void tomarResultados() {
        Equipo argentina = this.getEquipo("Argentina", "Argentina");
        Equipo bolivia = this.getEquipo("Bolivia", "Bolivia");
        Equipo chile = this.getEquipo("Chile", "Chile");
        Equipo arabiaSaudita = this.getEquipo("Arabia Saudita", "Arabia Saudita");


        Partido p1 = new Partido(argentina, bolivia, 3, 1);
        Partido p2 = new Partido(arabiaSaudita, argentina, 2, 1);
        Partido p3 = new Partido(bolivia, chile, 1, 2);
        Partido p4 = new Partido(chile, arabiaSaudita, 1, 4);

        Ronda r1 = new Ronda(1);

        r1.agregarPartido(p1);
        r1.agregarPartido(p2);
        r1.agregarPartido(p3);
        r1.agregarPartido(p4);

        Fase f1 = new Fase(1);

        f1.agregarRonda(r1);
        this.agregarFase(f1);

    }
    private void agregarFase(Fase fase){
        for (Fase f : this.fases){
            if (f.getNumeroFase() == fase.getNumeroFase()){
                throw new RuntimeException("La fase ya existe");
            }
        }
        this.fases.add(fase);
    }
    private Equipo getEquipo(String nombreEquipo, String descripcionEquipo) {
        Equipo equipo = null;

        for (Equipo e : this.equipos) {
            if (e.getNombre().equals(nombreEquipo)) {
                equipo = e;
            }
        }
        if (equipo == null) {
            equipo = new Equipo(nombreEquipo, descripcionEquipo);
            this.equipos.add(equipo);
        }
        return equipo;
    }
    public Fase getFase(int numeroFase){
        Fase fase = null;

        for(Fase f : this.fases){
            if(f.getNumeroFase() == numeroFase){
                fase = f;
            }
        }
        if (fase == null) {
            throw new RuntimeException("La fase no existe");
        }
        return fase;
    }

}
