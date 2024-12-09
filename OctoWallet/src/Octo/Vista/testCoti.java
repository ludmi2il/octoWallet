package Octo.Vista;

import Octo.Controlador.DataController;
import Octo.Servicios.CotizacionesRequest;
import Octo.Servicios.DataRequest;

public class testCoti {
    public static void main(String[] args) {
        DataController controlador = new DataController();
        controlador.cargarCache();
        System.out.println(controlador.getCacheMonedas());
        controlador.criptosOrdenadas();
        System.out.println(controlador.getCacheMonedas());
        while(true) {
            controlador.ActualizarCotizaciones();
            controlador.getCacheMonedas().forEach( moneda -> {
                System.out.println(moneda.getNombre() + " - " + moneda.getCotizacion() +",");});
            try {
                Thread.sleep(10000);  // Espera 1 minuto antes de hacer otra solicitud
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
