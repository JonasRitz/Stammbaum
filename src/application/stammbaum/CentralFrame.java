package application.stammbaum;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class CentralFrame extends JPanel {

	private ImageIcon icon;
	private GridBagLayout layout;
	private ArrayList<JComponent> personen;
	public int count = 0;
	private boolean drawAll = false;
	private int x;
	private int y;

	// Konstruktor
	public CentralFrame(){

		this.icon = this.setIcon("src/data/icons/vertical/1_new_person.png");
		this.personen = new ArrayList<>();

		JButton add = new JButton("Add Person");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addPerson("<html>Vorname Nachname<br>* TT.MM.JJJJ</html>", icon, 1);
			}
		});
		this.layout = new GridBagLayout();
		this.setLayout(this.layout);
		this.add(add);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	// Icon skalieren
	protected ImageIcon setIcon(String file) {

		ImageIcon icon = new ImageIcon(file);
		Image img = icon.getImage();
		int scale = icon.getIconHeight()/100;
		Image newimg = img.getScaledInstance(icon.getIconWidth()/scale, icon.getIconHeight()/scale,  java.awt.Image.SCALE_SMOOTH ) ;
		icon = new ImageIcon(newimg);

		return icon;
	}

	// Label skalieren
	protected JLabel setLabel(String text, ImageIcon icon) {

		JLabel label = new JLabel(text, icon, SwingConstants.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setBorder(BorderFactory.createLineBorder(Color.black));

		return label;
	}

	// Person hinzufuegen
	protected void addPerson(String infos, ImageIcon icon, int beziehung) {

		JLabel person = this.setLabel(infos, icon);
		personen.add(person);

		x += 1;

		GridBagConstraints g = new GridBagConstraints();
		// Person zu Eltern
		if (beziehung == 1) {
			x = 1;
			y += 1;
			g.insets = new Insets(100, 100, 0, 0);
		}
		// Person zu Partner/Geschwistern
		else {
			g.insets = new Insets(0, 100, 0, 0);
		}
		g.gridx = x;
		g.gridy = y;
		this.add(person, g);
		this.setVisible(true);
		
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
		this.drawArcs(0, 0, 400, 400);
	}
}
