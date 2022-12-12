package Eje3Repaso;

import Ej8.ProcesadorXPath;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class HtmlXPathJaxe {

    public static void main(String[] args) {
        File xml = new File("res" + File.separator + "mihtml.xml");

        if (xml.exists()){
            try {
                //  1
                System.out.println("Numero de tablas existentes");
                String exp = "count(//table)";
                Number numero= ProcesadorXPath.consultaJaxenCount(xml, exp);
                    System.out.println(numero);


                //2

                System.out.println("celda existentes en cada tabla");
                String exp2 = "//table/tr/td";
                ArrayList<Element> lista2 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp2);
                for (Element elemento : lista2)
                    System.out.println(elemento.asXML());

                //  3
                System.out.println("Primera fila de cada una de las tablas");
                String exp3 = "//table/tr[1]";
                ArrayList<Element> lista3 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp3);
                for (Element elemento : lista3)
                    System.out.println(elemento.asXML());




                //  4
                System.out.println("Numero de tablas que contienen el valor de la celda B1");
                String exp4 = "count(//table/tr[td=\"Celda B1\"])";
                Number numero4= ProcesadorXPath.consultaJaxenCount(xml, exp4);
                System.out.println(numero4);

                //  5
                System.out.println("Numero de filas que contienen al menos  dos filas");
                String exp5 = "count(//table/tr[count(td)>=2])";
                Number numero5= ProcesadorXPath.consultaJaxenCount(xml, exp5);
                System.out.println(numero5);



            } catch (DocumentException e) {
                System.err.println("-> Error en el parseo: " + e);
            } catch (JaxenException e) {
                System.err.println("-> Error en la expresión: " + e);
            } catch (Exception e) {
                System.err.println("-> Error: " + e);
            }

        }
    }
}
