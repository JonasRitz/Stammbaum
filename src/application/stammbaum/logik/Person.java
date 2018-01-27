package application.stammbaum;
import java.time.LocalDate;

public class Person{
	static int counter;
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
	public String getImageSource(){
		return this.imageSource;
	}
	public LocalDate getGeburtsdatum(){
		return this.geburtsdatum;
	}
	public LocalDate getSterbedatum(){
		return this.sterbedatum;
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
	public void setImageSource(String imageSource){
		this.imageSource = imageSource;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}
	public void setSterbedatum(LocalDate sterbedatum){
		this.sterbedatum = sterbedatum;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(this.vorname).append(" ").append(this.nachname);
		return b.toString();
	}
	
}