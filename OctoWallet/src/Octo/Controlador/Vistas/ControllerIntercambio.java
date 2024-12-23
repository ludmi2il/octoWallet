package Octo.Controlador.Vistas;

import Octo.Controlador.DataController;
import Octo.Controlador.Sesion;
import Octo.Exceptions.OctoNotFound;
import Octo.Modelo.JDBC.DaoTransaccionImpl;
import Octo.Modelo.JDBC.SQLManager;

import javax.accessibility.AccessibleRelation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerIntercambio {

    private JPanel mainPanel;
    private JLabel selectedCripto;
    private JComboBox<String> comboBox_1;
    private JTextField textField;
    private DataController dataController;
    private JLabel userNameLabel;
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
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad antes de convertir.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String criptoACambiar = selectedCripto.getText().toString();
                String criptoEsperadaStr = comboBox_1.getSelectedItem().toString().toLowerCase();
                if(criptoEsperadaStr.equals(criptoACambiar)){
                    JOptionPane.showMessageDialog(null,"error! no se puede intercambiar con la misma moneda!");
                    return;
                }
                double cantidad = Double.parseDouble(textField.getText());

                long criptoOriginal = Sesion.getInstance().getIdCriptotByNom(criptoACambiar);
                long criptoEsperada = Sesion.getInstance().getIdCriptotByNom(criptoEsperadaStr);

               try{
                   SQLManager.getInstancia().getTransaccion().swap(criptoOriginal, cantidad, criptoEsperada);
                   JOptionPane.showMessageDialog(mainPanel, "Swap realizado con éxito.");
                   CardLayout cl = (CardLayout)mainPanel.getLayout();
                   cl.show(mainPanel, "misActivos");
               }catch (OctoNotFound o) {
                   JOptionPane.showMessageDialog(null, o.getMessage());
               }
            }
        };
    }

    public void setUserNameLabel(JLabel label) {
        this.userNameLabel = label;
    }

    public void ModificarUserName() {
        String nombre = Sesion.getInstance().getUserResult().getUser().getNombres() + " " + Sesion.getInstance().getUserResult().getUser().getApellidos();
        this.userNameLabel.setText(nombre);
    }
    public void getCripto(){
        selectedCripto.setText(Sesion.getInstance().getCriptoCompra());
    }

    public void setLabel(JLabel j) {
        this.selectedCripto= j;
    }

    public void setComboBox_1(JComboBox<String> comboBox_1) {
        this.comboBox_1 = comboBox_1;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}