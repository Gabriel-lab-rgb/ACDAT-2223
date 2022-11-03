package Ej4;

import Ej3.GestionarSAX;

import java.io.File;
import java.io.IOException;

public class GestionarSAXUsuarios {

    public static void main(String args[]) throws IOException {
        Ej4.GestionarSAX MyParser = new Ej4.GestionarSAX();

        if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "usuarios.xml")) == 0) {
            // Si el documento se ha parseado correctamente
            // Mostrar lo procesado por el parser
            MyParser.CrearFichero();

        }
    }
}
