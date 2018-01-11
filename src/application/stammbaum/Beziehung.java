package application.stammbaum;
import java.util.*;
public class Beziehung{ // Modell für Beziehungen, //z.B. Geschwister oder Leute die im selben Verein Spielen
	protected String name;
	protected ArrayList<Person> teilnehmer;
	public Beziehung(String name){
		this.name = name;
		teilnehmer = new ArrayList<Person>();
	}
	
	public void hinzufuegen(Person p){
		teilnehmer.add(p);
	}
}