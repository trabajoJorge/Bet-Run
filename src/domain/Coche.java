package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Coche implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String Matricula;
	private String Modelo;
	private String Caballos;
	private Escuderia Escuderia;
	private boolean enUso;
	private Piloto Piloto;
	
	public Coche(String matricula, String modelo, String caballos, Escuderia escuderia) {
		this.Matricula=matricula;
		this.Modelo= modelo;
		this.Caballos= caballos;
		this.Escuderia= escuderia;
		escuderia.addCoche(this);
	}

	public String getModelo() {return Modelo;}
	public String getCaballos() {return Caballos;}
	public void setCaballos(String caballos) {Caballos = caballos;}
	public Escuderia getEscuderia() {return Escuderia;}
	public Piloto getPiloto() {return Piloto;}
	public void setPiloto(Piloto piloto) {
		Piloto = piloto;
		if (piloto==null)this.setEnUso(false);
		else {
			if (piloto.getCoche()!=null) piloto.getCoche().setEnUso(false);
			piloto.setCoche(this);
			this.setEnUso(true);
		}
		
	}
	public boolean isEnUso() {return enUso;}
	public void setEnUso(boolean enUso) {this.enUso = enUso;}
	public String toString() {return this.Modelo;}

	public String getMatricula() {
		return Matricula;
	}

}
