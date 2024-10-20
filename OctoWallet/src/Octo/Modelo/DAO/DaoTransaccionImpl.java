package Octo.Modelo.DAO;

import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.Transaccion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTransaccionImpl implements DaoTransaccion {

    @Override
    public void comprarCriptoMonedas(String cripto, String fiat, double cantidad) {
        // este metodo se debe valer de la relacion moneda con las diversas tablas.
        Moneda monedaCripto = FactorySQLManager.getInstancia().getMoneda().obtener(cripto);
        Moneda monedaFiat = FactorySQLManager.getInstancia().getMoneda().obtener(fiat);
        double valorAGastar = monedaFiat.getCotizacion() * cantidad; // la cantidad de plata que vas a gastar
        // hay que comprobar el valor del stock comparado con la cantidad
        // DaoActivo activos = new DaoActivo();
        // double valorAComprar = activos.obtenerCantidadPorNomenclatura(cripto)
        /*  si valorAcompron == -1
                error
               sino si valorAGastar/valorAComprar >= stock
                    actualizar en tabla por nomenclatura y el campo correspondiente (activo)
                    lo mismo pero restando en activo_fiat
           */

    }
    @Override
    public void swap(Moneda criptoOriginal, double cantidad, Moneda criptoEsperada) {
        // verificar que criptoOriginal y criptoEsperada existan como activos en mis activos
             
    }

    @Override
    public void crear(Transaccion dato) {
        // crear transaccion va a ser con la ayuda de todos los de acá
        // a subir la transaccion finalmente con los datos correspondientes
    }

    @Override
    public List<Transaccion> listar() {
        return List.of();
    }

    @Override
    public Transaccion obtener(String id) {
        return null;
    }
}
