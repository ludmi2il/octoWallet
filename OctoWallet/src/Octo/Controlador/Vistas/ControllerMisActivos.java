package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;
import Octo.Controlador.Utilitario.ExportCSV;
import Octo.Modelo.Entidad.Activo;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ControllerMisActivos {
    private JPanel mainPanel;
    private List<Activo> activos = new ArrayList<>();
    private JLabel userNameLabel;

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

    public ActionListener getExportarActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ExportCSV.exportToCSV(activos);
                    JOptionPane.showMessageDialog(null, "Archivo CSV exportado con éxito a la carpeta Descargas.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al exportar el archivo CSV: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public ActionListener getCerrarSesion() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sesion.getInstance().cerrarSesion();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "login");
            }
        };
    }

    public ActionListener getGenerarDatos() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activos.clear();
                JOptionPane.showMessageDialog(null, "datos de prueba generados correctamente.");
            }
        };
    }
}