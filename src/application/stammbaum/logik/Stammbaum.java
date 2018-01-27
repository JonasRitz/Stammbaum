package application.stammbaum;
import java.util.*;
public class Stammbaum{
	protected ArrayList<Person> personen;
	protected ArrayList<Beziehung> beziehungen;
	public Stammbaum(){
		System.out.println("Es wurde ein Stammbaum erzeugt");
		personen = new ArrayList<>();
		beziehungen = new ArrayList<>();
	}
	
	public ArrayList<Person[]> getHead(){
		ArrayList<Person[]> liste = new ArrayList<>();
		for(Beziehung b : beziehungen){
			if(gibEltern(b.getVater()) == null && gibEltern(b.getMutter()) == null){
				Person eltern[] = {b.getVater(), b.getMutter()};
				liste.add(eltern);
			}
		}
		return liste;
	}
	
	public Person[] gibEltern(Person p){
		for(Beziehung b: beziehungen){
			if(b.getKinder().contains(p)){
				Person eltern[] = {b.getVater(), b.getMutter()};
				return eltern;
			}
		}
		return null;
	}
	
	public ArrayList<Person> getKinderZuEltern(Person[] p){
		for(Beziehung b : beziehungen){
			if(b.getVater() == p[0] && b.getMutter() == p[1] || b.getVater() == p[1] && b.getMutter() == p[0]){
				return b.getKinder();
			}
		}
		return null;
	}
	
	public Person getBeziehungsPartner(Person p){
		for(Beziehung b : beziehungen){
			if(b.getVater() == p){
				return b.getMutter();
			}else if(b.getMutter() == p){
				return b.getVater();
			}
		}
		return null;
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
		for(int i=0; i<beziehungen.size(); i++){
			if(beziehungen.get(i).getVater() == p || beziehungen.get(i).getMutter() == p){
				beziehungen.remove(beziehungen.get(i));
			}else if(beziehungen.get(i).getKinder().indexOf(p) != -1){
				beziehungen.get(i).getKinder().remove(p);
			}
		}
		
	}
	
	public void beziehungHinzufuegen(Beziehung neu){
		for(Beziehung old : beziehungen){
			if(old.getVater() == neu.getVater() && old.getMutter() == neu.getMutter()){
				for(Person kind : neu.getKinder()){
					old.KindHinzufuegen(kind);
				}
				return;
			}
		}
		beziehungen.add(neu);
	}
	
	public void beziehungEntfernen(Beziehung bez1){
		beziehungen.remove(bez1);
	}
	
	public ArrayList<Person> getPersonen(){
		return personen;
	}
	
	public ArrayList<Beziehung> getBeziehungen(){
		return beziehungen;
	}
}