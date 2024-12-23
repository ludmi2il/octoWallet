package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;
import Octo.Controlador.Utilitario.FiatConsumo;
import Octo.Exceptions.OctoNotFound;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.Entidad.userResult;
import Octo.Modelo.JDBC.DaoTransaccionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import Octo.Controlador.DataController;
import Octo.Modelo.JDBC.SQLManager;


public class ControllerComprita {

    private JPanel mainPanel;
    private DaoTransaccionImpl daoTransaccion;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JLabel stockLabel;
    private JLabel priceLabel;

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
                String fiat = (String) comboBox.getSelectedItem();

                double cantidad = Double.parseDouble(textField.getText());

                long criptoId = Sesion.getInstance().getIdCriptotByNom(Sesion.getInstance().getCriptoCompra());
                assert fiat != null;
                long fiatId = FiatConsumo.getFiatId(fiat);

                try {
                    SQLManager.getInstancia().getTransaccion().comprarCriptoMonedas(criptoId,fiatId ,cantidad);
                    JOptionPane.showMessageDialog(mainPanel, "Compra realizada con éxito.");
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "cotizacion");
                } catch (OctoNotFound o) {
                    JOptionPane.showMessageDialog(mainPanel, "La compra no se pudo realizar. No tienes saldo suficiente");
                }
            }
        };
    }

    public ActionListener getConvertir(JLabel label1) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad antes de convertir.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    String cripto = Sesion.getInstance().getCriptoCompra();
                    double cantidad = Double.parseDouble(textField.getText())*FiatConsumo.getFiat(comboBox.getSelectedItem().toString()).getCotizacion();
                    Optional<Moneda> monedaEncontrada = Sesion.getInstance().getMonedasDisponibles().stream()
                            .filter(moneda -> moneda.getNomenclatura().equals(cripto)).findFirst();

                    if (monedaEncontrada.isPresent()) {
                        Moneda moneda = monedaEncontrada.get();
                        double cotizacion = moneda.getCotizacion();
                        System.out.println(cotizacion);
                        // Realiza operaciones con la moneda y la cantidad
                        double total = cantidad / cotizacion;
                        System.out.println("Total a pagar: " + total);
                        label1.setText(String.format("$%.2f", total));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, no se tienen datos de esa moneda.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    public void updateStockAndPrice(String cripto) {
        try {
            double stock = Sesion.getInstance().getStockByNom(cripto);
            double price = Sesion.getInstance().getCotizacionByNom(cripto);
            stockLabel.setText(String.format("%.2f", stock)); // Mostrar con 2 decimales
            priceLabel.setText(String.format("%.2f", price));
        } catch (Exception e) {
            stockLabel.setText("N/A");
            priceLabel.setText("N/A");
            JOptionPane.showMessageDialog(null,"Error al conseguir stock y precio, intente más tarde");
        }
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
