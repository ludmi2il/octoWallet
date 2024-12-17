package Octo.Controlador.Vistas;

import Octo.Modelo.JDBC.DaoTransaccionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerComprita {

    private JPanel mainPanel;
    private DaoTransaccionImpl daoTransaccion;
    private JTextField textField;
    private JComboBox<String> comboBox;

    public ControllerComprita(JPanel mainPanel) {

        this.mainPanel = mainPanel;
    }

    public ActionListener getCancelarActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "cotizacion");
            }
        };
    }

    public ActionListener getComprarActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cripto = (String) comboBox.getSelectedItem();
                double cantidad = Double.parseDouble(textField.getText());

                // Assuming the method comprarCriptoMonedas expects long IDs and a double amount
                long criptoId = getCriptoId(cripto); // Implement this method to get the ID based on the name
                long userId = getUserId(); // Implement this method to get the current user ID

                try {
                    daoTransaccion.comprarCriptoMonedas(userId, criptoId, cantidad);
                    JOptionPane.showMessageDialog(mainPanel, "Compra realizada con éxito.");
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "cotizacion");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "La compra no se pudo realizar.");
                }
            }
        };
    }

    private long getCriptoId(String cripto) {
        // Implement this method to return the correct ID based on the cryptocurrency name
        // For example:
        switch (cripto) {
            case "BTC": return 1;
            case "ETH": return 2;
            case "DOGE": return 3;
            case "USDC": return 4;
            case "USDT": return 5;
            default: throw new IllegalArgumentException("Criptomoneda desconocida: " + cripto);
        }
    }

    private long getUserId() {
        // Implement this method to return the current user ID
        // This is just a placeholder implementation
        return 12345;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}
