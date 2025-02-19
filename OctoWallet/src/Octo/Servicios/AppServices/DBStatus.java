package Octo.Servicios.AppServices;

import Octo.Exceptions.OctoDBException;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.JDBC.DaoUsuarioImpl;
import Octo.Modelo.JDBC.FactoryDao;
import Octo.Servicios.ApiServices.CotizacionesFiatRequest;
import Octo.Servicios.ApiServices.DataRequest;

import java.util.List;

public class DBStatus {
    private boolean DbStatus;
    public DBStatus() {
        try {
            DbStatus = verificarEstadoBd();
        } catch(OctoElemNotFoundException e){
            System.out.println(" no se pudo verificar el estado de la base de datos");
        }
        try{
            iniciarValoresBD();// la bd, si no existiera, se crearia en verificar por el bloque static.
        }catch(OctoDBException e){
            System.out.println("no se pudieron inicializar los valores en la bd!");
        }
    }
    private void iniciarValoresBD() throws OctoDBException {
        if (!DbStatus) {
            List<Moneda> criptos = DataRequest.RequestData();
            criptos.forEach(moneda -> {
                try {
                    FactoryDao.getMoneda().crear(moneda);
                } catch (OctoDBException e) {
                    System.out.println("error! moneda no pudo ser cargada!");
                }
            });
            Moneda argenta = CotizacionesFiatRequest.RequestData("ARS");
            argenta.setStock(ActivosService.darCantidad());
            argenta.setImagen("https://cdn3.iconfinder.com/data/icons/currency-2/460/Argentine-Peso-512.png");
            Moneda dolarYankee = new Moneda(0, "F", "USD", "USD", 1, 0, ActivosService.darCantidad(), "https://previews.123rf.com/images/putracetol/putracetol1805/putracetol180505700/101702994-dise%C3%B1o-de-icono-de-logotipo-de-dinero.jpg");
            FactoryDao.getMoneda().crear(argenta);
            FactoryDao.getMoneda().crear(dolarYankee);
            DbStatus = true;
        }
    }
    private boolean verificarEstadoBd() throws OctoElemNotFoundException {
        return !FactoryDao.getMoneda().listar().isEmpty();
    }
}
