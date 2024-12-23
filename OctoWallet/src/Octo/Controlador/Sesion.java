package Octo.Controlador;

import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;

import java.util.List;

// Singleton de Sesion, para poder mantener la informacion del usuario durante su uso.
//
public class Sesion {
    private static Sesion instancia;
    private Octo.Modelo.Entidad.userResult userResult;
    private String criptoCompra;
    private List<Activo> misActivos;
    private List<Moneda> monedasDisponibles;

    private Sesion() {
    }

    public Octo.Modelo.Entidad.userResult getUserResult() {

        return userResult;
    }
    public void setuserResult(Octo.Modelo.Entidad.userResult userResult) {

        this.userResult = userResult;
    }
    public static Sesion getInstance() {
        if(instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }
    public void setCriptoCompra(String compra){
        this.criptoCompra= compra;
    }
    public String getCriptoCompra(){
        return criptoCompra;
    }
    public void setMisActivos(List<Activo> lista){
        misActivos= lista;
    }

    public List<Moneda> getMonedasDisponibles() {
        return monedasDisponibles;
    }

    public void setMonedasDisponibles(List<Moneda> monedasDisponibles) {
        this.monedasDisponibles = monedasDisponibles;
    }

    public void cerrarSesion() {
        instancia = null;
    }
    public long getIdCriptotByNom(String criptoNombre) {
        for (Moneda moneda : monedasDisponibles) {
            if (moneda.getNomenclatura().equalsIgnoreCase(criptoNombre)) {
                return moneda.getIdM();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + criptoNombre);
    }

    public long getIdFiatById(String fiatNombre) {
        for (Moneda moneda : monedasDisponibles) {
            if (moneda.getNombre().equalsIgnoreCase(fiatNombre)) {
                return moneda.getIdM();
            }
        }
        throw new IllegalArgumentException("Fiat desconocido: " + fiatNombre);
    }
    public double getCotizacionByNom(String cripto) {
        for (Moneda moneda : monedasDisponibles) {
            if (moneda.getNomenclatura().equalsIgnoreCase(cripto)) {
                return moneda.getCotizacion();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
    }

    public double getStockByNom(String cripto) {
        for (Moneda moneda : monedasDisponibles) {
            if (moneda.getNomenclatura().equalsIgnoreCase(cripto)) {
                return moneda.getStock();
            }
        }
        throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
    }
}