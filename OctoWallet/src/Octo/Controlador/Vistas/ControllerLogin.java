package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;
import Octo.Modelo.Entidad.userResult;
import Octo.Modelo.JDBC.SQLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogin {
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanel mainPanel;

    public ControllerLogin(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public ActionListener getLimpiarActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                passwordField.setText("");
            }
        };
    }
    public ActionListener getRegistreseActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "registro");
            }
        };
    }
    public ActionListener getLoginActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!textField.getText().isEmpty()) && (!passwordField.getText().isEmpty())){
                    userResult a = SQLManager.getInstancia().getUsuario().obtener(textField.getText(), passwordField.getText());
                    if (a != null) {
                        Sesion.getInstance().setuserResult(a);
                        CardLayout cl = (CardLayout)mainPanel.getLayout();
                        cl.show(mainPanel, "misActivos");
                        JOptionPane.showMessageDialog(null, "Bienvenido/a " + a.getUser().getNombres());
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } else { JOptionPane.showMessageDialog(null, "Por favor complete los campos"); }
            }
        };
    }
}
