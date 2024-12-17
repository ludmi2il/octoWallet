package Octo.Controlador;

// Singleton de Sesion, para poder mantener la informacion del usuario durante su uso.
//
public class Sesion {
    private static Sesion instancia;
    private Octo.Modelo.Entidad.userResult userResult;

    private Sesion() {
    }

    public Octo.Modelo.Entidad.userResult getUserResult() {

        return userResult;
    }
    public void setuserResult(Octo.Modelo.Entidad.userResult userResult) {

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