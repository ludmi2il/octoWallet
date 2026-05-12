package Octo.Modelo.DAO;

import Octo.Exceptions.OctoElemNotFoundException;

import java.util.List;

public interface UserRelatedDao<T> {
    List<T> listarPorId(long id) throws OctoElemNotFoundException;
}
