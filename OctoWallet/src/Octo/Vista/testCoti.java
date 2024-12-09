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
       

    }
}
