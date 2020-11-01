package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.Coche;
import domain.Escuderia;
import domain.Piloto;
import java.awt.Toolkit;

public class A_PilotosACocheGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonInsert = new JButton("A\u00F1adir Piloto");
	private JButton jButtonClose = new JButton("Cerrar");
	private JLabel Msg = new JLabel();
	
	private Escuderia selectedEscuderia;
	DefaultComboBoxModel<Escuderia> modelEscuderia= new DefaultComboBoxModel<Escuderia>();
	DefaultComboBoxModel<Piloto> modelPiloto= new DefaultComboBoxModel<Piloto>();
	
	private JComboBox<Coche> comboBoxCoche= new JComboBox<Coche>();
	DefaultComboBoxModel<Coche> modelCoche= new DefaultComboBoxModel<Coche>();
	
	private Piloto Driver;
	private JButton frame;
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();

	public A_PilotosACocheGUI(Piloto p, JButton j) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_PilotosACocheGUI.class.getResource("/gui/Fotos/appLogo.png")));
		textField_1.setEditable(false);
		textField_1.setBounds(177, 61, 168, 20);
		textField_1.setColumns(10);
		textField.setEditable(false);
		textField.setBounds(177, 30, 168, 20);
		textField.setColumns(10);
		Driver= p;
		frame= j;
				
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		textField.setText(Driver.getEscuderia().toString());
		textField_1.setText(Driver.toString());
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(458, 256));
		this.setTitle("A\u00F1adir Pilotos");
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonInsert.setBackground(SystemColor.controlHighlight);

		jButtonInsert.setBounds(new Rectangle(31, 163, 169, 30));

		jButtonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(new Rectangle(237, 163, 169, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		Msg.setBounds(new Rectangle(31, 132, 375, 20));
		Msg.setForeground(new Color(0, 128, 0));
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<Escuderia> escuderias= facade.getescuderias();
		for(Escuderia e: escuderias) modelEscuderia.addElement(e);
		Msg.setText("");
		comboBoxCoche.removeAllItems();
		
		
		this.getContentPane().add(Msg, null);
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonInsert, null);
	
		
		comboBoxCoche.setBounds(177, 92, 168, 20);
		this.getContentPane().add(comboBoxCoche);
		
		JLabel lblNewLabel = new JLabel("Escuderia:");
		lblNewLabel.setBounds(63, 33, 104, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPiltotos = new JLabel("Piltotos:");
		lblPiltotos.setBounds(63, 64, 104, 14);
		getContentPane().add(lblPiltotos);
		
		JLabel lblCoches = new JLabel("Coches:");
		lblCoches.setBounds(63, 95, 104, 14);
		getContentPane().add(lblCoches);
		
		getContentPane().add(textField);
		
		getContentPane().add(textField_1);
		
		ArrayList<Coche> cochesLibres=Driver.getEscuderia().getCoches();
		if (cochesLibres.size() == 0) {
			Msg.setForeground(new Color(229, 57, 53));
			Msg.setText("NO QUEDAN COCHES DISPONIBLES EN ESTA ESCUDERIA");
			Msg.setVisible(true);
			comboBoxCoche.setEnabled(false);
			jButtonInsert.setEnabled(false);
		}else {
			for (Coche c : cochesLibres) {
				if(!c.isEnUso())
					modelCoche.addElement(c);
				}
			comboBoxCoche.setModel(modelCoche);
			comboBoxCoche.setEnabled(true);
			comboBoxCoche.repaint();
			jButtonInsert.setEnabled(true);
		} 
	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			Msg.setText("");
			BLFacade facade = MainGUI.getBusinessLogic();
			Coche selectedCoche=(Coche) comboBoxCoche.getSelectedItem();
			selectedEscuderia= Driver.getEscuderia();
			
			if (selectedCoche==null) {
				Msg.setForeground(new Color(229, 57, 53));
				Msg.setText("NO QUEDAN COCHES DISPONIBLES EN ESTA ESCUDERIA");
				Msg.setVisible(true);
				comboBoxCoche.setEnabled(false);
				jButtonInsert.setEnabled(false);
			}
			else {
				facade.crearPiloto(Driver);
				selectedCoche.setPiloto(Driver);
				Driver.setCoche(selectedCoche);
				
				facade.asignarCochePiloto(Driver, selectedCoche);
				System.out.println("\n\n **** PASA **** \n\n");
				Msg.setText("ASIGNAR COCHE A PILOTO HECHO");
				ArrayList<Coche> cochesLibres=Driver.getEscuderia().getCoches();
				if (cochesLibres.size() == 0) {
					Msg.setForeground(new Color(229, 57, 53));
					Msg.setText("NO QUEDAN COCHES DISPONIBLES EN ESTA ESCUDERIA");
					Msg.setVisible(true);
					comboBoxCoche.setEnabled(false);
					jButtonInsert.setEnabled(false);
				}else {
					for (Coche c : cochesLibres) {
						if(!c.isEnUso())
							modelCoche.addElement(c);
						}
					comboBoxCoche.setModel(modelCoche);
					comboBoxCoche.setEnabled(true);
					comboBoxCoche.repaint();
					jButtonInsert.setEnabled(true);
				} 
				if (((facade.getPilotosConCoche()).size())>2) {
					frame.setEnabled(true);
				}
			}
		}catch (Exception w) {
			Msg .setForeground(new Color(229, 57, 53));
			Msg .setText("ERROR DE TODO");
			Msg .setVisible(true);
		}
	}

	

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new A_CrearPilotosGUI(frame);
		a.setVisible(true);
	}
}