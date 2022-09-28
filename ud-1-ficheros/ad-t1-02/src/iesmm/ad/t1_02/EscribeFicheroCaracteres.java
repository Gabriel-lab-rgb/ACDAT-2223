package iesmm.ad.t1_02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribeFicheroCaracteres {

    public static void main(String[] args) {

        try {
            // Asociamos el flujo a la fuente origen a partir del path
            // Declaración del fichero a partir del identificador o variable f
            File f = new File("res" + File.separator + "datos2.txt");

            // Existencia y tratamiento del fichero
            if (!f.exists()) {
                FileWriter fichero = new FileWriter(f);
                char[] palabra = "2º DAM".toCharArray();

                // Recorrido del fichero
                fichero.write('A');
                fichero.write("cceso a datos".toUpperCase());
                fichero.write('\n');
                fichero.write(palabra);

                // Cierre del fichero
                fichero.close();
            } else
                System.out.println("El fichero " + f.getName() + " no se puede sobreescribir");
        } catch (IOException e) {
            System.err.println("Error al generar al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
