package Octo.Controlador.Vistas;

import Octo.Controlador.Control;
import Octo.Controlador.Sesion;
import Octo.Exceptions.OctoElemNotFoundException;
import Octo.Exceptions.OctoLoginException;
import Octo.Modelo.Entidad.User;
import Octo.Modelo.JDBC.FactoryDao;
import Octo.Servicios.AppServices.CacheCryptoService;
import Octo.Vista.gui3.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogin {
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JPanel contentPane;
    private vistas views;

    public ControllerLogin(JPanel mainPanel, JPanel contentPane, vistas views) {
        this.mainPanel = mainPanel;
        this.contentPane = contentPane;
        this.views = views;
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
               // CardLayout cl = (CardLayout)mainPanel.getLayout();
               // cl.show(mainPanel, "registro");
                showPanel("registro");
            }
        };
    }
    public ActionListener getLoginActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                    //CardLayout cl = (CardLayout)mainPanel.getLayout();
                    //cl.show(mainPanel, "cotizacion");
                    showPanel("misActivos");
                } catch (OctoLoginException ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
                }
            }
        };
    }
    public void login() throws OctoLoginException {
        if((textField.getText().isEmpty()) || (passwordField.getText().isEmpty())){
            throw new OctoLoginException("Por favor complete los campos");
        }
        User a = FactoryDao.getUsuario().obtenerPorMail(textField.getText(), passwordField.getText());
        if(a == null) {
            throw new OctoLoginException("Usuario o contraseña incorrectos");
        }
        Sesion.getInstance().setUser(a);

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
}
