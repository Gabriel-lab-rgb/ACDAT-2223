package iesmm.ad.t1;

/*
    Desarrollar TableroJuegoApp, implementando los m��todos de la clase TableroJuego y Ficha para que permita:
    * leer la informaci��n de un fichero properties compuesto de:
      - las dimensiones (filas x columnas) de un tablero de juego
      - valores de las casillas a procesar

    * representar en cada ficha un valor posible aleatorio.

    Recuerda incluir el tratamiento de errores y posibles excepciones.


    Ejemplo:

    =================
    res/tablero.props
    =================
    # Dimensiones tablero
    rows=3
    cols=4

    # Fichas
    value1=?
    value2=?

    Representaci��n generada:
    +-----------------+
    |  ?  ?  ?  ?  |
    |  ?  ?  ?  ?  |
    |  ?  ?  ?  ?  |
    +-----------------+

 */

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class TableroJuegoApp {


    public static void main(String[] args) {



            File f = new File("res" + File.separator + "tablero.props");

            if(!f.exists()){
                System.out.println("El fichero no existe");
            }else{
                TableroJuego tablero=new TableroJuego(f);
                tablero.mostrarFichas();
            }


    }


}
