package application.stammbaum;
//Beziehung für Ehe mit Attribute für Mann und Frau
//Homosexuell möglich aber zur einfachheit mit
//mann und frau bezeichnet
//keine teilnehmer
public class Ehe extends Beziehung{
	private Person mann;
	private Person frau;
	public Ehe(Person mann, Person frau){
		super("Ehe");
		teilnehmer = null;
		this.mann = mann;
		this.frau = frau;
	}
	
}