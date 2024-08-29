public interface AutenticationService {
    public boolean register(String nombres, String apellidos, String correo, String contrasena, String telefono, String fechaNacimiento, String pais);
    public void sendAuthenticationCode(Usuario user);
    public void sendEmail(String email, String message);
    public void sendSMS(String phoneNumber, String message);
}
