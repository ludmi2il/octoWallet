package Octo.Vista.gui3;

import Octo.Controlador.Vistas.ControllerOperaciones;
import Octo.Modelo.Entidad.Transaccion;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class operaciones extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JPanel transacciones;

    public operaciones(JPanel mainPanel, ControllerOperaciones controller) {

        this.mainPanel = mainPanel;
        setBackground(new Color(236, 236, 236));

        JLabel lblTitulo = new JLabel("OctoWallet - Mis Operaciones");
        lblTitulo.setForeground(new Color(128, 128, 128));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        JSeparator separator = new JSeparator();

        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(151, 177, 249));
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVolver.addActionListener(controller.getVolverActionListener());

        // Panel para contener las tarjetas
        JPanel panelTransacciones = new JPanel();
        panelTransacciones.setLayout(new BoxLayout(panelTransacciones, BoxLayout.Y_AXIS));
        panelTransacciones.setBackground(new Color(236, 236, 236));

        // Scroll para las transacciones
        JScrollPane scrollPane = new JScrollPane(panelTransacciones);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        /*
        for (int i = 1; i <= 10; i++) { // Simula 5 transacciones
            panelTransacciones.add(crearTarjetaTransaccion("Transacción " + i, "$100.00", "22/12/2024"));
        }
        */
        // Layout principal
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(separator, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo)
                                .addContainerGap(300, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(175)
                                .addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(175, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnVolver)
                                .addContainerGap())
        );

        setLayout(groupLayout);
        transacciones=panelTransacciones;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                controller.actualizarTransacciones(operaciones.this);
            }
        });
    }
    public void agregarTransaccion(String transaccion, String monto,String fecha) {
        JPanel nuevaTarjeta = crearTarjetaTransaccion(transaccion,monto,fecha);
        transacciones.add(nuevaTarjeta);
        transacciones.revalidate(); // Actualiza el diseño del panel
        transacciones.repaint();// Redibuja el panel
    }
    private JPanel crearTarjetaTransaccion(String titulo, String monto, String fecha) {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        tarjeta.setLayout(new GridLayout(2, 2));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblMonto = new JLabel(monto);
        lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Tahoma", Font.ITALIC, 12));
        lblFecha.setForeground(new Color(150, 150, 150));

        tarjeta.add(lblTitulo);
        tarjeta.add(lblMonto);
        tarjeta.add(lblFecha);

        return tarjeta;
    }
}

