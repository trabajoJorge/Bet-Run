package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PilotoEnCarrera implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int idPilotoEnCarrera;
	private int Posicion;
	private float CuotaGanancia;
	private float ApuestaMinima;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Carrera Carrera;
	//@OneToOne(cascade=CascadeType.PERSIST)
	private Piloto Piloto;

	private ArrayList<Apuesta> apuestas= new ArrayList<Apuesta>();
	
	public PilotoEnCarrera (int pos, Piloto piloto, Carrera carrera, float cuotaGanancia,  float apuestaMinima) {
		this.setPosicion(pos);
		this.Piloto=piloto;
		this.Carrera=carrera;
		this.CuotaGanancia= cuotaGanancia;
		this.ApuestaMinima= apuestaMinima;
		this.Piloto.addParrilla(this);
		this.Carrera.addParrilla(this);
	}
	
	public int getidPilotoEnCarrera() {return idPilotoEnCarrera;}
	public float getCuotaGanancia() {return CuotaGanancia;}
	public void setCuotaGanancia(float cuotaGanancia) {CuotaGanancia = cuotaGanancia;}
	public float getApuestaMinima() {return ApuestaMinima;}
	public void setApuestaMinima(float apuestaMinima) {ApuestaMinima = apuestaMinima;}
	public Piloto getPiloto() {return Piloto;}
	public void setPiloto(Piloto piloto) {Piloto = piloto;}
	public Carrera getCarrera() {return Carrera;}
	public void setCarrera(Carrera carrera) {Carrera = carrera;}
	public ArrayList<Apuesta> getApuestas() {return apuestas;}
	public void setApuestas(ArrayList<Apuesta> apuestas) {this.apuestas = apuestas;}
	public String toString() {
		return  this.Posicion+". "+this.Piloto.toString();}
	public void addApuesta(Apuesta apu) {
		this.getApuestas().add(apu);
	}

	public int getPosicion() {
		return Posicion;
	}

	public void setPosicion(int posicion) {
		Posicion = posicion;
	}
}