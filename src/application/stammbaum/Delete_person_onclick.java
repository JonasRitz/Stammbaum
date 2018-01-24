package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;

public class Delete_person_onclick implements ActionListener{
	Stammbaum stammbaum;
	CentralFrame central;
	
	public Delete_person_onclick(Stammbaum stammbaum, CentralFrame central){
		this.stammbaum = stammbaum;
		this.central = central;
	}
	
	public void actionPerformed(ActionEvent e){
		Delete_Person p = new Delete_Person(this.stammbaum, this.central);
	}
}