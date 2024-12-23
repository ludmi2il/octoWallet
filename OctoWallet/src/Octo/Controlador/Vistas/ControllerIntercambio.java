package Octo.Controlador.Vistas;

import Octo.Controlador.DataController;
import Octo.Controlador.Sesion;
import Octo.Modelo.JDBC.DaoTransaccionImpl;
import Octo.Modelo.JDBC.SQLManager;

import javax.accessibility.AccessibleRelation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerIntercambio {

    private JPanel mainPanel;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JTextField textField;
    private DataController dataController;
    private JLabel userNameLabel;
    private String selectedCripto;

    public ControllerIntercambio(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.dataController = new DataController();
    }

    public ActionListener getVolverActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "cotizacion");
            }
        };
    }

    public ActionListener getCerrarSesion(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sesion.getInstance().cerrarSesion();
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "login");
            }
        };
    }

    public ActionListener getRealizarSwap() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String criptoOriginalStr = selectedCripto;
                String criptoEsperadaStr = (String) comboBox_1.getSelectedItem();
                double cantidad = Double.parseDouble(textField.getText());

                //long criptoOriginal = dataController.getCriptoId(criptoOriginalStr);
                //long criptoEsperada = dataController.getCriptoId(criptoEsperadaStr);

               /* boolean success = SQLManager.getInstancia().getTransaccion().swap(criptoOriginal, cantidad, criptoEsperada);

                if (success) {
                    JOptionPane.showMessageDialog(mainPanel, "Swap realizado con éxito.");
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "misActivos");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "El swap no se pudo realizar.");
                }

                */
            }
        };
    }

    public void setSelectedCripto(String cripto) {
        this.selectedCripto = cripto;
    }

    public void setUserNameLabel(JLabel label) {
        this.userNameLabel = label;
    }

    public void ModificarUserName() {
        String nombre = Sesion.getInstance().getUserResult().getUser().getNombres() + " " + Sesion.getInstance().getUserResult().getUser().getApellidos();
        this.userNameLabel.setText(nombre);
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public void setComboBox_1(JComboBox<String> comboBox_1) {
        this.comboBox_1 = comboBox_1;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}