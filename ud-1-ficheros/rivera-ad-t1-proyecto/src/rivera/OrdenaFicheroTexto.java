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

//Compruebo si el fichero existe
        if(f.exists()){

            ArrayList<String> animales= new ArrayList<>();
            try {
                FileReader fichero= new FileReader(f);
                BufferedReader buffer = new BufferedReader(fichero);
                String linea="" ;
                while((linea=buffer.readLine())!=null){
                   // Añado la linea al array si el tamaño de la cadena  no es 0
                    if(!linea.isEmpty()){
                        animales.add(linea);
                    }

                }
                //Ordeno el array por orden alfabetico
                Collections.sort(animales);
                //cerramos los objetos para no consumir recursos
                buffer.close();
                fichero.close();

                // creo un nuevo objeto FileWriter para añadir al final del fichero
                FileWriter fichero2 = new FileWriter("res" + File.separator + "animalesOrdenados.dat");

                //Recorro el array y escribimos linea a linea el fichero
                for(int i=0;i<animales.size();i++){

                    fichero2.write(animales.get(i));
                    fichero2.write("\n");
                }
                //cierro el objeto
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
