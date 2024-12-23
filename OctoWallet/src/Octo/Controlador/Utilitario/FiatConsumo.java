package Octo.Controlador.Utilitario;

import Octo.Modelo.DAO.DaoMoneda;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.JDBC.DaoActivoFiat;
import Octo.Modelo.JDBC.DaoMonedaImpl;
import Octo.Modelo.JDBC.SQLManager;
import Octo.Servicios.CotizacionesFiatRequest;
// utilitario para el manejo de fiat
public class FiatConsumo {
    public static final DaoMoneda managerMoneda = SQLManager.getInstancia().getMoneda();
    public static final Moneda ArgFiat = managerMoneda.obtener("ARS");
    public static final Moneda USDFiat = managerMoneda.obtener("USD");
    public static final double ARGCotizacion = CotizacionesFiatRequest.RequestData("ARS").getCotizacion();
    public static final double USDCotizacion = 1.0;
    public static final double getCotizacion(String nomenclatura){
        double res;
        switch(nomenclatura){
            case "ARS": res =ARGCotizacion;
                break;
            case "USD": res = USDCotizacion;
                break;
            default: res=-1;
        }
        return res;
    }
    public static long getFiatId(String nomenclatura){
        long res;
        switch(nomenclatura){
            case "ARS": res =ArgFiat.getIdM();
                break;
            case "USD": res = USDFiat.getIdM();
                break;
            default: res=-1;
        }
        return res;
    }
    public static Moneda getFiat(String nomenclatura){
        Moneda res;
        switch(nomenclatura){
            case "ARS": res =ArgFiat;
                break;
            case "USD": res = USDFiat;
                break;
            default: res=null;
        }
        return res;
    }

}
