package Octo.Modelo.JDBC;

import Octo.Controlador.Sesion;
import Octo.Modelo.DAO.DaoTransaccion;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.Transaccion;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DaoTransaccionImpl implements DaoTransaccion {
    private List<Transaccion> transacciones = new ArrayList<>();
    @Override
    public void comprarCriptoMonedas(long cripto, long fiat, double cantidad) {
        long userId = Sesion.getInstance().getUserResult().getUserId();
        SQLManager factory = SQLManager.getInstancia();
        Moneda monFiat = factory.getMoneda().obtener(fiat);
        Moneda monCripto = factory.getMoneda().obtener(cripto);
        double valorAGastar = monFiat.getCotizacion() * cantidad;
        double cantAComprar = valorAGastar / monCripto.getCotizacion();
        Activo actiFiat = factory.getFiat().obtener(fiat,userId);
        try {
            Conexion.getConexion().setAutoCommit(false);
            if (valorAGastar < actiFiat.getSaldo()) {
                Activo actiCripto = factory.getCrypto().obtener(cripto,userId);
                if (actiCripto == null) {
                    factory.getCrypto().crear(new Activo(0, monCripto, cantAComprar));
                } else {
                    factory.getCrypto().actualizar(cantAComprar, Sesion.getInstance().getUserResult().getUserId(), monCripto.getIdM());
                }
                factory.getFiat().actualizar((-1) * cantidad, Sesion.getInstance().getUserResult().getUserId(), fiat);
                Transaccion transaccion = new Transaccion();
                transaccion.setResumen("se compraron " + cantAComprar + " criptomonedas " + monCripto.getNomenclatura() + " gastando $" + valorAGastar + " de la moneda FIAT: " + monFiat.getNomenclatura());
                transaccion.setFechaHora(LocalDateTime.now());
                transaccion.setIdUsuario(Sesion.getInstance().getUserResult().getUserId()); // Set a default or appropriate user ID
                crear(transaccion);
                Conexion.getConexion().commit();
            } else {
                System.out.println("problemas con los valores! no hay suficiente saldo");
            }
        } catch (SQLException e) {
            System.out.println("error durante la carga!");
            try {
                Conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                Conexion.getConexion().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("error! no se pudo modificar el commit");
            }
        }
    }

    @Override
    public void swap(long criptoOriginal, double cantidad, long criptoEsperada) {

        long userId = Sesion.getInstance().getUserResult().getUserId();
        SQLManager factory = SQLManager.getInstancia();
        try {
            Moneda monedaOriginal = factory.getMoneda().obtener(criptoOriginal);
            Moneda monedaEsperada = factory.getMoneda().obtener(criptoEsperada);
            Conexion.getConexion().setAutoCommit(false);
            double valorOriginal = monedaOriginal.getCotizacion() * cantidad;
            double cantidadEsperada = valorOriginal / monedaEsperada.getCotizacion();

            Activo actiCriptoEsperada = factory.getCrypto().obtener(criptoEsperada, userId);
            if (actiCriptoEsperada != null && actiCriptoEsperada.getSaldo() >= cantidadEsperada) {
                factory.getCrypto().actualizar(cantidadEsperada,userId, criptoEsperada);
                factory.getCrypto().actualizar(-cantidad, userId,criptoOriginal);
                Transaccion transaccion = new Transaccion();
                transaccion.setResumen("se Intercambiaron " + cantidad + " de criptomonedas " + monedaOriginal.getNomenclatura() + " por " + cantidadEsperada + " de la criptomoneda " + monedaEsperada.getNomenclatura());
                transaccion.setFechaHora(LocalDateTime.now());
                transaccion.setIdUsuario(1); // Set a default or appropriate user ID
                crear(transaccion);
                Conexion.getConexion().commit();
            } else {
                System.out.println("problemas con los valores! no hay suficiente saldo");
            }
        } catch (SQLException e) {
            System.out.println("error durante la carga!");
            try {
                Conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                Conexion.getConexion().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("error! no se pudo modificar el commit");
            }
        }
    }

    @Override
    public long crear(Transaccion transaccion) {
        String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA, ID_USUARIO) VALUES (?, ?, ?)";
        Connection connection = Conexion.getConexion();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, transaccion.getResumen());
            pstmt.setTimestamp(2, Timestamp.valueOf(transaccion.getFechaHora()));
            pstmt.setLong(3, transaccion.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL error mientras se cargaba la transaccion: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("error inesperado mientras se cargaba la transaccion: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    private Transaccion convertir(ResultSet rs) throws SQLException {
        Transaccion tr = new Transaccion();
        tr.setResumen(rs.getString("RESUMEN"));
        tr.setFechaHora(LocalDateTime.of(rs.getDate("FECHA_HORA").toLocalDate(), LocalTime.MIDNIGHT));
        tr.setIdUsuario(rs.getInt("ID_USUARIO"));
        tr.setId(rs.getLong("ID"));
        return tr;
    }
    public List<Transaccion> listar() {
        return transacciones;
    }
    /*@Override
    public List<Transaccion> listar() {
        List<Transaccion> transacciones = new ArrayList<>();
        try {
            Statement st = Conexion.getConexion().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM TRANSACCION");
            while (res.next()) {
                transacciones.add(convertir(res));
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return transacciones;
    }*/

    public void cargarTransaccionesDePrueba() {
        transacciones.add(new Transaccion("Compra de BTC", LocalDateTime.now(), 1));
        transacciones.add(new Transaccion("Venta de ETH", LocalDateTime.now(), 2));
        transacciones.add(new Transaccion("Compra de USD", LocalDateTime.now(), 3));
    }

    public Transaccion obtener(long ID) {
        String sql = "SELECT * FROM TRANSACCION WHERE ID = ?";
        Connection connection = Conexion.getConexion();
        Transaccion transaccion = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, ID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    transaccion = new Transaccion();
                    transaccion.setResumen(rs.getString("RESUMEN"));
                    transaccion.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                    transaccion.setIdUsuario(rs.getLong("ID_USUARIO"));
                    transaccion.setId(rs.getLong("ID"));
                    // Otros atributos si existen
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return transaccion;  // Devuelve el objeto Transaccion o null si no existe
    }
    public Transaccion obtener(String a){
        return null;
    }

    public void actualizar(Transaccion transaccion) {
        String sql = "UPDATE TRANSACCION SET RESUMEN = ?, FECHA_HORA = ?, ID_USUARIO = ? WHERE ID = ?";
        Connection connection = Conexion.getConexion();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, transaccion.getResumen());
            pstmt.setTimestamp(2, Timestamp.valueOf(transaccion.getFechaHora()));
            pstmt.setLong(3, transaccion.getIdUsuario());
            pstmt.setLong(4, transaccion.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}