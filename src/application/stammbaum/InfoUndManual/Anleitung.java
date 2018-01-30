package application.stammbaum;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;

public class Anleitung extends JFrame {
	public Anleitung() {
		getContentPane().setBackground(new Color(255, 255, 255));
		Toolkit tk = Toolkit.getDefaultToolkit();
		int width = (int) tk.getScreenSize().getWidth();
		int height = (int) tk.getScreenSize().getHeight();
		setBounds(width/4, height/4, 800, 550);
		getContentPane().setLayout(null);

		JLabel lblHierKommtDer = new JLabel("Startfenster:"
				+ "");
		lblHierKommtDer.setForeground(new Color(0, 153, 255));
		lblHierKommtDer.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblHierKommtDer.setBounds(6, 34, 900, 16);
		getContentPane().add(lblHierKommtDer);

		JLabel lblUeberDenButton = new JLabel("-Ueber den Button \"neuen Stammbaum generieren\" gelangt man in den Arbeitsbereich mit einem leeren Stammbaum");
		lblUeberDenButton.setForeground(new Color(0, 153, 255));
		lblUeberDenButton.setBounds(6, 62, 900, 25);
		getContentPane().add(lblUeberDenButton);

		JLabel lblNewLabel = new JLabel("-Durch \" Stammbaum laden\" kann man an einem zuvor gespeicherten Stammbaum weiterarbeiten");
		lblNewLabel.setForeground(new Color(0, 153, 255));
		lblNewLabel.setBounds(6, 84, 900, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("-Durch \" Stammbaum loeschen\" kann man, falls nicht mehr benoetigt, zuvor gespeicherten Stammbaume entfernen ");
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setBounds(6, 106, 900, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("-Ueber \" Einstellungen\" gelangt man in Die Programm-Einstellungen");
		lblNewLabel_2.setForeground(new Color(0, 153, 255));
		lblNewLabel_2.setBounds(6, 128, 900, 16);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("-Mit \" Impressum\" erhaelt man alle relevanten Entwickler-Informationen");
		lblNewLabel_3.setForeground(new Color(0, 153, 255));
		lblNewLabel_3.setBounds(6, 150, 900, 16);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("-Mit dem \" Save\"-Button kann man seinen erzeugten Stammbaum mithilfe des Speicherbildschirms abspeichern");
		lblNewLabel_4.setForeground(new Color(0, 153, 255));
		lblNewLabel_4.setBounds(6, 218, 900, 16);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("-Mit dem \" Open\"-Button kann man abgespeicherte Stammbaume oeffnen um weiter an ihnen zu arbeiten");
		lblNewLabel_5.setForeground(new Color(0, 153, 255));
		lblNewLabel_5.setBounds(6, 240, 900, 16);
		getContentPane().add(lblNewLabel_5);

		JLabel lblHauptmenue = new JLabel("Hauptmenue Liste horizontal:");
		lblHauptmenue.setForeground(new Color(0, 153, 255));
		lblHauptmenue.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblHauptmenue.setBounds(6, 190, 216, 16);
		getContentPane().add(lblHauptmenue);

		JLabel lblNewLabel_6 = new JLabel("-Ueber den \"Drucken\"-Button oeffnet sich der Drucker-Dialog um den momentanen Stammbaum auszudrucken");
		lblNewLabel_6.setForeground(new Color(0, 153, 255));
		lblNewLabel_6.setBounds(6, 262, 696, 16);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("-Das Lupensymbol veraendert die derzeitig angezeigte Groesse des Stammbaums");
		lblNewLabel_7.setForeground(new Color(0, 153, 255));
		lblNewLabel_7.setBounds(6, 284, 696, 16);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("-Durch druecken des \"I\"-Buttons gelangt man zum Impressum um Informationen ueber die Entwickler zu erlangen");
		lblNewLabel_8.setForeground(new Color(0, 153, 255));
		lblNewLabel_8.setBounds(6, 306, 727, 16);
		getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Hauptmenue Liste vertikal:");
		lblNewLabel_9.setForeground(new Color(0, 153, 255));
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_9.setBounds(6, 346, 714, 16);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("-Mit dem \"+\" und dem \"-\" Button auf der einzelnen Person kann man eine Person hinzufuegen oder entfernen");
		lblNewLabel_10.setForeground(new Color(0, 153, 255));
		lblNewLabel_10.setBounds(6, 374, 714, 16);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("-Mit dem \"Werkzeug\"-Button der Einzelperson kann man die verschiedenen Person bearbeiten (z.b. Foto hinzufuegen)");
		lblNewLabel_11.setForeground(new Color(0, 153, 255));
		lblNewLabel_11.setBounds(6, 396, 749, 16);
		getContentPane().add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("-Der \"+\" und der \"-\"-Button auf den 2 Personen bietet die Möglichkeit eine Beziehung hinzuzufuegen oder zu löschen ");
		lblNewLabel_12.setForeground(new Color(0, 153, 255));
		lblNewLabel_12.setBounds(6, 418, 785, 16);
		getContentPane().add(lblNewLabel_12);

		JLabel lblmitDemwerkzeugbutton = new JLabel("-Mit dem \"Werkzeug\"-Button auf 2 Personen kann eine bestehende Beziehung veraendert werden");
		lblmitDemwerkzeugbutton.setForeground(new Color(0, 153, 255));
		lblmitDemwerkzeugbutton.setBounds(6, 440, 737, 16);
		getContentPane().add(lblmitDemwerkzeugbutton);

		JLabel lblNewLabel_13 = new JLabel("-Der \"Gruppen\"-Button bietet die Moeglichkeit mehrere Personen in ein Beziehungsgeflecht zu setzen ");
		lblNewLabel_13.setForeground(new Color(0, 153, 255));
		lblNewLabel_13.setBounds(6, 462, 737, 16);
		getContentPane().add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("HILFEBILDSCHIRM");
		lblNewLabel_14.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel_14.setBounds(306, 6, 245, 16);
		getContentPane().add(lblNewLabel_14);
	}
}
