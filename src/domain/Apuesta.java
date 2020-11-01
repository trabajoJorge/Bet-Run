package domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Apuesta {
	@Id 
	@GeneratedValue
	private int idBet;
	private Float DineroApostado; 
	private Date FechaApuesta;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Cliente Cliente;
	private int Estado;
	@OneToOne(cascade=CascadeType.PERSIST)
	private PilotoEnCarrera PilotoEnCarrera;

	public Apuesta(Float dineroApostado, PilotoEnCarrera parrilladesalida, Cliente cliente) {
		this.DineroApostado= dineroApostado;
		this.FechaApuesta=Calendar.getInstance().getTime();
		this.PilotoEnCarrera= parrilladesalida;
		this.PilotoEnCarrera.addApuesta(this);
		this.Estado= 0; //todas las apuestas se crean en estado pendiente.
		this.Cliente= cliente;
		this.Cliente.addBet(this);
		
	}
	public int getId() {return idBet;}
	public Float getDineroApostado() {return DineroApostado;}
	public void setDineroApostado(Float dineroApostado) {DineroApostado = dineroApostado;}
	public Date getFechaApuesta() {return FechaApuesta;}
	public void setFechaApuesta(Date fechaApuesta) {FechaApuesta = fechaApuesta;}
	public int getEstado() {return Estado;}
	public void setEstado(int estado) {this.Estado = estado;}
	public PilotoEnCarrera getPilotoEnCarrera() {return PilotoEnCarrera;}
	public void setPilotoEnCarrera(PilotoEnCarrera pilotoEnCarrera) {PilotoEnCarrera = pilotoEnCarrera;}
	public Cliente getCliente() {return Cliente;}
	public void setCliente(Cliente cliente) {Cliente = cliente;}
}
