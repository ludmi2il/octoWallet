package Octo.Modelo.Entidad;
// consultar stock y moneda relacion y dao
public class Stock {
    Moneda moneda;
    double Stock;

    public Stock() {
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getStock() {
        return Stock;
    }

    public void setStock(double stock) {
        Stock = stock;
    }
}
