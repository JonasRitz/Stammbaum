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

public class New_Relation extends JOptionPane {
	private Stammbaum stammbaum;
	private JList liste;
	private DefaultListModel model;
	public New_Relation(Stammbaum stammbaum) {
		this.stammbaum = stammbaum;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/4_new_relation.png", 50);
		JPanel layout = new JPanel(new GridLayout(1,3));
		addAllSelections(layout);
	    int result = this.showConfirmDialog(null, layout, "Füge eine Beziehung hinzu: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result != this.OK_OPTION) {
	    	
	    }
	}
	
	public void addAllSelections(JPanel layout){
		String who[] = {"Vater", "Mutter", "Kinder"};
		for(String elem: who){
			addSelectionList(layout, elem);
		}
	}
	
	public String geschlecht(String who){
		if(who.equals("Vater")){
			return "männlich";
		}else{
			return "weiblich";
		}
	}
	
	public void addSelectionList(JPanel layout, String who){
		ArrayList<Person> pers_arr_list = stammbaum.getPersonen();
		model = new DefaultListModel();
		for(Person pers : pers_arr_list){
			if(!who.equals("Kinder")){
				if(pers.getGeschlecht().equals(geschlecht(who))){
					model.addElement(pers.toString());
				}
			}else{
				model.addElement(pers.toString());
			}
		}
		liste = new JList(model);
		if(who.equals("Kinder")){
			liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		Border niceBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
		Border compound = BorderFactory.createTitledBorder(niceBorder, who + " auswählen");
		liste.setBorder(compound);
		layout.add(liste);
	}
	
	
	
	

}
