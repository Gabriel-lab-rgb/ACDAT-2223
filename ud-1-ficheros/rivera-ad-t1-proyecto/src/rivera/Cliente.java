package rivera;

import java.io.Serializable;

public class Cliente implements Serializable {
	private String nombre;
	private long telefono;

	public Cliente(String nombre, long telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Cliente cliente = (Cliente) o;

		return nombre.equals(cliente.nombre);
	}

	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
}

