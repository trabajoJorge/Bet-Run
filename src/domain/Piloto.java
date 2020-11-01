package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Piloto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private String DNI;
	private String Nombre; 
	private String Edad; 
	private String Peso; 
	private Escuderia Escuderia;
	private Coche Coche;
	//@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<PilotoEnCarrera> Parrilla;
	private String Imagen;
	private int CarrerasGanadas;
	
	
	public Piloto(String dni, String nombre, String peso, String edad, Escuderia escuderia, String imagen, int victorias) {
		this.Coche=null;
		this.DNI=dni;
		this.Nombre= nombre;
		this.Edad=edad;
		this.Peso=peso;
		this.Escuderia= escuderia;
		escuderia.addPiloto(this);
		this.Parrilla= new ArrayList<PilotoEnCarrera>();
		this.setImagen(imagen);
		this.setCarrerasGanadas(victorias);
	}
	
	public Piloto(String dni, String nombre, String peso, String edad, Escuderia escuderia) {
		this.Coche=null;
		this.DNI=dni;
		this.Nombre= nombre;
		this.Edad=edad;
		this.Peso=peso;
		this.Escuderia= escuderia;
		escuderia.addPiloto(this);
		this.Parrilla= new ArrayList<PilotoEnCarrera>();
		this.setImagen("/gui/Fotos/default.png");
		this.setCarrerasGanadas(0);
	}
	
	public String getDNI() {return DNI;}
	public String getNombre() {return Nombre;}
	public String getEdad() {return Edad;}
	public void setEdad(String edad) {Edad = edad;}
	public String getPeso() {return Peso;}
	public void setPeso(String peso) {Peso = peso;}
	public Escuderia getEscuderia() {return Escuderia;}
	public void setEscuderia(Escuderia escuderia) {Escuderia = escuderia;}
	public ArrayList<PilotoEnCarrera> getParrilla() {return this.Parrilla;}
	public void setParrilla(ArrayList<PilotoEnCarrera> Parrilla) {this.Parrilla = Parrilla;}
	public void addPEC(PilotoEnCarrera parrilla) {this.Parrilla.add(parrilla);}
	public String toString() {return this.Nombre;}
	public Coche getCoche() {return Coche;}
	public String getModeloCoche() {
		if (this.Coche==null)return "Sin asignar";
		else return Coche.getModelo();
	}
	public String getCaballosCoche() {
		if (this.Coche==null)return "Sin asignar";
		else return Coche.getCaballos();
	}
	public void setCoche(Coche coche) {Coche = coche;}
	
	public void addParrilla(PilotoEnCarrera parrilla) {
		this.Parrilla.add(parrilla);
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public int getCarrerasGanadas() {
		return CarrerasGanadas;
	}

	public void setCarrerasGanadas(int carrerasGanadas) {
		CarrerasGanadas = carrerasGanadas;
	}



	/*public void setImagen(String imagen) {
		Imagen = imagen;
	}*/

	
}