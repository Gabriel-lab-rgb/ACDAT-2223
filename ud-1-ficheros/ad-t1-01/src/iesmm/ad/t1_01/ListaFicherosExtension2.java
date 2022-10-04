package iesmm.ad.t1_01;

import java.io.File;
import java.util.Scanner;

public class ListaFicherosExtension2 {

    public static void main(String args[]) {
        String path;


        // Flujo de entrada para solicitar la ruta al usuario por teclado
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Introducir nombre fichero/directorio: ");
            path = sc.nextLine();

            // Asociamos el flujo a la fuente origen a partir del path
            File f = new File(path);
            seeProperties(f);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    private static void seeProperties (File file){

        String archivo;

        if (!file.exists()) {
            System.out.println("No existe fichero o directorio");
        } else {

            if (!file.isDirectory()) {

                System.out.println("No es un directorio");
            } else {
                File ficheros[]= file.listFiles();
                for(int i=0;i<ficheros.length;i++){
                    if(ficheros[i].isFile()){
                        archivo=ficheros[i].getName();
                        if(archivo.endsWith(".txt")){
                            System.out.println(archivo);
                        }
                    }
                }

            }

        }
    }

}
