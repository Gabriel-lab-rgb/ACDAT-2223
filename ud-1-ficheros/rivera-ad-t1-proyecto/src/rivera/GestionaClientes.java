package rivera;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestionaClientes {


    public  boolean generarFichero(String fcsv) {


        try {
            File f = new File(fcsv);
             //Compuebo si el fichero existe
            if (f.exists()) {

                FileReader fichero = new FileReader(f);
                BufferedReader buffer = new BufferedReader(fichero);
                String linea = "";
                ArrayList<String[]> datos = new ArrayList<>();
                //Leo linea a linea la información del fichero y lo introduzco en un arraylist
                while ((linea = buffer.readLine()) != null) {
                    String[] array = linea.split(",");
                    datos.add(array);

                }
                // Cierro los objetos para no consumir recursos
                buffer.close();
                fichero.close();

                //Llamo al metodo quitarExtension para obtener el nombre del fichero sin la extensión
                String nombre=quitarExtension(f.getName());
                //Creo un nuevo objeto fichero
                File archivo = new File("res" + File.separator + nombre +".dat");
                //Para escribir  utilizo el FileOuputStream pasandole  como referencia un archivo
                FileOutputStream fos = new FileOutputStream(archivo);
                //Recorro el array datos y creo e  introduzco  objetos de tipo cliente al fichero
                ObjectOutputStream escribir = null;
                for (int i = 0; i < datos.size(); i++) {
                   escribir = new ObjectOutputStream(fos);
                   Cliente cliente = new Cliente(datos.get(i)[0] + " " + datos.get(i)[1] + "," + datos.get(i)[2], Long.parseLong(datos.get(i)[3]));
                   escribir.writeObject(cliente);
                }
                 //cierro el objeto escribir y fos
                escribir.close();
                fos.close();

                return true;

            } else {

                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public  void mostrarFichero(File f)  {

        if (f.exists()) {
            try {
                //Para leer  utilizamos el FileInputStream pasandole  como referencia un archivo
                FileInputStream fi = new FileInputStream(f);

                //bucle para leer la información del fichero
                while (fi.available() > 0) {
                    ObjectInputStream client = new ObjectInputStream(fi);
                    Cliente cliente = (Cliente) client.readObject();
                    //Imprimimos el objeto leido en consola
                    System.out.println(cliente.getTelefono() + "  " + cliente.getNombre().toUpperCase());
                }
//Cerramos el objeto fi y client
                fi.close();

            }catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public  boolean ordenarPorNombre(File f) {

        if(f.exists()){
           ArrayList<Cliente> array=new ArrayList<>();
           try {
               FileInputStream fi = new FileInputStream(f);
               ObjectInputStream client=null;
             //bucle para leer la información
               while (fi.available()>0){
                   client = new ObjectInputStream(fi);
                   Cliente cliente = (Cliente) client.readObject();
                   array.add(cliente);
               }
               //Ordeno los nombres por orden alfabético
               array.sort(Comparator.comparing(Cliente::getNombre)
                       .thenComparing(Cliente::getNombre));
               //Cierro los objetos client y fi
               client.close();
               fi.close();

               //Llamo al metodo quitarExtension para obtener el nombre del fichero sin la extensión
               String nombre=quitarExtension(f.getName());
               //Creo un nuevo objeto fichero
               File archivo = new File("res" + File.separator + nombre + ".tmp");
               FileOutputStream fos = new FileOutputStream(archivo);
               DataOutputStream escribir=null;
               //Recorro el array para introducir la informacion ordenada alfabéticamente al fichero
               for(int i=0;i< array.size();i++){
                   escribir= new DataOutputStream(fos);
                   escribir.writeUTF(String.valueOf(array.get(i).getNombre()));
                   escribir.writeLong(array.get(i).getTelefono());
                   escribir.write("\r\n".getBytes());

               }
               //Cierro los objetos
               escribir.close();
               fos.close();

           }catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           } catch (IOException e) {
               throw new RuntimeException(e);
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }

              return true;
        }else{

            return false;
        }


    }


    public  void duplicados (File f1,File f2,String path){

            try{
                //
                FileInputStream fi1 = new FileInputStream(f1);
                FileInputStream fi2 = new FileInputStream(f2);

                ArrayList<Cliente> array=new ArrayList<>();
                while (fi1.available()>0){
                    ObjectInputStream client = new ObjectInputStream(fi1);
                    Cliente cliente = (Cliente) client.readObject();
                    array.add(cliente);
                }

                while (fi2.available()>0){
                    ObjectInputStream client = new ObjectInputStream(fi2);
                    Cliente cliente = (Cliente) client.readObject();
                    array.add(cliente);
                }

                //Elimino los nombres que no aparecen en los dos ficheros y los ordeno alfabéticamente
                ArrayList<Cliente> array2 = (ArrayList<Cliente>) array.stream().distinct().collect(Collectors.toList());
                for (Cliente element : array2) {
                    array.remove(element);
                }
                array.sort(Comparator.comparing(Cliente::getNombre)
                        .thenComparing(Cliente::getNombre));


               File f=new File(path);
               FileOutputStream fos=new FileOutputStream(f);

                DataOutputStream escribir=null;
                //Recorro el array y  escribo  el resultado en el fichero
               for(int i=0;i< array.size();i++){
                   escribir = new DataOutputStream(fos);
                   escribir.writeUTF(array.get(i).getNombre());
                   escribir.write(",".getBytes());
                   escribir.writeUTF(String.valueOf(array.get(i).getTelefono()));
                   escribir.write("\r\n".getBytes());
                }
               escribir.close();
               fos.close();



            }catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

        }


    }

    public static String quitarExtension (String str) {

        String nombre = str.substring(0, str.length()-4);
        return nombre;
    }



}
