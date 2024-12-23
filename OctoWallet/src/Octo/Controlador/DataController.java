package Octo.Controlador;

import Octo.Controlador.Utilitario.Comparadores;
import Octo.Controlador.Utilitario.FiatConsumo;
import Octo.Modelo.JDBC.SQLManager;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Servicios.CotizacionesFiatRequest;
import Octo.Servicios.CotizacionesRequest;
import Octo.Servicios.DataRequest;
import Octo.Modelo.JDBC.DaoUsuarioImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataController {
    /* controlador de datos de api y datos de los modelos */
    private SQLManager factory = SQLManager.getInstancia();
    private boolean DbStatus;
    private List<Moneda> cacheMonedas;
    private String ids;
    private DaoUsuarioImpl daoUsuario;

    public DataController() {
        DbStatus = verificarEstadoBd();
        iniciarValoresBD();// la bd, si no existiera, se crearia en verificar por el bloque static.
        ids = "";
        daoUsuario = new DaoUsuarioImpl();
        cargarCache();
        criptosOrdenadas();
        cacheMonedas = MonedasMVP();
    }

    private void iniciarValoresBD() {
        if (!DbStatus) {
            List<Moneda> criptos = DataRequest.RequestData();
            System.out.println("no imprimo" + criptos.size());
            criptos.forEach(moneda -> factory.getMoneda().crear(moneda));
            Moneda argenta = CotizacionesFiatRequest.RequestData("ARS");
            argenta.setStock(darCantidad());
            argenta.setImagen("/imagenes/arg.jpg");
            Moneda dolarYankee = new Moneda(0, "F", "USD", "USD", 1, 0, darCantidad(), "/imagenes/usd.png");
            factory.getMoneda().crear(argenta);
            factory.getMoneda().crear(dolarYankee);
            DbStatus = true;
        }
    }

    private boolean verificarEstadoBd() {
        return !factory.getMoneda().listar().isEmpty();
    }

    public void cargarCache() {
        cacheMonedas = factory.getMoneda().listar();
    }

    public void criptosOrdenadas() {
        cacheMonedas = cacheMonedas.stream().filter(moneda ->
                moneda.getTipo().equals("C")).sorted(Comparadores.compararMonedaPorValorDolar().reversed()).toList();
    }

    public List<Moneda> getCacheMonedas() {
        return cacheMonedas;
    }

    public List<Moneda> MonedasMVP() {
        List<String> monedasMVP = Arrays.asList("btc", "eth", "usdc", "usdt", "doge");
        System.out.println(cacheMonedas);
        return cacheMonedas.stream()
                .filter(moneda -> monedasMVP.contains(moneda.getNomenclatura().toLowerCase()))
                .collect(Collectors.toList());
    }

    private CompletableFuture<Map<String, Map<String, Double>>> obtenerCotizaciones() {
        // obtengo solo las monedas de tipo C sin crear casos de where en las queries
        if (ids.equals("")) {
            ids = cacheMonedas.stream().map(Moneda::getNombre).collect(Collectors.joining(","));
        }
        return CotizacionesRequest.RequestAsync(ids); // quiero que retorne un mapa
    }

    public void ActualizarCotizaciones() {
        obtenerCotizaciones()
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

    private int darCantidad() { //falta ver realmente donde va a ir esto
        return (int) (Math.random() * 10000) + 1;
    }

    @Deprecated
    public boolean crearMoneda(String tipo, String nombre, String nomenclatura, double cotizacion, double volatilidad, double stock) {
        boolean exito;
        factory.getMoneda().crear(new Moneda(0, tipo, nombre, nomenclatura, cotizacion, volatilidad, stock, ""));
        exito = true;
        return exito;
    }

    @Deprecated
    public List<Moneda> listarMoneda(int opcion) {
        List<Moneda> monedas = factory.getMoneda().listar();
        switch (opcion) {
            case 1:
                monedas.sort(Comparadores.compararMonedaPorValorDolar());
                break;
            case 2:
                monedas.sort(Comparadores.compararMonedaPorNomenclatura());
        }
        return monedas;
    }


    public List<Activo> crearActivosDefault(List<Moneda> monedas) {
        // con el id de usuario Sesion.getInstance().getUserResult().getUserId()
        List<Activo> activos = new ArrayList<>();
        monedas.stream().forEach(moneda ->
                activos.add(new Activo(Sesion.getInstance().getUserResult().
                        getUserId(), moneda, darCantidad())));

        List<Activo> activosFiat =new ArrayList<>();
        activosFiat.add(new Activo(Sesion.getInstance().getUserResult().
                getUserId(), FiatConsumo.ArgFiat, darCantidad()));
        activosFiat.add(new Activo(Sesion.getInstance().getUserResult().
                getUserId(), FiatConsumo.USDFiat, darCantidad()));
        activos.forEach(activo -> SQLManager.getInstancia().getCrypto().crear(activo));
        activosFiat.forEach(activo -> SQLManager.getInstancia().getFiat().crear(activo));
        activos.addAll(activosFiat);
        return activos;

    }

    public void darStock() {
        cacheMonedas.forEach(cacheMonedas ->
                SQLManager.getInstancia().
                        getMoneda().
                        actualizar(cacheMonedas.getIdM(), darCantidad()));
    }

    @Deprecated
    public List<Activo> ListarActivos() {
        List<Activo> acts = factory.getCrypto().listar();
        acts.sort(Comparadores.compararActivoPorSaldo());
        return acts;
    }

    @Deprecated
    public boolean swap(long criptoOriginal, double cantidad, long criptoEsperada) {
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

    @Deprecated
    public boolean comprarCripto(long cripto, long fiat, double cantidad) {
        boolean exito = false;
        try {
            factory.getTransaccion().comprarCriptoMonedas(cripto, fiat, cantidad);
            exito = true;
        } catch (Exception e) {
            System.out.println("Error durante la compra: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    public boolean verificarMail(String mail) {
        return daoUsuario.verificarMail(mail);
    }


    public double getCotizacion(String cripto) {
        for (Moneda moneda : cacheMonedas) {
            if (moneda.getNomenclatura().equalsIgnoreCase(cripto)) {
                return moneda.getCotizacion();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
    }

    public double getStock(String cripto) {
        for (Moneda moneda : cacheMonedas) {
            if (moneda.getNomenclatura().equalsIgnoreCase(cripto)) {
                return moneda.getStock();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
    }

    public double getPrice(String cripto) {
        for (Moneda moneda : cacheMonedas) {
            if (moneda.getNomenclatura().equalsIgnoreCase(cripto)) {
                return moneda.getCotizacion();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
    }
}