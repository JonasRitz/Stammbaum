package application.stammbaum;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

public class CentralFrame extends JPanel {
	public int count = 0;
	private boolean drawAll = false;
	private int x;
	private int y;
	protected JFrame parent;
	private HashMap<Person, JLabel> persons;
	
	// Konstruktor
	public CentralFrame(JFrame parent){
		this.parent = parent;
		this.setVisible(true);
		this.persons = new HashMap<>();
	}

	// Icon skalieren
	protected ImageIcon setIcon(String file) {
		ImageIcon icon = new ImageIcon(file);
		Image img = icon.getImage();
		int scale = 0;
		if(icon.getIconHeight() > icon.getIconWidth()){
			scale = icon.getIconHeight()/100;
		}else{
			scale = icon.getIconWidth()/100;
		}
		Image newimg = img.getScaledInstance(icon.getIconWidth()/scale, icon.getIconHeight()/scale, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}

	// Person hinzufuegen
	protected void addPerson(Person p) {
		String src = p.getImageSource();
		String infos = "<html>" + p;
		if (p.getGeburtsdatum() != null) {
			infos += "<p>* " + p.getGeburtsdatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		if (p.getSterbedatum() != null) {
			infos += "<p>+" + p.getSterbedatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		infos += "<html>";
		JLabel label;
		if (src == null) {
			label = new JLabel(infos, SwingConstants.CENTER);
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setBorder(new LineBorder(Color.BLACK, 1, true));
		} else {
			ImageIcon icon = this.setIcon(src);
			label = new JLabel(infos, icon, SwingConstants.CENTER);
			label.setIconTextGap(10);
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setBorder(new LineBorder(Color.BLACK, 1, true));
		}
		this.add(label);
		this.persons.put(p, label);
		this.parent.setVisible(true);
		
		if (this.persons.size() >= 2) {
			JLabel[] labels = new JLabel[2];
			int i = 0;
			for (Person pers: this.persons.keySet()) {
				labels[i] = this.persons.get(pers);
				i++;
			}
			addBeziehung(labels[0], labels[1]);
		}
	}
	
	protected void removePerson(Person p) {
		
		for (Person pers: this.persons.keySet()) {
			if (pers == p) {
				this.remove(this.persons.get(p));
				this.persons.remove(p);
				break;
			}
		}
		this.parent.repaint();
	}
	
	// Beziehung hinzufuegen
	// ueber Beziehung auf Person auf JLabels zugreifen --> Positionen
	protected void addBeziehung(JLabel l1, JLabel l2) {
		
		System.out.println(l1.getLocation());
		System.out.println(l2.getLocation());
		Graphics g = this.getGraphics();
		g.drawLine(l1.getLocation().x, l1.getLocation().y, l2.getLocation().x, l2.getLocation().y);
		this.parent.repaint();
	}
}
