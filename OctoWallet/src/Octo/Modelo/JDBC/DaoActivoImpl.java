package Octo.Modelo.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Octo.Modelo.DAO.DaoActivo;
import Octo.Modelo.Entidad.Activo;

public abstract class DaoActivoImpl implements DaoActivo {
    
    public abstract void crear(Activo dato);
    protected boolean verificarNomenclatura(String nom){
        return (SQLManager.getInstancia().getStock().obtener(nom) != null);
    }
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
    protected Activo convertir(ResultSet res) throws SQLException {
        Activo activo = new Activo();
        activo.setNomenclatura(res.getString("NOMENCLATURA"));
        activo.setSaldo(res.getDouble("CANTIDAD"));
        return activo;
    }

    

}
