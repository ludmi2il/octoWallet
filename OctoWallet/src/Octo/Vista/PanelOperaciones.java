package Octo.Vista;

import Octo.Controlador.ControladorAIO;
import Octo.Modelo.Entidad.Transaccion;
import Octo.Modelo.JDBC.SQLManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelOperaciones extends JPanel{
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    public PanelOperaciones() {
        setLayout(new BorderLayout());

        // Header del panel
        JLabel titulo = new JLabel("Historial de Transacciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // TABLA SETEADA
        String[] columnas = {"Fecha", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 10);
        tabla = new JTable(modeloTabla);

        // para scrollear
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void cargarTransacciones(List<Transaccion> transacciones) {
        modeloTabla.setRowCount(0); // Limpiar datos previos
        for (Transaccion t : transacciones) {
            modeloTabla.addRow(new Object[]{t.getFechaHora(),t.getResumen()});
        }
    }

    /*public static void main(String[] args) {
        try {
            // Configuración del Look and Feel de flatlaf
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Historial de Transacciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);


        // Usar el panel con tabla
        PanelOperaciones panel = new PanelOperaciones();
        List<Transaccion> t = SQLManager.getInstancia().getTransaccion().listar();
        System.out.println(t);
        panel.cargarTransacciones(t);

        frame.add(panel);
        frame.setVisible(true);
    }

     */
    }



