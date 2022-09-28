package iesmm.ad.t1_02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribeSobreFicheroCaracteres {
	public static void main(String[] args) {
		String texto = "<Libros><Libro><Titulo>El Capote</Titulo></Libro></Libros>";

		try {
			// Se crea un nuevo objeto FileWriter (modo añadir al final)
			FileWriter fichero = new FileWriter("res" + File.separator + "libros.xml", true);

			// Se escribe el texto en fichero
			fichero.write(texto + "\n");

			// Se cierra el fichero
			fichero.close();

		} catch (IOException e) {
			System.err.println("Error al acceder al fichero");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
