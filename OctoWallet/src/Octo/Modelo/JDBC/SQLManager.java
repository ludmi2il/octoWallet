package Octo.Modelo.JDBC;

import Octo.Modelo.DAO.*;

import java.sql.Connection;

public class SQLManager {
    private DaoMoneda moneda;
    private DaoPersonaImpl persona;
    private DaoUsuarioImpl usuario;
    private DaoTransaccionImpl transaccion;
    private DaoActivoCrypto crypto;
    private DaoActivoFiat fiat;
    private static SQLManager instancia;
    private SQLManager(){
    }
    public static SQLManager getInstancia(){
        if(instancia ==null){
            instancia = new SQLManager();
        }
        return instancia;
    }
    public DaoMoneda getMoneda() {
        if( moneda == null){
            moneda= new DaoMonedaImpl();
        }
        return moneda;
    }
    public DaoUsuarioImpl getUsuario() {
        if( usuario == null){
            usuario = new DaoUsuarioImpl();
        }
        return usuario;
    }
    public DaoPersonaImpl getPersona() {
        if( persona == null){
            persona = new DaoPersonaImpl();
        }
        return persona;
    }
    public DaoActivoCrypto getCrypto() {
        if( crypto == null){
            crypto = new DaoActivoCrypto();
        }
        return crypto;
    }
    public DaoActivoFiat getFiat() {
        if( fiat == null){
            fiat = new DaoActivoFiat();
        }
        return fiat;
    }
    public DaoTransaccionImpl getTransaccion() {
        if(transaccion==null){
            transaccion = new DaoTransaccionImpl();
        }
        return transaccion;
    }


}
