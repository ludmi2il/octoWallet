package Octo.Modelo.DAO;

import Octo.Modelo.Entidad.Persona;

public interface DaoPersona{
    // acá podriamos darle más metodos para que daoimp de persona deba implementarlos
    public Persona obtener(long id);
}