package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;

public class Delete_relation_onclick implements ActionListener{
	Mainscreen main;
	public Delete_relation_onclick(Mainscreen main){
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e){
		Delete_relation p = new Delete_relation(this.main);
	}
}