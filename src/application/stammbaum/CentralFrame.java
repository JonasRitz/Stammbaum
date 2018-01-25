package application.stammbaum;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

public class CentralFrame extends JPanel {
	protected JFrame parent;
	private HashMap<Person, JLabel> persons;
	
	// Konstruktor
	public CentralFrame(JFrame parent){
		this.parent = parent;
		this.persons = new HashMap<>();
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
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
		
		// Bild auslesen
		String src = p.getImageSource();
		// Infos auslesen
		String infos = "<html>" + p;
		if (p.getGeburtsdatum() != null) {
			infos += "<p>* " + p.getGeburtsdatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		if (p.getSterbedatum() != null) {
			infos += "<p>+" + p.getSterbedatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		infos += "<html>";
		// Label erstellen
		JLabel label = new JLabel(infos, SwingConstants.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setBorder(new LineBorder(Color.BLACK, 1, true));
		if (src != null) {
			ImageIcon icon = this.setIcon(src);
			label.setIcon(icon);
			label.setIconTextGap(10);
		}
		this.add(label);
		this.persons.put(p, label);
		this.parent.setVisible(true);
		
		if (this.persons.size() >= 3) {
			Person[] personen = new Person[3];
			int i = 0;
			for (Person pers: this.persons.keySet()) {
				personen[i] = pers;
				i++;
			}
			addBeziehung(personen[0], personen[1], personen[2]);
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
	
	protected void editPerson(Person p) {
		
		for (Person pers: this.persons.keySet()) {
			if (pers == p) {
				String src = p.getImageSource();
				String infos = "<html>" + p;
				if (p.getGeburtsdatum() != null) {
					infos += "<p>* " + p.getGeburtsdatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
				}
				if (p.getSterbedatum() != null) {
					infos += "<p>+" + p.getSterbedatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
				}
				infos += "<html>";
				this.persons.get(p).setText(infos);
				if (src != null) {
					ImageIcon icon = this.setIcon(src);
					this.persons.get(p).setIcon(icon);
					this.persons.get(p).setIconTextGap(10);
				}
				break;
			}
		}
		this.parent.repaint();		
	}
	
	// Beziehung hinzufuegen
	// ueber Beziehung auf Person auf JLabels zugreifen --> Positionen
	protected void addBeziehung(Person vater, Person mutter, Person kind) {
		
		Point pos_v = this.persons.get(vater).getLocation();
		Point pos_m = this.persons.get(vater).getLocation();
		ImageIcon icon = this.setIcon("src/data/icons/source/strich.png");
		this.add(new JLabel(icon));
		
		this.parent.setVisible(true);
	}
}
