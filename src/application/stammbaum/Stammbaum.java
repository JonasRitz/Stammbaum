package application.stammbaum;
import java.util.*;
public class Stammbaum{
	private ArrayList<Person> personen;
	private CentralFrame c;
	
	public Stammbaum(){
		System.out.println("Es wurde ein Stammbaum erzeugt");
		personen = new ArrayList<>();
		c = new CentralFrame();
	}
	public void personHinzufuegen(Person p){
		personen.add(p);
		System.out.println("Es wurde dem Stammbaum eine Person hinzugefuegt.");
		c.addPerson(p);
	}
	
	public void personLoeschen(int index){
		personen.remove(index);
	}
	
	public ArrayList<Person> getPersonen(){
		return personen;
	}
}