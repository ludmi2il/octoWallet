package Octo.Vista;

import Octo.Controlador.Control;
import Octo.Modelo.Entidad.User;
import Octo.Modelo.JDBC.SQLManager;
import Octo.Vista.gui3.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.*;
import java.net.URL;

import javax.swing.*;

public class Principal {


    public static void main(String[] args) {

        Control control = new Control();
        vistas log = control.getprinc();
        log.setTitle("OctoWallet - Billetera Virtual");
        java.net.URL iconoUrl = Principal.class.getResource("/imagenes/pulpito.png");
        if (iconoUrl != null) {
            log.setIconImage(new ImageIcon(iconoUrl).getImage());
        } else {
            System.err.println("No se pudo encontrar la imagen: /imagenes/pulpito.png");
        }

        log.setVisible(true);

    }
}

