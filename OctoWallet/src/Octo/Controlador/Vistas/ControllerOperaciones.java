package Octo.Controlador.Vistas;


import Octo.Modelo.JDBC.DaoTransaccionImpl;
import Octo.Modelo.Entidad.Transaccion;
import Octo.Vista.gui3.operaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerOperaciones {
    private JPanel mainPanel;
    private JPanel textArea;
    private DaoTransaccionImpl daoTransaccion;
    private int cantTransacciones;
    public ControllerOperaciones(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.daoTransaccion = new DaoTransaccionImpl();
    }
    public ActionListener getVolverActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainPanel.getLayout();
                cl.show(mainPanel, "misActivos");
            }
        };
    }

    public void actualizarTransacciones(operaciones mainPanel1) {
            List<Transaccion> transacciones = daoTransaccion.listar();
            if ((transacciones!= null) && (transacciones.size() > cantTransacciones)){
                for (int i = cantTransacciones; i<transacciones.size(); i++) {
                    String[] partes = transacciones.get(i).getResumen().split(",");
                    String tipo = "Transacción: " + partes[0];
                    String monto = partes[1];
                    mainPanel1.agregarTransaccion(tipo,monto,transacciones.get(i).getFechaHora().toString());
                }
                cantTransacciones= transacciones.size();
            }
    }
}

