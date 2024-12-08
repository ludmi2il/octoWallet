package Octo.Modelo.Entidad;

public class User {
	private String nombres
	private String Email;
	private String contrasena;
	private String apellidos;



	public User() {
	}



	public User(String nombres, String email, String contrasena, String apellidos) {
		this.nombres = nombres;
		Email = email;
		this.contrasena = contrasena;
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
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
