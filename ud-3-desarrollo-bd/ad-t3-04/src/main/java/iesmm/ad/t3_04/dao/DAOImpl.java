package iesmm.ad.t3_04.dao;

import iesmm.ad.t3_04.model.Libro;
import iesmm.ad.t3_04.utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {
    @Override
    public List<Libro> getAll() throws SQLException, IOException {
        Connection c;
        ArrayList<Libro> lista = new ArrayList<Libro>();

        c = Conexion.getConnection();
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);
        ResultSet rs = statement.executeQuery("select * from libro");
        while (rs.next()) {

            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            int anio = rs.getInt("anio");
            int paginas = rs.getInt("paginas");

            lista.add(new Libro(id, titulo, autor, anio, paginas));
        }
        return lista;
    }

    @Override
    public Libro get(int id) throws SQLException, IOException {
        Connection c;
        Libro libro = null;

        c = Conexion.getConnection();
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);
        ResultSet rs = statement.executeQuery("select * from libro where id=" + id);
        if (rs.next()) {

            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            int anio = rs.getInt("anio");
            int paginas = rs.getInt("paginas");

            libro = new Libro(id, titulo, autor, anio, paginas);
        }
        return libro;

    }

    @Override
    public boolean insertar(Libro libro) throws SQLException, IOException {

        Connection c;

        c = Conexion.getConnection();
        PreparedStatement preparedStatement = c.prepareStatement("insert into libro (id,titulo,autor,anio,paginas) values (?,?,?,?,?)");
        preparedStatement.setInt(1, libro.getId());
        preparedStatement.setString(2, libro.getTitulo());
        preparedStatement.setString(3, libro.getAutor());
        preparedStatement.setInt(4, libro.getAnio());
        preparedStatement.setInt(5, libro.getPaginas());
        preparedStatement.execute();
        if (preparedStatement.executeUpdate()>0) {
            System.out.println("Libro añadido con exito");
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean actualizar(Libro libro) throws SQLException, IOException {

        Connection c;

        c = Conexion.getConnection();
        PreparedStatement preparedStatement = c.prepareStatement("UPDATE libro SET titulo=?, autor=?, anio=?, paginas=? WHERE id=" +libro.getId());
        preparedStatement.setString(1, libro.getTitulo());
        preparedStatement.setString(2, libro.getAutor());
        preparedStatement.setInt(3, libro.getAnio());
        preparedStatement.setInt(4, libro.getPaginas());
        preparedStatement.execute();
        if (preparedStatement.getUpdateCount() > 0) {
            System.out.println("Registro actualizado");
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean eliminar(int id) throws SQLException, IOException {

        Connection c;

        c = Conexion.getConnection();
        String sql = "delete from libro where id=" + id;
        Statement statement = c.createStatement();
        statement.execute(sql);
        if (statement.getUpdateCount() > 0) {
            System.out.println("Registro eliminado");
            return true;
        } else {
            return false;
        }

    }
}
