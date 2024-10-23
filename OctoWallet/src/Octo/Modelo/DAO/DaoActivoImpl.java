package Octo.Modelo.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;

public abstract class DaoActivoImpl implements DaoActivo{
    
    public abstract void crear(Activo dato);
    protected boolean verificarNomenclatura(String nom){
        return (FactorySQLManager.getInstancia().getStock().obtener(nom) != null);
    }

    public abstract Activo obtener(String id);
    @Override
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
    protected Activo convertir(ResultSet res) throws SQLException {
        Activo activo = new Activo();
        activo.setNomenclatura(res.getString("NOMENCATURA"));
        activo.setSaldo(res.getDouble("CANTIDAD"));
        return activo;
    }

    

}
