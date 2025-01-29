package Octo.Servicios.AppServices;

import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.JDBC.DaoUsuarioImpl;
import Octo.Modelo.JDBC.FactoryDao;
import Octo.Servicios.ApiServices.CotizacionesFiatRequest;
import Octo.Servicios.ApiServices.DataRequest;

import java.util.List;

public class DBStatus {
    private boolean DbStatus;
    public DBStatus() {
        DbStatus = verificarEstadoBd();
        iniciarValoresBD();// la bd, si no existiera, se crearia en verificar por el bloque static.
    }
    private void iniciarValoresBD() {
        if (!DbStatus) {
            List<Moneda> criptos = DataRequest.RequestData();
            criptos.forEach(moneda -> FactoryDao.getMoneda().crear(moneda));
            Moneda argenta = CotizacionesFiatRequest.RequestData("ARS");
            argenta.setStock(ActivosService.darCantidad());
            argenta.setImagen("/imagenes/arg.jpg");
            Moneda dolarYankee = new Moneda(0, "F", "USD", "USD", 1, 0, ActivosService.darCantidad(), "/imagenes/usd.png");
            FactoryDao.getMoneda().crear(argenta);
            FactoryDao.getMoneda().crear(dolarYankee);
            DbStatus = true;
        }
    }
    private boolean verificarEstadoBd() {
        return !FactoryDao.getMoneda().listar().isEmpty();
    }
}
