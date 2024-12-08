package Octo.Vista.gui3;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class operaciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public operaciones(JPanel mainPanel) {
		
		this.mainPanel=mainPanel;
		setBackground(new Color(240, 240, 240));
		
		JLabel lblNewLabel = new JLabel("OctoWallet - Mis Operaciones");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		
		JSeparator separator = new JSeparator();
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(255, 255, 255));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(126, 181, 254));
		scrollBar.setBackground(new Color(148, 200, 252));
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(151, 177, 249));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)mainPanel.getLayout();
				cl.show(mainPanel, "misActivos");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(273, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(161)
					.addComponent(btnNewButton)
					.addContainerGap(218, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
