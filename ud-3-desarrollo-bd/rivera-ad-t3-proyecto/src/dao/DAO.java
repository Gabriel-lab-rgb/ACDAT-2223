package dao;

import model.Equipos;
import model.Incidencia;
import model.Informe;

import java.io.File;
import java.util.List;

public interface DAO {
    boolean insertarIncidencia(Incidencia incidencia);
    boolean insertarEquipos(File f);
    List<Informe> listarInforme();
    List<Incidencia> listarIncidencias();
    Incidencia listarIncidenciasPorCodigo(String nIncidencia);
    List<Equipos> listarEquipos();
    boolean modificar(Incidencia incidencia);
    boolean eliminar(String nIncidencia);
}
