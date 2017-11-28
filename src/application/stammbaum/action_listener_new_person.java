package application.stammbaum;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class action_listener_new_person implements ActionListener {
	public void actionPerformed(ActionEvent e){
		New_Person p1 = new New_Person();
	}
}