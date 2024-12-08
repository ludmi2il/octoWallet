package Octo.Modelo.Entidad;

public class Activo {
    private String tipo;
    private String nomenclatura; // deberia cambiar por moneda
    private double saldo;

    public Activo(String tipo, String nomenclatura, double saldo) {
        this.tipo= tipo;
        this.nomenclatura = nomenclatura;
        this.saldo = saldo;
    }

    public Activo() {
    }
    public String getTipo() {
        return tipo;
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
                "tipo=" + tipo + "nomenclatura=" + nomenclatura +
                ", saldo=" + saldo +
                '}';
    }
}
