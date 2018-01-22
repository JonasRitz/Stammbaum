package application.stammbaum;
import java.util.*;
public class Stammbaum{
	private ArrayList<Person> personen;
	private ArrayList<Beziehung> beziehungen;
	
	
	public Stammbaum(){
		System.out.println("Es wurde ein Stammbaum erzeugt");
		personen = new ArrayList<>();
	}
	
	public void personHinzufuegen(Person p){
		personen.add(p);
		System.out.println("Es wurde dem Stammbaum eine Person hinzugefuegt.");
	}
	
	public void personLoeschen(int index){
		personen.remove(index);
	}
	
	public ArrayList<Person> getPersonen(){
		return personen;
	}
	
	public ArrayList<Beziehung> getBeziehungen(){
		return beziehungen;
	}
}