package Ej7;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class ExamenXPathJaxe {

    public static void main(String args[]) throws Exception {
        File xml = new File("res" + File.separator + "examen.xml");

        if (xml.exists())
            try {



                System.out.println("1");
                String exp2 = "count(//cuestion/respuesta)";
                //System.out.println(exp2);
                Number num =  ProcesadorXPath.consultaJaxenCount(xml, exp2);
                System.out.println(num);
                System.out.println("*************************");


                System.out.println("2");
                String exp1 = "//cuestion[position()=3]/categoria";
                //System.out.println(exp1);
                ArrayList<Element> lista1 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp1);
                for (Element elemento : lista1)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");



                System.out.println("3");
                String exp = "//cuestion[last()-1]/categoria";
                //System.out.println(exp);
                ArrayList<Element> lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp);
                for (Element elemento : lista)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");


                System.out.println("4");
                String ex = "//cuestion[not(respuesta)]";
                //System.out.println(ex);
                ArrayList<Element> list = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, ex);
                for (Element elemento : list)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");


                System.out.println("5");
                String exp4 = "//cuestion[puntuacion>=9]/respuesta";
               // System.out.println(exp4);
                ArrayList<Element> lista4 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp4);
                for (Element elemento : lista4)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");



                System.out.println("6");
                String exp3 = "count(//cuestion[puntuacion>=5])";
                //System.out.println(exp3);
                Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp3);
                System.out.print(numero);




            } catch (DocumentException e) {
                System.err.println("-> Error en el parseo: " + e);
            } catch (JaxenException e) {
                System.err.println("-> Error en la expresión: " + e);
            } catch (Exception e) {
                System.err.println("-> Error: " + e);
            }
    }
}
