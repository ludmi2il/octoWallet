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

import javax.swing.*;

public class Principal {


    public static void main(String[] args) {

        //SQLManager.getInstancia().getUsuario().crear(new User("Ludmi", "luwu", "1234", "Dosil", true));
        Control control = new Control();
        vistas log=control.getprinc();

        log.setIconImage(new ImageIcon("/imagenes/pulpito.png").getImage());
        log.setVisible(true);

    }
}

