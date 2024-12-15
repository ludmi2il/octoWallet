package Octo.Modelo.Entidad;

public class User extends Persona{
	private String Email;
	private String contrasena;



	public User() {
		super();
	}



	public User(String nombres, String email, String contrasena, String apellidos) {
		super(nombres, apellidos);
		Email = email;
		this.contrasena = contrasena;
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

	
}
