package Octo.Vista;

import Octo.Controlador.DataController;
import Octo.Modelo.JDBC.Conexion;
import Octo.Modelo.JDBC.SQLManager;
import Octo.Servicios.CotizacionesRequest;
import Octo.Servicios.DataRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class testCoti {
    public static void eliminar(String solana){
        String eliminar = "DELETE FROM MONEDA WHERE nombre = ?";
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(eliminar);
            ps.setString(1, solana);

            if (ps.executeUpdate()>0)
                System.out.println("Moneda eliminada");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void main(String[] args) {
        DataController controlador = new DataController();
        controlador.cargarCache();
        System.out.println(controlador.getCacheMonedas());
        controlador.criptosOrdenadas();
        System.out.println(controlador.getCacheMonedas());
        eliminar("cardano");

    }
}
