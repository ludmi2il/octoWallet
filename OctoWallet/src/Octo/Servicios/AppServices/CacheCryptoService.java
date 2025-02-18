package Octo.Servicios.AppServices;

import Octo.Controlador.Utilitario.Comparadores;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Exceptions.OctoServiceException;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.JDBC.FactoryDao;
import Octo.Servicios.ApiServices.CotizacionesRequest;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CacheCryptoService {
    private static CacheCryptoService instancia;
    private static List<Moneda> cacheMonedas;
    private static String ids;
    private CacheCryptoService(){
        try {
            cargarCache();
        }catch (OctoElemNotFoundException e){
            JOptionPane.showInputDialog("error al conseguir los valores del mercado actual de criptomnedas! intente conectarse más tarde!");
        }
        criptosOrdenadas();
        ActivosService.darStock(cacheMonedas);
        this.cacheMonedas = monedasMVP();
    } public static List<Moneda> MonedasMVP() {
        List<String> monedasMVP = Arrays.asList("btc", "eth", "usdc", "usdt", "doge");
        System.out.println(cacheMonedas);
        return cacheMonedas.stream()
                .filter(moneda -> monedasMVP.contains(moneda.getNomenclatura().toLowerCase()))
                .collect(Collectors.toList());
    }
    public static CacheCryptoService getInstancia(){
        if(instancia==null){
            instancia= new CacheCryptoService();
        }
        return instancia;
    }
    public List<Moneda> getCacheMonedas() {
        return cacheMonedas;
    }
    private List<Moneda> monedasMVP() {
        List<String> monedasMVP = Arrays.asList("btc", "eth", "usdc", "usdt", "doge");
        System.out.println(cacheMonedas);
        return cacheMonedas.stream()
                .filter(moneda -> monedasMVP.contains(moneda.getNomenclatura().toLowerCase()))
                .collect(Collectors.toList());
    }
    private void cargarCache() throws OctoElemNotFoundException {
        cacheMonedas = FactoryDao.getMoneda().listar();
    }
    private Map<String, Map<String, Double>> obtenerCotizaciones() {
        // obtengo solo las monedas de tipo C sin crear casos de where en las queries
        if (ids.equals("")) {
            ids = cacheMonedas.stream().map(Moneda::getNombre).collect(Collectors.joining(","));
        }
        try{
            Map<String, Map<String, Double>> resp = CotizacionesRequest.RequestSync(ids); // quiero que retorne un mapa
            return resp;
        }catch (OctoServiceException e){
            JOptionPane.showInputDialog(e.getMessage());
        }
        return Collections.emptyMap();
    }
    public void ActualizarCotizaciones() {
            Map<String, Map<String, Double>> cotizaciones = obtenerCotizaciones(); // Llamada síncrona

            // Verificamos si la respuesta no está vacía
            if (!cotizaciones.isEmpty()) {
                // Por cada moneda en caché
                cacheMonedas.forEach(moneda -> {
                    String nombreMoneda = moneda.getNombre().toLowerCase();
                    if (cotizaciones.containsKey(nombreMoneda)) {
                        // Obtengo la cotización
                        Double cotizacion = cotizaciones.get(nombreMoneda).get("usd");
                        moneda.setCotizacion(cotizacion); // Asignamos la cotización al objeto Moneda
                    }
                });
            } else {
                System.out.println("No se pudieron obtener las cotizaciones.");
            }
    }
    private void criptosOrdenadas() {
        cacheMonedas = cacheMonedas.stream().filter(moneda ->
                moneda.getTipo().equals("C")).sorted(Comparadores.compararMonedaPorValorDolar().reversed()).toList();
    }
}
