package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Escuderia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String Nombre;
	private ArrayList<Coche> Coches= new ArrayList<Coche>();
	private ArrayList<Piloto> Pilotos= new ArrayList<Piloto>();
	
	public Escuderia (String nombre) {
		this.Nombre= nombre;
	}

	public String getNombre() {return Nombre;}
	public ArrayList<Coche> getCoches() {return Coches;}
	public void setCoches(ArrayList<Coche> coches) {Coches = coches;}
	public String toString() {return this.Nombre;}
	public ArrayList<Piloto> getPilotos() {return Pilotos;}
	public void setPilotos(ArrayList<Piloto> pilotos) {Pilotos = pilotos;}
	
	public boolean addCoche(Coche coche) {
		try {
			this.Coches.add(coche);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean addPiloto(Piloto piloto) {
		try {
			this.Pilotos.add(piloto);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public ArrayList<Coche> getCochesLibres() {
		ArrayList<Coche>r= new ArrayList<Coche>();
		for(int i=0; i<this.Coches.size(); i++){
			if (this.Coches.get(i).isEnUso()==true)r.add(this.Coches.get(i)) ;
		}
		return this.Coches;
	}
	

}
