package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.Piloto;
import domain.PilotoEnCarrera;

public class C_VerCarreraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/*private class Pilotos{
		private int Posicion;
		private ParrillaDeSalida Piloto;
		
		public Pilotos(int Pos, ParrillaDeSalida PilotoCarrera) {
			this.Posicion= Pos;
			this.Piloto= PilotoCarrera;		
		}
		public String toString() {return this.Posicion+". "+this.Piloto.getPiloto().getNombre();}
		public ParrillaDeSalida getPiloto() {return Piloto;}
	}*/
	private JComboBox<PilotoEnCarrera> comboBoxParrilla= new JComboBox<PilotoEnCarrera>();
	DefaultComboBoxModel<PilotoEnCarrera> modelParrilla = new DefaultComboBoxModel<PilotoEnCarrera>();
	private JButton jButtonClose = new JButton("Atras");
	private final JTextField textFieldCarrera = new JTextField();
	private final JLabel jLabelMsg= new JLabel();

	private final JTextField textFieldCoche = new JTextField();
	private final JLabel lblNewLabel_2_1 = new JLabel("Cuota Ganancia:");
	private final JLabel lblNewLabel_2_1_1 = new JLabel("Lugar:");
	private final JTextField textFieldLugar = new JTextField();
	private final JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha:");
	private final JTextField textFieldFecha = new JTextField();
	private final JTextField textFieldCuotaGanancia = new JTextField();

	
	public C_VerCarreraGUI (){
		setIconImage(Toolkit.getDefaultToolkit().getImage(C_VerCarreraGUI.class.getResource("/gui/Fotos/appLogo.png")));
		textFieldCuotaGanancia.setEditable(false);
		textFieldCuotaGanancia.setBounds(160, 244, 86, 20);
		textFieldCuotaGanancia.setColumns(10);
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(160, 127, 285, 20);
		textFieldFecha.setColumns(10);
		textFieldCoche.setEditable(false);
		textFieldCoche.setBounds(160, 207, 285, 20);
		textFieldCoche.setColumns(10);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void jbInit() throws Exception {
		textFieldCarrera.setEditable(false);
		//textFieldGanancia.setText();
		textFieldCarrera.setBounds(160, 47, 367, 20);
		textFieldCarrera.setColumns(10);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		Carrera c=facade.getCarreraActiva();
		textFieldCarrera.setText("GP de "+c.getPais());
		this.setSize(new Dimension(593, 420));
		this.setTitle("Ver Carrera Activa");
		comboBoxParrilla.setBackground(SystemColor.controlHighlight);
		comboBoxParrilla.setBounds(160, 167, 285, 20);
		comboBoxParrilla.setEnabled(true);
		
		ArrayList<PilotoEnCarrera> pilotos=  c.getPilotosOrdenados(); 
		for(PilotoEnCarrera pec: pilotos) {
			modelParrilla.addElement(pec);
		}
		comboBoxParrilla.setModel(modelParrilla);
		comboBoxParrilla.repaint();
		textFieldLugar.setText(c.getCircuito()+", " +c.getCiudad()+ " (" +c.getPais()+").");
		String strDateFormat = "dd-MM-yyyy";
		SimpleDateFormat fecha = new SimpleDateFormat(strDateFormat);
		textFieldFecha.setText(fecha.format(c.getFechaComienzo())+" " );
		
		PilotoEnCarrera pec=(PilotoEnCarrera) comboBoxParrilla.getSelectedItem();
		Piloto p= (Piloto)(pec).getPiloto();
		textFieldCoche.setText(p.getCoche().getModelo()+", "+p.getCoche().getCaballos()+" caballos");
		textFieldCuotaGanancia.setText(pec.getCuotaGanancia()+"");
		
		comboBoxParrilla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PilotoEnCarrera pec=(PilotoEnCarrera) comboBoxParrilla.getSelectedItem();
				Piloto p= (Piloto)(pec).getPiloto();
				if (pec!=null) {
					textFieldCoche.setText(p.getModeloCoche()+", "+p.getCaballosCoche()+" caballos");
					textFieldCuotaGanancia.setText(pec.getCuotaGanancia()+"");
				}
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(40, 308, 155, 40);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		getContentPane().setLayout(null);

		this.getContentPane().add(jButtonClose);

		getContentPane().add(textFieldCarrera);
		
		getContentPane().add(comboBoxParrilla);
		
		JLabel lblNewLabel = new JLabel("Carrera:");
		lblNewLabel.setBounds(40, 50, 367, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPilotos = new JLabel("Pilotos:");
		lblPilotos.setBounds(40, 170, 110, 14);
		getContentPane().add(lblPilotos);
		
		jLabelMsg.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelMsg.setBounds(40, 334, 365, 14);
		getContentPane().add(jLabelMsg);
		
		getContentPane().add(textFieldCoche);
		
		JLabel lblNewLabel_2 = new JLabel("Coche:");
		lblNewLabel_2.setBounds(40, 210, 86, 14);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2_1.setBounds(40, 247, 110, 14);
		
		getContentPane().add(lblNewLabel_2_1);
		lblNewLabel_2_1_1.setBounds(40, 90, 110, 14);
		
		getContentPane().add(lblNewLabel_2_1_1);
		textFieldLugar.setEditable(false);
		textFieldLugar.setColumns(10);
		textFieldLugar.setBounds(160, 87, 367, 20);
		
		getContentPane().add(textFieldLugar);
		lblNewLabel_2_1_1_1.setBounds(40, 130, 110, 14);
		
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		getContentPane().add(textFieldFecha);
		
		getContentPane().add(textFieldCuotaGanancia);
		JTextField textFieldGanancia = new JTextField();
		textFieldGanancia.setEditable(false);
		textFieldGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGanancia.setBounds(425, 180, 245, 20);
	}
	

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
