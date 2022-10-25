package Ej3;

import Ej2.GestionarSAX;

import java.io.File;

public class GestionarDepartamentos {

    public static void main(String args[]) {
        Ej3.GestionarSAX MyParser = new Ej3.GestionarSAX();

        if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "empleados.xml")) == 0) {
            // Si el documento se ha parseado correctamente
            // Mostrar lo procesado por el parser
            MyParser.imprimirNodos();

        }
    }
}
