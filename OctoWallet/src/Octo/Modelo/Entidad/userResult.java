package Octo.Modelo.Entidad;

public class userResult {
    private User user;
    private long userId;
    private long personaId;

    public userResult(User user, long userId, long personaId) {
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
