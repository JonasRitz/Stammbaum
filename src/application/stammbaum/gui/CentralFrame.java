package application.stammbaum;
import java.awt.Insets;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.*;
import java.util.*;
import java.awt.Image.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;
import java.awt.print.*;
import java.io.File;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class CentralFrame extends JPanel implements Printable {
	private Dimension jlabelsize = new Dimension(138,70);
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
		boolean hatPlatz = personenOhneBeziehungHinzufuegen(baum);
		personenMitBeziehungHinzufuegen(baum);
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}
	
	public void personenMitBeziehungHinzufuegen(Stammbaum baum){
		ArrayList<Person[]> heads = null;
		if (baum.beziehungen.size() > 0) {
			//ArrayList<Person[]> ersteZeile = calculateHead(baum);
			ArrayList<Person[]> ersteZeile = baum.getHead();
			zeigeErsteZeile(ersteZeile);
			zeigeNaechsteZeile(baum, ersteZeile, 1); //Start der Rekursion
			/*
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
			}*/
		}
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}

	public void zeigeErsteZeile(ArrayList<Person[]> ersteZeile){
		Insets insets = this.getInsets();
		Dimension sizeOfJPanel = this.getSize();
		int breite = sizeOfJPanel.width-jlabelsize.width-2*15; //abstand 15 nach links und 15 nach rechts von Personen ohne Beziehungen 
		int distanz_zwischen_paaren = (breite - jlabelsize.width*2*ersteZeile.size()) / (ersteZeile.size()+1);
		for(int i=0; i<ersteZeile.size(); i++){
			JLabel vater = createJLabelOfPerson(ersteZeile.get(i)[0]);
			JLabel mutter = createJLabelOfPerson(ersteZeile.get(i)[1]);
			vater.setBounds(insets.left + distanz_zwischen_paaren*(i+1) + jlabelsize.width*2*i, insets.top + 15, jlabelsize.width, jlabelsize.height);
			mutter.setBounds(insets.left + distanz_zwischen_paaren*(i+1) + jlabelsize.width*2*i + jlabelsize.width, insets.top + 15, jlabelsize.width, jlabelsize.height);
			this.add(vater);
			this.add(mutter);
			vater.repaint();
			mutter.repaint();
		}
	}
	
	//Abstand zwischen Personen bzw. Paerchen und Personen abhangig von der Zeile ueber ihnen
	public int berechneAbstand(Stammbaum baum, ArrayList<Person[]> paareZeileDruber){
		ArrayList<Person> schonGehabt = new ArrayList<>();
		int platzDenJLabelwegnehmen = 0;
		int anzahl = 1;
		for(Person paar[] : paareZeileDruber){
			for(Person p: baum.getKinderZuEltern(paar)){
				if(baum.getBeziehungsPartner(p) != null && !schonGehabt.contains(baum.getBeziehungsPartner(p))){
					anzahl++;
					platzDenJLabelwegnehmen += jlabelsize.width*2;
					schonGehabt.add(p);
					schonGehabt.add(baum.getBeziehungsPartner(p));
				}else if(baum.getBeziehungsPartner(p) == null){
					schonGehabt.add(p);
					platzDenJLabelwegnehmen += jlabelsize.width;
					anzahl++;
				}
			}
		}
		Dimension sizeOfJPanel = this.getSize();
		int rest = sizeOfJPanel.width-jlabelsize.width-2*15-platzDenJLabelwegnehmen;
		return rest/anzahl;
	}
	
	public void zeigeNaechsteZeile(Stammbaum baum, ArrayList<Person[]> paareZeileDruber, int zeile){
		if(paareZeileDruber.size() == 0){
			return;
		}
		ArrayList<Person> schonGehabt = new ArrayList<>();
		int abstand = berechneAbstand(baum, paareZeileDruber);
		Insets insets = this.getInsets();
		ArrayList<Person[]> paare = new ArrayList<>();
		int anzPaare = 0;
		int anzPers = 0;
		int i =0;
		for(Person paar[] : paareZeileDruber){
			for(Person p: baum.getKinderZuEltern(paar)){
				if(baum.getBeziehungsPartner(p) != null && !schonGehabt.contains(baum.getBeziehungsPartner(p))){
					JLabel x = createJLabelOfPerson(p);
					JLabel y = createJLabelOfPerson(baum.getBeziehungsPartner(p));
					x.setBounds(insets.left + abstand*(i+1) + jlabelsize.width*(anzPaare*2+anzPers), insets.top + zeile*jlabelsize.height + (zeile+1)*15, jlabelsize.width, jlabelsize.height);
					y.setBounds(insets.left + abstand*(i+1) + jlabelsize.width*(anzPaare*2+anzPers) + jlabelsize.width, insets.top + zeile*jlabelsize.height + (zeile+1)*15, jlabelsize.width, jlabelsize.height);
					this.add(x);
					this.add(y);
					x.repaint();
					y.repaint();
					Person[] neuesPaar = {p, baum.getBeziehungsPartner(p)};
					paare.add(neuesPaar);
					anzPaare++;
					schonGehabt.add(p);
					schonGehabt.add(baum.getBeziehungsPartner(p));
					i++;
				}else if(baum.getBeziehungsPartner(p) == null){
					JLabel x = createJLabelOfPerson(p);
					x.setBounds(insets.left + abstand*(i+1) + jlabelsize.width*(anzPaare*2+anzPers), insets.top + zeile*jlabelsize.height + (zeile+1)*15, jlabelsize.width, jlabelsize.height);
					this.add(x);
					x.repaint();
					anzPers++;
					schonGehabt.add(p);
					i++;
				}
			}
		}
		zeigeNaechsteZeile(baum, paare, zeile+1);
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
				JLabel personLabel = createJLabelOfPerson(p);
				Insets insets = this.getInsets();	
				Dimension sizeOfJPanel = this.getSize();
				personLabel.setBounds(sizeOfJPanel.width-jlabelsize.width-15 + insets.left, 15 + insets.top + personenOhneBeziehung*(jlabelsize.height+15),jlabelsize.width, jlabelsize.height);
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
		label.setFont(label.getFont().deriveFont(9.0f));
		label.setMaximumSize(jlabelsize);
		label.setMinimumSize(jlabelsize);
		label.setPreferredSize(jlabelsize);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setBackground(Color.LIGHT_GRAY);
		label.setOpaque(true);
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
			scale = (int) (icon.getIconHeight()/jlabelsize.getHeight());
		}else{
			scale = (int) (icon.getIconWidth()/jlabelsize.getHeight());
		}
		if (scale != 0) {
			Image newimg = img.getScaledInstance(icon.getIconWidth()/scale, icon.getIconHeight()/scale, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
		}

		return icon;
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException{
		if(page > 0){
			return NO_SUCH_PAGE;
		}
		
		try{
			double scale = 0.6;
			Robot robot = new Robot();
			Rectangle r = new Rectangle(this.getX()+5, this.getY()+50, this.getWidth()-10, this.getHeight()-10);
			BufferedImage screenShot = robot.createScreenCapture(r);
			int w = (int) (screenShot.getWidth()*scale);
			int h = (int) (screenShot.getHeight()*scale);
			BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
		    AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);
		    scaleOp.filter(screenShot, after);
			Graphics2D g2d = (Graphics2D)g;
			pf.setOrientation(PageFormat.LANDSCAPE);
			pf.setPaper(PrinterJob.getPrinterJob().defaultPage().getPaper());
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.drawImage(after, 0, 0, null);
		}catch(AWTException exp){
			exp.printStackTrace();
		}
        return PAGE_EXISTS;
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
