package Octo.JDBC;

public class Moneda {
    private String nombre;
    private double cotizacion;
    private double volatilidad;
    private double stock;

    public Moneda(String nombre, double cotizacion, double volatilidad, double stock) {
        this.nombre = nombre;
        this.cotizacion = cotizacion;
        this.volatilidad = volatilidad;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getVolatilidad() {
        return volatilidad;
    }

    public void setVolatilidad(double volatilidad) {
        this.volatilidad = volatilidad;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return   "nombre='" + nombre + '\'' +
                ", cotizacion=" + cotizacion +
                ", volatilidad=" + volatilidad +
                ", stock=" + stock +
                '}';
    }
}
