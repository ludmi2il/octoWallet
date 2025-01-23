package Octo.Modelo.JDBC;

import Octo.Exceptions.OctoNotFound;
import Octo.Modelo.Entidad.Activo;

import java.sql.*;

public class DaoActivoCrypto extends DaoActivoImpl{
    @Override
    public long crear(Activo dato){
        long id = -1;
        if(verificarNomenclatura(dato.getMoneda().getNomenclatura())) {
            try {
                Statement st = Conexion.getConexion().createStatement();
                st.executeUpdate(sql);
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getLong(1);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else System.out.println("La nomenclatura no existe.");
        return id;
    }
    @Override
    public Activo obtener(String nomenclatura){
        Activo activo = null;
        try {
            String str = "SELECT * FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
            PreparedStatement st = Conexion.getConexion().prepareStatement(str);
            st.setString(1,nomenclatura);
            ResultSet res = st.executeQuery();
            if (res.next()){
                activo = convertir(res);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return activo;
    }
    public int actualizar(double valor, String nomenclatura ){
        int res = -1;
        try{
            String sql ="UPDATE ACTIVO_CRIPTO SET CANTIDAD = CANTIDAD + ? WHERE NOMENCLATURA = ?";

            PreparedStatement st = Conexion.getConexion().prepareStatement(sql);
            st.setDouble(1,valor);
            st.setString(2,nomenclatura);
            res = st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new OctoNotFound("error! no se encontró el activo CRYPTO con nomenclatura: " + nomenclatura);
        }
        return res;
    }

}
