package org.example;

import org.example.modelos.Fase;
import org.example.modelos.Persona;
import org.example.modelos.Pronostico;
import org.example.utilidades.LectorCSV;
import org.example.utilidades.LectorDB;

public class Main {
    public static void main(String[] args) {
        Integer puntosPorPartido = 1;
        Integer puntosExtraPorRonda = 2;
        Integer puntosExtraPorFase = 3;

        LectorCSV lectorCSV = new LectorCSV();
        lectorCSV.tomarResultados();

        LectorDB lectorDB = new LectorDB(lectorCSV);
        lectorDB.agregarPronosticos();

        calcPuntos(lectorDB, puntosPorPartido, puntosExtraPorRonda, puntosExtraPorFase);
    }

    private static void calcPuntos(LectorDB lectorDB,
                                   Integer puntosPorPartido,
                                   Integer puntosExtraPorRonda,
                                   Integer puntosExtraPorFase) {
        for (Pronostico p : lectorDB.getPronosticos()){
            if (p.fueAcertado()){
                p.getPersona().sumarPuntos(puntosPorPartido);
                p.getPersona().agregarAcierto();
            }

        }

//        for (Persona p : lectorDB.getPersonas()){
//            for (Ronda r : Fase.)
//        }
        for(Persona p : lectorDB.getPersonas()){
            System.out.println("Nombre" + p.getNombre());
            System.out.println("Acierto" + p.getCantAciertos());
            System.out.println("Puntaje" + p.getAciertos());
            System.out.println("--------------------------------------");
        }
    }
}