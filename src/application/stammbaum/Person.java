package application.stammbaum;
import java.time.LocalDate;
public class Person{
	private String vorname;
	private String nachname;
	private String geschlecht;
	private String imageSource;
	private LocalDate geburtsdatum;
	private LocalDate sterbedatum;
	
	public Person(){}
	
	public Person(String vorname, String nachname, String geschlecht, String imageSource, LocalDate geburtsdatum, LocalDate sterbedatum){
		System.out.println("Es wurde eine Person erzeugt");
		this.vorname = vorname;
		this.nachname = nachname;
		this.geschlecht = geschlecht;
		this.imageSource = imageSource;
		this.geburtsdatum = geburtsdatum;
		this.sterbedatum = sterbedatum;
	}
	
	public String getVorname(){
		return this.vorname;
	}
	public String getNachname(){
		return this.nachname;
	}
	public String getGeschlecht(){
		return this.geschlecht;
	}
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(this.vorname).append(" ").append(this.nachname);
		return b.toString();
	}
	
}