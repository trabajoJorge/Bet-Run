package dataAccess;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apuesta;
import domain.Carrera;
import domain.Cliente;
import domain.Coche;
import domain.Escuderia;
import domain.PilotoEnCarrera;
import domain.Piloto;
import domain.Usuario;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {
		
		c=ConfigXML.getInstance();
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
	}
	
	
	
	

	public DataAccess()  {	
		 new DataAccess(false);
	}
	

	
	
	public void initializeDB(){
		
		
		try {

			
		  Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1; // Abril
		   @SuppressWarnings("unused")
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
		   	Cliente cli01 = new Cliente("C.JoseAngel", "trabajode10", "ja.vadillo@ehu.eus", (float) 20000.0, "Jose Angel Vadillo", "1245 6543 3456 8766");
			Cliente cli02 = new Cliente("C.pobre", "p", "soyPobre@mail.com", (float) 0.0 , "Soy Pobre", "2345 6543 3456 8766");
			Cliente cli03 = new Cliente("C.rico", "r", "soyRico@mail.com", (float) 1000000.0 , "Soy Rico", "3445 6543 3456 8766");
			Cliente cli04 = new Cliente("C.Alfredo", "trabajode10", "alfredo@ehu.eus", (float) 20001.0 , "Usuario4 Apellido4", "4545 6543 3456 8766");
			Cliente cli05 = new Cliente("c", "c", "c@mail.com", (float) 350.0 , "Jorge Iglesias", "5645 6543 3456 8766");
			
			db.persist(cli01);
			db.persist(cli02);
			db.persist(cli03);
			db.persist(cli04);
			db.persist(cli05);

			Admin adm = new Admin("A.JoseAngel", "trabajode10", "ja.vadillo@ehu.eus");
			Admin adm01 = new Admin("A.Alfredo", "trabajode10", "alfredo@ehu.eus");
			Admin adm02 = new Admin("a", "a", "a@mail.com");
			
			db.persist(adm);
			db.persist(adm01);
			db.persist(adm02);
			
			Escuderia e1= new Escuderia("Mercedes");
			Escuderia e2= new Escuderia("McLaren");
			Escuderia e3= new Escuderia("Red Bull Racing");
			Escuderia e4= new Escuderia("Ferrari");
			Escuderia e5= new Escuderia("Williams");
			Escuderia e6= new Escuderia("Haas F1 Team");
			Escuderia e7= new Escuderia("Alfa Romeo Racing");
			Escuderia e8= new Escuderia("Renault");
			Escuderia e9= new Escuderia("Alpha Tauri");
			Escuderia e10= new Escuderia("Racing Point");
			
			Piloto d1= new Piloto("111000000C", "Lewis Hamilton", "68", "35", e1, "/gui/Fotos/Hamilton.png", 150);
			Piloto d2= new Piloto("122000000W", "Valteri Bottas",  "70", "30", e1, "/gui/Fotos/Bottas.png", 45);
			
			Piloto d3= new Piloto("213000000R","Carlos Sainz", "75", "25", e2, "/gui/Fotos/Albon.png", 1);
			Piloto d4= new Piloto("213000000R","Lando Norrris", "75", "25", e2,"/gui/Fotos/Norris.png", 0);
			
			Piloto d5= new Piloto("314000000U","Max Verstappen", "72", "23", e3, "/gui/Fotos/Verstappen.png", 31);
			Piloto d6 =new Piloto("325000000R","Alexander Albon", "73", "22", e3,"/gui/Fotos/Albon.png", 0);
			
			Piloto d7= new Piloto("415000000F", "Sebastian Vettel", "62", "33", e4, "/gui/Fotos/Vettel.png", 120);
			Piloto d8= new Piloto("426000000K", "Charles Leclerc", "70", "23", e4, "/gui/Fotos/Leclerc.png", 10);
			
			Piloto d9= new Piloto("517000000R", "George Russell", "74", "36", e5, "/gui/Fotos/Geo.png", 0); 
			Piloto d10= new Piloto("528000000N","Nicolas Lafiti", "80", "26", e5, "/gui/Fotos/Lafiti.png", 0);
			
			Piloto d11= new Piloto("619000000W","Romain Grosjean", "73", "21", e6, "/gui/Fotos/Romain.png", 10);
			Piloto d12= new Piloto("621000000A","Kevin Magnussen", "83", "25", e6, "/gui/Fotos/Kevin.png", 1);
			
			Piloto d14= new Piloto("711100000W","Kimi Raikkonen", "83", "28", e7, "/gui/Fotos/Kimi.png", 103);
			Piloto d15= new Piloto("721200000W","Antonio Giovinzzi", "65", "23", e7, "/gui/Fotos/Antonio.png", 0);
			
			Piloto d16= new Piloto("811300000W","Daniel Ricciardo", "72", "19", e8,"/gui/Fotos/Ric.png", 29);
			Piloto d17= new Piloto("821450000W","Esteban Ocon", "80", "25", e8,"/gui/Fotos/Ocon.png", 0);
			
			Piloto d18= new Piloto("911590000W","Pierre Gasly", "72", "19", e9, "/gui/Fotos/Gasly.png", 1);
			Piloto d19= new Piloto("921670000W","Daniil Kvyat", "80", "25", e9, "/gui/Fotos/Kvyat.png", 3);
			
			Piloto d20= new Piloto("1011760000W","Sergio Perez", "72", "19", e10, "/gui/Fotos/Perez.png", 8);
			Piloto d21= new Piloto("1021890000W","Lance Stroll", "80", "25", e10, "/gui/Fotos/Lance.png", 1);
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2020, 3, 30, 20, 00, 00);
			Date fecha= calendar.getTime();
			
			Carrera c1= new Carrera(fecha, 2, "Azerbaiyán","Baku", "Baku Urban Circuit");
			c1.Activar();
			
			Coche co1= new Coche("Mercedes W11-1", "Mercedes W11", "1503", e1);
			Coche co2= new Coche("Mercedes W11-2", "Ferrari SF1000", "1503", e1);
			
			Coche co3= new Coche("McLaren MCL35-1", "McLaren MCL35", "935", e2);
			Coche co4= new Coche("McLaren MCL35-2", "McLaren MCL35", "935", e2);
			
			Coche co5= new Coche("Red Bull Racing RB16-1", "Red Bull Racing RB160", "1416", e3);
			Coche co6= new Coche("Red Bull Racing RB16-2", "Red Bull Racing RB16", "1416", e3);
			
			Coche co7= new Coche("Ferrari SF1000-1", "Ferrari SF1000", "1256", e4);
			Coche co8= new Coche("Ferrari SF1000-2", "Ferrari SF1000", "1256", e3);
			
			Coche co9= new Coche("Williams FW43-1", "Williams FW43", "943", e5);
			Coche co10= new Coche("Williams FW43-2", "Williams FW43", "943", e5);
			
			Coche co11= new Coche("Haas F1 Team VF-20-1", "Haas F1 Team VF-20", "920", e6);
			Coche co12= new Coche("Haas F1 Team VF-20-2", "Haas F1 Team VF-20", "920", e6);
			
			Coche co13= new Coche("Alfa Romeo C39-1", "Alfa Romeo C39", "943", e7);
			Coche co14= new Coche("Alfa Romeo C39-2", "Alfa Romeo C39", "943", e7);
			
			Coche co15= new Coche("Renault RS20-1", "Renault RS20", "1020", e8);
			Coche co16= new Coche("Renault RS20-2", "Renault RS20", "1020", e8);
			
			Coche co17= new Coche("AlphaTauri AT01-1", "AlphaTauri AT01", "901", e9);
			Coche co18= new Coche("AlphaTauri AT01-2", "AlphaTauri AT01", "901", e9);
			
			Coche co19= new Coche("Racing Point RP20-1", "Racing Point RP20", "920", e10);
			Coche co20= new Coche("Racing Point RP20-2", "Racing Point RP20", "920", e10);

			db.getTransaction().begin();
			
			db.persist(e1);
			db.persist(e2);
			db.persist(e3);
			db.persist(e4);
			db.persist(e5);
			db.persist(e6);
			db.persist(e7);
			db.persist(e8);
			db.persist(e9);
			
			db.persist(d1);
			db.persist(d2);
			db.persist(d3);
			db.persist(d4);
			db.persist(d5);
			db.persist(d6);
			db.persist(d7);
			db.persist(d8);
			db.persist(d9);
			db.persist(d10);
			db.persist(d11);
			db.persist(d12);
			db.persist(d14);
			db.persist(d15);
			db.persist(d16);
			db.persist(d17);
			db.persist(d18);
			db.persist(d19);
			db.persist(d20);
			db.persist(d21);
			
			db.persist(c1);
			
			db.persist(co1);
			db.persist(co2);
			db.persist(co3);
			db.persist(co4);
			db.persist(co5);
			db.persist(co6);
			db.persist(co7);
			db.persist(co8);
			db.persist(co9);
			db.persist(co10);
			db.persist(co11);
			db.persist(co12);
			db.persist(co13);
			db.persist(co14);
			db.persist(co15);
			db.persist(co16);
			db.persist(co17);
			db.persist(co18);
			db.persist(co19);
			db.persist(co20);
			
			db.getTransaction().commit();
			
			asignarCochePiloto(d1, co1);
			asignarCochePiloto(d2, co2);
			asignarCochePiloto(d3, co3);
			asignarCochePiloto(d4, co4);
			asignarCochePiloto(d5, co5);
			asignarCochePiloto(d6, co6);
			asignarCochePiloto(d7, co7);
			asignarCochePiloto(d8, co8);
			asignarCochePiloto(d9, co9);
			asignarCochePiloto(d10, co10);
			asignarCochePiloto(d11, co11);
			asignarCochePiloto(d12, co12);
			asignarCochePiloto(d14, co13);
			asignarCochePiloto(d15, co14);
			asignarCochePiloto(d16, co15);
			asignarCochePiloto(d17, co16);
			asignarCochePiloto(d18, co17);
			asignarCochePiloto(d19, co18);
			asignarCochePiloto(d20, co19);
			asignarCochePiloto(d21, co20);
			
			añadirPilotoCarrera(1, d1, c1, Float.parseFloat("2"), Float.parseFloat("2")); 
			añadirPilotoCarrera(2, d2, c1, Float.parseFloat("3"), Float.parseFloat("4")); 
			añadirPilotoCarrera(3, d3, c1, Float.parseFloat("4"), Float.parseFloat("5"));
			añadirPilotoCarrera(5, d5, c1, Float.parseFloat("5"), Float.parseFloat("7")); 
			añadirPilotoCarrera(6, d6, c1, Float.parseFloat("6"), Float.parseFloat("2")); 
			añadirPilotoCarrera(7, d7, c1, Float.parseFloat("7"), Float.parseFloat("6")); 
			añadirPilotoCarrera(8, d8, c1, Float.parseFloat("8"), Float.parseFloat("2"));		
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Al inicializar la base de datos");
		}
	}
	
	public Vector<Coche> getCoches(){
		db.getTransaction().begin();
		TypedQuery<Coche> query = db.createQuery("SELECT e FROM Coche e",Coche.class);   
		List<Coche>q= new ArrayList<Coche>();
		q= query.getResultList();
		Vector<Coche> res= new Vector<Coche>(q);
		return res;
	}
	public Carrera getCarreraFinalizable() {
		try {
		Carrera c= getCarreraActiva();
		if (c.isApostable())return c;
		else return null;
		}catch (Exception e) {
			return null;
		}
	}
	
	//CREAR PILOTO
	public boolean IsPilotoAñadido(Piloto piloto)  {
		TypedQuery<Piloto> query = db.createQuery("SELECT d.Nombre FROM Piloto d WHERE d.DNI=?1",Piloto.class);   
		query.setParameter(1, piloto.getDNI());
		ArrayList<Piloto> res= new ArrayList<Piloto>(query.getResultList());
		if (res.size()!=0)return false;
		else return true;
	}
	
	public boolean crearPiloto(Piloto piloto)  {
		db.getTransaction().begin();
		TypedQuery<Piloto> query = db.createQuery("SELECT d.Nombre FROM Piloto d WHERE d.DNI=?1",Piloto.class);   
		query.setParameter(1, piloto.getDNI());
		ArrayList<Piloto> res= new ArrayList<Piloto>(query.getResultList());
		if (res.size()!=0)return false;
		else {
			db.persist(piloto); 
			db.getTransaction().commit();
		}
		boolean b= añadirPilotoEscuderia(piloto, piloto.getEscuderia().toString());
		return b;
	}
	private boolean añadirPilotoEscuderia(Piloto piloto, String escuderia) {
		db.getTransaction().begin();
		boolean b= false;
		TypedQuery<Escuderia> query = db.createQuery("SELECT e FROM Escuderia e",Escuderia.class);   
		List<Escuderia>q= query.getResultList();
		for (Escuderia e: q) {
			if (e.getNombre().equals(escuderia)) {
				b= e.addPiloto(piloto);
				db.persist(e);
			}
		}
		db.getTransaction().commit();
		return b;
	}
	
	//CREAR CARRERA
	public boolean crearCarrrera(Carrera carrera)  {
		db.getTransaction().begin();
		TypedQuery<Carrera> query = db.createQuery("SELECT c FROM Carrera c" ,Carrera.class);   
		List<Carrera> res= query.getResultList();
		for (Carrera c: res) {
			if (c.getCircuito().equals(carrera.getCircuito()) && c.getCiudad().equals(carrera.getCiudad()) && hayCarreras(carrera, c)) return false;
		}
		carrera.setApostable(true);
		db.persist(carrera); 
		System.out.println(">> DataAccess: Carrera creada => nombre: "+carrera.getPais());
		db.getTransaction().commit();
		return true;
	}
	
	boolean hayCarreras(Carrera carrera1, Carrera carrera2) {
		Date start1= carrera1.getFechaComienzo();
		Date end1= carrera1.getFechaFinal();
		Date start2= carrera2.getFechaComienzo(); 
		Date end2= carrera2.getFechaFinal();
	    return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime(); 
	}
	//
	
	//CREAR ESCUDERIA
	public boolean crearEscuderia(Escuderia escuderia) {
		db.getTransaction().begin();
		TypedQuery<Escuderia> query = db.createQuery("SELECT e FROM Escuderia e WHERE e.Nombre=?1",Escuderia.class);   
		query.setParameter(1, escuderia.getNombre());
		List<Escuderia> res= query.getResultList();
		if (res.size()!=0)return false;
		else {
			db.persist(escuderia); 
			db.getTransaction().commit();
			return true;
		}
	}
	//
	
	//CREAR COCHES ESCUDERIA
	public ArrayList<Escuderia> getEscuderias(){
		db.getTransaction().begin();
		
		TypedQuery<Escuderia> query = db.createQuery("SELECT e FROM Escuderia e",Escuderia.class);   
		List<Escuderia>q= new ArrayList<Escuderia>();
		q= query.getResultList();
		ArrayList<Escuderia> res= new ArrayList<Escuderia>(q);
		return res;
	}
	
	public boolean crearCoche(Coche coche) {
		db.getTransaction().begin();
		TypedQuery<Coche> query = db.createQuery("SELECT c FROM Coche c" , Coche.class);   
		List<Coche> res= query.getResultList();
		for (Coche c: res) if (c.getMatricula().equals(coche.getMatricula()))return false;
		db.persist(coche); 
		System.out.println(">> DataAccess: Coche guardado => nombre: "+coche.getModelo());
		db.getTransaction().commit();
		boolean b= añadirCocheEscuderia(coche, coche.getEscuderia().toString());
		return b;
	}
	private boolean añadirCocheEscuderia(Coche coche, String escuderia) {
		System.out.println("\n \n **** Añadir Coche escuderia **** \n\n");
		db.getTransaction().begin();
		boolean b= false;
		TypedQuery<Escuderia> query = db.createQuery("SELECT e FROM Escuderia e",Escuderia.class);   
		List<Escuderia>q= query.getResultList();
		for (Escuderia e: q) {
			if (e.getNombre().equals(escuderia)) {
				b= e.addCoche(coche);
				db.persist(e);
			}
		}
		db.getTransaction().commit();
		return b;
	}
	
	//PILOTOS A CARRERA
	public Vector<Carrera> getCarreras(Date fechaInicio, Date fechaFinal) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Carrera> res = new Vector<Carrera>();	
		TypedQuery<Carrera> query = db.createQuery("SELECT c FROM Carrera c WHERE c.FechaComienzo BETWEEN ?1 and ?2",Carrera.class);
		query.setParameter(1, fechaInicio);
		query.setParameter(2, fechaFinal);
		List<Carrera> events = query.getResultList();
	 	 for (Carrera ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.FechaComienzo FROM Carrera ev WHERE ev.FechaComienzo BETWEEN ?1 and ?2", Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	public Vector<Carrera> getCarrerasActivas(Date date) {
		System.out.println(">> DataAccess: getOpenEvents");
		Vector<Carrera> res = new Vector<Carrera>();
		TypedQuery<Carrera> query = db.createQuery("SELECT c FROM Carrera c WHERE c.FechaComienzo=?1",Carrera.class); 
		query.setParameter(1, date);
		List<Carrera> events = query.getResultList();
	 	 for (Carrera ev:events){
	 	   System.out.println(ev.toString());
		   res.add(ev);
		  }
	 	return res;
	}
	public Vector<Piloto>getPilotosDisponibles(Carrera selectedCarrera){
		Vector<Piloto> pilotos = getPilotosConCoche();
		TypedQuery<PilotoEnCarrera> query2 = db.createQuery("SELECT p FROM PilotoEnCarrera p WHERE p.Carrera=?1",PilotoEnCarrera.class); 
		query2.setParameter(1, selectedCarrera);
		Vector<PilotoEnCarrera> pilotosEnParrilla = new Vector<PilotoEnCarrera>(query2.getResultList());
		for (PilotoEnCarrera pds: pilotosEnParrilla) pilotos.remove(pds.getPiloto());
		return pilotos;
	}
	public Vector<Piloto>getPilotosConCoche(){
		TypedQuery<Piloto> query1 = db.createQuery("SELECT p FROM Piloto p WHERE p.Coche!=?1",Piloto.class); 
		query1.setParameter(1, null);
		Vector<Piloto> pilotos = new Vector<Piloto>(query1.getResultList());
		return pilotos;
	}
	 
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public Piloto getPilotByNombre(String pnombre) {
		db.getTransaction().begin();
		TypedQuery<Piloto> query = db.createQuery("SELECT q FROM Piloto q WHERE q.Nombre=?1", Piloto.class);
		query.setParameter(1, pnombre);
		Piloto p= null;
		ArrayList<Piloto> ArrayListDriver= new ArrayList<Piloto>(query.getResultList());
		for(Piloto dri:ArrayListDriver) {
			p=dri;
		}
		return p;
	}
	
	public boolean insertEvent(Carrera pev) {
		System.out.println(">> DataAccess: insertEvent=> Carrera= "+pev.getCircuito()+" date="+pev.getFechaComienzo());
		try {
			db.getTransaction().begin();
			db.persist(pev);
			db.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean addBet(Apuesta apu) {
		try {
			db.getTransaction().begin();
			db.persist(apu);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
		return false;
		}
	}	
	public boolean añadirPilotoCarrera(int pos, Piloto selectedPiloto, Carrera selectedCarrera, Float cuotaGanancia, Float apuestaMin){
		try {
			db.getTransaction().begin();
			Piloto p = db.find(Piloto.class, selectedPiloto.getDNI());
			Carrera c = db.find(Carrera.class, selectedCarrera.getidCarrera());
			PilotoEnCarrera pec= new PilotoEnCarrera(pos, p, c, cuotaGanancia, apuestaMin);
			int n= c.getPilotosEnCarrera().size();
			if(n==3)c.Activar();
			if(n>=10)c.setLlena(true);
			db.persist(pec);
			db.getTransaction().commit();   
			return true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage ());
			return false;
		}
	}
	public boolean añadirParrillaPiloto(PilotoEnCarrera pds, Piloto p, Carrera c){
		try {
			p.addParrilla(pds);
			db.getTransaction().begin();
			db.persist(p);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
		System.out.println("Cava");
			return false;
		}
	}
	public boolean añadirParrillaCarrera(PilotoEnCarrera pds, Piloto p, Carrera c){
		try {
			c.addParrilla(pds);
			db.getTransaction().begin();
			db.persist(c);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
		System.out.println("Cava");
			return false;
		}
	}
	public boolean asignarCochePiloto(Piloto selectedPiloto, Coche selectedCoche) {
		try {
			
			Piloto p = db.find(Piloto.class, selectedPiloto.getDNI());
			Coche c = db.find(Coche.class, selectedCoche.getMatricula());
			
			p.setCoche(c);
			c.setPiloto(p);
			db.getTransaction().begin();
			db.persist(c);
			db.persist(p);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			System.out.println("\n\n **Fallo en Asignar Piloto A COCHE* \n\n");
			JOptionPane.showMessageDialog(null, e.getMessage ());
			return false;
		}
	}
	public int numeroCorrectoPilotos(Carrera c) {
		try {
			int r;
			if(c.getPilotosEnCarrera()==null)r=0;
			else r=c.getPilotosEnCarrera().size();
			if (r<3)return 0;
			if (r>=3 && r<=10)return 1;
			else if (r>10)return 2;
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}
	
	public void setLlena(Carrera c) {
		Carrera carrera= db.find(Carrera.class, c.getidCarrera());
		db.getTransaction().begin();
		if (carrera.getPilotosEnCarrera().size()==10)carrera.setLlena(true);
		db.persist(carrera);
		db.getTransaction().commit();
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//LOGIN Y REGISTRARSE
	public boolean doLogin (String pusername, String ppassword) {

		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.username=?1 and us.password=?2", Usuario.class);
		query.setParameter(1, pusername);
		query.setParameter(2, ppassword);
		List<Usuario> usuarios = query.getResultList();
		
		if (usuarios.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean doRegister(String pusername, String ppassword, String pemail) throws RollbackException {
		try {
			TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.username=?1 and us.email=?2", Usuario.class);
			query.setParameter(1, pusername);
			query.setParameter(2, pemail);
			List<Usuario> usuarios = query.getResultList();
			if (usuarios.isEmpty()) {
				db.getTransaction().begin();
				Cliente c = new Cliente (pusername, ppassword, pemail, (float) 0.0, "", "");
				db.persist(c);
				db.getTransaction().commit();
				System.out.println("Usuario registrado ");
				return true;
		} 
			else return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean isAdmin(String pusername, String ppassword) {
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.username=?1 and us.password=?2", Usuario.class);
		query.setParameter(1, pusername);
		query.setParameter(2, ppassword);
		List<Usuario> usuarios = query.getResultList();

		for (Usuario u:usuarios){
			return (u instanceof Admin);
		}
		if (usuarios instanceof Admin) {
			return true;
		} else {
			return false;
		}
	}
	
	public Cliente getClientByUsername(String pusername) {
		System.out.println(">> DataAccess: getClientByUsername");
		
		TypedQuery<Cliente> query = db.createQuery("SELECT cli FROM Cliente cli", Cliente.class);
		List<Cliente> cliList = query.getResultList();
		
		
	       	//ArrayList<Cliente> result = new ArrayList<Cliente>();
	        for (Cliente c : cliList) {
	            if (c.getUsername().equals(pusername)) 
	    	        return c;
	        }
	        return null;
		    
	}
	
	public Admin getAdminByUsername(String pusername) {
		System.out.println(">> DataAccess: getAdminByUsername");
		
		TypedQuery<Admin> query = db.createQuery("SELECT adm FROM Admin adm", Admin.class);
		List<Admin> admList = query.getResultList();
		
		
	       	//ArrayList<Admin> result = new ArrayList<Admin>();
	        for (Admin a : admList) {
	            if (a.getUsername().equals(pusername)) 
	    	        return a;
	        }
	        return null;
		    
	}
	public ArrayList<PilotoEnCarrera> getPilotosEnCarreraOrdenados(Carrera c) {
		db.getTransaction().begin();
		TypedQuery<PilotoEnCarrera> query = db.createQuery("SELECT pds FROM PilotoEnCarrera pds WHERE pds.Carrera.idCarrera=?1 ORDER BY pds.Posicion ASC",PilotoEnCarrera.class);   
		query.setParameter(1, c.getidCarrera());
		List<PilotoEnCarrera>q= new ArrayList<PilotoEnCarrera>();
		q= query.getResultList();
		ArrayList<PilotoEnCarrera> res= new ArrayList<PilotoEnCarrera>(q);
		return res;
	}
	
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//APOSTAR
	public Carrera getCarreraActiva() {
		TypedQuery<Carrera> query = db.createQuery("SELECT c FROM Carrera c", Carrera.class);
		List<Carrera> cList = query.getResultList();
		if (cList!=null) {
	        for (Carrera c : cList) {
	            if (c.isActiva()) 
	    	        return c;
		    }
		}
		return null;
	}
	public Piloto getPiloto(String n) {
		TypedQuery<Piloto> query = db.createQuery("SELECT * FROM Piloto", Piloto.class);
		List<Piloto> pList = query.getResultList();
		for (Piloto p : pList) {
	            if (p.getNombre().equals(n)) return p;
	        }
	    return null;
	}
	public boolean crearApuesta(Float dineroApostado, PilotoEnCarrera parrilladesalida, Cliente cliente) {
		try {
			db.getTransaction().begin();
			Cliente c= db.find(Cliente.class, cliente);
			PilotoEnCarrera pds = db.find(PilotoEnCarrera.class, parrilladesalida.getidPilotoEnCarrera());
			c.setSaldo((c.getSaldo())- dineroApostado );
			Apuesta ap= new Apuesta(dineroApostado, pds, c);
			db.persist(ap);
			db.getTransaction().commit();
			System.out.println("\n **** Numero de apuestas: "+numeroApuestasTotal() +" ****\n");
			return true;
		}catch (Exception e) {
			System.out.println("\n\n **Mierda** \n\n");
			JOptionPane.showMessageDialog(null, e.getMessage ());
			return false;
		}
	}
	public ArrayList<Apuesta> getApuestasTodas() {
		TypedQuery<Apuesta> query = db.createQuery("SELECT c FROM Apuesta c", Apuesta.class);
		ArrayList<Apuesta> apuestas= new ArrayList<Apuesta>(query.getResultList());
		return apuestas;
	}
	public ArrayList<Apuesta> getApuestasEstado(Carrera carrera, int estado) {
		//db.getTransaction().begin();
		Carrera c= db.find(Carrera.class, carrera.getidCarrera());
		ArrayList<PilotoEnCarrera> pilotos= c.getPilotosEnCarrera();
		ArrayList<Apuesta> apuesta = new ArrayList<Apuesta>();
		for (PilotoEnCarrera p:pilotos) {
			for (Apuesta a: p.getApuestas()) {
				if (a.getEstado()==estado)apuesta.add(a);
			}
		}
		return apuesta;
	}
	public ArrayList<Apuesta> getApuestasCliente(Cliente c) {
		Cliente cli= db.find(Cliente.class, c.getUsername());
		ArrayList<Apuesta> apuesta= cli.getApuestas();
		return apuesta;
	}
	
	
	public int numeroApuestasTotal() {
		return getApuestasTodas().size();
	}
	
	public int numeroApuestasClienteTotal(Cliente c) {
		return getApuestasCliente(c).size();
	}
	
	public boolean puedeApostar(Cliente c, Carrera car) {
		if (getApuestasCliente(c).size()==0) return true;
		else {
			for (Apuesta a:getApuestasCliente(c)) {
				if (a.getEstado()==0)return false;
			}
			return true;
		}
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//FINALIZAR CARRERA
	public boolean finalizarCarrera(Carrera c, PilotoEnCarrera ganador){
		try {
			db.getTransaction().begin();
			Carrera carrera= db.find(Carrera.class, c.getidCarrera());
			PilotoEnCarrera winner= db.find(PilotoEnCarrera.class, ganador.getidPilotoEnCarrera());
			Piloto p= winner.getPiloto();
			p.setCarrerasGanadas(p.getCarrerasGanadas()+1);
			ArrayList<Apuesta> apuestas= getApuestasEstado(c, 0);
			for (Apuesta a: apuestas) {
				if ( (a.getPilotoEnCarrera().getidPilotoEnCarrera())== (winner.getidPilotoEnCarrera()) ) a.setEstado(1);
				else a.setEstado(2);
			}
			carrera.setFinalizada(true);
			db.persist(carrera);
			db.persist(p);
			db.getTransaction().commit();
			return resolverApuestas(c);
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean resolverApuestas(Carrera carrera){
		try {
			Carrera c= db.find(Carrera.class, carrera.getidCarrera());
			ArrayList<Apuesta> apuestas= getApuestasEstado(c, 1);
			for (Apuesta a: apuestas) {
				if (a.getEstado()==1) añadirFondos(a.getCliente(),( a.getDineroApostado()* ( a.getPilotoEnCarrera().getCuotaGanancia() ) ) );
				else if (a.getEstado()==0);
				else return false;
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//MONEDERO
	public float consultarFondos(Cliente c){
		try {
			Cliente cli= db.find(Cliente.class, c.getUsername());
			return cli.getSaldo();
		} catch (Exception e) {
			return (float)-1.0;
		}
	}
	
	public boolean añadirFondos(Cliente c, Float fondos){
		try {
			db.getTransaction().begin();
			Cliente cli= db.find(Cliente.class, c.getUsername());
			cli.setSaldo(cli.getSaldo()+fondos);
			db.persist(cli);
			db.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean retirarFondos(Cliente c, Float fondos){
		try {
			db.getTransaction().begin();
			Cliente cli= db.find(Cliente.class, c.getUsername());
			cli.setSaldo(cli.getSaldo()-fondos);
			db.persist(cli);
			db.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	//
	//EDITAR PERFIL
	public boolean confirmarEditarPerfilCliente(String pNombre, String pUsername, String pEmail, String pTarjeta) {
		try {
			db.getTransaction().begin();
			Cliente cliente=db.find(Cliente.class, pUsername);
			cliente.setName(pNombre);
			cliente.setEmail(pEmail);
			cliente.setTarjeta(pTarjeta);
			db.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	
}
	
