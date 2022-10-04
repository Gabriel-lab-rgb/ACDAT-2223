package iesmm.ad.t1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

public class TableroJuego {
	private int nfilas;
	private int ncolumnas;
	private Ficha [][] tablero;

	public TableroJuego(int nfilas,int ncolumnas) {
		this.nfilas = nfilas;
		this.ncolumnas =ncolumnas;
	}

    public TableroJuego(File f) {

        ArrayList<Character> array = new ArrayList();

        Properties props = new Properties();
        try {
            props.load(new FileReader(f));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        nfilas = Integer.parseInt(props.getProperty("rows"));
        ncolumnas = Integer.parseInt(props.getProperty("cols"));

        array.add(props.getProperty("value1").charAt(0));
        array.add(props.getProperty("value2").charAt(0));

        Ficha[][] ficha = new Ficha[nfilas][ncolumnas];
        for (int i = 0; i < nfilas; i++) {
            for (int j = 0; j < ncolumnas; j++) {
                char valor = ValorAleatorio(array);
                /*System.out.println("valor " + valor);*/
                ficha[i][j] = new Ficha(valor);
                this.tablero=ficha;

            }
        }
    }
    //Metodo para obtener un valor aleatorio
        public static Character ValorAleatorio(ArrayList <Character> lista) {

            int valor = (int) (Math.random() * lista.size());
           /* System.out.println("Valor: " + lista.get(valor));*/
            return lista.get(valor);

        }

        //Metodo para mostrar la tabla.
        public void mostrarFichas(){

        for(int i=0;i<this.tablero.length;i++){
            System.out.println("");
            for(int j=0;j<this.tablero[i].length;j++){
            System.out.print(" "+ this.tablero[i][j].toString() +" ");
            }
        }
        }

}