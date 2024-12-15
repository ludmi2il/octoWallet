package Octo.Modelo.JDBC;

import Octo.Modelo.DAO.*;

public class SQLManager {
    private DaoMoneda moneda;
    private DaoPersona persona;
    private DaoUsuario usuario;
    private DaoTransaccion transaccion;
    private DaoStock stock;
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
    public DaoUsuario getUsuario() {
        if( usuario == null){
            usuario = new DaoUsuarioImpl();
        }
        return usuario;
    }
    public DaoPersona getPersona() {
        if( persona == null){
            persona = new DaoPersonaImpl();
        }
        return persona;
    }
    public DaoActivo getCrypto() {
        if( crypto == null){
            crypto = new DaoActivoCrypto();
        }
        return crypto;
    }
    public DaoActivo getFiat() {
        if( fiat == null){
            fiat = new DaoActivoFiat();
        }
        return fiat;
    }
    public DaoTransaccion getTransaccion() {
        if(transaccion==null){
            transaccion = new DaoTransaccionImpl();
        }
        return transaccion;
    }

    public DaoStock getStock() {
        if(stock == null){
            stock = new DaoStockImpl();
        }
        return stock;
    }
}
