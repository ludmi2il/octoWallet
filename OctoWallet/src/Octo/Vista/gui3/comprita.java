package Octo.Vista.gui3;

import Octo.Controlador.Vistas.ControllerComprita;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class comprita extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public comprita(JPanel mainPanel, ControllerComprita controller) {
		setBackground(new Color(255, 255, 255));
		
		this.mainPanel=mainPanel;
		
		JLabel lblNewLabel = new JLabel("OctoWallet - Compra");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(128, 128, 128));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_5 = new JLabel("Quiero comprar con");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		controller.setTextField(textField);

		JButton btnNewButton = new JButton("Convertir\r\n");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(165, 199, 183));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_6 = new JLabel("Equivale a....");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_7 = new JLabel("Algo\r\n");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ARS", "USD"}));
		controller.setComboBox(comboBox);

		JButton btnNewButton_1 = new JButton("Realizar Compra");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(151, 177, 249));
		btnNewButton_1.addActionListener(controller.getComprarActionListener());
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(151, 177, 249));
		btnNewButton_2.addActionListener(controller.getCancelarActionListener());
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Elegir cripto a comprar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BTC", "ETH", "DOGE", "USDC", "USDT"}));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(6))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(29)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(btnNewButton))
								.addComponent(lblNewLabel_7, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGap(509)))
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(79)
					.addComponent(btnNewButton_1)
					.addGap(87)
					.addComponent(btnNewButton_2)
					.addContainerGap(538, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(662, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_7))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(34))
		);
		setLayout(groupLayout);

	}
}
