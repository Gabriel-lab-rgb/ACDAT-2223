package iesmm.ad.t1_01;

import java.io.File;
import java.util.Scanner;

public class ListaFicherosExtension {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Introducir nombre de directorio: ");
			String ruta = sc.nextLine();

			// Asociamos el flujo a la fuente origen a partir del path
			File dir = new File(ruta);

			if (dir.isDirectory()) {
				// Visualizamos que ficheros son los encontrados desde ese origen
				for (File f: dir.listFiles())
					if (f.isFile() && f.toString().endsWith(".txt"))
						System.out.println(f);
			}
			else
				System.out.println("No es directorio");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}