package application.stammbaum;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class CentralFrame extends JFrame {

	private ImageIcon icon;
	private Container c;
	private GridBagLayout layout;
	private ArrayList<JComponent> personen;
	public int count = 0;
	private boolean drawAll = false;
	private int x;
	private int y;

	// Konstruktor
	public CentralFrame(){
		super("Stammbaumeditor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.icon = this.setIcon("src/data/icons/vertical/2_new_person.png");
		this.personen = new ArrayList<>();

		this.c = getContentPane();
		this.c.setPreferredSize(this.c.getMaximumSize());

		//JScrollPane scroll = new JScrollPane(this.c);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//this.getContentPane().add(scroll);

		JButton add = new JButton("Add Person");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addPerson("<html>Vorname Nachname<br>* TT.MM.JJJJ</html>", icon, 1);
			}
		});
		this.layout = new GridBagLayout();
		this.c.setLayout(this.layout);
		this.c.add(add);
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
		this.c.add(person, g);
		this.setVisible(true);
		if (x >= 3) {
			this.drawAll = true;
			this.addRelationship();
		}
	}

	// Beziehung hinzufuegen
	protected void addRelationship() {

		if(!this.drawAll) return;

		this.c.getGraphics().drawLine(0,100,200,300);
		//this.c.getGraphics().drawLine((int)this.personen.get(1).getLocation().getX()+this.personen.get(1).getWidth(), (int)this.personen.get(1).getLocation().getY()+50, (int)this.personen.get(2).getLocation().getX(), (int)this.personen.get(2).getLocation().getY()+50);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.addRelationship();
	}
}
