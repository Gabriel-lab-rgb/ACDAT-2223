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

                System.out.println("1.Número de respuestas contestadas");
                String exp = "count(//cuestion/respuesta)";

                Number num =  ProcesadorXPath.consultaJaxenCount(xml, exp);
                System.out.println(num);
                System.out.println("*************************");


                System.out.println("2.Categorias existentes relacionadas con la 3ª pregunta");
                String exp2 = "//cuestion[position()=3]/categoria";

                ArrayList<Element> lista1 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp2);
                for (Element elemento : lista1)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");



                System.out.println("3.Categorias relacionadas con la penultima pregunta");
                String exp3 = "//cuestion[last()-1]/categoria";

                ArrayList<Element> lista = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp3);
                for (Element elemento : lista)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");


                System.out.println("4.Cuestiones no respondidas");
                String exp4 = "//cuestion[not(respuesta)]";

                ArrayList<Element> list = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp4);
                for (Element elemento : list)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");


                System.out.println("5.Respuestas ofrecidas por las cuestiones que hyan seperado maá de 9");
                String exp5 = "//cuestion[puntuacion>=9]/respuesta";

                ArrayList<Element> lista4 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(xml, exp5);
                for (Element elemento : lista4)
                    System.out.println(elemento.asXML());
                System.out.println("*************************");



                System.out.println("6.Números de respuestas aprobadas");
                String exp6 = "count(//cuestion[puntuacion>=5])";

                Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp6);
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
