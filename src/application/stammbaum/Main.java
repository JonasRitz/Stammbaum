package application.stammbaum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame{
  public static void main(String argv[]){
	  	//Fenster f1 = new Fenster("Stammbaumgenerator");
	  	JFrame f = new JFrame();
	  	JButton b = new JButton("bdmkfm");
	  	f.add(b);
	  	f.setVisible(true);
	  	f.setSize(500, 500);
	  	b.setIcon(UIManager.getIcon("FileView."));
	  	
	  	b.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent e){
	  			System.out.println("dsfd");
	  			f.removeAll();
	  		}
	  	});  	
  }
  
  public static void starte(){
	  	
  }
}
