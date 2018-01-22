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

public class New_Person extends JOptionPane {
	private Stammbaum stammbaum;
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
	
	
	public New_Person(Stammbaum stammbaum, CentralFrame central) {
		this.stammbaum = stammbaum;
		this.central = central;
		ImageIcon icon = Mainscreen.resizeImage("src/data/icons/vertical/1_new_person.png", 50);
		JPanel layout = new JPanel(new GridLayout(6,2));
		addFields(layout);
	    int result = this.showConfirmDialog(null, layout, "Neue Person Anlegen: ", this.OK_CANCEL_OPTION,  this.INFORMATION_MESSAGE, icon);
	    if (result == this.OK_OPTION) {
	    		if(!vorname.getText().equals("") && vorname.getText()!=null && !nachname.getText().equals("") && nachname.getText()!=null){
	    			getDataInserted();
	    		}else{
	    			JOptionPane.showMessageDialog(this, "Person konnte aufgrund unzureichender Informationen nicht erzeugt werden.", "", JOptionPane.ERROR_MESSAGE);
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
		}
		LocalDate geburtsdatum = null;
		if(year.getSelectedItem()!=null && month.getSelectedItem()!=null && day.getSelectedItem()!=null){
			geburtsdatum = LocalDate.of((int)year.getSelectedItem(), (int)month.getSelectedItem(), (int)day.getSelectedItem());
		}
		
		LocalDate sterbedatum = null;
		if(yearDied.getSelectedItem()!=null && monthDied.getSelectedItem()!=null && dayDied.getSelectedItem()!=null){
			sterbedatum = LocalDate.of((int)yearDied.getSelectedItem(), (int)monthDied.getSelectedItem(), (int)dayDied.getSelectedItem());
		}
		Person p1 = new Person(vor, nach, ges, file, geburtsdatum, sterbedatum);
		stammbaum.personHinzufuegen(p1);
		central.addPerson(p1);
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
		layout.add(new JLabel("Nachname:"));
	    layout.add(nachname);
	}

	public void addVornameField(JPanel layout){
		vorname = new JTextField(5); 
		layout.add(new JLabel("Vorname:"));
		layout.add(vorname);
	}
	
	public void addGeschlechterAuswahl(JPanel layout){
		String geschlechter[] = {"weiblich", "männlich", "X"};
		geschlecht = new JComboBox<>(geschlechter);
		geschlecht.setSelectedIndex(0);
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
		day.setSelectedIndex(-1);
		birthdate.add(day);
		
		month = new JComboBox<Integer>();
		for(int i=1; i<=12; i++){
			month.insertItemAt(i, i-1);
		}
		month.setSelectedIndex(-1);
		birthdate.add(month);
		
		
		int aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
		year = new JComboBox<Integer>();
		for(int i=aktuellesJahr; i>=0; i--){
			year.insertItemAt(i, aktuellesJahr-i);
		}
		year.setSelectedIndex(-1);
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
		dayDied.setSelectedIndex(-1);
		deathdate.add(dayDied);
		
		monthDied = new JComboBox<Integer>();
		for(int i=1; i<=12; i++){
			monthDied.insertItemAt(i, i-1);
		}
		monthDied.setSelectedIndex(-1);
		deathdate.add(monthDied);
		
		
		int aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
		yearDied = new JComboBox<Integer>();
		for(int i=aktuellesJahr; i>=0; i--){
			yearDied.insertItemAt(i, aktuellesJahr-i);
		}
		yearDied.setSelectedIndex(-1);
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
