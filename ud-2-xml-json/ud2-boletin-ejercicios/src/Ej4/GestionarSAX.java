package Ej4;


import Ej3.ManejadorSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class GestionarSAX {
    // Objeto Handler que almacena el XML seleccionado durante el recorrido.
    private Ej4.ManejadorSAX handler;

    public String imprimirNodos() {
        return handler.getCadena();
    }
    public void  CrearFichero() throws IOException {
        String cadena=imprimirNodos();
        FileWriter fichero = new FileWriter("res" + File.separator + "usuarios.txt");
        //BufferedWriter buffer = new BufferedWriter(fichero);
        //Recorro el array y escribimos linea a linea el fichero
        fichero.write(cadena);
        //cierro el objeto
        fichero.close();
    }

    public int abrir_XML_SAX(File fichero) {
        try {
            // Se crea un objeto SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Se crea un objeto SAXParser para interpretar el documento XML.
            SAXParser parser = factory.newSAXParser();

            // Se crea un instancia del manejador que será el que recorra el
            // documento XML secuencialmente
            handler = new Ej4.ManejadorSAX();

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