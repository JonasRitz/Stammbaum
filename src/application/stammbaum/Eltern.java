package application.stammbaum;
//Beziehung für Eltern mit Attribute für Vater und Mutter
//und die teilnehmer sind dann die Kinder
public class Eltern extends Beziehung{
	private Person mutter;
	private Person vater;
	public Eltern(Person mutter, Person vater){
		super("Eltern");
		this.mutter = mutter;
		this.vater = vater;
	}
	
}