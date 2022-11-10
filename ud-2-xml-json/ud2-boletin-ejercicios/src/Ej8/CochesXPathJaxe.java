package Ej8;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class CochesXPathJaxe {

    public static void main(String args[]) throws Exception {
        File xml = new File("res" + File.separator + "catalogo.xml");

        if (xml.exists())
            try {
                //  1
                System.out.println("Juegos de mesa existentes");
                String exp = "//juegomesa";
                ArrayList<Element> lista1 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp);
                for (Element elemento : lista1)
                    System.out.println(elemento.asXML());
                //  2
                System.out.println("*************************");
                System.out.println("Titulos de los videojuegos cuyo stock sea superior a 10 unidades");
                String exp2 = "//videojuego[stock>10]/titulo";
                ArrayList<Element> lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp2);
                for (Element elemento : lista)
                    System.out.println(elemento.asXML());
                // 3
                System.out.println("*************************");
                System.out.println("Contabilizar el número de videojuegos que no tienen aún capturas de pantalla");
                String exp3 = "count(//videojuego[not(captura)])";
                Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp3);
                System.out.print(numero +"\n");

                // 4
                System.out.println("*************************");

                System.out.println("Captura de pantalla del titulo de videojuegos \"MARIO BROS\" ");
                String exp4 = "//videojuego[titulo='MARIO BROS']/imagenes/captura";
                ArrayList<Element> lista4 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp4);
                for (Element elemento : lista4)
                    System.out.println(elemento.asXML());

                // 5
                System.out.println("*************************");
                System.out.println("Titulos de los videojuegos que sean de la plataforma \"NES\" y tengan un stock de,al menos ,15 unidades");
                String exp5 = "//videojuego[plataforma='NES' and stock<=15]/titulo";
                ArrayList<Element> listaR = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp5);
                for (Element elemento : listaR)
                    System.out.println(elemento.asXML());




            } catch (DocumentException e) {
                System.err.println("-> Error en el parseo: " + e);
            } catch (JaxenException e) {
                System.err.println("-> Error en la expresión: " + e);
            } catch (Exception e) {
                System.err.println("-> Error: " + e);
            }
    }
}
