package Octo.Modelo.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Octo.Modelo.DAO.DaoActivo;
import Octo.Modelo.Entidad.Activo;

public abstract class DaoActivoImpl implements DaoActivo {
    public abstract int actualizar(double valor, long idUser, long idMoneda);
    public abstract Activo obtener(long id, long idMoneda);
    public abstract long crear(Activo dato);

    @Override
    @Deprecated
    public List<Activo> listar() {
        List<Activo> activos = new ArrayList<Activo>(); // lista de activos
        try {
            Statement st= Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM ACTIVO_CRIPTO");
            while( res.next()) {
                activos.add(convertir(res));
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            Statement st= Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM ACTIVO_FIAT");
            while( res.next()) {
                activos.add(convertir(res));
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return activos;

    }
    public List<Activo> listarFiat(long id) {
        List<Activo> activos = new ArrayList<Activo>(); // lista de activos
        try {
            Statement st = Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM ACTIVO_FIAT WHERE ID_USUARIO = " + id);
            while (res.next()) {
                activos.add(convertir(res));
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return activos;
    }
    public List<Activo> listarCrypto(long id) {
        List<Activo> activos = new ArrayList<Activo>(); // lista de activos
        try {
            Statement st = Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM ACTIVO_CRIPTO WHERE ID_USUARIO = " + id);
            while (res.next()) {
                activos.add(convertir(res));
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return activos;
    }
    public List<Activo> listar(long id){
        List<Activo> activos = new ArrayList<Activo>(); // lista de activos
        activos.addAll(listarCrypto(id));
        activos.addAll(listarFiat(id));
        return activos;
    }
    public void borrado(long id) {
        Activo activo = null;
        try {
            String str = "DELETE FROM ACTIVO_CRIPTO WHERE ID_USUARIO = ?";
                PreparedStatement st = Conexion.getConexion().prepareStatement(str);
                st.setLong(1,id);
                int res = st.executeUpdate();
            } catch (SQLException e) {
            System.out.println("no se pudo borrar los activos!");
            }
        try {
            String str = "DELETE FROM ACTIVO_FIAT WHERE ID_USUARIO = ?";
            PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setLong(1,id);
            int res = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("no se pudo borrar los activos!");
        }
    }

    protected Activo convertir(ResultSet res) throws SQLException {
        Activo activo = new Activo();
        activo.setId(res.getLong("ID"));
        activo.setMoneda(SQLManager.getInstancia().getMoneda().obtener(res.getLong("ID_MONEDA")));
        activo.setSaldo(res.getDouble("CANTIDAD"));
        return activo;
    }
    public abstract void borrar(long id, long idMoneda);


}
