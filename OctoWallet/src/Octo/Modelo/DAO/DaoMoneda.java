package Octo.Modelo.DAO;

import Octo.Modelo.Entidad.Moneda;

public interface DaoMoneda extends Crud <Moneda>{
    // acá podriamos darle más metodos para que daoimp de moneda deba implementarlos
    public Moneda obtener(long id);
    public long actualizar(long idMoneda, double valor);
}
