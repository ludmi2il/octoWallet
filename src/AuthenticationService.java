import java.util.HashMap;
import java.util.Map;
 public class AuthenticationService {

     public Map<String, Usuario> users = new HashMap<>();

        public boolean register(String nombres, String apellidos, String correo, String contrasena, String telefono, String fechaNacimiento, String pais) {
            if (users.containsKey(correo)) {
                return false; // User already exists
            }
            Usuario user = new Usuario(nombres, apellidos, correo, contrasena, telefono, fechaNacimiento, pais);
            users.put(correo, user);
            return true;
        }

        public void sendAuthenticationCode(Usuario user) {
            String message = "Your authentication code is 1234"; // Example code
            if (user.getMetodoAutenticacion() == 0) {
                sendEmail(user.getCorreo(), message);
            } else if (user.getMetodoAutenticacion() == 1) {
                sendSMS(user.getTelefono(), message);
            }
        }

        private void sendEmail(String email, String message) {
            // Implement email sending logic here
            System.out.println("Sending email to " + email + ": " + message);
        }

        private void sendSMS(String phoneNumber, String message) {
            // Implement SMS sending logic here
            System.out.println("Sending SMS to " + phoneNumber + ": " + message);
        }
    }

