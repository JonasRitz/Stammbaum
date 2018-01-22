package application.stammbaum;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class CentralFrame extends JPanel {
	public int count = 0;
	private boolean drawAll = false;
	private int x;
	private int y;
	protected JFrame parent;
	
	// Konstruktor
	public CentralFrame(JFrame parent){
		this.parent = parent;
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
		String src = p.getImageSource();
		String infos = "<html>" + p;
		if (p.getGeburtsdatum() != null) {
			infos += "<p>" + p.getGeburtsdatum() + "</p>";
		}
		if (p.getSterbedatum() != null) {
			infos += "<p>" + p.getSterbedatum() + "</p>";
		}
		infos += "<html>";
		JLabel label;
		if (src == null) {
			label = new JLabel(infos, SwingConstants.CENTER);
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setBorder(BorderFactory.createLineBorder(Color.black));
		} else {
			ImageIcon icon = this.setIcon(src);
			System.out.println(icon.toString());
			label = new JLabel(infos, icon, SwingConstants.CENTER);
			label.setVerticalTextPosition(JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		this.add(label);
		parent.setVisible(true);
	}

	// Beziehung hinzufuegen
	protected void addRelationship() {
		if(!this.drawAll) return;
		this.drawArcs(0, 0, 500, 500);
		this.setVisible(true);
	}
	
	public void drawArcs(int x1, int y1, int x2, int y2) {
		Graphics g = this.getGraphics();
        //Graphics2D g2 = (Graphics2D) g;
        g.drawLine(x1,  y1,  x2,  y2);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//this.drawArcs(0, 0, 400, 400);
	}
}
