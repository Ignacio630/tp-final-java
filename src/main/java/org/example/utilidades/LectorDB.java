package org.example.utilidades;

import org.example.modelos.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LectorDB {

    private String direccDB;

    private String user;

    private String password;

    private List<Pronostico> pronosticos;

    private List<Persona> personas;

    private List<Fase> fases;
    private LectorCSV lectorCSV;
    public LectorDB(LectorCSV lectorCSV, String direccDB, String user, String password) {
        this.lectorCSV = lectorCSV;
        this.pronosticos = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.direccDB = direccDB;
        this.user = user;
        this.password = password;
        this.fases = new ArrayList<>();
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public List<Persona> getPersonas(){
        return personas;
    }
    public boolean acertoTodosLasRondas(Persona p, int ronda){
        List<Pronostico> pronosticos1 = this.pronosticos.stream().filter(
                pronostico -> pronostico.getPersona().equals(p)
        ).toList();

        pronosticos1 = pronosticos1.stream().filter(
                pronostico -> pronostico.getRonda().getNroRonda() == ronda
        ).toList();

        return pronosticos1.stream().allMatch(pronostico -> pronostico.fueAcertado());
    }

    public boolean acertoTodosLasFases(Persona p, int fase){
        List<Pronostico> pronosticos1 = this.pronosticos.stream().filter(
                pronostico -> pronostico.getPersona().equals(p)
        ).toList();

        pronosticos1 = pronosticos1.stream().filter(
                pronostico -> pronostico.getFase().getNumeroFase() == fase
        ).toList();

        return pronosticos1.stream().allMatch(pronostico -> pronostico.fueAcertado());
    }
    public void agregarPronosticos() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + this.direccDB ,this.user ,this.password);
            Statement stmt = con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from pronosticos");
            while (rs.next()){

                Fase fase = lectorCSV.getFase(rs.getInt("fase"));
                Ronda ronda = fase.getRonda(rs.getInt("ronda"));
                Persona persona = this.getPersona(rs.getString("persona"));
                Partido partido = ronda.obtenerPartido(rs.getString("equipo_1"),rs.getString("equipo_2"));

                boolean gana_1 = rs.getBoolean("gana_1");
                boolean gana_2 = rs.getBoolean("gana_2");
                boolean empata = rs.getBoolean("empata");

                ResultadoEnum res = null;

                if(gana_1){
                    res = ResultadoEnum.GANADOR1;
                } else if (gana_2) {
                    res = ResultadoEnum.GANADOR2;
                } else if (empata) {
                    res = ResultadoEnum.EMPATE;
                } else {
                    throw new RuntimeException("No se establecio un resultado");
                }
                Pronostico pronostico = new Pronostico(fase,ronda,persona,partido,res);
                this.addPronosticos(pronostico);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addPronosticos(Pronostico pronostico) {
        for(Pronostico p : this.pronosticos){
            if (p.getFase().equals(pronostico.getFase())
                && p.getPartido().equals(pronostico.getPartido())
                && p.getPersona().equals(pronostico.getPersona())
                && p.getRonda().equals(pronostico.getRonda())){
                throw new RuntimeException("Ya hay un pronostico de " + pronostico.getPersona().getNombre() + " para el partido");
            }
        }

        this.pronosticos.add(pronostico);
    }

    private Persona getPersona(String nombrePersona) {
        Persona persona = null;

        for (Persona p : this.personas){
            if (p.getNombre().equals(nombrePersona)){
                persona = p;
            }
        }
        if (persona == null){
            persona = new Persona(nombrePersona);
            this.personas.add(persona);
        }

        return persona;
    }
}
