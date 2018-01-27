package application.stammbaum;
import java.util.regex.Pattern;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;

import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings_relation extends JOptionPane {
	private Stammbaum stammbaum;
	private Mainscreen main;
	private CentralFrame central;
	private JList liste;
	private DefaultListModel model;

	public Settings_relation(Mainscreen main) {
		this.stammbaum = main.stammbaum;
		this.central = main.central;
		this.main = main;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/6_settings_relation.png", 50);
		JPanel layout = new JPanel();
		addSelectionList(layout);
		addSettingsButton(layout);
	    int result = this.showConfirmDialog(null, layout, "Bearbeite eine Beziehung: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	    	Settings_relation_newData nD = new Settings_relation_newData(main, stammbaum.getBeziehungen().get(liste.getSelectedIndex()));
	    }
	}
	
	public void addSettingsButton(JPanel layout){
		JButton b = new JButton("Beziehung bearbeiten");
		layout.add(b);
		Dimension d = new Dimension(150, 30);
		b.setSize(d);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				Settings_relation_newData nD = new Settings_relation_newData(main, stammbaum.getBeziehungen().get(liste.getSelectedIndex()));
			}
		});
	}
	
	public void addSelectionList(JPanel layout){
		ArrayList<Beziehung> bez_arr_list = stammbaum.getBeziehungen();
		model = new DefaultListModel();
		for(Beziehung bez : bez_arr_list){
			StringBuilder b1 = new StringBuilder("Vater: ");
			b1.append(bez.getVater()).append(", Mutter: ").append(bez.getMutter()).append(", Kinder: ");
			for(Person p : bez.getKinder()){
				if(bez.getKinder().indexOf(p) == bez.getKinder().size()-1){
					b1.append(p.toString());
				}else{
					b1.append(p.toString()).append(", ");
				}
			}
			model.addElement(b1.toString());
		}
		liste = new JList(model);
		Border niceBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
		Border compound = BorderFactory.createTitledBorder(niceBorder, "Beziehung auswählen");
		liste.setBorder(compound);
		layout.add(liste);
	}

}
