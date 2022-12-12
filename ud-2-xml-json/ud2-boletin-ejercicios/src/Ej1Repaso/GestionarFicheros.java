package Ej1Repaso;



import java.io.*;

public class GestionarFicheros {

    public static void main(String args[]) {
         GestionarSAX MyParser = new GestionarSAX();

        if (MyParser.abrir_XML_SAX(new File("res" + File.separator + "empleados.xml")) == 0) {
            // Si el documento se ha parseado correctamente
            // Mostrar lo procesado por el parser

          File fichero= MyParser.crearFichero();
          GenerarFicherosBytes(fichero);


        }
    }

    public static void GenerarFicherosBytes(File f){

        if(f.exists()){

            if(f.isFile()){
                try {
                   File fichero=new File("res"+ File.separator + "empleados2.dat");
                    BufferedReader b=new BufferedReader(new FileReader(f));
                    String linea="";
                    FileOutputStream fi = new FileOutputStream(fichero);
                    ObjectOutputStream o=new ObjectOutputStream(fi);;
                    while ((linea= b.readLine())!=null){
                        String[] empleados=linea.split(";");
                        o.writeObject(new empleado(empleados[0],empleados[1],empleados[2]));
                    }
                    o.close();
                    b.close();






                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }

    }
}
