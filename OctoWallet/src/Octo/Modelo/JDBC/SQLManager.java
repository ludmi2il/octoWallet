package Octo.Modelo.JDBC;

import Octo.Modelo.DAO.*;

public class SQLManager {
    private DaoMoneda moneda;
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
