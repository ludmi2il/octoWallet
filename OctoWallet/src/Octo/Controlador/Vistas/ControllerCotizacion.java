package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;
import Octo.Controlador.Utilitario.Actualizador;
import Octo.Servicios.AppServices.CacheCryptoService;
import Octo.Servicios.AppServices.DBStatus;
import Octo.Vista.gui3.cotizacion;
import Octo.Vista.gui3.vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControllerCotizacion {
        private JLabel userNameLabel;
        private JPanel mainPanel;
        private JPanel ContentPane;
        private CacheCryptoService cachemoneda = CacheCryptoService.getInstancia();
        private vistas views;
        private Actualizador updater;
        private cotizacion c;
        public ControllerCotizacion(JPanel mainPanel,JPanel ContentPane, vistas views) {
        	
            this.mainPanel = mainPanel;
            this.ContentPane = ContentPane;
            this.views = views;
        }
        public void addCotizacionView(cotizacion c){
            this.c= c;
        }
        public void launchUpdater(){
            this.updater= new Actualizador(this.cachemoneda, this.c);
        }
    public void iniciarActualizaciones() {

            updater.iniciarActualizaciones();
    }

    public ActionListener getCerrarSesion(){
          return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sesion.getInstance().cerrarSesion();
                //CardLayout cl = (CardLayout) mainPanel.getLayout();
                //cl.show(mainPanel, "login");
                showPanel("login");
            }
        };
       }

        public ActionListener getVolverActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //CardLayout cl = (CardLayout)mainPanel.getLayout();
                    //cl.show(mainPanel, "misActivos");
                    showPanel("misActivos");
                }
            };
        }
        public ActionListener getComprarActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Sesion.getInstance().setCriptoCompra(e.getActionCommand());
                   // CardLayout cl = (CardLayout)mainPanel.getLayout();
                    //cl.show(mainPanel, "comprita");
                    showPanel("comprita");
                }
            };
        }
        public ActionListener getSwapActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Sesion.getInstance().setCriptoCompra(e.getActionCommand());
                   //CardLayout cl = (CardLayout)mainPanel.getLayout();
                    //cl.show(mainPanel, "intercambio");
                    showPanel("intercambio");
                }
            };
        }
      public void setUserNameLabel(JLabel label) {
        this.userNameLabel = label;
      }

      public void ModificarUserName() {
        String nombre = Sesion.getInstance().getUser().getNombres() + " " + Sesion.getInstance().getUser().getApellidos();
        this.userNameLabel.setText(nombre);
      }
    public void showPanel(String name) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, name);
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible()) {
                Dimension preferredSize = comp.getPreferredSize();
                mainPanel.setPreferredSize(preferredSize);
                views.getContentPane().setPreferredSize(preferredSize);
                views.pack();
                views.setLocationRelativeTo(null);
                break;
            }
        }
    }
}
