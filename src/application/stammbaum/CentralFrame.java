package application.stammbaum;
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
	private HashMap<Point, Point> positionen;

	public CentralFrame(Mainscreen parent, Stammbaum baum){
		this.draw = false;
		this.setLayout(null);
		this.parent = parent;
		this.persons = new HashMap<>();
		this.positionen = new HashMap<>();
		this.setVisible(true);
	}

	protected void refreshAll(Stammbaum baum){
		this.removeAll();
		boolean hatMehrPlatz = personenOhneBeziehungHinzufuegen(baum);
		personenMitBeziehungHinzufuegen(baum, hatMehrPlatz);
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}
	
	public void personenMitBeziehungHinzufuegen(Stammbaum baum, boolean hatMehrPlatz){
		ArrayList<Person[]> heads = null;
		if (baum.beziehungen.size() > 0) {
			ArrayList<Person[]> orig = calculateHead(baum);
			heads = new ArrayList<Person[]>(orig.size());
			for (Person[] item : orig) {
				heads.add(item.clone());
			}
			this.draw = true;
			int anzahl = heads.size();
			Insets insets = this.getInsets();
			Dimension sizeOfJPanel = this.getSize();
			for(Person[] p : heads){
				JLabel personLabel_v = createJLabelOfPerson(p[0]);
				JLabel personLabel_m = createJLabelOfPerson(p[1]);
				Dimension size = personLabel_v.getPreferredSize();
				personLabel_v.setBounds(insets.left+10, 10 + insets.top, size.width, size.height);
				personLabel_m.setBounds(insets.left+10+2*size.width, 10 + insets.top, size.width, size.height);
				this.add(personLabel_v);
				this.add(personLabel_m);
				this.positionen.put(new Point(insets.left+10+size.width, 10+insets.top+50), new Point(insets.left+10+2*size.width, 10+insets.top+50));
				System.out.println("Person mit Beziehung wird optisch hinzugefuegt: " + p[0].toString());
				System.out.println("Person mit Beziehung wird optisch hinzugefuegt: " + p[1].toString());			
			}
		}
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.draw) {
        	for (Point p : this.positionen.keySet()) {
        		g.drawLine(p.x, p.y, this.positionen.get(p).x, this.positionen.get(p).y);
            	this.repaint();
        	}
        }

    }
	
	
	public boolean personenOhneBeziehungHinzufuegen(Stammbaum baum){
		//iteriert ueber die Personen in einem Stammbaum und fuegt die Person, die nicht Teil 
		//einer Beziehung sind dem Stammbaum auf der Rechten Seite als Art Liste hinzu liefert 
		//true wenn eine Hinzugefuegt wurde, ansonsten false, damit man in dem Fall, dass man 
		//keine Person in einer Beziehung hat mehr Platz benutzen kann
		int personenOhneBeziehung = 0;
		boolean hatMehrPlatz = true;
		for(Person p : parent.stammbaum.getPersonen()){
			if(!baum.istInBeziehung(p)){
				hatMehrPlatz = false;
				System.out.println("Person ohne Beziehung wird optisch hinzugefuegt: " + p.toString());
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
		return hatMehrPlatz;
	}

	protected ArrayList<Person[]> calculateHead(Stammbaum baum) {

		ArrayList<Person[]> heads = new ArrayList<>();
		Person head_v = baum.beziehungen.get(0).vater;
		Person head_m = baum.beziehungen.get(0).mutter;
		Person[] head = {head_v, head_m};
		heads.add(head);
		
		for (Beziehung b: baum.beziehungen) {
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
		return heads;
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
