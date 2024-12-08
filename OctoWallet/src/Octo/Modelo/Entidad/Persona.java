package Octo.Modelo.Entidad;

public class Persona {
     private String Email;
     private String nombres;
     private String apellidos;
     private String contrasena;
     
	public Persona(String email, String nombres, String apellidos, String contrasena) {
		this.Email = email;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
     
	
     
}
