package Octo.Modelo.Entidad;

public class User extends Persona{
	private String Email;
	private String contrasena;
	private boolean aceptaTerminos;


	public User() {
		super();
	}



	public User(String nombres, String email, String contrasena, String apellidos, boolean aceptaTerminos) {
		super(nombres, apellidos);
		Email = email;
		this.contrasena = contrasena;
		this.aceptaTerminos = aceptaTerminos;
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
