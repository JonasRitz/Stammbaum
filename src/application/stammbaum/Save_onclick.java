package application.stammbaum;

import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Save_onclick implements ActionListener {
	JFrame parent;
	Stammbaum stammbaum;
	public Save_onclick(JFrame parent, Stammbaum stammbaum){
		this.parent = parent;
		this.stammbaum = stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Stammbaum (*.json) ", "json");
		chooser.setFileFilter(filter);
		int result = chooser.showSaveDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION) {
			JSONObject obj = convert_Stammbaum_to_Json();
			/* Wenn der Nutzer die Datei .json nennt kein .json anhängen, sonst schon */
			String dateiname = chooser.getSelectedFile().getAbsolutePath();
			Pattern pattern = Pattern.compile(".*\\.json");
			Matcher matcher = pattern.matcher(dateiname);
			if(!matcher.matches()){
				dateiname += ".json";
			}
			try (FileWriter file = new FileWriter(dateiname)) {
				file.write(obj.toJSONString());
	            file.flush();
	        } catch (IOException error) {
	            error.printStackTrace();
	        }
		}
	}
	
	public JSONObject convert_Stammbaum_to_Json(){
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
		
		//TODO: Beziehungen zu einer JSON Datei konvertieren
		
		return all;
	}
}
