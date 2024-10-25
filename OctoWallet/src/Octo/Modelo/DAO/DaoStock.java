package Octo.Modelo.DAO;

import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.Stock;

public interface DaoStock extends Crud<Stock>{
    int actualizar(double valor, String nomenclatura);
}
