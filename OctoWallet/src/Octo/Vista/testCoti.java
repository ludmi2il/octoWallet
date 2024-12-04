package Octo.Vista;

import Octo.Servicios.CotizacionesRequest;

public class testCoti {
    public static void main(String[] args) {
        while(true) {
            CotizacionesRequest.RequestAsync();
            try {
                Thread.sleep(60000);  // Espera 1 minuto antes de hacer otra solicitud
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
