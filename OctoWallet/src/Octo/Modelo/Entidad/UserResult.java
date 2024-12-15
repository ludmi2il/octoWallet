package Octo.Modelo.Entidad;
import Octo.Modelo.Entidad.User;

public class UserResult {
    private User user;
    private long userId;
    private long personaId;

    public UserResult(User user, long userId, long personaId) {
        this.user = user;
        this.userId = userId;
        this.personaId = personaId;
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

    public long getPersonaId() {
        return personaId;
    }
}