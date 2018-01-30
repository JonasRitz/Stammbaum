package application.stammbaum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
public class Main extends JFrame{
  public static void main(String argv[]){
	  erstelle_fenster();
  }
  
  public static void erstelle_fenster(){
	  //Mainscreen f = new Mainscreen();
	  EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startbildschirm frame = new Startbildschirm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
}
