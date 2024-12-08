package Octo.Vista.gui3;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cotizacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public cotizacion(JPanel mainPanel) {
		
		JLabel lblNewLabel = new JLabel("OctoWallet - Cotizaciones");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("nameUser");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(151, 177, 249));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Cripto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_3 = new JLabel("Precio de Compra\r\n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnNewButton_1 = new JButton("Compra\r\n");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "comprita");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_2 = new JButton("Compra");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 128, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "comprita");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_3 = new JButton("Compra");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 128, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "comprita");
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_4 = new JButton("Compra");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(0, 128, 0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "comprita");
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_5 = new JButton("Compra");
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setBackground(new Color(0, 128, 0));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "comprita");
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_6 = new JButton("Swap");
		btnNewButton_6.setBackground(new Color(255, 128, 0));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "intercambio");
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_7 = new JButton("Swap");
		btnNewButton_7.setBackground(new Color(255, 128, 0));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "intercambio");
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_8 = new JButton("Swap");
		btnNewButton_8.setBackground(new Color(255, 128, 0));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "intercambio");
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_9 = new JButton("Swap");
		btnNewButton_9.setBackground(new Color(255, 128, 0));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "intercambio");
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_10 = new JButton("Swap");
		btnNewButton_10.setBackground(new Color(255, 128, 0));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "intercambio");
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Bitcoin(BTC)");
		lblNewLabel_4.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/bitcoin.png")));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_5 = new JLabel("Ethereum(ETH)");
		lblNewLabel_5.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/ethereum.png")));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_6 = new JLabel("Usdc(USDC)");
		lblNewLabel_6.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/USDC.png")));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_7 = new JLabel("Tether(USDT)");
		lblNewLabel_7.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/tether.png")));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_8 = new JLabel("Dodgecoin(DOGE)");
		lblNewLabel_8.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/Dogecoin.png")));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_9 = new JLabel("$66,788.39");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_10 = new JLabel("$2,200");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_11 = new JLabel("$0.88");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_12 = new JLabel("$0.99\r\n");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_13 = new JLabel("$0.25");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_11 = new JButton("Volver");
		btnNewButton_11.setForeground(new Color(255, 255, 255));
		btnNewButton_11.setBackground(new Color(151, 177, 249));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "misActivos");
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon(cotizacion.class.getResource("/imagenes/pulpito.png")));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 595, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(458, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(408, Short.MAX_VALUE)
					.addComponent(lblNewLabel_14)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(36))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_6))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_7))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_8)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_5)
										.addComponent(btnNewButton_4))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_9)
										.addComponent(btnNewButton_10)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_3)
									.addGap(18)
									.addComponent(btnNewButton_8))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_2)
									.addGap(18)
									.addComponent(btnNewButton_7))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1)
									.addGap(18)
									.addComponent(btnNewButton_6))))
						.addComponent(lblNewLabel_3))
					.addContainerGap(89, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(240)
					.addComponent(btnNewButton_11)
					.addContainerGap(284, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(72)
									.addComponent(lblNewLabel_2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_3)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(45)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_5)
												.addComponent(lblNewLabel_10)
												.addComponent(btnNewButton_2)
												.addComponent(btnNewButton_7))
											.addGap(23)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_11)
												.addComponent(btnNewButton_3)
												.addComponent(btnNewButton_8)
												.addComponent(lblNewLabel_6)))
										.addComponent(lblNewLabel_9)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton_1)
										.addComponent(btnNewButton_6))))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_12)
								.addComponent(btnNewButton_9)
								.addComponent(btnNewButton_4)
								.addComponent(lblNewLabel_7))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_13)
								.addComponent(btnNewButton_5)
								.addComponent(btnNewButton_10)
								.addComponent(lblNewLabel_8))
							.addGap(42)
							.addComponent(btnNewButton_11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_14)
							.addGap(66)
							.addComponent(lblNewLabel_4)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
