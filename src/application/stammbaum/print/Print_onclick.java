package application.stammbaum;
import java.awt.*;
import java.awt.Insets;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.*;
import java.awt.Image.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.print.*;
import java.io.File;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
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
import java.awt.print.*;
public class Print_onclick implements ActionListener {
	Mainscreen main;
	public Print_onclick(Mainscreen main){
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e){		
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(main.central);
		if(printJob.printDialog()){
			try{
				printJob.print();
			}catch(PrinterException exc){
				exc.printStackTrace();
			}
		}
	}
}
