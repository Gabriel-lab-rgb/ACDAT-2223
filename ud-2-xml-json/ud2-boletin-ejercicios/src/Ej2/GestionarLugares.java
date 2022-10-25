package Ej2;

import java.io.File;

public class GestionarLugares {

    public static void main(String args[]) {
        GestionarSAX MyParser = new GestionarSAX();

        if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "mapa.xml")) == 0) {
            // Si el documento se ha parseado correctamente
            // Mostrar lo procesado por el parser
            MyParser.imprimirNodos();

        }
    }
}
