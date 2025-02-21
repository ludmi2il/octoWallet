package Octo.Modelo.DAO;

import Octo.Exceptions.OctoDBException;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Modelo.Entidad.Transaccion;

public interface DaoTransaccion extends Crud<Transaccion>, UserRelatedDao<Transaccion> {
    void comprarCriptoMonedas(long idCrypto, long idFiat, double cantidad ) throws OctoElemNotFoundException, OctoDBException;
    void swap(long idOriginal, double cantidad, long idEsperada) throws OctoElemNotFoundException, OctoDBException;

}
