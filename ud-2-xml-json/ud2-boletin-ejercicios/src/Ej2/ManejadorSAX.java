package Ej2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {

    private String XmlResult = "";

    public String getXmlResult() {
        return XmlResult;
    }

    /**
     * INICIO DEL DOCUMENTO XML
     */
    @Override
    public void startDocument() throws SAXException {

    }

    /**
     * FIN DEL DOCUMENTO XML
     */
    @Override
    public void endDocument() throws SAXException {

    }

    /**
     * COMIENZO DEL ELEMENTO
     */
    @Override
    public void startElement(String uri, String nombre, String elemento,
                             Attributes atts) throws SAXException {
    }


    /**
     * FIN DEL ELEMENTO
     */
    @Override
    public void endElement(String uri, String nombre, String elemento)
            throws SAXException {

        switch (elemento) {
            case "nombre":
                System.out.println("NOMBRE: " + XmlResult);
                break;
            case "x":
                System.out.println("|_ x:  " + XmlResult);
                break;
            case "y":
                System.out.println("|_ y: " + XmlResult);
                break;
            case "z":
                System.out.println("|_ z: " + XmlResult);
                break;
        }
        XmlResult = "";
    }

    /**
     * CADENA DE CARACTERES (VALOR DEL CONTENIDO)
     *
     * @param cadena    Cadena de caracteres extraída del elemento como valor
     *                  específico
     * @param posinicio Posición del caracter dentro del documento XML
     * @param longitud  Número de carácteres
     * @throws SAXException Cualquier excepción producida en la lectura del valor
     */
    @Override
    public void characters(char[] cadena, int posinicio, int longitud)
            throws SAXException {
        // OPCIÓN 1

        XmlResult += new String(cadena, posinicio, longitud);


    }



}
