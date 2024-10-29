package Octo.Controlador;

import Octo.Modelo.DAO.FactorySQLManager;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.Stock;

import java.util.List;

public class ControladorAIO {
    /* controlador all in one
     será la interfaz de programador desde la cual main se comunicará y creará los objetos necesarios, y llamará
    a los objetos pertinentes*/
    private FactorySQLManager factory = FactorySQLManager.getInstancia();
    public int darCantidad (){ //falta ver realmente donde va a ir esto
        return (int)(Math.random()*10000) + 1;
    }
    public boolean crearMoneda(String tipo,String nombre, String nomenclatura, double cotizacion, double volatilidad, double stock){
        boolean exito = false;
        factory.getMoneda().crear(new Moneda(tipo,nombre,nomenclatura,cotizacion,volatilidad,stock));
        exito= true;
        return exito;
    }
    public List<Moneda> listarMoneda(int opcion){
        List<Moneda> monedas = factory.getMoneda().listar();
        switch (opcion){
            case 1: monedas.sort(Comparadores.compararMonedaPorValorDolar());
                break;
            case 2: monedas.sort(Comparadores.compararMonedaPorNomenclatura());
        }
        return monedas;
    }
    public boolean crearStock(String nomenclaturaStock){
        boolean exito = false;
        factory.getStock().crear(new Stock(nomenclaturaStock, darCantidad()));
        exito = true;
        return exito;
    }
    public List<Stock> ListarStock(int opcion){
        List<Stock> stocks = factory.getStock().listar();
        switch (opcion){
            case 1: stocks.sort(Comparadores.compararStockPorNomenclatura());
                    break;
        }
        return stocks;
    }
    public boolean crearActivo(String tipo, String nomenclatura, double saldo){
        boolean exito = false;
        if( tipo.toUpperCase().equals("CRYPTO")){
            factory.getCrypto().crear(new Activo(tipo.toUpperCase(),nomenclatura,saldo));
        }else if(tipo.toUpperCase().equals("FIAT")){
            factory.getFiat().crear(new Activo(tipo.toUpperCase(),nomenclatura,saldo));
        }
        exito=true;
        return exito;
    }
    public List<Activo> ListarActivos(){
        List<Activo> acts = factory.getCrypto().listar();
        acts.sort(Comparadores.compararActivoPorSaldo());
        return acts;
    }
    // falta modulo de swap y compra
}
