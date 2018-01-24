package application.stammbaum;
import java.util.*;
public class Beziehung{
	protected Person vater;
	protected Person mutter;
	protected ArrayList<Person> kinder;
	
	public Beziehung(Person mutter, Person Vater){
		this.mutter = mutter;
		this.vater = Vater;
		kinder = new ArrayList<Person>();
	}
	
	public void KindHinzufuegen(Person p){
		kinder.add(p);
	}
	
}