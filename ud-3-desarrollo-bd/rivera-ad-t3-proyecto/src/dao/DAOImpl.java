package dao;

import model.Equipos;
import model.Incidencia;
import model.Informe;
import utils.Conexion;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO{
    Connection c;
    @Override
    public boolean insertarIncidencia(Incidencia incidencia) {
        boolean valueReturn = false;

        // implementar código
        String sql = "INSERT INTO incidencias VALUES(?, ?, ?, ?, ?)";

        try {
            c.setAutoCommit(false);
            c = Conexion.getConnection();
            PreparedStatement sqlStatement = c.prepareStatement(sql);
            sqlStatement.setString(1, incidencia.getNumIncidencia());
            sqlStatement.setString(2, incidencia.getCodpartido());
            sqlStatement.setString(3, incidencia.getCodjugador());
            sqlStatement.setInt(4, incidencia.getMinuto());
            sqlStatement.setString(5, incidencia.getTipo());

            if (sqlStatement.executeUpdate() > 0) {
                valueReturn = true;
            }

            c.commit();
            c.setAutoCommit(true);
            Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valueReturn;
    }

    @Override
    public boolean insertarEquipos(File f) {
        boolean valueReturn = false;
        String sql = "INSERT INTO equipo VALUES (?, ?, ?)";
        PreparedStatement sqlStatement;
        if(f.exists()){
            try {
                c = Conexion.getConnection();
                c.setAutoCommit(false);
                BufferedReader buffer=new BufferedReader(new FileReader(f));
                String linea="";
                while((linea= buffer.readLine())!=null){
                    String[] lista=linea.split(",");
                    sqlStatement = c.prepareStatement(sql);
                    sqlStatement.setString(1, lista[0]);
                    sqlStatement.setString(2, lista[1]);
                    sqlStatement.setString(3, lista[2]);

                    if (sqlStatement.executeUpdate() > 0) {
                        valueReturn = true;
                    }

                }
                c.commit();
                c.setAutoCommit(true);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                try{
                    c.rollback();
                    System.out.println("Error encontrado, rollback realizado");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else {
            System.out.println("No existe el fichero");
        }

        return valueReturn;
    }

    @Override
    public List<Informe> listarInforme() {
        List<Informe> listaInformeEquipo = new ArrayList<Informe>();


        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);

            CallableStatement callableStatement = c.prepareCall("{call informe()}");
            boolean result = callableStatement.execute();
            if (result) {
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    String cod=rs.getString(1);
                    String nombre=rs.getString(2);
                    int p_jugados=rs.getInt(3);
                    int p_ganados=rs.getInt(4);
                    int p_empatados=rs.getInt(5);
                    int p_perdidos=rs.getInt(6);
                    int g_favor=rs.getInt(7);
                    int g_contra=rs.getInt(8);
                    int puntos=rs.getInt(9);
                    Informe equipo=new Informe(cod,nombre,p_jugados,p_ganados,p_empatados,p_perdidos,g_favor,g_contra,puntos);
                    listaInformeEquipo.add(equipo);
                }
            }
            c.commit();
            c.setAutoCommit(true);
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listaInformeEquipo;

    }


    @Override
    public List<Incidencia> listarIncidencias() {
        List<Incidencia> listaIncidencias = new ArrayList<Incidencia>();

        // implementar código
        String sql = "SELECT * FROM incidencias";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);
            Statement sqlStatement = c.createStatement();

            ResultSet set = sqlStatement.executeQuery(sql);

            while (set.next()) {
                //1. Obtener todos los campos necesarios para crear el objeto Incidencia
                String numeroIncidencias = set.getString("NumIncidencia");
                String codPartido = set.getString("CodPartido");
                String codJugador = set.getString("CodJugador");
                int minuto = set.getInt("Minuto");
                String tipo = set.getString("Tipo");


                //2. Crear el objeto articulo
                Incidencia incidencia = new Incidencia(numeroIncidencias, codPartido, codJugador, minuto, tipo);
                listaIncidencias.add(incidencia);
            }

            c.commit();
            c.setAutoCommit(true);
            //Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaIncidencias;
    }

    @Override
    public Incidencia listarIncidenciasPorCodigo(String nIncidencia) {
        Incidencia incidencia = null;

        // implementar código
        String sql = "SELECT * FROM incidencias WHERE NumIncidencia=?";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);
            PreparedStatement sqlStatement = c.prepareStatement(sql);
            sqlStatement.setString(1, nIncidencia);


            ResultSet set = sqlStatement.executeQuery();
            while (set.next()) {
                //1. Obtener todos los campos necesarios para crear el objeto Articulo
                String codPartido = set.getString("CodPartido");
                String codJugador = set.getString("CodJugador");
                int minuto = set.getInt("Minuto");
                String tipo = set.getString("Tipo");

                //2. Crear el objeto articulo
                incidencia = new Incidencia(nIncidencia, codPartido, codJugador, minuto, tipo);
            }

            c.commit();
            c.setAutoCommit(true);
            //Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return incidencia;
    }

    @Override
    public List<Equipos> listarEquipos() {
        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        // implementar código
        String sql = "SELECT * FROM equipo";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);
            Statement sqlStatement = c.createStatement();

            ResultSet set = sqlStatement.executeQuery(sql);

            while (set.next()) {
                //1. Obtener todos los campos necesarios para crear el objeto Incidencia
                String codEquipo = set.getString("CodEquipo");
                String nombre = set.getString("Nombre");
                String localidad = set.getString("Localidad");

                //2. Crear el objeto articulo
                Equipos equipo = new Equipos(codEquipo, nombre, localidad);
                listaEquipos.add(equipo);
            }
            c.commit();
            c.setAutoCommit(true);
            //Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaEquipos;
    }

    @Override
    public boolean modificar(Incidencia incidencia) {
        boolean valueReturn = false;

        // implementar código
        String sql = "UPDATE incidencias SET CodPartido = ?, CodJugador = ?, Minuto = ?, Tipo = ? WHERE NumIncidencia = ?";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);

            PreparedStatement sqlStatement = c.prepareStatement(sql);
            sqlStatement.setString(1, incidencia.getCodpartido());
            sqlStatement.setString(2, incidencia.getCodjugador());
            sqlStatement.setInt(3, incidencia.getMinuto());
            sqlStatement.setString(4, incidencia.getTipo());
            sqlStatement.setString(5,incidencia.getNumIncidencia());

            if (sqlStatement.executeUpdate() > 0) {
                valueReturn = true;
            }

            c.commit();
            c.setAutoCommit(true);
            //Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valueReturn;
    }

    @Override
    public boolean eliminar(String nIncidencia) {
        boolean valueReturn = false;

        // implementar código
        String sql = "DELETE FROM incidencias WHERE NumIncidencia = ?";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);
            PreparedStatement sqlStatement = c.prepareStatement(sql);

            sqlStatement.setString(1, nIncidencia);

            if (sqlStatement.executeUpdate() > 0) {
                valueReturn = true;
            }

            c.commit();
            c.setAutoCommit(true);

            //Conexion.close();
        } catch (SQLException e) {
            try{
                c.rollback();
                System.out.println("Error encontrado, rollback realizado");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valueReturn;
    }
}
