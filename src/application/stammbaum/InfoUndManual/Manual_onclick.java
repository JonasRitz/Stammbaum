package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;

public class Manual_onclick implements ActionListener{
	Mainscreen main;
	public Manual_onclick(Mainscreen main){
		this.main = main;
	}
	public void actionPerformed(ActionEvent e){
		try {
			Anleitung frame = new Anleitung();
			frame.setVisible(true);
			frame.setResizable(false);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}
