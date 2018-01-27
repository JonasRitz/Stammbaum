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

public class Delete_Person extends JOptionPane {
	private Stammbaum stammbaum;
	private CentralFrame central;
	private ArrayList<Person> zwischenspeicher; // enthaelt alle gelöschten Personen um im Falle von Cancel diese wieder dem Stammbaum hinzuzufuegen
	private JList liste;
	private DefaultListModel model;

	public Delete_Person(Mainscreen main) {
		this.stammbaum = main.stammbaum;
		this.central = main.central;
		zwischenspeicher = new ArrayList<Person>();
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/2_delete_person.png", 50);
		JPanel layout = new JPanel();
		addSelectionList(layout);
		addRemoveButton(layout);
	    int result = this.showConfirmDialog(null, layout, "Entferne eine Person: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result != this.OK_OPTION) {
	    	addPersonsAgain();
	    }
	}
	
	public void addPersonsAgain(){
		for(Person p : zwischenspeicher){
			stammbaum.personHinzufuegen(p);
			central.refreshAll(stammbaum);
		}
	}
	
	public void addRemoveButton(JPanel layout){
		JButton b = new JButton("Person Löschen");
		Dimension d = new Dimension(150, 30);
		b.setSize(d);
		layout.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				model.remove(index);
				zwischenspeicher.add(stammbaum.getPersonen().get(index));
				stammbaum.personEntfernen(stammbaum.getPersonen().get(index));
				central.refreshAll(stammbaum);
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
		//JScrollPane sp = new JScrollPane(liste);
		//layout.add(sp);
		//liste.setPreferredSize(new Dimension(150, 50));
		Border niceBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
		Border compound = BorderFactory.createTitledBorder(niceBorder, "Person auswählen");
		liste.setBorder(compound);
		layout.add(liste);
	}

}
