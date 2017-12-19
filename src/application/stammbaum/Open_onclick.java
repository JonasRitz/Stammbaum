package application.stammbaum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Open_onclick implements ActionListener {
	JFrame parent;
	public Open_onclick(JFrame parent){
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Stammbaum (*.json) ", "json");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(this.parent);
	}
}
