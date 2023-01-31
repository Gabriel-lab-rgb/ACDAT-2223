package model;

public class Equipos {
    private String cod_equipo;
    private String nombre;
    private String localidad;

    public Equipos(String cod_equipo, String nombre, String localidad) {
        this.cod_equipo =cod_equipo;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public String getCodequipo() {
        return cod_equipo;
    }

    public void setCodequipo(String cod_equipo) {
        this.cod_equipo = cod_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodjugador(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String toString() {
        return "(" + this.getCodequipo() + "," + this.getNombre() + "," + this.getLocalidad()+")";
    }
}
