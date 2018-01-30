package application.stammbaum;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowEvent;
public class Startbildschirm extends JFrame {
	private JPanel contentPane;
	public Startbildschirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int buttonxsize = (int) tk.getScreenSize().getWidth();
		int buttonysize = (int) tk.getScreenSize().getHeight();
		int x = buttonxsize/3;
		int y = buttonysize/3;
		int width = buttonxsize/3;
		int height = (int) (buttonysize/2.5);
		setBounds(x,y,width,height);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		//contentPane.setBorder(new EmptyBorder(400/7, 200, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JLabel lblStammbaumgenerator = new JLabel("Stammbaumgenerator");
		lblStammbaumgenerator.setForeground(new Color(0, 153, 255));
		lblStammbaumgenerator.setFont(new Font("Lucida Grande", Font.PLAIN, height/10));
		lblStammbaumgenerator.setAlignmentX(CENTER_ALIGNMENT);
		lblStammbaumgenerator.setBorder(new EmptyBorder(50,50,50,50));
		contentPane.add(lblStammbaumgenerator);

		JButton neuer_Stammbaum = new JButton(" Neuen Stammbaum generieren");
		neuer_Stammbaum.setBackground(new Color(204, 204, 204));
		neuer_Stammbaum.setForeground(new Color(0, 153, 255));
		neuer_Stammbaum.setFont(new Font("Lucida Grande", Font.PLAIN, height/20));
		neuer_Stammbaum.setAlignmentX(CENTER_ALIGNMENT);
		neuer_Stammbaum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Mainscreen screen = new Mainscreen();
					setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(neuer_Stammbaum);


		JButton Stammbaum_laden = new JButton("          Stammbaum laden         ");
		Stammbaum_laden.setForeground(new Color(0, 153, 255));
		Stammbaum_laden.setFont(new Font("Lucida Grande", Font.PLAIN, height/20));
		Stammbaum_laden.setAlignmentX(CENTER_ALIGNMENT);
		Stammbaum_laden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Mainscreen screen = new Mainscreen();
					screen.horizontal.get(2).doClick();
					setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(Stammbaum_laden);

		JButton Einstellungen = new JButton("                    Hilfe                    ");
		Einstellungen.setBackground(new Color(204, 204, 204));
		Einstellungen.setForeground(new Color(0, 153, 255));
		Einstellungen.setFont(new Font("Lucida Grande", Font.PLAIN, height/20));
		Einstellungen.setAlignmentX(CENTER_ALIGNMENT);
		Einstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Anleitung frame = new Anleitung();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(Einstellungen);

		JButton Impressum = new JButton("              Impressum                ");
		Impressum.setForeground(new Color(0, 153, 255));
		Impressum.setFont(new Font("Lucida Grande", Font.PLAIN, height/20));
		Impressum.setAlignmentX(CENTER_ALIGNMENT);
		Impressum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(Impressum);
	}

}
