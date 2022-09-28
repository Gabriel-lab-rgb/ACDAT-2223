package iesmm.ad.t1_01;

import java.io.File;

public class ListaFicheros {
	public static void main(String args[]) {
		// Asociamos el flujo a la fuente del canal a partir de un path concreto
		File canal = new File("C:" + File.separator);

		// Generamos un listado de archivos o directorios en función del origen
		File[] lista = canal.listFiles();

		// Visualizamos que ficheros son los encontrados desde ese origen
		for (int i = 0; i < lista.length; i++)
			System.out.println(lista[i]);

		// Otra posibilidad: bucle for-extendido (sin variable lista)
		// for (File f : canal.listFiles())
		// System.out.println(f);
	}
}
