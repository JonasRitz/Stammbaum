package application.stammbaum;
import java.util.regex.Pattern;
import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class Fenster extends JFrame {
	private JPanel contentPane;
	
	private final int NEW_PERSON = 0;
	private final int REMOVE_PERSON = 1;
	private final int SETTINGS_PERSON = 2;
	private final int NEW_RELATION = 3;
	private final int REMOVE_RELATION = 4;
	private final int SETTINGS_RELATION = 5;
	
	private final int RETURN = 0;
	private final int SAVE = 1;
	private final int OPEN = 2;
	private final int PRINT = 3;
	private final int ZOOM = 4;
	private final int INFO = 5;
	private final int MANUAl = 6;
	
	private ArrayList<JButton> vertical = new ArrayList<>();
	private ArrayList<JButton> horizontal = new ArrayList<>();
	private int button_size = 130;
	
	public Fenster() {
		super("Stammbaumeditor");
	  	this.setVisible(true);
	  	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	  	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.initialisiere_alles();
	  	this.initialisiere_onClickListener();
	}
	
	public void initialisiere_onClickListener(){
		vertical.get(NEW_PERSON).addActionListener(new action_listener_new_person());
	}

	
	public void initialisiere_alles() {
		Container pane = this.getContentPane();
		JPanel hor = initialisiere_horizontal_buttonbox();
		JPanel ver = initialisiere_vertical_buttonbox();
		JPanel main = create_mainframe();
		pane.add(hor, BorderLayout.PAGE_START);
		pane.add(ver, BorderLayout.LINE_START);
		pane.add(main, BorderLayout.CENTER);
	
	}
	
	public JPanel create_mainframe(){
		CentralFrame sub = new CentralFrame();
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
		sub.setBorder(compound);
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    d.setSize(d.getWidth()-button_size, d.getHeight()-button_size);
		sub.setMaximumSize(d);
		sub.setMinimumSize(d);
		sub.setPreferredSize(d);
		return sub;
	}
	
	public JPanel initialisiere_vertical_buttonbox(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7,1));
		String vertical_icon_source = "src/data/icons/vertical";
		this.vertical = new ArrayList<>();  
		File v = new File(vertical_icon_source);
		File[] filearray_v = v.listFiles();
		String[] filearray = sortiereArray(filearray_v);
		int counter = 0;
		for(String files : filearray){
			JButton tmp = new JButton();
			if(Pattern.matches(".*\\d_.*", files)){ // filtert Filenames, in denen [0-9]_ vorkommt, d.h. nur die Icons (dauert en bissl)
				tmp.setIcon(resizeImage(files, button_size-30));
				this.vertical.add(tmp);
				panel.add(tmp,counter);
				counter++;
			}
		}
		return panel;
	}
	
	public JPanel initialisiere_horizontal_buttonbox(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 6));
		String horizontal_icon_source = "src/data/icons/horizontal";
		this.horizontal = new ArrayList<>();
		File h = new File(horizontal_icon_source);
		File[] filearray_h = h.listFiles();
		String[] filearray = sortiereArray(filearray_h);
		int counter = 0;
		for(String files : filearray){
			JButton tmp = new JButton();
			if(Pattern.matches(".*\\d_.*", files)){ // filtert Filenames, in denen [0-9]_ vorkommt, d.h. nur die Icons (dauert en bissl)
				tmp.setIcon(resizeImage(files, button_size));
				this.horizontal.add(tmp);
				panel.add(tmp,counter);
				counter++;
			}
		}
		return panel;
	}
	
	public String[] sortiereArray(File[] filearray){
		String sorted[] = new String[filearray.length];
		for(int i=0; i<filearray.length; i++){
			sorted[i] = filearray[i].toString();
		}
		Arrays.sort(sorted);
		return sorted;
	}
	
	public ImageIcon resizeImage(String name, int size){
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		return imageIcon; // diese und zwei Zeilen darüber: Skalieren das Bild auf gewünschte Pixelgröße
	}
	

}
