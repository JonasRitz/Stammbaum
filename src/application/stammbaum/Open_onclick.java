package application.stammbaum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
		/*
		int result = chooser.showOpenDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION) {
			JSONObject obj = convert_Stammbaum_to_Json();
			try (FileWriter file = new FileWriter(dateiname)) {
				file.write(obj.toJSONString());
	            file.flush();
	        } catch (IOException error) {
	            error.printStackTrace();
	        }
		}*/
	}
	/*
	public Stammbaum convert_Json_to_Stammbaum(JSONObject obj){
		
	}*/
	
}
