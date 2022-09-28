package iesmm.ad.t1_02;

import java.io.*;

public class TransformadorCSV_XML {

    public static void main(String[] args) {

        try {
            // Asociamos el flujo a la fuente origen a partir del path
            // Declaración del fichero a partir del identificador o variable f
            File fichero1 = new File("res" + File.separator + "agenda.csv");
            File fichero2 = new File("res"+ File.separator + "agenda.xml");

            // Existencia y tratamiento del fichero
            if (fichero1.exists()) {
                // FileReader no posee métodos que permitan leer líneas
                // completas de texto, pero la clase BufferedReader si
                BufferedReader f1 = new BufferedReader(new FileReader(fichero1));
                FileWriter f2 = new FileWriter(fichero2); // Fichero destino

                // Lectura primera línea
                String linea = f1.readLine();
                f2.write("<personas>");

                // Recorrido del fichero CSV
                while (linea != null) {
                    f2.write(transformar_CSV_XML(linea)); // Escritura en fichero resultante
                    linea = f1.readLine();
                }

                f2.write("</personas>");

                // Cierre del fichero
                f1.close();
                f2.close();
            } else
                System.out.println("El fichero " + fichero1.getName() + " no existe");
        } catch (IOException e) {
            System.err.println("Error al acceder al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Transforma datos separados por comas (CSV) en formato XML
     *
     * @param cad Cadena de entrada en formato (nombre;edad;ciudad)
     * @return Devuelve la cadena transformada en XML
     */
    private static String transformar_CSV_XML(String cad) {
        // 1) Separación de campos
        String nombre = cad.split(";")[0];
        String edad = cad.split(";")[1];
        String ciudad = cad.split(";")[2];

        // 2) Generación de la estructura XML
        String xml = "<persona>";
        xml += "<nombre>" + nombre + "</nombre>";
        xml += "<edad>" + edad + "</edad>";
        xml += "<ciudad>" + ciudad + "</ciudad>";
        xml += "</persona>";

        return xml;
    }
}
