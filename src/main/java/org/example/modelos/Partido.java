package org.example.modelos;

public class Partido {
    private Equipo equipo1;

    private Equipo equipo2;

    private int golesEquipo1;

    private int golesEquipo2;


    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public boolean igual(Partido partido) {
        return (this.equipo1.getNombre().equals(partido.equipo1.getNombre()) && this.equipo2.getNombre().equals(partido.equipo2.getNombre())) ||
                this.equipo1.getNombre().equals(partido.equipo2.getNombre()) && this.equipo2.getNombre().equals(partido.equipo1.getNombre());
    }

    public boolean juegan(String equipo1, String equipo2) {
        return (this.equipo1.getNombre().equals(equipo1) && this.equipo2.getNombre().equals(equipo2)) ||
                (this.equipo1.getNombre().equals(equipo2) && this.equipo2.getNombre().equals(equipo1));
    }

    public ResultadoEnum getResult() {
        if (golesEquipo1 > golesEquipo2){
            return ResultadoEnum.GANADOR1;
        } else if (golesEquipo2 > golesEquipo1) {
            return ResultadoEnum.GANADOR2;
        } else {
          return ResultadoEnum.EMPATE;
        }
    }
}
