package Octo.Modelo.DAO;

import Octo.Modelo.Entidad.Moneda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// acá me encargo de darle forma a la conexion pasando los objetos a la bbdd
public class DaoMonedaImpl implements DaoMoneda{
    @Override
    public void crear(Moneda dato) {
        try {
            Statement st = Conexion.getConexion().createStatement();
            String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK)" +
                        "VALUES('" + dato.getTipo() + "', '"+ dato.getNombre() + "', '"+ dato.getNomenclatura() + "', '"
                        + dato.getCotizacion() + "', '" + dato.getVolatilidad()+ "', '" + dato.getStock() + "');";
                // se puede usar sets de Statement y los campos para evitar errores de tipeo.
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
        }
    }

    @Override
    public List<Moneda> listar() {
        List<Moneda> monedas = new ArrayList<>(); Moneda moneda;
        try {
            Statement st= Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM MONEDA");
            while( res.next()) {
                moneda = new Moneda();
                moneda.setTipo(res.getString("TIPO"));
                moneda.setNombre(res.getString("NOMBRE"));
                moneda.setNomenclatura(res.getString("NOMENCLATURA"));
                moneda.setCotizacion(res.getDouble("VALOR_DOLAR"));
                moneda.setVolatilidad(res.getDouble("VOLATILIDAD"));
                moneda.setStock(res.getDouble("STOCK"));
                monedas.add(moneda);
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return monedas;
    }
}
