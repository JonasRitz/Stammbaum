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

public class Refresh_onclick implements ActionListener {
	Mainscreen main;
	Stammbaum stammbaum;
	protected final int BEZIEHUNGS_GEFLECHT;
	public Refresh_onclick(Mainscreen main){
		this.main = main;
		this.stammbaum = main.stammbaum;
		BEZIEHUNGS_GEFLECHT = main.BEZIEHUNGS_GEFLECHT;
	}
	
	public void actionPerformed(ActionEvent e){
		main.central.refreshAll(stammbaum);
		main.vertical.get(BEZIEHUNGS_GEFLECHT).removeActionListener(main.vertical.get(BEZIEHUNGS_GEFLECHT).getActionListeners()[0]);
		main.vertical.get(BEZIEHUNGS_GEFLECHT).addActionListener(new Beziehungsgeflecht_onclick(main));
	}
}
