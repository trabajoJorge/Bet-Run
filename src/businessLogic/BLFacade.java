package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Admin;
import domain.Apuesta;
import domain.Carrera;
import domain.Cliente;
import domain.Coche;
import domain.Escuderia;
import domain.Piloto;
import domain.PilotoEnCarrera;

@WebService
public interface BLFacade {

	public void initializeBD();

	public Vector<Date> getEventsMonth(Date date);

	/*
	 * @WebMethod public Vector<Carrera> getEvents(Date date);
	 */
	public Carrera getCarreraFinalizable();

	// CREAR PILOTO
	public boolean crearPiloto(Piloto piloto);

	public boolean IsPilotoAñadido(Piloto piloto);

	// CREAR CARRERA GUI
	public boolean crearCarrera(Carrera carrera);

	// CREAR ESCUDERIA
	public boolean crearEscuderia(Escuderia escuderia);

	// CREAR COCHES ESCUDERIA
	public ArrayList<Escuderia> getescuderias();

	public boolean crearCoche(Coche coche);

	// AÑADIR PILOTO A CARRERA
	public Vector<Carrera> getCarreras(Date fechaInicio, Date fechaFinal);

	public Vector<Coche> getCoches();

	//
	public Vector<Carrera> getCarrerasActivas(Date date);

	public Vector<Piloto> getPilotosDisponibles(Carrera selectedCarrera);

	public boolean añadirPilotoCarrera(int pos, Piloto selectedPiloto, Carrera selectedCarrera, Float cuotaGanancia,
			Float apuestaMin);

	public boolean asignarCochePiloto(Piloto selectedPiloto, Coche selectedCoche);

	public Vector<Piloto> getPilotosConCoche();

	public boolean doLogin(String username, String password);

	public boolean doRegister(String username, String password, String email);

	public boolean isAdmin(String username, String password);

	@WebMethod
	public Cliente getClientByUsername(String us);

	@WebMethod
	public Admin getAdminByUsername(String us);

	public Carrera getCarreraActiva();

	public boolean crearApuesta(Float dineroApostado, PilotoEnCarrera parrilladesalida, Cliente cliente);

	public int numeroCorrectoPilotos(Carrera c);

	public Piloto getPiloto(String n);

	public void setLlena(Carrera c);

	public ArrayList<Apuesta> getApuestasTodas();

	public ArrayList<Apuesta> getApuestasEstado(Carrera carrera, int estado);

	public ArrayList<Apuesta> getApuestasCliente(Cliente c);

	public int numeroApuestasTotal();

	public int numeroApuestasClienteTotal(Cliente c);

	public boolean puedeApostar(Cliente c, Carrera car);

	public boolean finalizarCarrera(Carrera c, PilotoEnCarrera ganador);

	public float consultarFondos(Cliente c);

	public boolean añadirFondos(Cliente c, Float fondos);

	public boolean retirarFondos(Cliente c, Float fondos);

	public boolean confirmarEditarPerfilCliente(String pNombre, String pUsername, String pEmail, String pTarjeta);

	public ArrayList<PilotoEnCarrera> getPilotosEnCarreraOrdenados(Carrera c);

	/*
	 * @WebMethod public Pilo to getPilotByNombre(String pnombre);
	 * 
	 * 
	 * 
	 * @WebMethod public boolean addBet(Apuesta apu);
	 * 
	 * @WebMethod public Vector<Piloto> getDriversAvailable(Carrera Carrera);
	 * 
	 * @WebMethod public boolean addDriverToEvent(Piloto selectedDriver, Carrera
	 * selectedEvent);
	 */
}
