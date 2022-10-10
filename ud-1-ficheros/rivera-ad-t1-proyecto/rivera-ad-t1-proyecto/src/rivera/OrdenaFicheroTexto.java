package rivera;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class OrdenaFicheroTexto {

    public static void main(String[] args) {


        File f=new File("res" + File.separator + "animales");
        sort(f);

    }

    public static void  sort(File f){


        if(f.exists()){

            ArrayList<String> animales= new ArrayList<>();
            try {
                FileReader fichero= new FileReader(f);
                BufferedReader buffer = new BufferedReader(fichero);
                String linea="" ;
                while((linea=buffer.readLine())!=null){
                    animales.add(linea);
                }
                Collections.sort(animales);
               /* for(String temp: animales){
                    System.out.println(temp);
                }*/
                /*
                for(int i=0;i<animales.size();i++){

                    System.out.print(animales.get(i));
                }*/

                buffer.close();
                fichero.close();

                FileWriter fichero2 = new FileWriter("res" + File.separator + "animalesOrdenados.dat");

                for(int i=0;i<animales.size();i++){

                    fichero2.write(animales.get(i));
                    fichero2.write("\n");
                }
                fichero2.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }else{
            System.out.println("El fichero no existe");


        }
    }
}
