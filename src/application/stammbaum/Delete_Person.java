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
	public Stammbaum stammbaum;
	private JList liste;
	private DefaultListModel model;
	public Delete_Person(Stammbaum stammbaum) {
		this.stammbaum = stammbaum;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/2_delete_person.png", 50);
		JPanel layout = new JPanel(new GridLayout(2,1));
		addSelectionList(layout);
		addRemoveButton(layout);
	    int result = this.showConfirmDialog(null, layout, "Entferne eine Person: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	  
	    }
	}
	
	public void addRemoveButton(JPanel layout){
		JButton b = new JButton("Person Löschen");
		layout.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				model.remove(index);
				stammbaum.personLoeschen(index);
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
		liste.setMinimumSize(new Dimension(100,100));
		layout.add(liste);
		
	}

}