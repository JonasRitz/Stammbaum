package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class New_Person extends JOptionPane {
	public New_Person() {
		ImageIcon icon = resizeImage("src/data/icons/vertical/1_new_person.png", 50);
		JPanel layout = new JPanel(new GridLayout(4,2));
		JTextField name = new JTextField(5);
	    JTextField nachname = new JTextField(5);
	    String geschlechter[] = {"weiblich", "männlich", "X"};
		JComboBox<String> geschlecht = new JComboBox<>(geschlechter);
		geschlecht.setSelectedIndex(0);
	    layout.add(new JLabel("Vorname:"));
	    layout.add(name);
	    layout.add(new JLabel("Nachname:"));
	    layout.add(nachname);
	    layout.add(new JLabel("Geschlecht:"));
	    layout.add(geschlecht);
	    layout.add(new JLabel("Bild: "));
	    JButton openButton = new JButton("Bild auswählen");
	    openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				choose.showOpenDialog(layout);
			}
		});
	    layout.add(openButton);
	    int result = this.showConfirmDialog(null, layout, "Neue Person Anlegen: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {   

	    }
	}
	
	
	public ImageIcon resizeImage(String name, int size){
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		return imageIcon; // diese und zwei Zeilen darüber: Skalieren das Bild auf gewünschte Pixelgröße
	}
	
	
}
