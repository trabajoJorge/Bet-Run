package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private int idCarrera;
	private int Duracion;
	private Date FechaComienzo;
	private Date FechaFinal;
	private String Pais; 
	private String Ciudad;
	private String Circuito; 
	private boolean isApostable;
	private boolean isActiva;
	private boolean isLlena;
	private boolean isFinalizada;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<PilotoEnCarrera> Parrilla= new ArrayList<PilotoEnCarrera>();
 
	public Carrera(Date fechaComienzo, int duracion, String pais, String ciudad, String circuito) {
		this.FechaComienzo= fechaComienzo;
		this.Duracion= duracion;
		this.FechaFinal= addDuracion(fechaComienzo, this.Duracion);
		this.Pais= pais;
		this.Ciudad= ciudad;
		this.Circuito= circuito;
		this.isActiva=false;
		this.isLlena= false;
		this.isFinalizada=false;
		if (Calendar.getInstance().before(fechaComienzo)) this.setApostable(true);
		else this.setApostable(false);
	}

	public int getidCarrera() {return idCarrera;}
	public Date getFechaComienzo() {return FechaComienzo;}
	public void setFechaComienzo(Date fechaComienzo) {FechaComienzo = fechaComienzo;}
	public Date getFechaFinal() {return FechaFinal;}
	public void setFechaFinal(Date fechafinal) {FechaFinal= fechafinal;}
	public String getPais() {return Pais;}
	public void setPais(String pais) {Pais = pais;}
	public String getCiudad() {return Ciudad;}
	public void setCiudad(String ciudad) {Ciudad = ciudad;}
	public String getCircuito() {return Circuito;}
	public void setCircuito(String circuito) {Circuito = circuito;}
	public boolean isActiva() {return isActiva;}
	public void Activar() {
		this.isActiva = true;
		this.isFinalizada= false;
	}
	public void Desactivar() {this.isActiva = false;}
	public ArrayList<PilotoEnCarrera> getPilotosEnCarrera() {
		return Parrilla;
	}
	
	public void setParrilla(ArrayList<PilotoEnCarrera> parrilla) {Parrilla = parrilla;}
	public String toString() {return this.getCircuito();}
	
	public Date addDuracion(Date date, int horas) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, horas);
	    return calendar.getTime();
  }

	public boolean isLlena() {
		return isLlena;
	}

	public void setLlena(boolean isLlena) {
		this.isLlena = isLlena;
	}
	
	public void addParrilla(PilotoEnCarrera parrilla) {
		this.Parrilla.add(parrilla);
	}
	public int getDuracion() {
		return this.Duracion;
	}

	public boolean isFinalizada() {
		return isFinalizada;
	}

	public void setFinalizada(boolean isFinalizada) {
		this.isFinalizada = isFinalizada;
		this.isActiva= !isFinalizada;
	}
	public ArrayList<PilotoEnCarrera> getPilotosOrdenados(){
		ArrayList<PilotoEnCarrera> a= this.getPilotosEnCarrera();
		ArrayList<PilotoEnCarrera> r= new ArrayList<PilotoEnCarrera>();
		for (int i=0; i<=10; i++) {
			for (int j=0; j<a.size(); j++) {
				if (a.get(j).getPosicion()==i) {
					r.add(a.get(j));
					break;
				}
			}
		}
		return r;
	}

	public boolean isApostable() {
		return isApostable;
	}

	public void setApostable(boolean isApostable) {
		this.isApostable = isApostable;
	}
}
