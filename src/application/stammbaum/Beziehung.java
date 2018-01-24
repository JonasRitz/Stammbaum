package application.stammbaum;
import java.util.*;
public class Beziehung{
	protected Person vater;
	protected Person mutter;
	protected ArrayList<Person> kinder;
	
	public Beziehung(Person vater, Person mutter){
		this.mutter = mutter;
		this.vater = vater;
		kinder = new ArrayList<Person>();
	}
	
	public Person getVater(){
		return vater;
	}
	
	public Person getMutter(){
		return mutter;
	}
	
	public void KindHinzufuegen(Person p){
		kinder.add(p);
	}
	
	public ArrayList<Person> getKinder(){
		return kinder;
	}
}