package application.stammbaum;
//private Box box1;
//private Box box2;
//private Beziehung b;
//private boolean draw;
//private ArrayList<Box> boxes;
//Ziel: Eine Methode, die einen Stammbaum empfaengt und automatisch das passende Abbild davon zeichnen
// Konstruktor
//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//this.boxes = new ArrayList<Box>();
//this.boxes.add()
//box1 = Box.createHorizontalBox();
//box1.setAlignmentX(Box.CENTER_ALIGNMENT);
//box1.add(Box.createVerticalGlue());

//box2 = Box.createHorizontalBox();
//box2.setAlignmentX(Box.CENTER_ALIGNMENT);
//box2.add(Box.createVerticalGlue());

//this.add(box1);
//this.add(box2);
import java.awt.Insets;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.time.format.DateTimeFormatter;

import javax.swing.border.LineBorder;

public class CentralFrame extends JPanel {
	private Mainscreen parent;
	private HashMap<Person, JLabel> persons;
	boolean draw;
	private Stammbaum baum;
	private ArrayList<Person[]> hierarchie; //erstes Elem = unterstes

	public CentralFrame(Mainscreen parent, Stammbaum baum){
		this.draw = false;
		this.setLayout(null);
		this.parent = parent;
		this.persons = new HashMap<>();
		this.hierarchie = new ArrayList<>();
		this.baum = baum;
		this.setVisible(true);
	}

	protected void refreshAll(Stammbaum baum){
		this.removeAll();
		int personenOhneBeziehung = 0;
		for(Person p : parent.stammbaum.getPersonen()){
			if(baum.istInBeziehung(p)){
				System.out.println("Person mit Beziehung wird optisch hinzugefuegt.");
			}else{
				System.out.println("Person wird optisch hinzugefuegt: " + p.toString());
				JLabel personLabel = createJLabelOfPerson(p);
				Insets insets = this.getInsets();	
				Dimension size = personLabel.getPreferredSize();
				Dimension sizeOfJPanel = this.getSize();
				personLabel.setBounds(sizeOfJPanel.width-size.width-25 + insets.left, 25 + insets.top + personenOhneBeziehung*(size.height+25), size.width, size.height);
				this.add(personLabel);
				personLabel.repaint();
				personenOhneBeziehung++;
			}
		}
		if (this.baum.beziehungen.size() > 0) {
			calculateHead();
		}
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}

	protected void calculateHead() {

		ArrayList<Person[]> heads = new ArrayList<>();
		Person head_v = this.baum.beziehungen.get(0).vater;
		Person head_m = this.baum.beziehungen.get(0).mutter;
		Person[] head = {head_v, head_m};
		heads.add(head);
		
		for (Beziehung b: this.baum.beziehungen) {
			for (Person p : b.kinder) {
				boolean found = false;
				for (Person[] paar: heads) {
					if (paar[0] == p || paar[1] == p) {
						heads.remove(paar);
						Person[] new_head = {b.vater, b.mutter};
						heads.add(new_head);
						found = true;
						break;
					}
				}
				if (found == false) {
					if (!(b.vater == head[0] && b.mutter == head[1])) {
						Person[] new_head = {b.vater, b.mutter};
						heads.add(new_head);
					}
				}
			}
		}
	}


	/*protected void calculateHead() {

		Person head_v = this.baum.beziehungen.get(0).vater;
		Person head_m = this.baum.beziehungen.get(0).mutter;
		for (Beziehung b: this.baum.beziehungen) {
			for (Person p : b.kinder) {
				if(p == head_v || p == head_m) {
					head_v = b.vater;
					head_m = b.mutter;
				}
			}
		}
		System.out.println(head_v + " " + head_m);
	}*/

	protected Insets calculateInsets(Insets old){



		return null;
	}

	protected JLabel createJLabelOfPerson(Person p){
		//Erstellt ein Label an Hand einer Person und fuegt (Person, JLabel) der Hashmap hinzu
		String src = p.getImageSource();
		String infos = "<html>" + p;
		if (p.getGeburtsdatum() != null) {
			infos += "<p>*" + p.getGeburtsdatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		if (p.getSterbedatum() != null) {
			infos += "<p>†" + p.getSterbedatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
		}
		infos += "<html>";
		// Label erstellen
		JLabel label = new JLabel(infos, SwingConstants.CENTER);
		label.setMaximumSize(new Dimension(200, 100));
		label.setMinimumSize(new Dimension(200, 100));
		label.setPreferredSize(new Dimension(200, 100));
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setBorder(new LineBorder(Color.BLACK, 1, true));
		if (src != null) {
			ImageIcon icon = this.setIcon(src);
			label.setIcon(icon);
			label.setIconTextGap(10);
		}
		this.persons.put(p, label);
		return label;
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


	protected ImageIcon setIcon(String file) {
		ImageIcon icon = new ImageIcon(file);
		Image img = icon.getImage();
		int scale = 0;
		if(icon.getIconHeight() > icon.getIconWidth()){
			scale = icon.getIconHeight()/100;
		}else{
			scale = icon.getIconWidth()/100;
		}
		if (scale != 0) {
			Image newimg = img.getScaledInstance(icon.getIconWidth()/scale, icon.getIconHeight()/scale, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
		}

		return icon;
	}

	// Person hinzufuegen
	/*
		protected void addPerson(Person p) {
			// Bild auslesen
			String src = p.getImageSource();
			// Infos auslesen
			String infos = "<html>" + p;
			if (p.getGeburtsdatum() != null) {
				infos += "<p>*" + p.getGeburtsdatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
			}
			if (p.getSterbedatum() != null) {
				infos += "<p>†" + p.getSterbedatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</p>";
			}
			infos += "<html>";
			// Label erstellen
			JLabel label = new JLabel(infos, SwingConstants.CENTER);
			label.setMaximumSize(new Dimension(200, 100));
			label.setMinimumSize(new Dimension(200, 100));
			label.setPreferredSize(new Dimension(200, 100));
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setBorder(new LineBorder(Color.BLACK, 1, true));
			if (src != null) {
				ImageIcon icon = this.setIcon(src);
				label.setIcon(icon);
				label.setIconTextGap(10);
			}
			this.persons.put(p, label);
			this.parent.setVisible(true);
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
		/*
		protected void addRelation(Beziehung b) {
			this.draw = true;
			this.b = b;
			for (Person kind: b.kinder) {
	    		JLabel label = this.persons.get(kind);
	    		this.box2.add(label);
	    		this.box2.add(Box.createRigidArea(new Dimension(100,25)));
	    		this.box1.remove(label);
	    	}
			this.paintComponent(this.getGraphics());
			this.parent.repaint();
			this.parent.setVisible(true);
		}*/


	/*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.draw) {

        	Point pos_v = this.persons.get(b.vater).getLocation();
    		Point pos_m = this.persons.get(b.mutter).getLocation();
    		if (pos_v.x > pos_m.x) {
    			g.drawLine(pos_v.x, pos_v.y+50, pos_m.x, pos_m.y+50);
    		} else {
    			g.drawLine(pos_v.x, pos_v.y+50, pos_m.x, pos_m.y+50);
    		}
        	this.repaint();
        }

    }*/
}
