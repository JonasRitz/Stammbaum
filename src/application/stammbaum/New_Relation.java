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
	private JList vater;
	private JList mutter;
	private JList kinder;
	private ArrayList<Person> väter_p = new ArrayList<Person>();
	private ArrayList<Person> mütter_p = new ArrayList<Person>();
	private ArrayList<Person> kinder_p = new ArrayList<Person>();
	
	private DefaultListModel model;
	
	public New_Relation(Stammbaum stammbaum) {
		this.stammbaum = stammbaum;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/4_new_relation.png", 50);
		JPanel layout = new JPanel(new GridLayout(1,3));
		addAllSelections(layout);
	    int result = this.showConfirmDialog(null, layout, "Füge eine Beziehung hinzu: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	    		if(vater.getSelectedIndex() != -1 && mutter.getSelectedIndex() != -1 && kinder.getSelectedIndices().length != 0){
	    			Beziehung b = new Beziehung(väter_p.get(vater.getSelectedIndex()), mütter_p.get(mutter.getSelectedIndex()));
	    			for(int i : kinder.getSelectedIndices()){
	    				b.KindHinzufuegen(kinder_p.get(i));
	    			}
	    			stammbaum.beziehungHinzufuegen(b);
	    		}
	    }
	}
	
	public void addAllSelections(JPanel layout){
		vater = addSelectionList(layout, "Vater");
		mutter = addSelectionList(layout, "Mutter");
		kinder = addSelectionList(layout, "Kinder");
	}
	
	public String geschlecht(String who){
		if(who.equals("Vater")){
			return "männlich";
		}else{
			return "weiblich";
		}
	}
	
	public JList addSelectionList(JPanel layout, String who){
		ArrayList<Person> pers_arr_list = stammbaum.getPersonen();
		model = new DefaultListModel();
		for(Person pers : pers_arr_list){
			if(!who.equals("Kinder")){
				if(who.equals("Vater")){
					väter_p.add(pers);
				}else{
					mütter_p.add(pers);
				}
				if(pers.getGeschlecht().equals(geschlecht(who))){
					model.addElement(pers.toString());
				}
			}else{
				model.addElement(pers.toString());
				kinder_p.add(pers);
			}
		}
		JList liste = new JList(model);
		if(who.equals("Kinder")){
			liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		Border niceBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
		Border compound = BorderFactory.createTitledBorder(niceBorder, who + " auswählen");
		liste.setBorder(compound);
		layout.add(liste);
		return liste;
	}
	
	
	
	

}
