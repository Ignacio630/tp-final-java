package org.example.utilidades;

import org.example.modelos.Equipo;
import org.example.modelos.Fase;
import org.example.modelos.Partido;
import org.example.modelos.Ronda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    List<Fase> fases;

    List<Equipo> equipos;

    private String directCSV;

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
        this.directCSV = "\"src/main/resources/Resultados.csv\"";
        try {
            Path csvPath = Paths.get("src/main/resources/Resultados.csv");

            // Lee todas las líneas del archivo
            List<String> lines = Files.readAllLines(csvPath);

            // Crea los equipos y partidos a partir de las líneas del archivo
            for (String line : lines) {
                String[] values = line.split(",");
                Ronda ronda = new Ronda(Integer.parseInt(values[1]));
                String equipo_1 = values[2];
                String equipo_2 = values[3];

                Equipo equipo1 = new Equipo(equipo_1);
                Equipo equipo2 = new Equipo(equipo_2);
                Partido partido = new Partido(equipo1, equipo2, Integer.parseInt(values[4]), Integer.parseInt(values[5]));

                // Agrega el partido a la ronda correspondiente
                ronda.agregarPartido(partido);


            }

        } catch (Exception e){
            e.printStackTrace();
        }
        Equipo argentina = this.getEquipo("Argentina", "Argentina");
        Equipo mexico = this.getEquipo("Mexico", "Mexico");
        Equipo polonia = this.getEquipo("Polonia", "Polonia");
        Equipo arabiaSaudita = this.getEquipo("Arabia Saudita", "Arabia Saudita");


        Partido p1 = new Partido(argentina, mexico, 3, 1);
        Partido p2 = new Partido(arabiaSaudita, argentina, 2, 1);
        Partido p3 = new Partido(mexico, polonia, 1, 2);
        Partido p4 = new Partido(polonia, arabiaSaudita, 1, 4);

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
            equipo = new Equipo(nombreEquipo);
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
