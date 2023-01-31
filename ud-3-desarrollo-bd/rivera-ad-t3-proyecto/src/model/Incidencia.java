package model;

public class Incidencia {
    private String NumIncidencia;
    private String cod_partido;
    private String cod_jugador;
    private int minuto;
    private String tipo;

    public Incidencia(String NumIncidencia, String cod_partido, String cod_jugador, int minuto, String tipo) {
        this.NumIncidencia =NumIncidencia;
        this. cod_partido = cod_partido;
        this.cod_jugador = cod_jugador;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public String getNumIncidencia() {
        return NumIncidencia;
    }

    public void setNumIncidencia(String NumIncidencia) {
        this.NumIncidencia = NumIncidencia;
    }

    public String getCodjugador() {
        return cod_jugador;
    }

    public void setCodjugador(String cod_jugador) {
        this.cod_jugador = cod_jugador;
    }

    public String getCodpartido() {
        return cod_partido;
    }

    public void setCodpartido(String cod_partido) {
        this.cod_partido = cod_partido;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "(" + this.getNumIncidencia()+","+ this.getCodpartido() + "," + this.getCodjugador() + "," + this.getMinuto() +
                "," + this.getTipo()+ ")";
    }
}
