package Octo.Vista;

import Octo.Controlador.Control;
import Octo.Vista.gui3.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class Principal {

    public static void main(String[] args) {
        /*try {
            // Configuración del Look and Feel de flatlaf
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

         */
        Control control = new Control();
        vistas log=control.getprinc();
        log.setVisible(true);
    }
}

