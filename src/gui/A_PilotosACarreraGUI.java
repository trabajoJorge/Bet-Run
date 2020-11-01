package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.PilotoEnCarrera;
import domain.Piloto;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class A_PilotosACarreraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelCarreras = new DefaultComboBoxModel<Carrera>();

	private JComboBox<Piloto> ComboBoxPilotos = new JComboBox<Piloto>();
	DefaultComboBoxModel<Piloto> modelPilotos = new DefaultComboBoxModel<Piloto>();
	
	private JComboBox<PilotoEnCarrera> ComboBoxParrilla= new JComboBox<PilotoEnCarrera>();
	DefaultComboBoxModel<PilotoEnCarrera> modelParrilla = new DefaultComboBoxModel<PilotoEnCarrera>();
	
	private JComboBox<Integer> comboBoxPos= new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> modelPos= new DefaultComboBoxModel<Integer>();
	
//	private Cliente currentClient;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	
	private Piloto selectedQuestion;
	private Carrera selectedCarrera;

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApostarGUI.jButtonCreate.text")); //$NON-NLS-1$ //$NON-NLS-2$
//	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonClose = new JButton("Cancelar");
	//private final JLabel lblSeleccionarPresgunta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostarGUI.lblSeleccionarPresgunta.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblSeleccionarEvento = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostarGUI.lblSeleccionarEvento.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelMsg = new JLabel(); 
	private final JTextArea resultDialog = new JTextArea();
	private JTextField textFieldApuestaMinima;
	private JTextField textFieldCuotaGanancia;
	private final JTextField textField = new JTextField();
	private JButton bCrearCarrera;
	private JButton bFinalizarCarrera;
	
	
	public A_PilotosACarreraGUI(JButton btnCrearCarrera, JButton btnFinalizarCarrera, Carrera carrera) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_PilotosACarreraGUI.class.getResource("/gui/Fotos/appLogo.png")));
		bCrearCarrera= btnCrearCarrera;
		bFinalizarCarrera= btnFinalizarCarrera;
		try {
			try {
				selectedCarrera=carrera;
			}catch (Exception e1){
				BLFacade facade = MainGUI.getBusinessLogic();
				selectedCarrera= facade.getCarreraActiva();
			}
			jbInit();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(442, 340));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PilotosACarreraGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonCreate.setBackground(SystemColor.controlHighlight);
		ComboBoxParrilla.setBounds(543, 197, 108, 20);
		ComboBoxParrilla.setModel(modelParrilla);
		ComboBoxPilotos.setBackground(SystemColor.controlHighlight);
		ComboBoxPilotos.setBounds(140, 66, 250, 20);
		ComboBoxPilotos.setEnabled(false);
		comboBoxPos.setBackground(SystemColor.controlHighlight);
		comboBoxPos.setBounds(140, 172, 46, 20);
		getContentPane().add(comboBoxPos);
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonCreate.setBounds(223, 235, 162, 40);

		textField.setEditable(false);
		textField.setText("GP de "+selectedCarrera.getPais());
		textField.setBounds(140, 36, 250, 20);
		textField.setColumns(10);
		
		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(40, 235, 155, 40);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		getContentPane().setLayout(null);

		this.getContentPane().add(jButtonClose);
		this.getContentPane().add(jButtonCreate);
		jButtonCreate.setEnabled(true);
		
		
		
		lblSeleccionarEvento.setBounds(40, 36, 155, 20);
		
		
		getContentPane().add(lblSeleccionarEvento);
		jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("PilotosACarrera.jLabelMsg.text")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelMsg.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelMsg.setBounds(50, 208, 317, 16);
		
		getContentPane().add(jLabelMsg);
		resultDialog.setWrapStyleWord(true);
		resultDialog.setLineWrap(true);
		resultDialog.setEditable(false);
		resultDialog.setBackground(UIManager.getColor("Button.background"));
		resultDialog.setText("");
		resultDialog.setBounds(543, 280, 167, 40);
		
		getContentPane().add(resultDialog);
		
		textFieldApuestaMinima = new JTextField();
		textFieldApuestaMinima.setText("");
		textFieldApuestaMinima.setBounds(140, 101, 86, 20);
		getContentPane().add(textFieldApuestaMinima);
		textFieldApuestaMinima.setColumns(10);
		
		textFieldCuotaGanancia = new JTextField();
		textFieldCuotaGanancia.setBounds(140, 136, 86, 20);
		getContentPane().add(textFieldCuotaGanancia);
		textFieldCuotaGanancia.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostarGUI.lblNewLabel_3.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_3.setBounds(40, 105, 108, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostarGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(40, 140, 108, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PilotosACarrera.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setBounds(40, 70, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		getContentPane().add(textField);
		
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("A_PilotosACarreraGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(40, 175, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Piloto> pilotos = facade.getPilotosDisponibles(selectedCarrera);
		for (Piloto q : pilotos) modelPilotos.addElement(q);
		ComboBoxPilotos.setModel(modelPilotos);
		getContentPane().add(ComboBoxPilotos);
		if (pilotos.size()==0) {
			ComboBoxPilotos.setEnabled(false);
			jButtonCreate.setEnabled(false);
			textFieldApuestaMinima.setEnabled(false);
			textFieldCuotaGanancia.setEnabled(false);
			jLabelMsg .setForeground(new Color(128, 0, 0));
			jLabelMsg .setText("NO QUEDAN PILOTOS DISPONIBLES");
			jLabelMsg .setVisible(true);
			jButtonClose.setEnabled(true);
		}
		else {
			ComboBoxPilotos.setEnabled(true);
		}
		
		ArrayList<Integer> a= new ArrayList<Integer>();
		for (int i=1; i<=10; i++) a.add(i);
		ArrayList<PilotoEnCarrera> pilotosEnCarrera= facade.getCarreraActiva().getPilotosEnCarrera();
		ArrayList<Integer> b= new ArrayList<Integer>();
		for (PilotoEnCarrera p:pilotosEnCarrera)b.add(p.getPosicion());
		
		for (int j=0; j<a.size(); j++) {
			for (int k=0; k<b.size(); k++) {
				if (a.get(j)==b.get(k)) {
					a.set(j, null);
				}
			}
		}
		ArrayList<Integer> r= new ArrayList<Integer>();
		for(int l=0; l<10; l++) {
			if (a.get(l)!=null)r.add(a.get(l));
		}
		if (r.size()==0) {
			jLabelMsg .setForeground(new Color(128, 0, 0));
			jLabelMsg .setText("NO QUEDAN POSICIONES DISPONIBLES");
			jLabelMsg .setVisible(true);
			jButtonClose.setEnabled(true);
			comboBoxPos.setEnabled(false);
		}
		
		for (Integer t : r) modelPos.addElement(t);
		comboBoxPos.setModel(modelPos);
		comboBoxPos.repaint();
		int numeroPilotos= (facade.getCarreraActiva().getPilotosEnCarrera().size())+1;
	if (numeroPilotos<3) {
		jButtonClose.setEnabled(false);
	}
}
	
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		BLFacade facade = MainGUI.getBusinessLogic();
		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
		Calendar calendar = jCalendar.getCalendar();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);
		if (Locale.getDefault().equals(new Locale("es")))offset += 4;
		else offset += 5;
	 	for (Date d:dates){
	 		calendar.setTime(d);
	 		System.out.println(d);
			Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.ORANGE);
	 	}
	 	calendar.set(Calendar.DAY_OF_MONTH, 1);
	 	calendar.set(Calendar.MONTH, month);
	}
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		boolean c=true;
		BLFacade facade = MainGUI.getBusinessLogic();
		try {
			@SuppressWarnings("unused")
			float apuestaMin= Float.parseFloat(textFieldApuestaMinima.getText());
		}catch (Exception e35) {
			c=false;
			jLabelMsg .setForeground(new Color(128, 0, 0));
			jLabelMsg .setText("INTRODUCE CORRECTAMENTE LA APUESTA MINIMA");
			jLabelMsg .setVisible(true);
			textFieldApuestaMinima.setText("");
		}
		try {
			@SuppressWarnings("unused")
			float cuotaGanancia=Float.parseFloat(textFieldCuotaGanancia.getText());
		}catch (Exception e35) {
			c=false;
			jLabelMsg .setForeground(new Color(128, 0, 0));
			jLabelMsg .setText("INTRODUCE CORRECTAMENTE LA CUOTA DE GANANCIA");
			jLabelMsg .setVisible(true);
			textFieldCuotaGanancia.setText("");
		}
		
		if (c) {
			float apuestaMin= Float.parseFloat(textFieldApuestaMinima.getText());
			float cuotaGanancia=Float.parseFloat(textFieldCuotaGanancia.getText());
			Piloto selectedPiloto= (Piloto) ComboBoxPilotos.getSelectedItem();
			int selectedPos= (int)comboBoxPos.getSelectedItem();
			int numeroPilotos= (facade.getCarreraActiva().getPilotosEnCarrera().size())+1;
			if (numeroPilotos>10) {
				jLabelMsg .setForeground(new Color(128, 0, 0));
				bCrearCarrera.setEnabled(false);
				jLabelMsg .setText("NUMERO MAXIMO DE PILOTOS ALCANZADO");
				jLabelMsg .setVisible(true); 
				jButtonCreate.setEnabled(false);
				c=false;
			}
			else if (numeroPilotos<3) {
				jButtonClose.setEnabled(false);
			}
			else if (3<=numeroPilotos && numeroPilotos<=10) {
				if (bCrearCarrera!=null)bCrearCarrera.setEnabled(true);
				bFinalizarCarrera.setEnabled(true);
				jButtonCreate.setEnabled(true);
				jButtonClose.setEnabled(true);
				
			}
			
			try {
				if(facade.añadirPilotoCarrera(selectedPos, selectedPiloto, selectedCarrera, cuotaGanancia, apuestaMin)) {

					System.out.println("\n\n **** Entra **** \n\n");
					jLabelMsg .setForeground(new Color(0, 128, 0));
					jLabelMsg .setVisible(true);
					jLabelMsg .setText("PILOTO AÑADIDO");
				}
				
				if (facade.getCarreraActiva().getPilotosEnCarrera().size()==10) {
					facade.setLlena(selectedCarrera);
					bCrearCarrera.setEnabled(false);
				}
				facade.getCarreraActiva();
			}catch (Exception e2) {
				jLabelMsg .setForeground(new Color(128, 0, 0));
				jLabelMsg .setText("ERROR EN AÑADIR PILOTO");
				jLabelMsg .setVisible(true);
			}
			try {
				List<Piloto> pilotos = facade.getPilotosDisponibles(selectedCarrera);
	
				ComboBoxPilotos.removeAllItems();
				System.out.println("Preguntas " + pilotos);
	
				for (Piloto q : pilotos)
					modelPilotos.addElement(q);
					ComboBoxPilotos.repaint();
				
				comboBoxPos.removeAllItems();
				ArrayList<Integer> a= new ArrayList<Integer>();
				for (int i=1; i<=10; i++) a.add(i);
				ArrayList<PilotoEnCarrera> pilotosEnCarrera= facade.getCarreraActiva().getPilotosEnCarrera();
				ArrayList<Integer> b= new ArrayList<Integer>();
				for (PilotoEnCarrera p:pilotosEnCarrera)b.add(p.getPosicion());
				
				for (int j=0; j<a.size(); j++) {
					for (int k=0; k<b.size(); k++) {
						if (a.get(j)==b.get(k)) {
							a.set(j, null);
						}
					}
				}
				ArrayList<Integer> r= new ArrayList<Integer>();
				for(int l=0; l<10; l++) {
					if (a.get(l)!=null)r.add(a.get(l));
				}
				if (r.size()==0) {
					jLabelMsg .setForeground(new Color(128, 0, 0));
					jLabelMsg .setText("NO QUEDAN POSICIONES DISPONIBLES");
					jLabelMsg .setVisible(true);
					jButtonCreate.setEnabled(false);
					jButtonClose.setEnabled(true);
					comboBoxPos.setEnabled(false);
				}
				for (Integer t : r) modelPos.addElement(t);
				comboBoxPos.setModel(modelPos);
				comboBoxPos.repaint();
							
				if (pilotos.size() == 0) {
					ComboBoxPilotos.setEnabled(false);
					jButtonCreate.setEnabled(false);
					jLabelMsg .setForeground(new Color(128, 0, 0));
					jLabelMsg .setText("NO QUEDAN PILOTOS DISPONIBLES");
					jLabelMsg .setVisible(true);
				}
			} catch (Exception e1) {
				jLabelMsg .setForeground(new Color(128, 0, 0));
				jLabelMsg .setText("ERROR EN CARGAR PILOTOS");
				jLabelMsg .setVisible(true);	
			}
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	public void checkCaption(Carrera pevento, Piloto pquestion){
		if (selectedCarrera == null || selectedQuestion == null) {
			jLabelMsg.setVisible(false); 
			jButtonCreate.setEnabled(true);
			resultDialog.setText("");
		} else { 
			jLabelMsg.setVisible(true);
			jButtonCreate.setEnabled(true);
		}
	}
	public Date addHoursAndMinuntsToDate(Date date, int horas, int minutos) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, horas);
	    calendar.add(Calendar.MINUTE, minutos);
	    return calendar.getTime();
  }
}
