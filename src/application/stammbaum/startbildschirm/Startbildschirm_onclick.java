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

public class Startbildschirm_onclick implements ActionListener {
	Mainscreen main;
	Stammbaum stammbaum;
	public Startbildschirm_onclick(Mainscreen main){
		this.main = main;
		this.stammbaum = main.stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		//TODO Startbildschirm oeffnen
	}
}
