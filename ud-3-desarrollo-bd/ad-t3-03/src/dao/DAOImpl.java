package dao;

import model.Articulo;
import utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOImpl implements DAO {
    private static final Logger logTag = Logger.getLogger("AD");

    @Override
    public boolean insertar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;

        // implementar código

        return valueReturn;
    }

    @Override
    public List<Articulo> listarArticulos() {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();

        // implementar código

        return listaArticulos;
    }

    @Override
    public List<Articulo> listarArticulosPorCategoria(String categoria) {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();
        String sql = "{call articulos_categoria(?)}";

        try {
            c = Conexion.getConnection();
            CallableStatement callableStatement = c.prepareCall(sql);
            callableStatement.setString(1, categoria);
            boolean result = callableStatement.execute(); // Ejecutar sentencia de llamada a procedimiento almacenado

            // Obtener resultados a mostrar
            if (result) {
                ResultSet resulSet = callableStatement.getResultSet();

                while (resulSet.next()) {
                    // Consultar campos
                    int id = resulSet.getInt("id");
                    String codigo = resulSet.getString("codigo");
                    String nombre = resulSet.getString("nombre");
                    String descripcion = resulSet.getString("descripcion");
                    Double existencia = resulSet.getDouble("existencia");
                    Double precio = resulSet.getDouble("precio");
                    Integer categoria_articulo = resulSet.getInt("idcategoria");

                    // Crear objeto Articulo
                    Articulo articulo = new Articulo(id, codigo, nombre, descripcion, existencia, precio, categoria_articulo);

                    // Insertar objeto en colección
                    listaArticulos.add(articulo);
                }
            }

            Conexion.close();
        } catch (SQLException e) {
            logTag.severe("Error al consultar: ");
            showSQLErrors(e);
        } catch (Exception e) {
            logTag.severe(e.getMessage());
        }

        return listaArticulos;
    }

    @Override
    public Articulo obtenerPorId(int id) {
        Connection c;
        Articulo articulo = null;
        boolean encontrado = false;

        try {
            c = Conexion.getConnection();
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from articulo WHERE id=" + id);


            if (rs.next()) {

                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Double existencia = rs.getDouble("existencia");
                Double precio = rs.getDouble("precio");
                Integer categoria_articulo = rs.getInt("idcategoria");

                articulo = new Articulo(id, codigo, nombre, descripcion, existencia, precio, categoria_articulo);
                Conexion.close();
                return articulo;
            } else {
                Conexion.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean actualizar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;


        return valueReturn;
    }

    @Override
    public boolean eliminar(int id) {
        Connection c;
        boolean valueReturn = false;

        // implementar código

        return valueReturn;
    }

    /**
     * Muestra los errores y excepciones producidas en la operación en la base de datos.
     *
     * @param e Excepción
     */
    private static void showSQLErrors(SQLException e) {
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }
}