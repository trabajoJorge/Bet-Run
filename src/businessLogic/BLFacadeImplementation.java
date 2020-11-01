package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Apuesta;
import domain.Carrera;
import domain.Cliente;
import domain.Coche;
import domain.Escuderia;
import domain.PilotoEnCarrera;
import domain.Piloto;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	@WebMethod public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}
	public Vector<Coche> getCoches(){
		DataAccess dbManager=new DataAccess();
		Vector<Coche>  c=dbManager.getCoches();
		dbManager.close();
		return c;
	}

  //
  /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
  //
  //CREAR PILOTO
  public boolean crearPiloto(Piloto piloto){
	    DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.crearPiloto(piloto);		
		dBManager.close();
		return b;
   };
   public boolean IsPilotoAñadido(Piloto piloto){
	    DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.IsPilotoAñadido(piloto);
		dBManager.close();
		return b;
   }
   //
   /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
   //
   //CREAR CARRERA
	public Carrera getCarreraFinalizable() {
		DataAccess dBManager=new DataAccess();
	    Carrera b= dBManager.getCarreraFinalizable();		
		dBManager.close();
		return b;
  };

   public boolean crearCarrera(Carrera carrera){
	    DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.crearCarrrera(carrera);		
		dBManager.close();
		return b;
  };

  public ArrayList<PilotoEnCarrera> getPilotosEnCarreraOrdenados(Carrera c){
	  	DataAccess dBManager=new DataAccess();
	  	ArrayList<PilotoEnCarrera> r= dBManager.getPilotosEnCarreraOrdenados(c);
	  	dBManager.close();
		return r;
};
  //
  /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
  //
  //CREAR ESCUDERIA
  public boolean crearEscuderia(Escuderia escuderia){
	    DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.crearEscuderia(escuderia);
		dBManager.close();
		return b;
  };
  //
  /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
  //
  //CREAR COCHES ESCUDERIA
  public ArrayList<Escuderia> getescuderias(){
	    DataAccess dBManager=new DataAccess();
	    ArrayList<Escuderia> r= dBManager.getEscuderias();
		dBManager.close();
		return r;
  };
  public boolean crearCoche(Coche coche) {
	  	DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.crearCoche(coche) ;
		dBManager.close();
		return b;
  }
  //
  /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
  //
  // AÑADIR A CORRERDOR EN CARRERA
  public Vector<Carrera> getCarreras(Date fechaInicio, Date fechaFinal) {
		DataAccess dbManager=new DataAccess();
		Vector<Carrera>  events=dbManager.getCarreras(fechaInicio, fechaFinal);
		dbManager.close();
		return events;
	}
  //
  /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
  //
  //CREAR CARRERA
  public Vector<Carrera> getCarrerasActivas(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Carrera>  events=dbManager.getCarrerasActivas(date);
		dbManager.close();
		return events;
	}
   
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	public Vector<Piloto>getPilotosDisponibles(Carrera selectedCarrera){
		DataAccess dbManager=new DataAccess();
		Vector<Piloto> r =dbManager.getPilotosDisponibles(selectedCarrera);
		dbManager.close();
		return r;
	}
	
	
	public boolean añadirPilotoCarrera(int pos, Piloto selectedPiloto, Carrera selectedCarrera, Float cuotaGanancia, Float apuestaMin){
    	DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.añadirPilotoCarrera(pos, selectedPiloto, selectedCarrera, cuotaGanancia, apuestaMin);
		dBManager.close();
		return b;
    }
	public int numeroCorrectoPilotos(Carrera c) {
		DataAccess dBManager=new DataAccess();
	    int b= dBManager.numeroCorrectoPilotos(c);
		dBManager.close();
		return b;
	}
	public void setLlena(Carrera c){
		DataAccess dBManager=new DataAccess();
	    dBManager.setLlena(c);
		dBManager.close();
	}
	
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//ASIGNAR PILOTOS A COCHES
	public boolean asignarCochePiloto(Piloto selectedPiloto, Coche selectedCoche) {
		DataAccess dBManager=new DataAccess();
	    boolean b= dBManager.asignarCochePiloto(selectedPiloto, selectedCoche);
		dBManager.close();
		return b;
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//LOGIN Y REGISTRARSE
	public boolean doLogin (String pusername, String ppassword) {
    	DataAccess dBManager=new DataAccess();
		boolean existUser = dBManager.doLogin(pusername, ppassword);
		dBManager.close();
    	return existUser;
    }
    
	public boolean doRegister(String pusername, String ppassword, String pemail) {
		DataAccess dBManager=new DataAccess();
		boolean existUser = dBManager.doRegister(pusername, ppassword, pemail);
		dBManager.close();
		return existUser;
	}
	
	public boolean isAdmin(String pusername, String ppassword) {
		DataAccess dBManager=new DataAccess();
		boolean isAdmin = dBManager.isAdmin(pusername, ppassword);
		dBManager.close();
		return isAdmin;
	}
	@Override
	public Cliente getClientByUsername(String pusername) {
		DataAccess dbManager= new DataAccess();
		Cliente CliDB = dbManager.getClientByUsername(pusername);
		dbManager.close();
		return CliDB;
	}
	public Admin getAdminByUsername(String pusername) {
		DataAccess dbManager= new DataAccess();
		Admin AdmDB = dbManager.getAdminByUsername(pusername);
		dbManager.close();
		return AdmDB;
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//APOSTAR
	public Carrera getCarreraActiva() {
		DataAccess dBManager=new DataAccess();
	    Carrera c= dBManager.getCarreraActiva();
		dBManager.close();
		return c;
	}
	public boolean crearApuesta(Float dineroApostado, PilotoEnCarrera parrilladesalida, Cliente cliente){
		DataAccess dBManager=new DataAccess();
	    boolean b= dBManager. crearApuesta(dineroApostado, parrilladesalida, cliente);
		dBManager.close();
		return b;
	}
	@Override
	public Vector<Piloto> getPilotosConCoche() {
			DataAccess dbManager=new DataAccess();
			Vector<Piloto> r =dbManager.getPilotosConCoche();
			dbManager.close();
			return r;
	}
	public Piloto getPiloto(String n) {
		DataAccess dbManager=new DataAccess();
		Piloto r =dbManager.getPiloto(n);
		dbManager.close();
		return r;
	}
	
	public ArrayList<Apuesta> getApuestasTodas(){
		DataAccess dbManager=new DataAccess();
		ArrayList<Apuesta> r =dbManager.getApuestasTodas();
		dbManager.close();
		return r;
	}
	
	public ArrayList<Apuesta> getApuestasEstado(Carrera carrera, int estado){
		DataAccess dbManager=new DataAccess();
		ArrayList<Apuesta> r =dbManager.getApuestasEstado(carrera, estado);
		dbManager.close();
		return r;
	}
	
	public ArrayList<Apuesta> getApuestasCliente(Cliente cliente){
		DataAccess dbManager=new DataAccess();
		ArrayList<Apuesta> r =dbManager.getApuestasCliente(cliente);
		dbManager.close();
		return r;
	}
	
	public int numeroApuestasTotal(){
		DataAccess dbManager=new DataAccess();
		int r =dbManager.numeroApuestasTotal();
		dbManager.close();
		return r;
	}
	
	public int numeroApuestasClienteTotal(Cliente cliente) {
		DataAccess dbManager=new DataAccess();
		int r =dbManager.numeroApuestasClienteTotal(cliente);
		dbManager.close();
		return r;
	}
	
	public boolean puedeApostar(Cliente cliente, Carrera carrera){
		DataAccess dbManager=new DataAccess();
		boolean r =dbManager.puedeApostar(cliente, carrera);
		dbManager.close();
		return r;
	}
	
	public boolean finalizarCarrera(Carrera carrera, PilotoEnCarrera ganador){
		DataAccess dbManager=new DataAccess();
		boolean r =dbManager.finalizarCarrera(carrera, ganador);
		dbManager.close();
		return r;
	}
	
	public float consultarFondos(Cliente c){
		DataAccess dbManager=new DataAccess();
		float r =dbManager.consultarFondos(c);
		dbManager.close();
		return r;
	}
	
	public boolean añadirFondos(Cliente cliente, Float fondos){
		DataAccess dbManager=new DataAccess();
		boolean r =dbManager.añadirFondos(cliente, fondos);
		dbManager.close();
		return r;
	}
	
	public boolean retirarFondos(Cliente cliente, Float fondos){
		DataAccess dbManager=new DataAccess();
		boolean r =dbManager.retirarFondos(cliente, fondos);
		dbManager.close();
		return r;
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//APOSTAR
	public boolean confirmarEditarPerfilCliente(String pNombre, String pUsername, String pEmail, String pTarjeta) {
		DataAccess dbManager=new DataAccess();
		boolean r =dbManager.confirmarEditarPerfilCliente(pNombre, pUsername, pEmail, pTarjeta);
		dbManager.close();
		return r;
	}
	
}
 