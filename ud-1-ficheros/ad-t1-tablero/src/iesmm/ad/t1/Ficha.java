package iesmm.ad.t1;

public class Ficha {

    private char caracter;

    public Ficha(char caracter) {
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    @Override
    public String toString() {
        return String.valueOf(caracter);
    }
}
