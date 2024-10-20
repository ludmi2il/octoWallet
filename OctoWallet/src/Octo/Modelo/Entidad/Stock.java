package Octo.Modelo.Entidad;
// consultar stock y moneda relacion y dao
public class Stock {
    long id; // clave primaria autoincrementable
    String nomenclaturaMoneda;
    double monto;

    public Stock() {
    }

    public double getStock() {
        return monto;
    }

    public void setStock(double stock) {
        monto = stock;
    }
}
