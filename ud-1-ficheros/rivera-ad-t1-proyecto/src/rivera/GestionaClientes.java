package rivera;

import java.io.*;
import java.util.Arrays;

public class GestionaClientes {

    public static void main(String[] args) {

        generarFichero("res" + File.separator + "clientes1.csv");
    }
    public  static boolean generarFichero(String fcsv){


        try {
            File f = new File(fcsv);

            if(f.exists()){
                DataInputStream fichero = new DataInputStream(new FileInputStream(f));
                BufferedInputStream buffer=new BufferedInputStream(fichero);
                int linea=buffer.read();
                while(linea!= -1) {
                    System.out.print((char)linea);

                    /*String[] split =String.valueOf((char)linea).split(",");
                    System.out.println(split.length);
                    System.out.println(Arrays.asList(split));*/

                    /*System.out.print("Apellido1 : " + fichero.readUTF());
                    System.out.print("Apellido2 : " + fichero.readUTF());
                    System.out.println(" - Telefono: " + fichero.readInt());*/
                    linea=buffer.read();
                }

                // Cierre del fichero
                fichero.close();
            }

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;

    }

    }


