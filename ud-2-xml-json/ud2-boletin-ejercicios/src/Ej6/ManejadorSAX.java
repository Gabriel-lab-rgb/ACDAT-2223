package Ej6;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {

    private boolean flag;
    private String cadena = "";

    private String xmlresult ="";
    private String html="";



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
                "    <title>Catalogo</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Catalogo de videojuegos clasico</h1>\n"  +
                "<table border=\"1\">\n";
    }

    /**
     * FIN DEL DOCUMENTO XML
     */
    @Override
    public void endDocument() throws SAXException {

        xmlresult += "</tr></table>\n"
                +"</body>\n" +
                "</html>";
    }

    /**
     * COMIENZO DEL ELEMENTO
     */
    @Override
    public void startElement(String uri, String nombre, String elemento,
                             Attributes atts) throws SAXException {

        if(elemento.equals("juegomesa")){
            flag=false;
        }


    }


    /**
     * FIN DEL ELEMENTO
     */
    @Override
    public void endElement(String uri, String nombre, String elemento)
            throws SAXException {


    switch (elemento) {

        case "juegomesa":
            flag=true;
            break;
        case "caratula":

            xmlresult += "<td>\n<img src=\""+cadena+ "\">\n";

            break;
        case "titulo":
            //flag=false;
    if(flag){
    html+= "<h3>"+cadena+"</h3>\n";
}

            break;
        case "plataforma":
            //flag=false;
            html += "<p>Consola:"+cadena+"</p>\n";
            break;
        case "stock":
            if(flag){
                xmlresult += html + "<p>Stock actual:"+cadena+"</p>";
                xmlresult +="</td>\n";
                html="";
            }

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

        //if(flag) {
            this.cadena += new String(cadena, posinicio, longitud);
            //flag=false;
       // }

        // OPCIÓN 2
        /*if(flag){
            XmlResult += new String(cadena, posinicio, longitud);
            flag=false;
        }*/

    }



}


