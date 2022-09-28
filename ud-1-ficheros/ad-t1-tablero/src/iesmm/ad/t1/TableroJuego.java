package iesmm.ad.t1;

import java.io.File;

public class TableroJuego {
	private int nfilas;
	private int ncolumnas;
	private Ficha [][] tablero;

	public TableroJuego(int nfilas,int ncolumnas) {
		this.nfilas = nfilas;
		this.ncolumnas =ncolumnas;
	}

	public int getNfilas() {
		return nfilas;
	}

	public void setNfilas(int nfilas) {
		this.nfilas = nfilas;
	}

	public int getNcolumnas() {
		return ncolumnas;
	}

	public void setNcolumnas(int ncolumnas) {
		this.ncolumnas = ncolumnas;
	}

	public TableroJuego(Ficha[][] tablero) {
		this.tablero = tablero;
	}



	public void setTablero(Ficha[][] tablero) {
		this.tablero = tablero;
	}

	public TableroJuego(File f) {

	}
	
	@Override
	public String toString() {
		return tablero.toString();
	}
}