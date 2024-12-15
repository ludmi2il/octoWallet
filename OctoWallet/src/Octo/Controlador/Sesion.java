package Octo.Controlador;

import Octo.Modelo.Entidad.User;
import Octo.Modelo.Entidad.UserResult;

// Singleton de Sesion, para poder mantener la informacion del usuario durante su uso.
//
public class Sesion {
    private static Sesion instancia;
    private UserResult userResult;

    private Sesion() {
    }

    public UserResult getUserResult() {
        return userResult;
    }
    public void setUserResult(UserResult userResult) {
        this.userResult = userResult;
    }
    public static Sesion getInstance() {
        if(instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }
    public void cerrarSesion() {
        instancia = null;
    }
}