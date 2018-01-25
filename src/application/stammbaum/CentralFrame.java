package application.stammbaum;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.time.format.DateTimeFormatter;

import javax.swing.border.LineBorder;

public class CentralFrame extends JPanel {
	private Mainscreen parent;
	private HashMap<Person, JLabel> persons;
	private Box box1;
	private Box box2;
	private Beziehung b;
	private boolean draw;
	private ArrayList<Box> boxes;

	// Konstruktor
	public CentralFrame(Mainscreen parent){
		this.draw = false;
		this.parent = parent;
		this.persons = new HashMap<>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.boxes = new ArrayList<Box>();
		this.boxes.add()
		box = Box.createHorizontalBox();
		box1.setAlignmentX(Box.CENTER_ALIGNMENT);
		box1.add(Box.createVerticalGlue());
		
		box2 = Box.createHorizontalBox();
		box2.setAlignmentX(Box.CENTER_ALIGNMENT);
		box2.add(Box.createVerticalGlue());

		this.add(box1);
		this.add(box2);
		this.setVisible(true);
	}
	
	protected void refreshAll(Stammbaum baum){
		for(int i=0; i<baum.getPersonen().size(); i++){
			addPerson(baum.getPersonen().get(i));
		}
		for(int i=0; i<baum.getBeziehungen().size(); i++){
			addRelation(baum.getBeziehungen().get(i));
		}
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
		this.box1.add(label);
		this.box1.add(Box.createRigidArea(new Dimension(100,25)));
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
		this.parent.setVisible(true);
	}
	
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
