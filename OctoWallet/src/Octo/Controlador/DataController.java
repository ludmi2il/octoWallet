package Octo.Controlador;

import Octo.Controlador.Utilitario.Comparadores;
import Octo.Modelo.JDBC.SQLManager;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.Stock;
import Octo.Servicios.CotizacionesRequest;
import Octo.Servicios.DataRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataController {
    /* controlador de datos de api y datos de los modelos */
    private SQLManager factory = SQLManager.getInstancia();
    private boolean DbStatus;
    private List<Moneda> cacheMonedas;
    public DataController() {
     DbStatus= verificarEstadoBd(); // la bd, si no existiera, se crearia en verificar por el bloque static.
    }

    private void iniciarValoresBD(){
        if(!DbStatus) {
            List<Moneda> criptos = DataRequest.RequestData();
            criptos.forEach(moneda -> factory.getMoneda().crear(moneda));
            Moneda argenta = new Moneda("F", "Peso Argentino", "ARS", 0, 0.0, darCantidad(), "/imagenes/argentina.png");
            Moneda dolarYankee = new Moneda("F", "Dolar Estadounidense", "USD", 1, 0, darCantidad(), "/imagenes/usa.png");
            factory.getMoneda().crear(argenta);
            factory.getMoneda().crear(dolarYankee);
            DbStatus = true;
        }
    }
    private boolean verificarEstadoBd(){
        return factory.getMoneda().listar().isEmpty();
    }
    private void CargarCache(){
        cacheMonedas = factory.getMoneda().listar();
    }
    private void CriptosOrdenadas(){
                cacheMonedas = cacheMonedas.stream().filter(moneda ->
                        moneda.getTipo().equals("C")).sorted(Comparadores.compararMonedaPorValorDolar()).toList();}
    private List<Moneda> getCacheMonedas(){
        return cacheMonedas;
    }
    private CompletableFuture<Map<String, Map<String,Double>>> ObtenerCotizaciones(){
        // obtengo solo las monedas de tipo C sin crear casos de where en las queries
        String ids= cacheMonedas.stream().map(Moneda::getNombre).collect(Collectors.joining(","));
        return CotizacionesRequest.RequestAsync(ids); // quiero que retorne un mapa
    }
    public void ActualizarCotizaciones(){
        ObtenerCotizaciones()
                .thenAccept(cotizaciones -> {
                    // Verificamos si la respuesta no está vacía
                    if (!cotizaciones.isEmpty()) {
                        // por cada moneda
                        cacheMonedas.forEach(moneda -> {
                            String nombreMoneda = moneda.getNombre().toLowerCase();
                            if (cotizaciones.containsKey(nombreMoneda)) {
                                // Obtengo la cotización
                                Double cotizacion = cotizaciones.get(nombreMoneda).get("usd");
                                moneda.setCotizacion(cotizacion); // Asignamos la cotización al objeto Moneda
                            }
                        });
        // Después de actualizar las cotizaciones,ya puedo actualizar la vista
        //actualizarVistaConCotizaciones(listaMonedas);
    }
})
        .exceptionally(e -> {
        System.out.println("Error al obtener las cotizaciones: " + e.getMessage());
        return null;
        });
    }
    private int darCantidad (){ //falta ver realmente donde va a ir esto
        return (int)(Math.random()*10000) + 1;
    }
    public boolean crearMoneda(String tipo,String nombre, String nomenclatura, double cotizacion, double volatilidad, double stock){
        boolean exito;
        factory.getMoneda().crear(new Moneda(tipo,nombre,nomenclatura,cotizacion,volatilidad,stock,""));
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
        boolean exito;
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
        boolean exito;
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
    public boolean swap(String criptoOriginal, double cantidad, String criptoEsperada) {
        boolean exito = false;
        try {
            factory.getTransaccion().swap(criptoOriginal, cantidad, criptoEsperada);
            exito = true;
        } catch (Exception e) {
            System.out.println("Error durante el intercambio: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }
    public boolean comprarCripto(String cripto, String fiat, double cantidad){
        boolean exito = false;
        try{
            factory.getTransaccion().comprarCriptoMonedas(cripto,fiat,cantidad);
            exito = true;
        }catch (Exception e){
            System.out.println("Error durante la compra: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }
}
