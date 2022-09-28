package iesmm.ad.t1_01;

import java.io.File;
import java.util.Scanner;

public class PropiedadesFichero {

	public static void main(String args[]) {
		String ruta;

		// Flujo de entrada para solicitar la ruta al usuario por teclado
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Introducir nombre fichero/directorio: ");
			ruta = sc.nextLine();

			// Asociamos el flujo a la fuente origen a partir del path
			File f = new File(ruta);
			seeProperties(f);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void seeProperties(File file) {
		// Comprobar existencia del fichero
		if (file.exists()) {
			// Determinar si es fichero o directorio
			if (file.isFile())
				System.out.println("Es un fichero");
			else if (file.isDirectory())
				System.out.println("Es un directorio");

			// Visualizar propiedades del fichero/directorio
			System.out.println("Nombre: " + file.getName());
			System.out.println("Directorio padre: " + file.getParent());
			System.out.println("Ruta relativa: " + file.getPath());
			System.out.println("Ruta absoluta: " + file.getAbsolutePath());
			System.out.println("Longitud:" + file.length() + " bytes");
		}
		else
			System.out.println("Fichero NO existe");
	}
}
