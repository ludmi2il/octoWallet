package Octo.Modelo.JDBC;

import Octo.Modelo.DAO.*;

public class FactoryDao {
    private static DaoMoneda moneda = new DaoMonedaImpl(); ;
    private static DaoPersona persona= new DaoPersonaImpl();
    private static DaoUsuario usuario= new DaoUsuarioImpl();
    private static DaoTransaccion transaccion = new DaoTransaccionImpl();
    private static DaoActivoCrypto crypto = new DaoActivoCrypto();
    private static DaoActivoFiat fiat = new DaoActivoFiat();

    public static DaoMoneda getMoneda() {
        return moneda;
    }
    public static DaoUsuario getUsuario() {
        return usuario;
    }
    public static DaoPersona getPersona() {
        return persona;
    }
    public static DaoActivoCrypto getCrypto() {
        return crypto;
    }
    public static DaoActivoFiat getFiat() {
        return fiat;
    }
    public static DaoTransaccion getTransaccion() {
        return transaccion;
    }
}
