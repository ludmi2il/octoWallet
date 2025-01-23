package Octo.Modelo.JDBC;

import Octo.Modelo.DAO.DaoPersona;
import Octo.Modelo.Entidad.Persona;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoPersonaImpl implements DaoPersona {
    private Persona convertir(java.sql.ResultSet rs, long id) throws SQLException {
        String nombres = rs.getString("NOMBRES");
        String apellidos = rs.getString("APELLIDOS");
        return new Persona(nombres, apellidos, id);
    }

    public List<Persona> listar() {
        return null;
    }

    public long crear(String nombre, String apellido) throws SQLException {
        long id = -1;
        String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS)VALUES(?, ?);";
        try (
                PreparedStatement statement = Conexion.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return id;
        }
    }
    public Persona obtener(long id) {
        Persona persona = null;
        try {
            String str = "SELECT * FROM PERSONA WHERE ID = ?";
            java.sql.PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setLong(1,id);
            java.sql.ResultSet res = st.executeQuery();
            if (res.next()){
                persona = convertir(res, id);
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persona;
    }
}
