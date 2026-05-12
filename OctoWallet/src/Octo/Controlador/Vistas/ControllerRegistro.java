package Octo.Controlador.Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;
import Octo.Exceptions.OctoDBException;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Exceptions.OctoRegisterException;
import Octo.Vista.gui3.vistas;
import Octo.Modelo.Entidad.User;
import Octo.Modelo.JDBC.FactoryDao;

public class ControllerRegistro {
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JPanel contentPane;
    private JCheckBox chckbxNewCheckBox;
    private JTextField ApellidoField;
    private JTextField NombreField;
    private JPasswordField passR;
    private vistas views;
    
    
    public ControllerRegistro(JPanel mainPanel, JPanel contentPane, vistas views) {
        this.mainPanel = mainPanel;
        this.contentPane = contentPane;
        this.views = views;
    }
    
    public ActionListener getVolverActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("login");
            }
        };
    }

    public ActionListener getRegistrarActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = new String(passwordField.getPassword());
                String passr = new String(passR.getPassword());
                String nombre = NombreField.getText();
                String apellido = ApellidoField.getText();
                boolean aceptaTerminos = chckbxNewCheckBox.isSelected();
                try {
                    registrar(email, password,passr,nombre,apellido,aceptaTerminos);
                    JOptionPane.showMessageDialog(mainPanel, "Usuario registrado con éxito");
                    textField.setText("");
                    passwordField.setText("");
                    ApellidoField.setText("");
                    NombreField.setText("");
                    passR.setText("");
                    chckbxNewCheckBox.setSelected(false);
                    showPanel("login");
                } catch (OctoRegisterException | OctoDBException ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
                }
            };
        };
   }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
    public void setApellido(JTextField AtextField) {
        this.ApellidoField = AtextField;
    }
    public void setNombre(JTextField NtextField) {
        this.NombreField = NtextField;
    }
    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
    public void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
        this.chckbxNewCheckBox = chckbxNewCheckBox;
    }
    
    public void setPassworldFieldR(JPasswordField passR) {
    	this.passR = passR;
    }

    public void showPanel(String name) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, name);
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible()) {
                Dimension preferredSize = comp.getPreferredSize();
                mainPanel.setPreferredSize(preferredSize);
                views.getContentPane().setPreferredSize(preferredSize);
                views.pack();
                views.setLocationRelativeTo(null);
                break;
            }
        }
    }
    public boolean isEmailCorrect(String email) {
        String pattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        return m.matches();
    }
    public void registrar(String email, String password, String passr, String nombre, String apellido, boolean aceptaTerminos) throws OctoRegisterException, OctoDBException {


        if(email.isEmpty() || password.isEmpty() || passr.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            throw new OctoRegisterException("Debe completar todos los campos");
        }
        if(!password.equals(passr)) {
            throw new OctoRegisterException("Las contraseñas no coinciden");
        }
        if(!FactoryDao.getUsuario().verificarMail(email)) {
            throw new OctoRegisterException("El email ya se encuentra registrado");
        }
        if(!aceptaTerminos) {
            throw new OctoRegisterException("Debe aceptar los términos y condiciones");
        }
        if(!isEmailCorrect(email)) {
            throw new OctoRegisterException("El email no es válido");
        }

        FactoryDao.getUsuario().crear(new User(nombre, email, password, apellido, true, -1));


    }
}
