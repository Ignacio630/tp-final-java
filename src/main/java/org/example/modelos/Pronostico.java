package org.example.modelos;

public class Pronostico {
    private Fase fase;

    private Ronda ronda;

    private Persona persona;

    private Partido partido;

    private ResultadoEnum resultado;

    public Pronostico(Fase fase, Ronda ronda, Persona persona, Partido partido, ResultadoEnum resultado) {
        this.fase = fase;
        this.ronda = ronda;
        this.persona = persona;
        this.partido = partido;
        this.resultado = resultado;
    }

    public Fase getFase() {
        return fase;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public Persona getPersona() {
        return persona;
    }

    public Partido getPartido() {
        return partido;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public boolean fueAcertado() {
        return this.partido.getResult().equals(this.resultado);
    }
}
