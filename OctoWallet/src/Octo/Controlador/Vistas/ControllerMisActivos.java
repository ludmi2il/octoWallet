package Octo.Controlador.Vistas;

import Octo.Controlador.DataController;
import Octo.Controlador.Sesion;
import Octo.Controlador.Utilitario.ExportCSV;
import Octo.Modelo.Entidad.Activo;
import Octo.Modelo.Entidad.Moneda;
import Octo.Modelo.JDBC.SQLManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    public ActionListener getGenerarDatos(DefaultTableModel table) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activos.clear();
                SQLManager.getInstancia().getCrypto().borrado(Sesion.getInstance().getUserResult().getUserId());// deberia borrar activos del usuario
                List<String> criptosMVP = Arrays.asList("BTC", "ETH", "usdc");
                List<Moneda> monedas = new ArrayList<>();
                criptosMVP.stream().forEach(cripto -> monedas.add(SQLManager.getInstancia().getMoneda().obtener(cripto.toLowerCase())));
                System.out.println(criptosMVP);
                DataController d = new DataController();
                activos = d.crearActivosDefault(monedas);
                System.out.println(activos);
                cargarDatosEnTabla(table);
                JOptionPane.showMessageDialog(null, "datos de prueba generados correctamente.");

            }
        };
    }
        public void cargarDatosEnTabla(DefaultTableModel table){
           activos = SQLManager.getInstancia().getCrypto().listar(Sesion.getInstance().getUserResult().getUserId());
           table.setRowCount(0);
            for (Activo activo : activos) {
                try {
                    table.addRow(new Object[] {
                            new ImageIcon(new ImageIcon(new URL(SQLManager.getInstancia().getMoneda().obtener(activo.getMoneda().getNomenclatura()).getImagen())).getImage().getScaledInstance(32,32, Image.SCALE_SMOOTH)),
                            activo.getMoneda().getNombre(),
                            activo.getSaldo()
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            };
        };
    }