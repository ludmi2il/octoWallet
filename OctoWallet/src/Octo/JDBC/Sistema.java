package Octo.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class Sistema {
    // Ruta de la base de datos. Si el archivo no existe, SQLite lo creará al ejecutar el metodo.
    public static final String URL = "jdbc:sqlite:monedas.db";
    private static final Scanner in = new Scanner(System.in);
    private static boolean baseCreada;

    public static void main(String[] args) {
        if(baseCreada)
            System.out.println("la base de datos ya ha sido creada: ");
        else
            crearBBDD();
        try {
            Connection con = DriverManager.getConnection(URL);
            crearMonedas(con);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void crearMonedas(Connection connection){
        String nombre, nomenclatura; double cotizacion, volatilidad, stock;
        System.out.println("tipo de moneda: (0 - crypto, 1 - fiat");
        int tipo = in.nextInt();
        if(tipo == 0 || tipo == 1) {
            System.out.println("nombre moneda");
            nombre = in.next();
            System.out.println("nomenclatura");
            nomenclatura = in.next();
            System.out.println("cotizacion");
            cotizacion = in.nextDouble();
            System.out.println("volatilidad");
            volatilidad = in.nextDouble();
            System.out.println("stock");
            stock = in.nextDouble();
            System.out.println("registrar moneda? true/false");
            boolean opcion = in.nextBoolean();
            if (opcion)
                insertarMoneda(connection, new Moneda(tipo, nombre,nomenclatura, cotizacion, volatilidad, stock));
        }
    }
    public static void insertarMoneda(Connection con, Moneda moneda){
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK)" +
                    "VALUES('" + moneda.getTipo() + "', '"+ moneda.getNombre() + "', '"+ moneda.getNomenclatura() + "', '"+ moneda.getCotizacion()
                    + "', '" + moneda.getVolatilidad()+ "', '" + moneda.getStock() + "');";
            // se puede usar sets de Statement y los campos para evitar errores de tipeo.
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    public static boolean crearBBDD(){
        if(!baseCreada) {
            try {
                Connection con = DriverManager.getConnection(URL);
                creaciónDeTablasEnBD(con);
            } catch (SQLException e) {
                System.out.println("error! no se pudo crear la conexión" + e.getMessage());
                e.printStackTrace();
            }
            baseCreada=true;
        }
        return baseCreada;
    }
    private static void creaciónDeTablasEnBD(Connection connection) throws
            SQLException {
        Statement stmt;
        stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS MONEDA "
                + "("
                + " TIPO       VARCHAR(1)    NOT NULL, "
                + " NOMBRE       VARCHAR(50)    NOT NULL, "
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
                + " VALOR_DOLAR	REAL     NOT NULL, "
                + " VOLATILIDAD	REAL     NULL, "
                + " STOCK	REAL     NULL "  + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO"
                + "("
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
                + " CANTIDAD	REAL    NOT NULL " + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS ACTIVO_FIAT"
                + "("
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
                + " CANTIDAD	REAL    NOT NULL " + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE IF NOT EXISTS TRANSACCION"
                + "("
                + " RESUMEN VARCHAR(1000)   NOT NULL, "
                + " FECHA_HORA		DATETIME  NOT NULL " + ")";
        stmt.executeUpdate(sql);
        stmt.close();
    }

}
