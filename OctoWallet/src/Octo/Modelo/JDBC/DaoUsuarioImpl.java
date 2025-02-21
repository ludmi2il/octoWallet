package Octo.Modelo.JDBC;
import Octo.Exceptions.OctoDBException;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Modelo.DAO.DaoPersona;
import Octo.Modelo.DAO.DaoUsuario;
import Octo.Modelo.Entidad.Persona;
import Octo.Modelo.Entidad.User;

import java.sql.SQLException;
import java.sql.Statement;

public class DaoUsuarioImpl implements DaoUsuario{
    public long crear(User user) throws OctoDBException {
        long id = -1;
        DaoPersonaImpl daoPersona = new DaoPersonaImpl();
        try {
            id = daoPersona.crear(new Persona(user.getNombres(), user.getApellidos()));
        } catch (Exception e) {
            throw new OctoDBException(e.getMessage());
        }
        if (id != -1) {
            try {
                Statement st = Conexion.getConexion().createStatement();
                String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD,ACEPTA_TERMINOS)" + "VALUES('" + id + "', '" + user.getEmail() + "', '" + user.getContrasena() + "', '" + user.isAceptaTerminos() +"');";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
                throw new OctoDBException("Algo inesperado sucedió con la conexion a la base de datos. Intente más tarde");
            }
        }
        return id;
    }

    @Override
    public User obtener(long id) throws OctoElemNotFoundException {
        User usuario = null;
        try {
            String str = "SELECT * FROM PERSONA WHERE ID = ?";
            java.sql.PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setLong(1,id);
            java.sql.ResultSet res = st.executeQuery();
            if (res.next()){
                usuario = convertir(res);
            }
            st.close();
        } catch (SQLException e) {
            throw new OctoElemNotFoundException(e.getMessage());
        }
        return usuario;
    }
    @Override
    public boolean verificarMail(String mail) { // Devuelve true si no existe el mail en la BD, si no false
        boolean res = false;
        try {
            String str = "SELECT * FROM USUARIO WHERE EMAIL = ?";
            java.sql.PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setString(1,mail);
            java.sql.ResultSet rs = st.executeQuery();
            if (!rs.next()){
                res = true;
            }
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    private User convertir(java.sql.ResultSet rs) throws OctoElemNotFoundException{
        DaoPersona con = FactoryDao.getPersona();
        Persona per = null;
        try {
            per = con.obtener(rs.getLong("ID_PERSONA"));
            String email = rs.getString("EMAIL");
            String contrasena = rs.getString("PASSWORD");
            boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
            return new User(per.getNombres(), email, contrasena, per.getApellidos(), aceptaTerminos, rs.getLong("ID"));
        } catch (SQLException e) {
            throw new OctoElemNotFoundException("Error al obtener la persona del usuario");
        }

    }

    public User obtenerPorMail(String email, String contrasena) {
        User usuario = null;
        try {
            String str = "SELECT * FROM USUARIO WHERE EMAIL = ? AND PASSWORD = ?";
            java.sql.PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setString(1,email);
            st.setString(2,contrasena);
            java.sql.ResultSet res = st.executeQuery();
            if (res.next()){
                usuario = convertir(res);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (OctoElemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }
}
