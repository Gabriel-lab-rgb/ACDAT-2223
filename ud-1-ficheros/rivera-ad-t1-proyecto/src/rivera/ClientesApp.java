package rivera;

import java.io.File;

public class ClientesApp {

    public static void main(String[] args) {

        GestionaClientes gestion = new GestionaClientes();
       boolean b= gestion.generarFichero("res" + File.separator + "clientes1.csv");
       boolean b2=gestion.generarFichero("res" + File.separator + "clientes2.csv");
       if(b){
           System.out.println("Fichero clientes1.dat  creado con exito");
       }else{

           System.out.println("Fallo al crear el fichero clientes1.dat ");
       }
       if(b2){
           System.out.println("Fichero clientes2.dat creado con exito");
       }else{
           System.out.println("Fallo al crear el fichero  clientes2.dat");
       }

        File f = new File("res" + File.separator + "clientes1.dat");
        File f2=new File("res" +File.separator + "clientes2.dat");
        gestion.mostrarFichero(f);
        gestion.mostrarFichero(f2);
        boolean c= gestion.ordenarPorNombre(f);
        boolean c2= gestion.ordenarPorNombre(f2);
        if(c){
            System.out.println("Fichero ordenado clientes1.tmp generado con exito ");
        }else{
            System.out.println("Fallo al generar el fichero clientes1.tmp");
        }
        if(c2){
            System.out.println("Fichero ordenado clientes2.tmp generado con exito ");
        }else{
            System.out.println("Fallo al generar el fichero clientes2.tmp");
        }
        gestion.duplicados(f,f2,"res" +File.separator + "clientes.dat");



    }



}
