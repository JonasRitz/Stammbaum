package application.stammbaum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Open_onclick implements ActionListener {
	JFrame parent;
	Stammbaum stammbaum;
	public Open_onclick(JFrame parent, Stammbaum stammbaum){
		this.parent = parent;
		this.stammbaum = stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Stammbaum (*.json) ", "json");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION) {
			try{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
				convert_Json_to_Stammbaum(obj);
	        } catch (IOException error) {
	            error.printStackTrace();
	        } catch (ParseException errorParse){
	        		errorParse.printStackTrace();
	        }
		}
	}
	/*
	JSONObject all = new JSONObject();
	JSONArray personen = new JSONArray();
	for(Person p : stammbaum.getPersonen()){
		JSONObject einePerson = new JSONObject();
		einePerson.put("vorname", p.getVorname());
		einePerson.put("nachname", p.getNachname());
		einePerson.put("geschlecht", p.getGeschlecht());
		einePerson.put("imageSource", p.getImageSource());
		einePerson.put("geburtsdatum", p.getGeburtsdatum());
		einePerson.put("sterbedatum", p.getSterbedatum());
		personen.add(einePerson);
	}
	all.put("Personen", personen);
	*/
	
	public Stammbaum convert_Json_to_Stammbaum(Object obj){
		JSONObject jsonObject = (JSONObject) obj;
		System.out.println(jsonObject);
		return new Stammbaum();
	}
	
}
