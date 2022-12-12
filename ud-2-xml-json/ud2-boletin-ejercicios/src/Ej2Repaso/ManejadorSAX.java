package Ej2Repaso;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {

    private String XmlResult = "";
    private String cadena="";
    private boolean esTabla=false;
    private int contador=0;


    public String getXmlResult() {
        return XmlResult;
    }

    public int getContador() {
        return contador;
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

         System.out.println("Documento XML parseado correctamente");
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

        switch (elemento){

            case "td":
                XmlResult=XmlResult + cadena + " " ;
                break;

            case "table":
                XmlResult=XmlResult +"\n";
                contador++;
                break;
        }
        cadena="";


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

        this.cadena += new String(cadena, posinicio, longitud);


    }



}
