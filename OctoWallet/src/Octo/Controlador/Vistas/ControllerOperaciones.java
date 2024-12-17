package Octo.Controlador.Vistas;


import Octo.Modelo.JDBC.DaoTransaccionImpl;
import Octo.Modelo.Entidad.Transaccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerOperaciones {
    private JPanel mainPanel;
    private JTextArea textArea;
    private DaoTransaccionImpl daoTransaccion;
    public ControllerOperaciones(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.daoTransaccion = new DaoTransaccionImpl();
        this.daoTransaccion.cargarTransaccionesDePrueba();
    }
    public ActionListener getVolverActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "misActivos");
            }
        };
    }

    public void actualizarTextArea() {
        if (textArea != null) {
            List<Transaccion> transacciones = daoTransaccion.listar();
            StringBuilder sb = new StringBuilder();
            for (Transaccion transaccion : transacciones) {
                sb.append(transaccion.toString()).append("\n");
            }
            textArea.setText(sb.toString());
        } else {
            System.err.println("textArea is not initialized.");
        }
    }
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
