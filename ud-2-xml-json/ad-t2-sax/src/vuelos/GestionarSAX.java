package vuelos;

import vuelos.ManejadorVuelosSax;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class GestionarSAX {
	// Objeto Handler que almacena el XML seleccionado durante el recorrido.
	private ManejadorVuelosSax handler;

	public void imprimirNodos() {
		System.out.println(handler.getXmlResult());
	}

	public int abrir_XML_SAX(File fichero) {
		try {
			// Se crea un objeto SAXParserFactory
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// Se crea un objeto SAXParser para interpretar el documento XML.
			SAXParser parser = factory.newSAXParser();

			// Se crea un instancia del manejador que será el que recorra el
			// documento XML secuencialmente
			handler = new ManejadorVuelosSax();

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

	public static void main(String args[]) {
		GestionarSAX MyParser = new GestionarSAX();

		if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "vuelos.xml")) == 0) {
			// Si el documento se ha parseado correctamente
			// Mostrar lo procesado por el parser
			/*MyParser.imprimirNodos();*/

		}
	}
}