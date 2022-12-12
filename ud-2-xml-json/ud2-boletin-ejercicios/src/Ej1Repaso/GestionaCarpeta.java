package Ej1Repaso;

import java.io.*;
import java.util.ArrayList;

public class GestionaCarpeta {

    public static File  makeGlobalFile(String f) throws IOException {
        ArrayList<File> xml=new ArrayList<>();
        File salida=null;
        File directorio=new File(f);

      if(directorio.exists()){

          if(directorio.isDirectory()){

              File[] lista=directorio.listFiles();
              for(int i=0;i< lista.length;i++){
                  if(lista[i].getName().endsWith(".xml")){
                      xml.add(lista[i]);
                  }

                  if(xml.size()>0){
                      String linea;
                      salida=new File(directorio.getAbsolutePath() + File.separator + directorio.getName() + ".txt");
                      FileWriter fsalida=new FileWriter(salida);

                      for(int j=0;j<xml.size();j++){
                          fsalida.write(xml.get(j).getName() + "\n");

                          BufferedReader b=new BufferedReader(new FileReader(xml.get(j)));
                          while ((linea=b.readLine())!=null){
                              fsalida.write(linea + "\n");

                          }
                          b.close();


                      }
                      fsalida.close();
                  }
              }
          }
      }

        return salida;
    }

    public static void main(String[] args) throws IOException {
        File f=GestionaCarpeta.makeGlobalFile("res");
    }


}
