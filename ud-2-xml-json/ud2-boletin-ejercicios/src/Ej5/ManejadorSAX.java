package Ej5;


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

        xmlresult +="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Listado de vuelos</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Listado de salida de vuelos</h1>\n"  +
                " <table border=\"1\">\n " +
                "<tr>\n" +
                "<th>Origen</th>\n" +
                "<th>Destino</th>\n" +
                "</tr>\n" ;


    }

    /**
     * FIN DEL DOCUMENTO XML
     */
    @Override
    public void endDocument() throws SAXException {

        xmlresult += "</table>\n"
                +"</body>\n" +
                "</html>";
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

            case "origen":
                //flag=true;
                xmlresult += "<tr><td>"+cadena+"</td>\n";
                break;
            case "destino":
                //flag=true;
                xmlresult += "<td>"+cadena+"</td>\n" +"</tr>\n";
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


