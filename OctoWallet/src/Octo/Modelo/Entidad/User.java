package Octo.Modelo.Entidad;

public class User extends Persona{
	private long userId;
	private String Email;
	private String contrasena;
	private boolean aceptaTerminos;


	public User() {
		super();
	}



	public User(String nombres, String email, String contrasena, String apellidos, boolean aceptaTerminos, long userId) {
		super(nombres, apellidos, userId);
		Email = email;
		this.contrasena = contrasena;
		this.aceptaTerminos = aceptaTerminos;
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isAceptaTerminos() {
		return aceptaTerminos;
	}
	public void setAceptaTerminos(boolean aceptaTerminos) {
		this.aceptaTerminos = aceptaTerminos;
	}
}
