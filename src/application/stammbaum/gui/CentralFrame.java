package application.stammbaum;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.lang.Math;
public class CentralFrame extends JPanel implements Printable {
	private Dimension jlabelsize = new Dimension(150,70);
	private Mainscreen parent;
	private HashMap<Person, JLabel> persons;
	boolean draw;
	private HashMap<Person, Point> positionen;
	private HashMap<Person, Point[]> verbindungen;

	public CentralFrame(Mainscreen parent, Stammbaum baum){
		this.draw = false;
		this.setLayout(null);
		this.parent = parent;
		this.persons = new HashMap<>();
		this.positionen = new HashMap<>();
		this.verbindungen = new HashMap<>();
		this.setVisible(true);
	}
	
	protected void showBeziehungsGeflecht(Stammbaum baum){
		this.removeAll();
		this.positionen.clear();
		this.verbindungen.clear();
		Insets insets = this.getInsets();
		Dimension sizeOfJPanel = this.getSize();
		for(Person p : baum.getPersonen()){
			JLabel personLabel = createJLabelOfPerson(p);
			int zufallX = (int)((Math.random()) * sizeOfJPanel.width);
			int zufallY = (int)((Math.random()) * sizeOfJPanel.height);
			if(zufallX > sizeOfJPanel.width - jlabelsize.width){
				zufallX -= jlabelsize.width;
			}
			if(zufallY > sizeOfJPanel.height - jlabelsize.height){
				zufallY -= jlabelsize.height;
			}
			personLabel.setBounds(insets.left+zufallX, insets.top+zufallY, jlabelsize.width, jlabelsize.height);
			this.add(personLabel);
			
		}

		JLabel head = new JLabel("Jeder mit Jedem irgendwie");
		Dimension headsize = head.getPreferredSize(); 
		head.setBounds(sizeOfJPanel.width/2, insets.top+25, headsize.width, headsize.height);
		this.add(head);
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}

	protected void refreshAll(Stammbaum baum){
		this.removeAll();
		this.positionen.clear();
		this.verbindungen.clear();
		boolean hatPlatz = personenOhneBeziehungHinzufuegen(baum);
		personenMitBeziehungHinzufuegen(baum);
		this.setVisible(true);
		this.parent.repaint();
		this.parent.setVisible(true);
	}
	
	public void personenMitBeziehungHinzufuegen(Stammbaum baum){
		ArrayList<Person[]> heads = null;
		if (baum.beziehungen.size() > 0) {
			ArrayList<Person[]> ersteZeile = baum.getHead();
			zeigeErsteZeile(ersteZeile);
			zeigeNaechsteZeile(baum, ersteZeile, 1); //Start der Rekursion
			this.draw = true;
		}
		this.setVisible(true);
		this.paintComponent(this.getGraphics());
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
			vater.setBounds(insets.left + distanz_zwischen_paaren*(i+1) + jlabelsize.width*2*i, insets.top + 40, jlabelsize.width, jlabelsize.height);
			mutter.setBounds(insets.left + distanz_zwischen_paaren*(i+1) + jlabelsize.width*2*i + jlabelsize.width, insets.top + 40, jlabelsize.width, jlabelsize.height);
			this.add(vater);
			this.add(mutter);
			this.positionen.put(ersteZeile.get(i)[0], new Point(vater.getBounds().x, vater.getBounds().y));
			this.positionen.put(ersteZeile.get(i)[1], new Point(mutter.getBounds().x, mutter.getBounds().y));
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
		int i = 0;
		for(Person paar[] : paareZeileDruber){
			Point eltern1 = this.positionen.get(paar[0]);
			Point eltern2 = this.positionen.get(paar[1]);
			Point tmp = null;
			if (eltern1.x > eltern2.x) {
				eltern1.setLocation(eltern1.x, eltern1.y+jlabelsize.height);
				tmp = eltern1;
			} else {
				eltern2.setLocation(eltern2.x, eltern2.y+jlabelsize.height);
				tmp = eltern2;
			}
			for(Person p: baum.getKinderZuEltern(paar)){
				if(baum.getBeziehungsPartner(p) != null && !schonGehabt.contains(baum.getBeziehungsPartner(p))){
					JLabel x = createJLabelOfPerson(p);
					JLabel y = createJLabelOfPerson(baum.getBeziehungsPartner(p));
					x.setBounds(insets.left + abstand*(i+1) + jlabelsize.width*(anzPaare*2+anzPers), insets.top + zeile*jlabelsize.height + (zeile+1)*40, jlabelsize.width, jlabelsize.height);
					y.setBounds(insets.left + abstand*(i+1) + jlabelsize.width*(anzPaare*2+anzPers) + jlabelsize.width, insets.top + zeile*jlabelsize.height + (zeile+1)*40, jlabelsize.width, jlabelsize.height);
					this.add(x);
					this.add(y);
					this.positionen.put(p, new Point(x.getBounds().x, x.getBounds().y));
					this.positionen.put(baum.getBeziehungsPartner(p), new Point(y.getBounds().x, y.getBounds().y));

					
					Point kind = this.positionen.get(p);
					kind.setLocation(kind.x+jlabelsize.width/2, kind.y);
					Point[] punkte = {kind, tmp};
					this.verbindungen.put(p, punkte);
					
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
					x.setBounds(insets.left+abstand*(i+1)+jlabelsize.width*(anzPaare*2+anzPers), insets.top+zeile*jlabelsize.height+(zeile+1)*40, jlabelsize.width, jlabelsize.height);
					this.add(x);
					this.positionen.put(p, new Point(x.getBounds().x, x.getBounds().y));
					
					Point kind = this.positionen.get(p);
					kind.setLocation(kind.x+jlabelsize.width/2, kind.y);
					Point[] punkte = {kind, tmp};
					this.verbindungen.put(p, punkte);
					
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        if (this.draw) {
        	for (Person p : this.verbindungen.keySet()) {
        		g2.drawLine(this.verbindungen.get(p)[0].x, this.verbindungen.get(p)[0].y, this.verbindungen.get(p)[1].x, this.verbindungen.get(p)[1].y);
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
			if(p.getSterbedatum() != null){
				icon = toGrey(icon);
			}
			label.setIcon(icon);
			label.setIconTextGap(10);
		}
		this.persons.put(p, label);
		return label;
	}
	
	public ImageIcon toGrey(ImageIcon i){
		Image image = i.getImage();
		BufferedImage sourceImage = imageToBufferedImage(image);
		BufferedImage dstImage = null;
		ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(colorSpace, null);
		dstImage = op.filter(sourceImage, dstImage);
		i = new ImageIcon(dstImage);
		return i;
	}
	
	public BufferedImage imageToBufferedImage(Image img){
		 BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		  // Draw the image on to the buffered image
		 Graphics2D bGr = bimage.createGraphics();
		 bGr.drawImage(img, 0, 0, null);
		 bGr.dispose();
		 // Return the buffered image
		 return bimage;
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
		double scale = jlabelsize.width/2.4;
		Image newimg = img.getScaledInstance((int)scale, (int) ((scale/icon.getIconWidth()) * icon.getIconHeight()), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException{
		if(page > 0){
			return NO_SUCH_PAGE;
		}
		
		try{
			double scale = 0.65;
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
}
