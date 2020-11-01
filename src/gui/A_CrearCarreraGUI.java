package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Carrera;
import java.awt.Toolkit;

public class A_CrearCarreraGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;
	private final JLabel lblEligeDia = new JLabel("Elige dia:");
	private final JLabel lblDescripcionDeEvento = new JLabel("Inserta el Circuito:");
	private final JTextField textFieldCountry = new JTextField();
	private final JButton btnCrearEvento = new JButton("A\u00F1adir Pilotos");
	private Date fechaInicio;
	private final JLabel lblMensajes = new JLabel("Evento creado con Exito");
	private JTextField textFieldCircuit;
	private JTextField textFieldCiudad;
 	
	private JComboBox<String> comboBoxHora= new JComboBox<String>();
	DefaultComboBoxModel<String> modelHora= new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBoxMinuto= new JComboBox<String>();
	DefaultComboBoxModel<String> modelMinuto= new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBoxDuracion= new JComboBox<String>();
	DefaultComboBoxModel<String> modelDuracion= new DefaultComboBoxModel<String>();
	
	private JButton bCrearCarrera;
	private JButton bFinalizarCarrera;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public A_CrearCarreraGUI(JButton btnCrearCarrera, JButton btnFinalizarCarrera) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_CrearCarreraGUI.class.getResource("/gui/Fotos/appLogo.png")));
		bCrearCarrera= btnCrearCarrera;
		bFinalizarCarrera=btnFinalizarCarrera;
		this.setTitle("A\u00F1adir evento");
		textFieldCountry.setBounds(275, 102, 315, 20);
		textFieldCountry.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		contentPane.setLayout(null);

	
		this.setTitle("Crear Evento");
		jCalendar.getDayChooser().getDayPanel().setBackground(SystemColor.controlHighlight);
		jCalendar.getDayChooser().setBackground(SystemColor.controlShadow);
		jCalendar.getDayChooser().setDecorationBackgroundColor(SystemColor.activeCaption);
		jCalendar.getMonthChooser().getSpinner().setForeground(SystemColor.controlHighlight);
		jCalendar.getYearChooser().getSpinner().setForeground(SystemColor.controlHighlight);


		
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		contentPane.add(jCalendar);
		lblEligeDia.setBounds(40, 25, 78, 14);

		contentPane.add(lblEligeDia);
		lblDescripcionDeEvento.setBounds(275, 25, 193, 14);

		contentPane.add(lblDescripcionDeEvento);

		contentPane.add(textFieldCountry);
		btnCrearEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCrearEvento.setBackground(SystemColor.controlHighlight);
		btnCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BLFacade facade = MainGUI.getBusinessLogic();
					fechaInicio = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					fechaInicio= addHoursAndMinuntsToDate(fechaInicio, Integer.parseInt(comboBoxHora.getSelectedItem().toString()), Integer.parseInt(comboBoxMinuto.getSelectedItem().toString()));
					Carrera nuevaCarrera = new Carrera(fechaInicio, Integer.parseInt(comboBoxDuracion.getSelectedItem().toString()), textFieldCountry.getText(), textFieldCiudad.getText(), textFieldCircuit.getText() );
					nuevaCarrera.Activar();
					
					if(textFieldCircuit.getText().equals("") || textFieldCiudad.getText().equals("") || textFieldCircuit.getText().equals("")) {
						lblMensajes.setForeground(new Color(229, 57, 53));
						lblMensajes.setText("TIENES QUE RELLENAR TODOS LOS CAMPOS");
						lblMensajes.setVisible(true);
					}	
					else if(fechaInicio.getTime()< Calendar.getInstance().getTime().getTime()){
						lblMensajes.setForeground(new Color(229, 57, 53));
						lblMensajes.setText("NO PUEDES CREAR EVENTOS EN EL PASADO");
						lblMensajes.setVisible(true);
					}
					else if(facade.crearCarrera(nuevaCarrera)) {
						lblMensajes.setForeground(new Color(0, 128, 0));
						lblMensajes.setText("EVENTO INGRESADO.");
						lblMensajes.setVisible(true);
						btnCrearCarrera.setText("Añadir pilotos a Carrera");
						JFrame a = new A_PilotosACarreraGUI(bCrearCarrera, bFinalizarCarrera, nuevaCarrera);
						a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						a.setVisible(true);
						close1();
					} else {
						lblMensajes.setForeground(new Color(229, 57, 53));
						lblMensajes.setText("EL CIRCUITO ESTA OCUPADO EN ESE MOMENTO");
						lblMensajes.setVisible(true);
					}
				}catch (Exception e) {
					lblMensajes.setVisible(true);
					lblMensajes.setForeground(new Color(229, 57, 53));
					lblMensajes.setText("ERROR AL INGRESAR EVENTO.");
				}
			}
		});
		btnCrearEvento.setBounds(275, 183, 315, 33);

		lblMensajes.setVisible(false);
		
		contentPane.add(btnCrearEvento);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrar.setBackground(SystemColor.controlHighlight);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				close1();
			}
		});
		btnCerrar.setBounds(421, 254, 169, 30);
		contentPane.add(btnCerrar);
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setForeground(new Color(0, 128, 0));
		lblMensajes.setBounds(275,227,315,16);
		contentPane.add(lblMensajes);
		
		textFieldCircuit = new JTextField();
		textFieldCircuit.setBounds(275, 50, 315, 20);
		contentPane.add(textFieldCircuit);
		textFieldCircuit.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Introduce el pais donde se disputara la carrera:");
		lblNewLabel_2.setBounds(275, 81, 315, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Introduce la ciudad:");
		lblNewLabel.setBounds(275, 133, 182, 14);
		contentPane.add(lblNewLabel);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setBounds(275, 152, 315, 20);
		contentPane.add(textFieldCiudad);
		textFieldCiudad.setColumns(10);
		comboBoxHora.setBackground(SystemColor.controlHighlight);
		

		comboBoxHora.setBounds(90, 211, 45, 20);
		
		ArrayList<String> horas= new ArrayList<String>();
		for (int i=0; i<24; i++) horas.add(i+"");
		for(String d : horas) modelHora.addElement(d);
		comboBoxHora.setModel(modelHora);
		comboBoxHora.repaint();
		
		contentPane.add(comboBoxHora);
		comboBoxMinuto.setBackground(SystemColor.controlHighlight);

		
		comboBoxMinuto.setBounds(151, 211, 45, 20);
		ArrayList<String> minutos= new ArrayList<String>();
		for (int i=0; i<60; i++) {
			if (i<10) minutos.add("0"+i);
			else minutos.add(i+"");
		}
		for(String d : minutos)modelMinuto.addElement(d);
		comboBoxMinuto.setModel(modelMinuto);
		comboBoxMinuto.repaint();
		
		contentPane.add(comboBoxMinuto);
		comboBoxDuracion.setBackground(SystemColor.controlHighlight);
		
		
		comboBoxDuracion.setBounds(100, 242, 45, 20);
		
		ArrayList<String> duracion= new ArrayList<String>();
		for (int i=1; i<=24; i++) duracion.add(i+"");
		for(String d :duracion) modelDuracion.addElement(d);
		contentPane.add(comboBoxDuracion);
		comboBoxDuracion.setModel(modelDuracion);
		
		comboBoxMinuto.repaint();
		
		contentPane.add(comboBoxDuracion);
		
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setBounds(142, 211, 27, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Hora:");
		lblNewLabel_3.setBounds(40, 214, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duraci\u00F3n:");
		lblNewLabel_4.setBounds(34, 245, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("h");
		lblNewLabel_5.setBounds(151, 245, 46, 14);
		contentPane.add(lblNewLabel_5);
		jCalendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) evt.getNewValue());
				} else if (evt.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) evt.getNewValue();
					jCalendar.setCalendar(calendarMio);
					fechaInicio = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					System.out.println(fechaInicio);
				}
			}
		});

	}
	public Date addHoursAndMinuntsToDate(Date date, int horas, int minutos) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, horas);
	    calendar.add(Calendar.MINUTE, minutos);
	    return calendar.getTime();
  }

	private void close1(){
		this.dispose();
	}
}
