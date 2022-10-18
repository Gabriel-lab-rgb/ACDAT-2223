package iesmm.ad.t2;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;

import static iesmm.ad.t2.ProcesadorXPath.consulta;

public class cochesXpath {

    public static void main(String[] args) throws  Exception {

        File xml=new File("coches.xml");

        if(xml.exists()){

            try{
                //Ejemplo 1
                System.out.println("***********************************");
                String exp="//coche[fabricante='Nissan']";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));

                // Ejemplo 2
                System.out.println("***********************************");
                exp="//coche[fabricante='Renault' and unidades>15]";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));

                //Ejemplo 3
                System.out.println("***********************************");
                exp="count(//coche)";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));

                //Ejemplo 4
                System.out.println("***********************************");
                exp="//coche[fabricante='Nissan'][precio=1000][precio<=1000]";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));

                //Ejemplo 5
                System.out.print("***********************************");
                exp="count(//coche[fabricante='Nissan'])";
                System.out.println(exp);
                System.out.println(consulta(xml, exp));


            }catch (XPathExpressionException e ){
                System.out.print("Error en la expresión: " + e );

            }catch (Exception e){
                System.out.print("Error :" + e );
            }

        }

    }
}
