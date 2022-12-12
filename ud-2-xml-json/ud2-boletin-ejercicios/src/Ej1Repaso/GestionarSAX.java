package Ej1Repaso;


import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GestionarSAX {
    // Objeto Handler que almacena el XML seleccionado durante el recorrido.
    private ManejadorSAX handler;

    public String imprimirNodos() {
        return handler.getXmlResult();
    }

    public File crearFichero() {
        File empleados = new File("res" + File.separator + "empleados.txt");


        if (!empleados.exists()) {
            try {
                FileWriter fempleados = new FileWriter(empleados);
                fempleados.write(imprimirNodos());
                fempleados.close();
                return empleados;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }

    }



    public int abrir_XML_SAX(File fichero) {
        try {
            // Se crea un objeto SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Se crea un objeto SAXParser para interpretar el documento XML.
            SAXParser parser = factory.newSAXParser();

            // Se crea un instancia del manejador que será el que recorra el
            // documento XML secuencialmente
            handler = new ManejadorSAX();

            // Se da la salida al parser para que comience a manejar el
            // documento XML. Esto recorrerá secuencialmente el documento XML
            // y cuando detecte un comienzo o fin de elemento o un texto
            // entonces lo tratará (según la implementación realizada en el
            // event handler)
            parser.parse(fichero, handler);

            return 0;

        } catch (SAXException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


}