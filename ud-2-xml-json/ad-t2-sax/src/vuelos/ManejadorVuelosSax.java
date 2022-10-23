package vuelos;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ManejadorVuelosSax extends DefaultHandler {

    private String xmlResult="";
    private int contadorVuelos=0;



    public String getXmlResult() {
        return xmlResult;
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

     if(elemento.equals("Vuelo")){
             System.out.println("Vuelo " + (contadorVuelos=contadorVuelos + 1) +":");
      }

    }



    /**
     * FIN DEL ELEMENTO
     */
    @Override
    public void endElement(String uri, String nombre, String elemento)
            throws SAXException {

        switch (elemento) {
            case "origen":
                System.out.println("      Origen:" + xmlResult);
                break;
            case "destino":
                System.out.println("      Destino: " + xmlResult);
                break;
        }
        xmlResult="";
    }

    /**
     * CADENA DE CARACTERES (VALOR DEL CONTENIDO)
     *
     * @param cadena
     *            Cadena de caracteres extraída del elemento como valor
     *            específico
     * @param posinicio
     *            Posición del caracter dentro del documento XML
     * @param longitud
     *            Número de carácteres
     *
     * @throws SAXException
     *             Cualquier excepción producida en la lectura del valor
     */
    @Override
    public void characters(char[] cadena, int posinicio, int longitud)
            throws SAXException {
        // OPCIÓN 1

        xmlResult += new String(cadena, posinicio, longitud);

       /* xmlResult += "VALOR: " + new String(cadena, posinicio, longitud) + "\n";*/

        // OPCIÓN 2
        // for (int i = start; i < length + start; i++)
        // result += Character.toString(cadena[i]);
    }


}
