package Octo.Modelo.Entidad;

public class User {
	
	private String Email;
	private String contrasena;
	
	public User(String email, String contrasena) {
		this.Email = email;
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
