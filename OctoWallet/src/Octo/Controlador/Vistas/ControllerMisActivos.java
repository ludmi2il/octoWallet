package Octo.Controlador.Vistas;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ControllerMisActivos {
    private JPanel mainPanel;

    public ControllerMisActivos(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ActionListener getCotizacionesActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "cotizacion");
            }
        };
    }
    public ActionListener getTransaccionesActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "operaciones");
            }
        };
    }
}