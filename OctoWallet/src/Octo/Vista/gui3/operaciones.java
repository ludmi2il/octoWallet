package Octo.Vista.gui3;

import Octo.Controlador.Vistas.ControllerOperaciones;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class operaciones extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;

    /**
     * Create the panel.
     */
    public operaciones(JPanel mainPanel, ControllerOperaciones controller) {
        
        this.mainPanel = mainPanel;
        setBackground(new Color(236, 236, 236));
        
        JLabel lblNewLabel = new JLabel("OctoWallet - Mis Operaciones");
        lblNewLabel.setForeground(new Color(128, 128, 128));
        lblNewLabel.setBackground(new Color(128, 128, 128));
        
        JSeparator separator = new JSeparator();
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(151, 177, 249));
        btnNewButton.addActionListener(controller.getVolverActionListener());
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane();
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(separator, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(276, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(161)
        			.addComponent(btnNewButton)
        			.addContainerGap(218, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(78)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(57, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
        			.addGap(41)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnNewButton)
        			.addContainerGap(96, Short.MAX_VALUE))
        );
        
        JTextArea txtrDfsdsd = new JTextArea();
        txtrDfsdsd.setForeground(new Color(0, 0, 0));
        txtrDfsdsd.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtrDfsdsd.setEnabled(false);
        txtrDfsdsd.setEditable(false);
        controller.setTextArea(txtrDfsdsd);
        scrollPane.setViewportView(txtrDfsdsd);
        setLayout(groupLayout);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                controller.actualizarTextArea();
            }
        });
    }
}
