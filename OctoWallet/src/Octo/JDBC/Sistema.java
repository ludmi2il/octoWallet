package Octo.JDBC;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Sistema {
    // Ruta de la base de datos. Si el archivo no existe, SQLite lo creará al ejecutar el metodo.
    public static final String URL = "jdbc:sqlite:monedas.db";
    private static boolean baseCreada;

    public static void main(String[] args) {
        crearBBDD();
    }
    public static void crearBBDD(){
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
    }
    private static void creaciónDeTablasEnBD(Connection connection) throws
            SQLException {
        Statement stmt;
        stmt = connection.createStatement();
        String sql = "CREATE TABLE MONEDA "
                + "("
                + " TIPO       VARCHAR(1)    NOT NULL, "
                + " NOMBRE       VARCHAR(50)    NOT NULL, "
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
                + " VALOR_DOLAR	REAL     NOT NULL, "
                + " VOLATILIDAD	REAL     NULL, "
                + " STOCK	REAL     NULL "  + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE ACTIVO_CRIPTO"
                + "("
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
                + " CANTIDAD	REAL    NOT NULL " + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE ACTIVO_FIAT"
                + "("
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
                + " CANTIDAD	REAL    NOT NULL " + ")";
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE TRANSACCION"
                + "("
                + " RESUMEN VARCHAR(1000)   NOT NULL, "
                + " FECHA_HORA		DATETIME  NOT NULL " + ")";
        stmt.executeUpdate(sql);
        stmt.close();
    }

}
