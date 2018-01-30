package application.stammbaum;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;
import java.awt.Font;

public class Impressum extends JFrame {
	private JPanel contentPane;
	public Impressum() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int labelxsize = (int) tk.getScreenSize().getWidth()/3; 
		int labelnysize = (int) tk.getScreenSize().getHeight()/10;
		setBounds(150, 100, 450, 200);
		this.setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Frederik Peters,");
		lblNewLabel.setForeground(new Color(0, 153, 255));
		lblNewLabel.setBounds(6, 86, 100, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Jonas Ritz,");
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setBounds(108, 86, 68, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Julia Thoenissen");
		lblNewLabel_2.setForeground(new Color(0, 153, 255));
		lblNewLabel_2.setBounds(181, 86, 112, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblImpressum = new JLabel("IMPRESSUM");
		lblImpressum.setForeground(new Color(0, 153, 255));
		lblImpressum.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblImpressum.setBounds(157, 19, 127, 16);
		contentPane.add(lblImpressum);
		
		JLabel lblSoftwaree = new JLabel("Software Engineering-Projekt");
		lblSoftwaree.setForeground(new Color(0, 153, 255));
		lblSoftwaree.setBounds(6, 114, 184, 16);
		contentPane.add(lblSoftwaree);
		
		JLabel lblForschungszentrumJuelich = new JLabel("Forschungszentrum Jülich");
		lblForschungszentrumJuelich.setForeground(new Color(0, 153, 255));
		lblForschungszentrumJuelich.setBounds(6, 142, 278, 16);
		contentPane.add(lblForschungszentrumJuelich);
	}
}
