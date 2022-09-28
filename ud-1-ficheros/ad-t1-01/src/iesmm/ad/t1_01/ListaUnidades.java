package iesmm.ad.t1_01;

import java.io.File;

public class ListaUnidades {
	public static void main(String args[]) {
		// Generamos un listado de los volúmenes existentes desde el origen
		// (método estático)
		File[] listaVolumenes = File.listRoots();

		// Visualizamos que volúmenes de información son los encontrados
		for (int i = 0; i < listaVolumenes.length; i++)
			System.out.print(listaVolumenes[i] + " ");
	}
}
