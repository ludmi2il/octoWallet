package Octo.Modelo.Entidad;

public class Activo {
    private String nomenclatura; // deberia cambiar por moneda
    private double saldo;

    public Activo(String nomenclatura, double saldo) {
        this.nomenclatura = nomenclatura;
        this.saldo = saldo;
    }

    public Activo() {
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
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
                "tipo=" + tipo +
                ", saldo=" + saldo +
                '}';
    }
}
