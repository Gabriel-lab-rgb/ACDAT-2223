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


                // EJEMPLO 1
                System.out.println("*************************");
                String exp1 = "//juegomesa";
                System.out.println(exp1);
                ArrayList<Element> lista1 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp1);

                // Recorrido de la lista de elementos seleccionados
                for (Element elemento : lista1)
                    System.out.println(elemento.asXML());
                // EJEMPLO 2
                System.out.println("*************************");
                String exp = "//videojuego[stock>10]/titulo";
                System.out.println(exp);
                ArrayList<Element> lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp);

              // Recorrido de la lista de elementos seleccionados
                for (Element elemento : lista)
                    System.out.println(elemento.asXML());

                // EJEMPLO 3
                System.out.println("*************************");
                String exp2 = "count(//videojuego[count(captura)=0])";
                System.out.println(exp2);
               // ArrayList<Element> lista2 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp2);
                Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp2);
                System.out.print(numero);

                //EJEMPLO 4

                System.out.println("*************************");
                String exp4 = "//videojuego[titulo='MARIO BROS']/imagenes/captura";
                System.out.println(exp4);
                ArrayList<Element> lista4 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp4);

                // Recorrido de la lista de elementos seleccionados
                for (Element elemento : lista4)
                    System.out.println(elemento.asXML());

                //EJEMPLO 5

                System.out.println("*************************");
                String exp3 = "//videojuego[plataforma='NES' and stock<=15]/titulo";
                System.out.println(exp3);
                ArrayList<Element> listaR = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp3);

                // Recorrido de la lista de elementos seleccionados
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
