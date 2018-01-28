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

public class Settings_relation_newData extends JOptionPane {
	private Stammbaum stammbaum;
	private CentralFrame central;
	private JList vater;
	private JList mutter;
	private JList kinder;
	private Beziehung toEdit;
	
	private ArrayList<Person> väter_p = new ArrayList<Person>();
	private ArrayList<Person> mütter_p = new ArrayList<Person>();
	private ArrayList<Person> kinder_p = new ArrayList<Person>();
	
	private DefaultListModel model;
	
	public Settings_relation_newData(Mainscreen main, Beziehung b) {
		this.stammbaum = main.stammbaum;
		this.central = main.central;
		this.toEdit = b;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/6_settings_relation.png", 50);
		JPanel layout = new JPanel(new GridLayout(1,3));
		personenListenErstellen();
		addAllSelections(layout);
	    int result = this.showConfirmDialog(null, layout, "Bearbeite diese Beziehung: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	    		if(vater.getSelectedIndex() != -1 && mutter.getSelectedIndex() != -1 && kinder.getSelectedIndices().length != 0){
	    			stammbaum.getBeziehungen().remove(toEdit);
	    			Beziehung neu = new Beziehung(väter_p.get(vater.getSelectedIndex()),  mütter_p.get(mutter.getSelectedIndex()));
	    			for(int i : kinder.getSelectedIndices()){
	    				if(!neu.getKinder().contains(kinder_p.get(i))){
	    					neu.KindHinzufuegen(kinder_p.get(i));
	    				}
	    			}
	    			stammbaum.beziehungHinzufuegen(neu);
	    			central.refreshAll(main.stammbaum);
	    		}
	    }
	}
	
	public void addAllSelections(JPanel layout){
		vater = addSelectionList(layout, "Vater");
		vater.setSelectedIndex(väter_p.indexOf(toEdit.getVater()));
		
		mutter = addSelectionList(layout, "Mutter");
		mutter.setSelectedIndex(mütter_p.indexOf(toEdit.getMutter()));
		
		kinder = addSelectionList(layout, "Kinder");
	}
	
	public JList addSelectionList(JPanel layout, String who){
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
	
	public void personenListenErstellen(){
		for(Person p: stammbaum.getPersonen()){
			if(p.getGeschlecht().equals("männlich")){
				väter_p.add(p);
			}else if(p.getGeschlecht().equals("weiblich")){
				mütter_p.add(p);
			}
			kinder_p.add(p);
		}
	}
	
	public String geschlecht(String who){
		if(who.equals("Vater")){
			return "männlich";
		}else{
			return "weiblich";
		}
	}

}
