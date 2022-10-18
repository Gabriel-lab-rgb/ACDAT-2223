package iesmm.ad.t2;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class CochesXPathJaxe {

    public static void main(String args[]) throws Exception {
        File xml = new File("coches.xml");

        if (xml.exists())
            try {
                // EJEMPLO 1
                System.out.println("*************************");
                String exp = "//coche/nombre";
                System.out.println(exp);
                ArrayList<Element> lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp);

                // Recorrido de la lista de elementos seleccionados
                for (Element elemento : lista)
                    System.out.println(elemento.asXML());

                // EJEMPLO 2: Nº de coches Nissan
                System.out.println("*************************");
                exp = "count(//coche[fabricante='Nissan'])";
                System.out.println(exp);
                Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp);
                System.out.print(numero);
                // RESOLVER


                // EJEMPLO 3: Elementos coche que tengan un número de unidades superior a 10
                System.out.println("*************************");
                exp = "//coche[unidades>10]";
                System.out.println(exp);
                lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp);

                // Recorrido de la lista de elementos seleccionados
                for (Element elemento : lista)
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
