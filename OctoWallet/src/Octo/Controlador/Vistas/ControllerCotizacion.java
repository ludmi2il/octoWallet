package Octo.Controlador.Vistas;

import Octo.Controlador.Sesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerCotizacion {
        private JLabel userNameLabel;
        private JPanel mainPanel;

        public ControllerCotizacion(JPanel mainPanel) {
            this.mainPanel = mainPanel;
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
                    CardLayout cl = (CardLayout)mainPanel.getLayout();
                    cl.show(mainPanel, "comprita");
                }
            };
        }
        public ActionListener getSwapActionListener() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
