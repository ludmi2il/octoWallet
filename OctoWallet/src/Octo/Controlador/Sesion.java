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

    public User getUsuario() {
        return usuario;
    }
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    public long getuserId() {
        return userId;
    }
    public void setuserId(long userId) {
        this.userId = userId;
    }
    public long getpersonaId() {
        return personaId;
    }
    public void setpersonaId(long personaId) {
        this.personaId = personaId;
    }
    public void initSesion(User usuario, long userId, long personaId) {
        this.usuario = usuario;
        this.userId = userId;
        this.personaId = personaId;
    }
    public static Sesion getInstance() {
        if(instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }
    public static void cerrarSesion() {
        instancia = null;
    }
}
