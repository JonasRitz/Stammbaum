package application.stammbaum;
import java.util.regex.Pattern;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings_relation_newData extends JOptionPane {
	private Stammbaum stammbaum;
	private Person toEdit;
	protected CentralFrame central;
	private JTextField vorname;
	private JTextField nachname;
	private JComboBox<String> geschlecht;
	private JButton openButton;
	private JButton birth;
	private JFileChooser choose;
	private int resultOfFileSelection = JFileChooser.CANCEL_OPTION;
	private JComboBox<Integer> day;
	private JComboBox<Integer> month;
	private JComboBox<Integer> year;
	private JComboBox<Integer> dayDied;
	private JComboBox<Integer> monthDied;
	private JComboBox<Integer> yearDied;
	private CentralFrame centralFrame;
	
	public Settings_relation_newData(Mainscreen main, Person toEdit) {
		this.stammbaum = main.stammbaum;
		this.toEdit = toEdit;
		this.centralFrame = main.central;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/3_settings_person.png", 50);
		JPanel layout = new JPanel(new GridLayout(6,2));
		addFields(layout);
	    int result = this.showConfirmDialog(null, layout, "Ausgewaehlte Person bearbeiten: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	    		if(!vorname.getText().equals("") && vorname.getText()!=null && !nachname.getText().equals("") && nachname.getText()!=null){
	    			getDataInserted();
	    		}else{
	    			JOptionPane.showMessageDialog(this, "Person konnte aufgrund unzureichender Informationen nicht geändert werden.", "", JOptionPane.ERROR_MESSAGE);
	    		}
	    }
	}
	
	public void getDataInserted(){ //Werte die optional sind, werden, wenn sie nicht angegeben werden als null realisiert
		String vor = vorname.getText();
		String nach = nachname.getText();
		String ges = (String)geschlecht.getSelectedItem();
		
		String file = null;
		if(resultOfFileSelection == JFileChooser.APPROVE_OPTION){
			file = choose.getSelectedFile().getAbsolutePath();
		}else{
			file = toEdit.getImageSource();
		}
		
		LocalDate geburtsdatum = null;
		if(year.getSelectedItem()!=null && month.getSelectedItem()!=null && day.getSelectedItem()!=null){
			geburtsdatum = LocalDate.of((int)year.getSelectedItem(), (int)month.getSelectedItem(), (int)day.getSelectedItem());
		}
		
		LocalDate sterbedatum = null;
		if(yearDied.getSelectedItem()!=null && monthDied.getSelectedItem()!=null && dayDied.getSelectedItem()!=null){
			sterbedatum = LocalDate.of((int)yearDied.getSelectedItem(), (int)monthDied.getSelectedItem(), (int)dayDied.getSelectedItem());
		}
		toEdit.setVorname(vor);
		toEdit.setNachname(nach);
		toEdit.setGeschlecht(ges);
		toEdit.setImageSource(file);
		toEdit.setGeburtsdatum(geburtsdatum);
		toEdit.setSterbedatum(sterbedatum);
		
		centralFrame.refreshAll(stammbaum);
	}
	
	public void addFields(JPanel layout){
		addNachnameField(layout);
		addVornameField(layout);
		addGeschlechterAuswahl(layout);
		addBilderAuswahl(layout);
	    addGeburtsDatumAuswahl(layout);
	    addSterbeDatumAuswahl(layout);
	}
	
	public void addNachnameField(JPanel layout){
		nachname = new JTextField(5);
		nachname.setText(toEdit.getNachname());
		layout.add(new JLabel("Nachname:"));
	    layout.add(nachname);
	}

	public void addVornameField(JPanel layout){
		vorname = new JTextField(5); 
		vorname.setText(toEdit.getVorname());
		layout.add(new JLabel("Vorname:"));
		layout.add(vorname);
	}
	
	public void addGeschlechterAuswahl(JPanel layout){
		String geschlechter[] = {"weiblich", "männlich", "X"};
		geschlecht = new JComboBox<>(geschlechter);
		geschlecht.setSelectedItem(toEdit.getGeschlecht());
	    layout.add(new JLabel("Geschlecht:"));
	    layout.add(geschlecht);
	}
	
	public void addBilderAuswahl(JPanel layout){
		layout.add(new JLabel("Bild: "));
	    openButton = new JButton("Bild auswählen");
	    openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bildateien (*.jpg, *.png) ", "jpg", "png");
				choose.setFileFilter(filter);
				resultOfFileSelection = choose.showOpenDialog(layout);
			}
		});
	    layout.add(openButton);
	}
	
	public void addGeburtsDatumAuswahl(JPanel layout){
		layout.add(new JLabel("Geburtsdatum: "));
		Box birthdate = Box.createHorizontalBox();
		
		day = new JComboBox<Integer>();
		for(int i=1; i<=31; i++){
			day.insertItemAt(i, i-1);
		}
		birthdate.add(day);
		
		month = new JComboBox<Integer>();
		for(int i=1; i<=12; i++){
			month.insertItemAt(i, i-1);
		}
		birthdate.add(month);
		
		
		int aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
		year = new JComboBox<Integer>();
		for(int i=aktuellesJahr; i>=0; i--){
			year.insertItemAt(i, aktuellesJahr-i);
		}
		
		if(toEdit.getGeburtsdatum() == null){
			day.setSelectedIndex(-1);
			month.setSelectedIndex(-1);
			year.setSelectedIndex(-1);
		}else{
			day.setSelectedIndex(toEdit.getGeburtsdatum().getDayOfMonth()-1);
			month.setSelectedIndex(toEdit.getGeburtsdatum().getMonthValue()-1);
			year.setSelectedIndex(aktuellesJahr - toEdit.getGeburtsdatum().getYear());
		}
		
		birthdate.add(year);

		layout.add(birthdate);
	    
	}
	
	public void addSterbeDatumAuswahl(JPanel layout){
		layout.add(new JLabel("Sterbedatum: "));

		Box deathdate = Box.createHorizontalBox();
		dayDied = new JComboBox<Integer>();
		for(int i=1; i<=31; i++){
			dayDied.insertItemAt(i, i-1);
		}
		deathdate.add(dayDied);
		
		monthDied = new JComboBox<Integer>();
		for(int i=1; i<=12; i++){
			monthDied.insertItemAt(i, i-1);
		}
		deathdate.add(monthDied);
		
		int aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
		yearDied = new JComboBox<Integer>();
		for(int i=aktuellesJahr; i>=0; i--){
			yearDied.insertItemAt(i, aktuellesJahr-i);
		}

		
		if(toEdit.getSterbedatum() == null){
			dayDied.setSelectedIndex(-1);
			monthDied.setSelectedIndex(-1);
			yearDied.setSelectedIndex(-1);
		}else{
			dayDied.setSelectedIndex(toEdit.getSterbedatum().getDayOfMonth()-1);
			monthDied.setSelectedIndex(toEdit.getSterbedatum().getMonthValue()-1);
			yearDied.setSelectedIndex(aktuellesJahr - toEdit.getSterbedatum().getYear());
		}
		
		deathdate.add(yearDied);
		layout.add(deathdate);
	    
	}
	
	public ImageIcon resizeImage(String name, int size){
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		return imageIcon; // diese und zwei Zeilen darüber: Skalieren das Bild auf gewünschte Pixelgröße
	}
	
}
