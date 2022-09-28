package iesmm.ad.t1;

/*
    Desarrollar TableroJuegoApp, implementando los m¨¦todos de la clase TableroJuego y Ficha para que permita:
    * leer la informaci¨®n de un fichero properties compuesto de:
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

    Representaci¨®n generada:
    +-----------------+
    |  ?  ?  ?  ?  |
    |  ?  ?  ?  ?  |
    |  ?  ?  ?  ?  |
    +-----------------+

 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class TableroJuegoApp {


    public static void main(String[] args) {

        ArrayList<Character> array = new ArrayList<Character>();

        try {

            File f = new File("res" + File.separator + "tablero.props");
            Properties props = new Properties();
            props.load(new FileReader(f));
            TableroJuego tablero = new TableroJuego(Integer.parseInt(props.getProperty("rows")),Integer.parseInt(props.getProperty("cols")));
            /*System.out.println("filas " + tablero.getNfilas());*/
            array.add(props.getProperty("value1").charAt(0));
            array.add(props.getProperty("value2").charAt(0));


            Ficha[][] ficha = new Ficha[tablero.getNfilas()][tablero.getNcolumnas()];
            for (int i = 0; i < tablero.getNfilas(); i++){
                for (int j = 0; j < tablero.getNcolumnas(); j++){
                    Character valor=ValorAleatorio(array);
                    /*System.out.println("valor " + valor);*/
                    ficha[i][j] = new Ficha(valor);


                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("Fichero no existe");
        }
        catch (IOException e) {
            System.err.println("Error en lectura de propiedades de configuraci¨®n");
        }



    }

    public static Character ValorAleatorio(ArrayList<Character> lista){

    int valor=(int)(Math.random()*lista.size());
    /*System.out.println("Valor: " + lista.get(valor));*/
    return lista.get(valor);

    }
}
