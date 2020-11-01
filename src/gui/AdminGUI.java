package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Software Engineering teachers
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Carrera;


public class AdminGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton btnAñadirCorredores = null;
	
	 private static BLFacade appFacadeInterface;
	public static BLFacade getBusinessLogic(){
			return appFacadeInterface;
	}
		
	public static void setBussinessLogic (BLFacade afi){
			appFacadeInterface=afi;
	}
	
	//private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCreateEvent;
	
	private JButton jButtonAñadirCoche;
	private JButton ButtonCerrarSesion;
	private JLabel lblMenuDeAdministrador;
	private JButton btnFinalizarCarrera;
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminGUI.class.getResource("/gui/Fotos/appLogo.png")));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(560, 210);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			btnFinalizarCarrera = new JButton();
			btnFinalizarCarrera.setText("Finalizar Carrera");
			btnFinalizarCarrera.setEnabled(false);
			btnFinalizarCarrera.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnFinalizarCarrera.setBackground(SystemColor.controlHighlight);
			btnFinalizarCarrera.setBounds(270, 90, 250, 30);
			jContentPane.add(btnFinalizarCarrera);
			btnFinalizarCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new A_TerminarCarreraGUI(btnFinalizarCarrera, btnCreateEvent);
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					a.setVisible(true);
				}
			});
		
			BLFacade facade = MainGUI.getBusinessLogic();
			if (((facade.getPilotosConCoche()).size())<3) {
				btnCreateEvent.setEnabled(false);
				btnFinalizarCarrera.setEnabled(false);
			}
			int o= facade.getescuderias().size();
			if(o==0) {
				btnAñadirCorredores.setEnabled(false);
				getJButtonAñadirCoche().setEnabled(false);
			}
			else if ( ((facade.getCoches()) .size())==0){
				btnAñadirCorredores.setEnabled(false);
				getBtnCreateEvent().setEnabled(false);
			}
			Carrera c=facade.getCarreraActiva();
			
			jContentPane.add(getBtnCreateEvent());
			 if (c!=null) {
				if (c.isLlena()) {
					getBtnCreateEvent().setEnabled(false);
				}
				btnCreateEvent.setText("Añadir Pilotos a Carrera");
				if (c.getPilotosEnCarrera().size()>2)  {
					btnFinalizarCarrera.setEnabled(true);
				}
				Carrera c1= facade.getCarreraActiva();
				Date fecha= c1.getFechaFinal();
				if (fecha.getTime()> Calendar.getInstance().getTime().getTime()) {
					btnFinalizarCarrera.setEnabled(false);
				}
			}
			
		}
		
		jContentPane.add(getBoton2());
		
		jContentPane.add(getButtonCerrarSesion());
		jContentPane.add(getBtnEscuderia());
		jContentPane.add(getJButtonAñadirCoche());
		jContentPane.add(getLblMenuDeAdministrador());
		return jContentPane;
	}
	

	private JButton getBtnEscuderia() {
		JButton btnCrearEscuderia = new JButton();
		btnCrearEscuderia.setBackground(SystemColor.controlHighlight);
		btnCrearEscuderia.setBounds(20, 60, 250, 30);
		btnCrearEscuderia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearEscuderia.setText("Añadir Escuderia");
		btnCrearEscuderia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new A_CrearEscuderiaGUI(jButtonAñadirCoche);
				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setVisible(true);
			}
		});
		return btnCrearEscuderia;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (btnAñadirCorredores == null) {
			btnAñadirCorredores = new JButton();
			btnAñadirCorredores.setBackground(SystemColor.controlHighlight);
			btnAñadirCorredores.setBounds(20, 120, 250, 30);
			btnAñadirCorredores.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAñadirCorredores.setText(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.jButtonCreateQuery.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnAñadirCorredores.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new A_CrearPilotosGUI(btnCreateEvent);
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					a.setVisible(true);
				}
			});
		}
		return btnAñadirCorredores;
		
	}
	

	private JButton getBtnCreateEvent() {
		if (btnCreateEvent == null) {
			btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text"));
			btnCreateEvent.setBackground(SystemColor.controlHighlight);
			btnCreateEvent.setBounds(270, 60, 250, 30);
			btnCreateEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnCreateEvent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = MainGUI.getBusinessLogic();
					if (facade.getCarreraActiva()==null) {
						JFrame a = new A_CrearCarreraGUI(btnCreateEvent, btnFinalizarCarrera);
						a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						a.setVisible(true);
					}
					else{
						//btnCreateEvent.setText("Añadir Pilotos a Carrera Activa");
						JFrame a = new A_PilotosACarreraGUI(btnCreateEvent, btnFinalizarCarrera, facade.getCarreraActiva());
						a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						a.setVisible(true);
					}
				}
			});
		}
		return btnCreateEvent;
	}

	private JButton getJButtonAñadirCoche() {
		if (jButtonAñadirCoche == null) {
			jButtonAñadirCoche = new JButton();
			jButtonAñadirCoche.setBackground(SystemColor.controlHighlight);
			jButtonAñadirCoche.setBounds(20, 90, 250, 30);
			jButtonAñadirCoche.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.jButtonA\u00F1adirCoche.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonAñadirCoche.setFont(new Font("Tahoma", Font.PLAIN, 20));
			jButtonAñadirCoche.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new A_CrearCochesEscuderiaGUI(btnAñadirCorredores);
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					a.setVisible(true);
				}
			});
		}
		return jButtonAñadirCoche;
	}
	
	private JButton getButtonCerrarSesion() {
		if (ButtonCerrarSesion == null) {
			ButtonCerrarSesion = new JButton();
			ButtonCerrarSesion.setBackground(SystemColor.controlHighlight);
			ButtonCerrarSesion.setBounds(270, 120, 250, 30);
			ButtonCerrarSesion.setText("Cerrar Sesion"); 
			ButtonCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			ButtonCerrarSesion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainGUI();
					jButtonClose_actionPerformed(e);
					a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					a.setVisible(true);
				}
			});
		}
		return ButtonCerrarSesion;
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	private JLabel getLblMenuDeAdministrador() {
		if (lblMenuDeAdministrador == null) {
			lblMenuDeAdministrador = new JLabel();
			lblMenuDeAdministrador.setText(ResourceBundle.getBundle("Etiquetas").getString("AdminGUI.lblMenuDeAdministrador.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblMenuDeAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenuDeAdministrador.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
			lblMenuDeAdministrador.setBounds(0, 11, 543, 42);
		}
		return lblMenuDeAdministrador;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

