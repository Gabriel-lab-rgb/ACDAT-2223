package iesmm.ad.t3_04.dao;

import iesmm.ad.t3_04.model.Libro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<Libro> getAll() throws SQLException, IOException;
    Libro get(int id) throws SQLException, IOException;
    boolean insertar(Libro libro) throws SQLException, IOException;
    boolean actualizar(Libro libro) throws SQLException, IOException;
    boolean eliminar(int id) throws SQLException, IOException;
}
