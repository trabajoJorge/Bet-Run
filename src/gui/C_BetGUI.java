package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import domain.Cliente;
import domain.PilotoEnCarrera;
import domain.Piloto;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class C_BetGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<PilotoEnCarrera> comboBoxParrilla= new JComboBox<PilotoEnCarrera>();
	DefaultComboBoxModel<PilotoEnCarrera> modelParrilla = new DefaultComboBoxModel<PilotoEnCarrera>();
	private JTextField textFieldMonto = new JTextField();
	private JButton jButtonCreate = new JButton("Apostar");
	private JButton jButtonClose = new JButton("Cancelar");
	private final JTextField textFieldCarrera = new JTextField();
	private final JTextField textFieldGanancia = new JTextField();
	private final JLabel jLabelMsg= new JLabel();
	private Cliente currentClient;
	private final JTextField textFieldCoche = new JTextField();
	private final JTextField textFieldEscuderia = new JTextField();
	private final JLabel lblApuestaMinima = new JLabel("Apuesta Minima:");
	private final JTextField textFieldApuestaMinima = new JTextField();
	
	public C_BetGUI(Cliente pcli) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(C_BetGUI.class.getResource("/gui/Fotos/appLogo.png")));
		textFieldEscuderia.setEditable(false);
		textFieldEscuderia.setBounds(160, 167, 155, 20);
		textFieldEscuderia.setColumns(10);
		textFieldCoche.setEditable(false);
		textFieldCoche.setBounds(160, 127, 271, 20);
		textFieldCoche.setColumns(10);
		try {
			currentClient=pcli;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		textFieldCarrera.setEditable(false);
		textFieldCarrera.setBounds(160, 47, 271, 20);
		textFieldCarrera.setColumns(10);
		textFieldGanancia.setEditable(false);
		textFieldGanancia.setText(""); 
		textFieldGanancia.setBounds(160, 247, 60, 20);
		textFieldGanancia.setColumns(10);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		Carrera c=facade.getCarreraFinalizable();
		textFieldCarrera.setText("GP de "+c.getPais());
		this.setSize(new Dimension(512, 468));
		this.setTitle("Crear apuesta");
		comboBoxParrilla.setBackground(SystemColor.controlHighlight);
		comboBoxParrilla.setBounds(160, 87, 271, 20);
		comboBoxParrilla.setEnabled(true);
		
		ArrayList<PilotoEnCarrera> pilotos=  facade.getPilotosEnCarreraOrdenados(c); 
		for(PilotoEnCarrera pds: pilotos) {
			modelParrilla.addElement(pds);
		}
		comboBoxParrilla.setModel(modelParrilla);
		comboBoxParrilla.repaint();
		
		textFieldGanancia.setText( ((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getCuotaGanancia()+"");
		textFieldEscuderia.setText( ((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getEscuderia().getNombre());
		textFieldCoche.setText(((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getCoche().getModelo()+", "+((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getCoche().getCaballos()+" caballos");
		
		textFieldApuestaMinima.setText( ((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getApuestaMinima()+"");

		comboBoxParrilla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PilotoEnCarrera pil= (PilotoEnCarrera) comboBoxParrilla.getSelectedItem();
				PilotoEnCarrera selectedPiloto= pil;
				if (selectedPiloto!=null) {
					textFieldMonto.setEnabled(true);
					textFieldGanancia.setEnabled(true);
					float cuotaGanancia=  (((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getCuotaGanancia());
					textFieldGanancia.setText(String.valueOf(cuotaGanancia));
					float apuMin=  (((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getApuestaMinima());
					textFieldApuestaMinima.setText(String.valueOf(apuMin));
					textFieldEscuderia.setText( ((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getEscuderia().getNombre());
					textFieldCoche.setText(((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getCoche().getModelo()+", "+((PilotoEnCarrera) comboBoxParrilla.getSelectedItem()).getPiloto().getCoche().getCaballos()+" caballos");
				}
			}
		});
		textFieldMonto.setText("");
		textFieldMonto.setBounds(160, 207, 60, 20);
		jButtonCreate.setBackground(SystemColor.controlHighlight);
		
		jButtonCreate.setBounds(269, 358, 162, 40);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelMsg .setText("");
				float monto=0;
				boolean c=true;
				try {
					monto= Float.parseFloat(textFieldMonto.getText());
				}catch (Exception e1) {
					jLabelMsg .setForeground(new Color(229, 57, 53));
					jLabelMsg .setText("EL MONTO NO ES CORRECTO");
					jLabelMsg .setVisible(true);
					textFieldMonto.setText("");
					c=false;
				}
				if (c) {
					PilotoEnCarrera pds= (PilotoEnCarrera) comboBoxParrilla.getSelectedItem();
					if (monto<=(pds.getApuestaMinima())) {
						jLabelMsg .setForeground(new Color(229, 57, 53));
						jLabelMsg .setText("NO PUEDES APOSTAR MENOS QUE LA APUESTA MIN");
						jLabelMsg .setVisible(true);
					}
					else{
						JFrame a = new C_ApuestaGUI(monto, currentClient, pds);
						a.setVisible(true);
					}
				}
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(40, 358, 155, 40);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		getContentPane().setLayout(null);

		this.getContentPane().add(jButtonClose);
		this.getContentPane().add(jButtonCreate);
		this.getContentPane().add(textFieldMonto);

		getContentPane().add(textFieldCarrera);
		
		getContentPane().add(comboBoxParrilla);
		
		getContentPane().add(textFieldGanancia);
		
		JLabel lblNewLabel = new JLabel("Carrera:");
		lblNewLabel.setBounds(40, 50, 367, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPilotos = new JLabel("Pilotos:");
		lblPilotos.setBounds(40, 93, 110, 14);
		getContentPane().add(lblPilotos);
		
		JLabel lblMonto_1 = new JLabel("Monto:");
		lblMonto_1.setBounds(40, 210, 110, 14);
		getContentPane().add(lblMonto_1);
		
		JLabel lblCuotaDeGanancia = new JLabel("Cuota de ganancia:");
		lblCuotaDeGanancia.setBounds(40, 250, 121, 14);
		getContentPane().add(lblCuotaDeGanancia);
		
		jLabelMsg.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelMsg.setBounds(40, 334, 365, 14);
		getContentPane().add(jLabelMsg);
		
		getContentPane().add(textFieldCoche);
		
		getContentPane().add(textFieldEscuderia);
		
		JLabel lblNewLabel_1 = new JLabel("Escuderia:");
		lblNewLabel_1.setBounds(40, 170, 110, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Coche:");
		lblNewLabel_2.setBounds(40, 130, 86, 14);
		getContentPane().add(lblNewLabel_2);
		lblApuestaMinima.setBounds(40, 290, 121, 14);
		
		getContentPane().add(lblApuestaMinima);
		textFieldApuestaMinima.setEditable(false);
		textFieldApuestaMinima.setBounds(160, 287, 60, 20);
		
		getContentPane().add(textFieldApuestaMinima);
		JTextField textFieldGanancia = new JTextField();
		textFieldGanancia.setEditable(false);
		textFieldGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGanancia.setBounds(425, 180, 245, 20);
	}
	

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	public void checkCaption(Event pevento, Piloto pquestion) {
	
	}
}
