package iesmm.ad.t2;

import java.util.ArrayList;
import java.util.List;

public class Vuelos {
    private List<Vuelo> vuelos;


    public Vuelos() {
        this.vuelos = new ArrayList<Vuelo>();
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public void add(Vuelo vuelo){
        vuelos.add(vuelo);
    }
}
