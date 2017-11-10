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
	private ArrayList<JButton> vertical = new ArrayList<>();
	private ArrayList<JButton> horizontal = new ArrayList<>();
	public Fenster() {
		super("Stammbaumeditor");
	  	this.setVisible(true);
	  	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	  	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.initialisiere_buttons();
	}
	
	public void initialisiere_buttons(){
		JPanel vert_and_hor = new JPanel(new GridBagLayout());
		/*BoxLayout layout = new BoxLayout(vert_and_hor, BoxLayout.X_AXIS);*/
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		JPanel vert = initialisiere_vertical_buttonbox();
		vert.setPreferredSize(new Dimension(150, 1300));
        vert.setMaximumSize(vert.getPreferredSize()); 
        vert.setMinimumSize(vert.getPreferredSize());
        vert_and_hor.add(vert, c);
		
        /*
		JPanel hor = initialisiere_horizontal_buttonbox();
		hor.setPreferredSize(new Dimension(6*143, 150));
        hor.setMaximumSize(hor.getPreferredSize()); 
        hor.setMinimumSize(hor.getPreferredSize());
        vert_and_hor.add(hor, c);
		*/
		this.add(vert_and_hor);
	}
	
	
	public JPanel initialisiere_vertical_buttonbox(){
		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
	
		int vertical_button_size = 125;
		String vertical_icon_source = "src/data/icons/vertical";
		this.vertical = new ArrayList<>();  
		File v = new File(vertical_icon_source);
		File[] filearray_v = v.listFiles();
		for(File files : filearray_v){
			JButton tmp = new JButton();
			if(Pattern.matches(".*\\d_.*", files.toString())){ // filtert Filenames, in denen [0-9]_ vorkommt, d.h. nur die Icons (dauert en bissl)
				tmp.setIcon(resizeImage(files.toString(), vertical_button_size));
				container.add(tmp);
				this.vertical.add(tmp);
			}
		}
		return container;
	}
	
	public JPanel initialisiere_horizontal_buttonbox(){
		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
		container.setLayout(layout);
	
		int horizontal_button_size = 125;
		String horizontal_icon_source = "src/data/icons/horizontal";
		this.horizontal = new ArrayList<>();
		File h = new File(horizontal_icon_source);
		File[] filearray_h = h.listFiles();
		for(File files : filearray_h){
			JButton tmp = new JButton();
			if(Pattern.matches(".*\\d_.*", files.toString())){ // filtert Filenames, in denen [0-9]_ vorkommt, d.h. nur die Icons (dauert en bissl)
				tmp.setIcon(resizeImage(files.toString(), horizontal_button_size));
				container.add(tmp);
				this.horizontal.add(tmp);
			}
		}
		return container;
	}
	
	public ImageIcon resizeImage(String name, int size){
		ImageIcon imageIcon = new ImageIcon(name);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		return imageIcon; // diese und zwei Zeilen darüber: Skalieren das Bild auf gewünschte Pixelgröße
	}

}
