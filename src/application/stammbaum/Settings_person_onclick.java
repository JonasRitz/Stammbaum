package application.stammbaum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings_person_onclick implements ActionListener {
	Stammbaum stammbaum;
	public Settings_person_onclick(Stammbaum stammbaum){
		this.stammbaum = stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		Settings_person p = new Settings_person(this.stammbaum);
	}
}
