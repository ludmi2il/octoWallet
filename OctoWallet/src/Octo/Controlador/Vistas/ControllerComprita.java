package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;
import Octo.Modelo.Entidad.userResult;
import Octo.Modelo.JDBC.DaoTransaccionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Octo.Controlador.DataController;
import Octo.Modelo.JDBC.SQLManager;


public class ControllerComprita {

    private JPanel mainPanel;
    private DaoTransaccionImpl daoTransaccion;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private DataController dataController;
    private JLabel conversionResultLabel;
    private JLabel stockLabel;
    private JLabel priceLabel;
    private String selectedCripto;
    private long userId;

    public ControllerComprita(JPanel mainPanel) {
        this.dataController = new DataController();
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
                String fiat = (String) comboBox.getSelectedItem();

                double cantidad = Double.parseDouble(textField.getText());

                //long criptoId = dataController.getCriptoId(selectedCripto);

                //long fiatId = dataController.getFiatId(fiat);

                /*try {
                    SQLManager.getInstancia().getTransaccion().comprarCriptoMonedas(criptoId,fiatId ,cantidad);
                    JOptionPane.showMessageDialog(mainPanel, "Compra realizada con éxito.");
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "cotizacion");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "La compra no se pudo realizar.");
                }

                 */
            }
        };
    }

    public ActionListener getConvertir() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cripto = selectedCripto;
                double cantidad = Double.parseDouble(textField.getText());

               // double cotizacion = dataController.getCotizacion(cripto); // Implement this method to get the exchange rate
                //double resultado = cantidad / cotizacion;

                //conversionResultLabel.setText("Resultado: " + resultado);
            }
        };
    }

    public void updateStockAndPrice(String cripto) {
       // double stock = dataController.getStock(cripto); // Implement this method to get the stock
        //double price = dataController.getPrice(cripto); // Implement this method to get the price

        //stockLabel.setText(String.valueOf(stock));
        //priceLabel.setText(String.valueOf(price));
    }

    public void setSelectedCripto(String cripto) {
        this.selectedCripto = cripto;
        updateStockAndPrice(cripto);
    }


    public void setStockLabel(JLabel stockLabel) {
        this.stockLabel = stockLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}
