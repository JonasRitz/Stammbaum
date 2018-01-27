package application.stammbaum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings_relation_onclick implements ActionListener {
	Mainscreen main;
	public Settings_relation_onclick(Mainscreen main){
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e){
		Settings_person p = new Settings_person(this.main);
	}
}
