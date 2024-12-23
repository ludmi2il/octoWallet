package Octo.Controlador.Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Octo.Controlador.DataController;
import Octo.Modelo.Entidad.User;
import Octo.Modelo.JDBC.SQLManager;

public class ControllerRegistro {
    private JTextField textField;
    private JTextField passwordField;
    private JPanel mainPanel;
    private DataController dataController;
    private JCheckBox chckbxNewCheckBox;
    private JTextField ApellidoField;
    private JTextField NombreField;

    public ControllerRegistro(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.dataController = new DataController();
    }
    public ActionListener getVolverActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "login");
            }
        };
    }

    public ActionListener getRegistrarActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String email = textField.getText();
                 String password = passwordField.getText();

                  if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "El mail no puede estar vacio.");
                    return;
                  }

                  if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "la contrasena no puede estar vacia.");
                     return;
                  }

                  if (dataController.verificarMail(email)&& (chckbxNewCheckBox.isSelected())) {
                      SQLManager.getInstancia().getUsuario().crear(new User(NombreField.getText(), email, password, ApellidoField.getText(), true));
                      textField.setText("");
                      passwordField.setText("");
                      ApellidoField.setText("");
                      NombreField.setText("");
                      CardLayout cl = (CardLayout)mainPanel.getLayout();
                      cl.show(mainPanel, "login");
                      chckbxNewCheckBox.setSelected(false);
                 } else {
                    JOptionPane.showMessageDialog(mainPanel, "la verificacion del mail fallo.");
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
    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }
    public void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
        this.chckbxNewCheckBox = chckbxNewCheckBox;
    }
}
