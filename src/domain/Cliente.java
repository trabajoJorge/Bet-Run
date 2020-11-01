package domain;

import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario {
	

	private String tarjeta;
	private Float saldo;
	private Vector<Apuesta> apuestas=new Vector<Apuesta>();

	
	public Cliente(String username, String ppassword, String pemail,Float psaldo, String nombre,  String ptarjeta) {
		super(username, ppassword, pemail, nombre);
		this.setSaldo(psaldo);
		this.setTarjeta(ptarjeta);
		//this.apuestas=new Vector<Apuesta>();
	}
	
	public Cliente(String pusername, String ppassword, String pemail, String nombre, String ptarjeta) {
		super(pusername, ppassword, pemail, nombre);
		this.setSaldo(Float.parseFloat("0"));
		this.setTarjeta(ptarjeta);
		//this.apuestas=new Vector<Apuesta>();
	}



	
	
	//ACCESO A ATRIBUTOS
	public void setBet (Vector<Apuesta> Apuestas) {
		this.apuestas= Apuestas;
	}
	
	public ArrayList<Apuesta> getApuestas(){
		return new ArrayList<Apuesta>(apuestas);
	}
	
	public void setTarjeta (String ptarjeta) {
		this.tarjeta = ptarjeta;
	}
	
	public String getTarjeta () {
		return this.tarjeta;
	}
	
	public void setSaldo (Float psaldo) {
		this.saldo = psaldo;
	}
	
	public Float getSaldo () {
		return this.saldo;
	}

	public void addBet(Apuesta apuesta) {
		apuestas.add(apuesta);
	}


}
