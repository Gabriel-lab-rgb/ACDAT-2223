package iesmm.ad.t1_02;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeeFicheroCaracteres {

	public static void main(String[] args) {

		try {
			// Asociamos el flujo a la fuente origen a partir del path
			// Declaración del fichero a partir del identificador o variable f
			File f = new File("res" + File.separator + "datos1.txt");

			// Existencia y procesamiento del fichero
			if (f.exists()) {
				int pos;
				FileReader fichero = new FileReader(f);

				// Lectura del primer carácter
				pos = fichero.read();

				// Recorrido del fichero
				while (pos != -1) {
					System.out.print((char) pos);
					pos = fichero.read();
				}

				// Cierre del fichero
				fichero.close();

				// Recorrido del fichero: código optimizado
				// while ((pos = fichero.read()) != -1)
				// System.out.print((char) pos);
			}
			else
				System.out.println("El fichero " + f.getName() + " no existe");
		} catch (IOException e) {
			System.err.println("Error al acceder al fichero");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
