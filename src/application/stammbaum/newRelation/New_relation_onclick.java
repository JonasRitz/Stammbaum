package application.stammbaum;

import java.io.FileReader;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;

public class New_relation_onclick implements ActionListener{
	Mainscreen main;
	public New_relation_onclick(Mainscreen main){
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e){
		New_Relation p = new New_Relation(this.main);
	}
}