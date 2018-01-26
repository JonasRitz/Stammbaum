package application.stammbaum;
import java.util.*;
public class Stammbaum{
	private ArrayList<Person> personen;
	private ArrayList<Beziehung> beziehungen;
	public Stammbaum(){
		System.out.println("Es wurde ein Stammbaum erzeugt");
		personen = new ArrayList<>();
		beziehungen = new ArrayList<>();
	}
	
	public void personHinzufuegen(Person p){
		personen.add(p);
		System.out.println("Es wurde dem Stammbaum eine Person hinzugefuegt.");
	}
	
	public boolean istInBeziehung(Person p){
		for(Beziehung b : beziehungen){
			if(b.getVater() == p || b.getMutter() == p || b.getKinder().contains(p)){
				return true;
			}
		}
		return false;
	}
	
	public void personEntfernen(Person p){
		personen.remove(p);
		
		for(Beziehung b : beziehungen){
			if(b.getVater() == p || b.getMutter() == p){
				beziehungen.remove(b);
			}
			if(b.getKinder().indexOf(p) != -1){
				b.getKinder().remove(p);
			}
		}
		
	}
	
	public void beziehungHinzufuegen(Beziehung neu){
		for(Beziehung old : beziehungen){
			if(old.getVater() == neu.getVater() && old.getMutter() == neu.getMutter()){
				System.out.println("Es wurde im Stammbaum eine Beziehung ueberarbeitet.");
				for(Person kind : neu.getKinder()){
					old.KindHinzufuegen(kind);
				}
				return;
			}
		}
		beziehungen.add(neu);
		System.out.println("Es wurde dem Stammbaum eine Beziehung hinzugefuegt.");
	}
	
	public ArrayList<Person> getPersonen(){
		return personen;
	}
	
	public ArrayList<Beziehung> getBeziehungen(){
		return beziehungen;
	}
}