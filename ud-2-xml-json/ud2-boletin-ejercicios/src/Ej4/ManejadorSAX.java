package Ej4;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {

    private boolean flag;
    private String cadena = "";
    private String xmlresult ="";

    public String getXmlresult() {
        return xmlresult;
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

            case "username":
               //flag=true;
                xmlresult += cadena + ":";
                break;
            case "password":
                //flag=true;
                xmlresult += cadena + "\n";
                break;

        }
        cadena = "";
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

        // OPCIÓN 2
        /*if(flag){
            XmlResult += new String(cadena, posinicio, longitud);
            flag=false;
        }*/

    }



}

