package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.PilotoEnCarrera;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class A_TerminarCarreraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JComboBox<PilotoEnCarrera> comboBoxPilotos= new JComboBox<PilotoEnCarrera>();
	DefaultComboBoxModel<PilotoEnCarrera> modelPilotos= new DefaultComboBoxModel<PilotoEnCarrera>();
	

	private JTextField textFieldCarrera;
	private JTextField textFieldApuestas;
	private JButton btnFinalizarCarrera;
	private JButton btnCrearCarrera;
	private JButton bFinalizarCarrera;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	
	public A_TerminarCarreraGUI(JButton jButton1, JButton jButton2) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_TerminarCarreraGUI.class.getResource("/gui/Fotos/appLogo.png")));
		setTitle("Finalizar Carrera");
		bFinalizarCarrera= jButton1;
		btnCrearCarrera= jButton2;

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		Carrera c= facade.getCarreraActiva();
		ArrayList<PilotoEnCarrera> pilotos= c.getPilotosOrdenados();
		for(PilotoEnCarrera pds: pilotos) modelPilotos.addElement(pds);
		comboBoxPilotos.setBackground(SystemColor.controlHighlight);
		comboBoxPilotos.setModel(modelPilotos);
		comboBoxPilotos.repaint();
		
		textFieldCarrera = new JTextField();
		textFieldCarrera.setEditable(false);
		textFieldCarrera.setBounds(175, 25, 181, 20);
		textFieldCarrera.setText(c.getCircuito());
		
		getContentPane().add(textFieldCarrera);
		textFieldCarrera.setColumns(10);
		
		comboBoxPilotos.setBounds(175, 59, 181, 20);
		getContentPane().add(comboBoxPilotos);
		
		textFieldApuestas = new JTextField();
		textFieldApuestas.setEditable(false);
		textFieldApuestas.setBounds(175, 90, 49, 20);
		textFieldApuestas.setText( (facade.getApuestasEstado(c, 0)).size()+"");
		c= facade.getCarreraActiva();
		textFieldCarrera.setText(c.toString());
		getContentPane().add(textFieldApuestas);
		textFieldApuestas.setColumns(10);
		
		JButton btnAtras = new JButton("Cerrar");
		btnAtras.setBackground(SystemColor.controlHighlight);
		btnAtras.setBounds(36, 152, 130, 30);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close(e);
			}
		});
		getContentPane().add(btnAtras);
		
		btnFinalizarCarrera = new JButton("Finalizar Carrera");
		btnFinalizarCarrera.setBackground(SystemColor.controlHighlight);
		btnFinalizarCarrera.setBounds(214, 152, 142, 30);
		btnFinalizarCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.finalizarCarrera(facade.getCarreraActiva(), (PilotoEnCarrera) comboBoxPilotos.getSelectedItem());
				bFinalizarCarrera.setEnabled(false);
				btnCrearCarrera.setEnabled(true);
				btnCrearCarrera.setText("Crear Carrera");
				Close(e);
			}
		});
		getContentPane().add(btnFinalizarCarrera);
		
		lblNewLabel = new JLabel("Carrera:");
		lblNewLabel.setBounds(36, 28, 114, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Escoge ganador:");
		lblNewLabel_1.setBounds(36, 60, 114, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Numero de Apuestas: ");
		lblNewLabel_2.setBounds(36, 93, 141, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(36, 127, 320, 14);
		getContentPane().add(lblNewLabel_3);
		this.setSize(new Dimension(408, 260));
		
	
		Carrera c1= facade.getCarreraActiva();
		Date fecha= c1.getFechaFinal();
		if (fecha.getTime()> Calendar.getInstance().getTime().getTime()) {
			btnFinalizarCarrera.setEnabled(false);
		}
	}
	private void Close(ActionEvent e) {
		this.setVisible(false);
	}
}