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

public class Settings_person extends JOptionPane {
	public Stammbaum stammbaum;
	private JList liste;
	private DefaultListModel model;
	public Settings_person(Stammbaum stammbaum) {
		this.stammbaum = stammbaum;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/3_settings_person.png", 50);
		JPanel layout = new JPanel(new GridLayout(2,1));
		addSelectionList(layout);
		addSettingsButton(layout);
	    int result = this.showConfirmDialog(null, layout, "Bearbeite eine Person: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION && liste.getSelectedIndex() != -1) {
	    	Settings_person_newData nD = new Settings_person_newData(stammbaum, stammbaum.getPersonen().get(liste.getSelectedIndex()));
	    }
	}
	
	public void addSettingsButton(JPanel layout){
		JButton b = new JButton("Person bearbeiten");
		layout.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				Settings_person_newData nD = new Settings_person_newData(stammbaum, stammbaum.getPersonen().get(liste.getSelectedIndex()));
			}
		});
	}
	
	public void addSelectionList(JPanel layout){
		ArrayList<Person> pers_arr_list = stammbaum.getPersonen();
		model = new DefaultListModel();
		for(Person pers : pers_arr_list){
			model.addElement(pers.toString());
		}
		liste = new JList(model);
		Border niceBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
		Border compound = BorderFactory.createTitledBorder(niceBorder, "Person auswählen");
		liste.setBorder(compound);
		layout.add(liste);
	}

}
