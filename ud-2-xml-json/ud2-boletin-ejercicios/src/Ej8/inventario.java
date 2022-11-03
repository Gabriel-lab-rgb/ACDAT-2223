package Ej8;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;
import java.io.File;
import java.util.ArrayList;

import static Ej8.ProcesadorXPath.consulta;


public class inventario {

    public static void main(String args[]) throws Exception {
        File xml = new File("res" + File.separator + "catalogo.xml");

        if (xml.exists())
            try {
                //Ejercicio2
                System.out.println("*************************");
                String exp = "/catalogo/videojuego[stock>=10]";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));

                //Ejercicio3
                System.out.println("*************************");
                String exp2 = "count(//videojuego/count(capturas=0))";
                System.out.println(exp2);
                System.out.println(consulta(xml, exp2));




                // EJEMPLO 2: Nº de coches Nissan
                //System.out.println("*************************");
               //exp = "count(//coche[fabricante='Nissan'])";
               // System.out.println(exp);
                //Number numero =  ProcesadorXPath.consultaJaxenCount(xml, exp);
                //System.out.print(numero);
                // RESOLVER


                // EJEMPLO 3: Elementos coche que tengan un número de unidades superior a 10
                //System.out.println("*************************");
                //exp = "//coche[unidades>10]";
                //System.out.println(exp);


            } catch (Exception e) {
                System.err.println("-> Error: " + e);
            }
    }
}
