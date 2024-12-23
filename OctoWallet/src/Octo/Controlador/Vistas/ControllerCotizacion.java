package Octo.Controlador.Vistas;

import Octo.Controlador.DataController;
import Octo.Controlador.Sesion;
import Octo.Modelo.Entidad.Moneda;
import Octo.Vista.gui3.cotizacion;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControllerCotizacion {
        private JLabel userNameLabel;
        private JPanel mainPanel;
        private DataController dataControl;
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        public ControllerCotizacion(JPanel mainPanel) {
            this.mainPanel = mainPanel; this.dataControl = new DataController();
            Sesion.getInstance().setMonedasDisponibles(dataControl.getCacheMonedas());
        }
    public void iniciarActualizaciones(cotizacion c) {
        Runnable tareaActualizacion = () -> {
            dataControl.ActualizarCotizaciones();
            SwingUtilities.invokeLater(() -> {
                System.out.println(dataControl.getCacheMonedas());
                c.actualizarCotizaciones(dataControl.getCacheMonedas());
            });
        };
        scheduler.scheduleAtFixedRate(tareaActualizacion, 0, 20, TimeUnit.SECONDS);
    }
    public void detenerActualizaciones() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public ActionListener getCerrarSesion(){
          return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sesion.getInstance().cerrarSesion();
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "login");
            }
        };
       }

        public ActionListener getVolverActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "misActivos");
                }
            };
        }
        public ActionListener getComprarActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Sesion.getInstance().setCriptoCompra(e.getActionCommand());
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "comprita");
                }
            };
        }
        public ActionListener getSwapActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Sesion.getInstance().setCriptoCompra(e.getActionCommand());
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "intercambio");
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
}
