package application.stammbaum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import jdk.nashorn.internal.runtime.JSONListAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Open_onclick implements ActionListener {
	Mainscreen main;
	Stammbaum stammbaum;
	public Open_onclick(Mainscreen main, Stammbaum stammbaum){
		this.main = main;
		this.stammbaum = stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Stammbaum (*.json) ", "json");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(main);
		if(result == JFileChooser.APPROVE_OPTION) {
			try{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
				Stammbaum baum = convert_Json_to_Stammbaum(obj);
				main.stammbaum = baum;
				main.central.refreshAll(baum, stammbaum);
				main.remove_onClickListener();
				main.initialisiere_onClickListener();
				
	        } catch (IOException error) {
	            error.printStackTrace();
	        } catch (ParseException errorParse){
	        		errorParse.printStackTrace();
	        }
		}
	}
	
	public Stammbaum convert_Json_to_Stammbaum(Object obj){
		Stammbaum stammbaum = new Stammbaum();
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray personen = (JSONArray) jsonObject.get("Personen");
		for(int i =0; i<personen.size(); i++){
			JSONObject person = (JSONObject) personen.get(i);
			String vorname = (String) person.get("vorname");
			String nachname = (String) person.get("nachname");
			String geschlecht = (String) person.get("geschlecht");
			String imageSource = (String) person.get("imageSource");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate geburtsdatum = null;
			
			if(! ((String) person.get("geburtsdatum")).equals("null")){
				String date = (String) person.get("geburtsdatum");
				geburtsdatum = LocalDate.parse(date, formatter);
			}
			
			LocalDate sterbedatum = null;
			if(! ((String) person.get("sterbedatum")).equals("null")){
				String date = (String) person.get("sterbedatum");
				sterbedatum = LocalDate.parse(date, formatter);
			}
			stammbaum.personHinzufuegen(new Person(vorname, nachname, geschlecht, imageSource, geburtsdatum, sterbedatum));
		}
		
		JSONArray beziehungen = (JSONArray) jsonObject.get("Beziehungen");
		for(int i =0; i<beziehungen.size(); i++){
			JSONObject bez = (JSONObject) beziehungen.get(i);
			int vater_id = ((Long) bez.get("vater")).intValue();
			int mutter_id = ((Long) bez.get("mutter")).intValue();
			Person vater = stammbaum.getPersonen().get(vater_id);
			Person mutter = stammbaum.getPersonen().get(mutter_id);
			Beziehung b1 = new Beziehung(vater, mutter);
			JSONArray kinder = (JSONArray) bez.get("kinder");
			
			for(int j=0; j<kinder.size(); j++){
				int kind_id = ((Long) kinder.get(i)).intValue();
				b1.KindHinzufuegen(stammbaum.getPersonen().get(kind_id));
			}
			stammbaum.beziehungHinzufuegen(b1);
		}
		return stammbaum;
	}
	
}
