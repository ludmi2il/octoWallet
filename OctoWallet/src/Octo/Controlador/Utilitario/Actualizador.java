package Octo.Controlador.Utilitario;

import Octo.Servicios.AppServices.CacheCryptoService;
import Octo.Vista.gui3.cotizacion;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Actualizador extends TimerTask {
    private CacheCryptoService monedas;
    private cotizacion cotizacionView;
    private Timer t;
    public Actualizador(CacheCryptoService cacheMonedas, cotizacion c){
        monedas= cacheMonedas;
        cotizacionView = c;
        t= new Timer(true);
    }
    public void iniciarActualizaciones() {
        t.scheduleAtFixedRate(this, 0, 20000);
    }
    @Override
    public void run() {
        /* creo un nuevo hilo para poder manejar las request de forma asincrona, sin tener el riesgo
        de que la respuesta tarde y las actualizaciones pierdan el tiempo en el que deben ejecutarse realmente
        (20 segs). De forma que no se bloquee el timer como recurso por utilizar su unico hilo*/
       new Thread(() -> {
        monedas.ActualizarCotizaciones();
        SwingUtilities.invokeLater(() -> {
            cotizacionView.actualizarCotizaciones(monedas.getCacheMonedas());
        });});
    }
}
