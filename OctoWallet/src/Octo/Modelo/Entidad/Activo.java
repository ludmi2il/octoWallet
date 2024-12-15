package Octo.Modelo.Entidad;

public class Activo {
    private String tipo;
    private Moneda moneda;
    private double saldo;

    public Activo(String tipo, Moneda moneda, double saldo) {
        this.tipo = tipo;
        this.moneda = moneda;
        this.saldo = saldo;
    }

    public Activo() {
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Moneda getMoneda() {
        return moneda;
    }
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Activo{" +
                "tipo=" + tipo + "Moneda =" + moneda.toString() +
                ", saldo=" + saldo +
                '}';
    }
}
