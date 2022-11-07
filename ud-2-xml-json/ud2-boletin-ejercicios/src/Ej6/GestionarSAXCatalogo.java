package Ej6;

import java.io.File;
import java.io.IOException;

public class GestionarSAXCatalogo {


        public static void main(String args[]) throws IOException {
            GestionarSAX MyParser = new GestionarSAX();

            if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "catalogo.xml")) == 0) {
                // Si el documento se ha parseado correctamente
                // Mostrar lo procesado por el parser
                MyParser.CrearFichero();

            }

    }

}
