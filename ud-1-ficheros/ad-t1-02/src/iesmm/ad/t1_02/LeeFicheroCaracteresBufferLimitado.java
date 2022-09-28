package iesmm.ad.t1_02;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeeFicheroCaracteresBufferLimitado {
    // Se prefija un valor constante pero sería mejorable previamente calcularla
    // a partir de la longitud del fichero u otros parámetros
    final static int BUFFER_SIZE = 10;

    public static void main(String[] args) {

        try {
            // Asociamos el flujo a la fuente origen a partir del path
            // Declaración del fichero a partir del identificador o variable f
            File f = new File("res" + File.separator + "datos1.txt");

            // Existencia y procesamiento del fichero
            if (f.exists()) {
                int pos = 0;
                FileReader fichero = new FileReader(f);
                char[] caracteres = new char[BUFFER_SIZE];

                // Carga en el buffer de memoria del array los caracteres
                while ((pos = fichero.read(caracteres)) != -1)
                    System.out.println("Buffer leido: " + String.valueOf(caracteres));

                // Visualiza la información del buffer
                System.out.println(caracteres);

                // Cierre del fichero
                fichero.close();
            } else
                System.out.println("El fichero " + f.getName() + " no existe");
        } catch (IOException e) {
            System.err.println("Error al acceder al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
