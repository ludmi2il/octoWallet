package Octo.Vista.gui3;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import Octo.Vista.gui3.*;
import Octo.Controlador.Vistas.ControllerMisActivos;

public class vistas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public vistas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		mainPanel.setLayout(new CardLayout(0, 0));
		contentPane.setLayout(gl_contentPane);
		
		login card1 = new login(mainPanel);
		registro card2 = new registro(mainPanel);

		ControllerMisActivos conMisActivos = new ControllerMisActivos(mainPanel);
		misActivos card3 = new misActivos(mainPanel,conMisActivos);
		operaciones card4 = new operaciones(mainPanel);
		cotizacion card5 = new cotizacion(mainPanel);
		comprita card6 = new comprita(mainPanel);
		intercambio card7 = new intercambio(mainPanel);
		
		mainPanel.add(card1, "login");
		mainPanel.add(card2, "registro");
		mainPanel.add(card3, "misActivos");
		mainPanel.add(card4, "operaciones");
		mainPanel.add(card5, "cotizacion");
		mainPanel.add(card6, "comprita");
		mainPanel.add(card7, "intercambio");
		
	}
}
