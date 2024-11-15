package Octo.Modelo.JDBC;
import Octo.Exceptions.OctoNotFound;
import Octo.Modelo.Entidad.Activo;

import java.sql.*;

public class DaoActivoFiat extends DaoActivoImpl{
    @Override
    public void crear(Activo dato){
        if(verificarNomenclatura(dato.getNomenclatura())) {
            try {
                Statement st = Conexion.getConexion().createStatement();
                String sql = "INSERT INTO ACTIVO_FIAT (NOMENCLATURA, CANTIDAD)" + "VALUES('" + dato.getNomenclatura() + "', '" + dato.getSaldo() + "');";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else System.out.println("La nomenclatura no existe.");
    }
    public int actualizar(double valor, String nomenclatura ){
        int res = -1;
        try{
            String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = CANTIDAD + ? WHERE NOMENCLATURA = ?";
            PreparedStatement st = Conexion.getConexion().prepareStatement(sql);
            st.setDouble(1,valor);
            st.setString(2,nomenclatura);
            res = st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    @Override
    public Activo obtener(String nomenclatura){
        Activo activo = null;
        try {
            String str = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
            PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setString(1,nomenclatura);
            ResultSet res = st.executeQuery();
            if (res.next()){
                activo = convertir(res);
            }
        } catch (SQLException e) {
            throw new OctoNotFound("error! no se encontró el activo con nomenclatura: " + nomenclatura);
        }
        return activo;
    }

}