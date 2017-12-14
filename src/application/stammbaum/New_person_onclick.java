package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;

public class New_person_onclick implements ActionListener{
	Stammbaum stammbaum;
	public New_person_onclick(Stammbaum stammbaum){
		this.stammbaum = stammbaum;
	}
	
	public void actionPerformed(ActionEvent e){
		New_Person p = new New_Person(this.stammbaum);
	}
}