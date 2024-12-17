package Octo.Modelo.JDBC;
import Octo.Modelo.DAO.DaoPersona;
import Octo.Modelo.DAO.DaoUsuario;
import Octo.Modelo.Entidad.Persona;
import Octo.Modelo.Entidad.User;
import Octo.Modelo.Entidad.userResult;

import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUsuarioImpl implements DaoUsuario{
    public List<User> listar(){
        return null;}
    public void crear(User user) {
        long id = -1;
        DaoPersonaImpl daoPersona = new DaoPersonaImpl();
        try {
            id = daoPersona.crear(new Persona(user.getNombres(), user.getApellidos()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (id != -1) {
            try {
                Statement st = Conexion.getConexion().createStatement();
                String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD,ACEPTA_TERMINOS)" + "VALUES('" + id + "', '" + user.getEmail() + "', '" + user.getContrasena() + "', '" + user.isAceptaTerminos() +"');";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
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
            throw new RuntimeException(e);
        }
        return res;
    }
    private userResult convertir(java.sql.ResultSet rs) throws SQLException {
        DaoPersona con = SQLManager.getInstancia().getPersona();
        Persona per = con.obtener(rs.getLong("ID_PERSONA"));
        String email = rs.getString("EMAIL");
        String contrasena = rs.getString("PASSWORD");
        boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
        return (new userResult(new User(per.getNombres(), email, contrasena, per.getApellidos(), aceptaTerminos), rs.getLong("ID"),rs.getLong("ID_PERSONA")));
    }

    public userResult obtener(String email, String contrasena) {
        userResult usuario = null;
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
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
