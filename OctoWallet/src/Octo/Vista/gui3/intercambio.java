package Octo.Vista.gui3;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

import Octo.Controlador.Sesion;
import Octo.Controlador.Vistas.ControllerIntercambio;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;

public class intercambio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JPanel mainPanel;
	/**
	 * Create the panel.
	 */
	public intercambio(JPanel mainPanel, ControllerIntercambio controller) {
		this.mainPanel=mainPanel;
		
		JLabel lblNewLabel = new JLabel("OctoWallet - Swap");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(128, 128, 128));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		controller.setUserNameLabel(lblNewLabel_1);

		JButton btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(controller.getCerrarSesion());
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(151, 177, 249));
		
		JLabel lblNewLabel_2 = new JLabel("Crypto a Vender:\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_3 = new JLabel("Crypto a Comprar:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_4 = new JLabel("Monto:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		textField = new JTextField();
		textField.setColumns(10);
		controller.setTextField(textField);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"BTC", "ETH", "DOGE", "USDC", "USDT"}));
		controller.setComboBox(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BTC", "ETH", "DOGE", "USDC", "USDT"}));
		controller.setComboBox_1(comboBox_1);

		JButton btnNewButton_1 = new JButton("Realizar Swap\r\n");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(151, 177, 249));
		btnNewButton_1.addActionListener(controller.getRealizarSwap());
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_2 = new JButton("Volver");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(151, 177, 249));
		btnNewButton_2.addActionListener(controller.getVolverActionListener());
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(intercambio.class.getResource("/imagenes/pulpito.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(122)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(13, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox, Alignment.LEADING, 0, 71, Short.MAX_VALUE)))
					.addGap(185))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(btnNewButton_1)
					.addGap(111)
					.addComponent(btnNewButton_2)
					.addContainerGap(138, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel)
							.addComponent(lblNewLabel_5))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton))))
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				controller.ModificarUserName();
			}
		});
	}

}
