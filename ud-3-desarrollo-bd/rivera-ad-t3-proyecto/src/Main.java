import dao.DAO;
import dao.DAOImpl;
import model.Equipos;
import model.Incidencia;
import model.Informe;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sn = new Scanner(System.in);
        String nIncidencia;
        String codPartido;
        String codJugador;
        Incidencia incidenciaEncontrada; //Objeto incidencia para encontrar por codigo
        List<Incidencia> incidencia;
        int minuto;
        String tipo;
        boolean valueReturn = false; // Valor de retorno en cada modificación del modelo

        DAO dao = new DAOImpl();

        // MENÚ DE CONSULTAS
        int op = 0;
        do {
            System.out.println("============================");
            System.out.println("1. Insertar incidencia");
            System.out.println("2. Modificar incidencia");
            System.out.println("3. Eliminar incidencia");
            System.out.println("4. Mostrar informe liga");
            System.out.println("5. Importar equipos");
            System.out.println("6. SALIR");
            System.out.println("Introducir opción (1-6): ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    //Creamos la lista de Inicdencias
                    incidencia = dao.listarIncidencias();
                    System.out.println("Incidencias encontradas");
                    System.out.println("===========================");

                    //Recorremos dicha lista y la mostramos
                    for (Incidencia a : incidencia){
                        System.out.println(a);
                    }

                    //Pedimos las propiedades de las incidencias a insertar
                    System.out.println("===========================");
                    System.out.println("Numero de incidencia: ");
                    nIncidencia = sn.nextLine();
                    System.out.println("Partido (codigo): ");
                    codPartido = sn.nextLine();
                    System.out.println("Jugador (codigo): ");
                    codJugador = sn.nextLine();
                    System.out.println("Minuto: ");
                    minuto = sc.nextInt();
                    System.out.println("Tipo de incidencia: ");
                    tipo = sn.nextLine();

                    //Retornamos el valor que sera true si se ejecuta la consulta y false si no se ejecuta
                    valueReturn = dao.insertarIncidencia(new Incidencia(nIncidencia, codPartido, codJugador, minuto, tipo));

                    //Si el valor es true salta el mensaje correcto
                    if (valueReturn)
                        System.out.println("Incidencia "+nIncidencia+" insertada");
                    break;

                case 2:
                    //Creamos la lista de Inicdencias
                    incidencia = dao.listarIncidencias();
                    System.out.println("Incidencias encontradas");
                    System.out.println("===========================");

                    //Recorremos dicha lista y la mostramos
                    for (Incidencia a : incidencia){
                        System.out.println(a);
                    }

                    //Inicidencia a modificar
                    System.out.println("===========================");
                    System.out.println("Numero de incidencia: ");
                    nIncidencia = sn.nextLine();

                    incidenciaEncontrada = dao.listarIncidenciasPorCodigo(nIncidencia);
                    System.out.println("============================");

                    if (incidenciaEncontrada==null){
                        System.out.println("No existen ninguna incidencia con numero: "+nIncidencia);
                    }else{
                        System.out.println("Incidencia con codigo: "+nIncidencia+" encontrada");
                        System.out.println(incidenciaEncontrada);

                        //Pedimos las nuevas propiedades de la incidencia
                        System.out.println("Partido (codigo): ");
                        codPartido = sn.nextLine();
                        System.out.println("Jugador (codigo): ");
                        codJugador = sn.nextLine();
                        System.out.println("Minuto: ");
                        minuto = sc.nextInt();
                        System.out.println("Tipo de incidencia: ");
                        tipo = sn.nextLine();

                        if (dao.modificar(new Incidencia(nIncidencia, codPartido, codJugador, minuto, tipo)))
                            System.out.println("Incidencia actualizada");
                    }
                    break;

                case 3:
                    //Creamos la lista de Inicdencias
                    incidencia = dao.listarIncidencias();
                    System.out.println("Incidencias encontradas");
                    System.out.println("===========================");

                    //Recorremos dicha lista y la mostramos
                    for (Incidencia a : incidencia){
                        System.out.println(a);
                    }

                    //Inicidencia a eliminar
                    System.out.println("===========================");
                    System.out.println("Numero de incidencia: ");
                    nIncidencia = sn.nextLine();

                    incidenciaEncontrada = dao.listarIncidenciasPorCodigo(nIncidencia);


                    if (incidenciaEncontrada==null){
                        System.out.println("No existen ninguna incidencia con numero: "+nIncidencia);
                    }else{
                        System.out.println("Incidencia con codigo: "+nIncidencia);
                        System.out.println(incidenciaEncontrada);
                    }

                    //Retornamos el valor que sera true si se ejecuta la consulta y false si no se ejecuta
                    valueReturn = dao.eliminar(nIncidencia);

                    if (valueReturn)
                        System.out.println("Producto eliminado con codigo: "+nIncidencia);
                    break;

                case 4:
                    //Creamos el listado de informe de equipos
                    List<Informe> InformeEquipos = dao.listarInforme();

                    //Recorremos dicho listado y lo mostramos
                    for (Informe i : InformeEquipos)
                        System.out.println(" Equipo: "+ i.getNombre() +
                                " PJ: " + i.getPartidos_jugados() +
                                " PG: " + i.getPartidos_ganados() +
                                " PE: "+ i.getPartidos_empatados() +
                                " PP: " + i.getPartidos_perdidos() +
                                " GF: " + i.getGoles_favor() +
                                " GC: " + i.getGoles_contra() +
                                " Puntos: " + i.getPuntos());

                    break;

                case 5:
                    //Pedimos la ruta del fichero a importar
                    System.out.println("Introduce la ruta de un fichero para importar los datos: ");
                    String fichero = sn.nextLine();

                    //Retornamos el valor que sera true si se ejecuta la consulta y false si no se ejecuta
                    valueReturn = dao.insertarEquipos(new File(fichero));

                   if(valueReturn)
                       System.out.println("Equipos insertados corractamente");
                   else
                       System.out.println("No se ha podido insertar los equipos");

                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción errónea");
            }
        } while (op != 6);
    }

}