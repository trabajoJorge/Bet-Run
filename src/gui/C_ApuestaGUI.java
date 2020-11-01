package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.Cliente;
import domain.Piloto;
import domain.PilotoEnCarrera;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class C_ApuestaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonInsert = new JButton("Confirmar Apuesta");
	private JButton jButtonClose = new JButton("Atras");
	private JLabel jLabelMsg = new JLabel();
	
	private Cliente cliente;
	private Float monto;
	private PilotoEnCarrera parrilla;
	private final JLabel foto = new JLabel("");
	public C_ApuestaGUI(Float m, Cliente c, PilotoEnCarrera pds) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(C_ApuestaGUI.class.getResource("/gui/Fotos/appLogo.png")));
		cliente=c;
		monto=m;
		parrilla=pds;
		
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(645, 279));
		this.setTitle("Confirmar Apuesta");
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonInsert.setBackground(SystemColor.controlHighlight);

		jButtonInsert.setBounds(new Rectangle(30, 186, 169, 30));

		jButtonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(new Rectangle(217, 186, 169, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		jLabelMsg.setHorizontalAlignment(SwingConstants.CENTER);

		jLabelMsg.setBounds(new Rectangle(30, 155, 356, 20));
		jLabelMsg.setForeground(new Color(0, 128, 0));

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonInsert, null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(38, 35, 348, 103);
		BLFacade facade = MainGUI.getBusinessLogic();
		Carrera c=facade.getCarreraFinalizable();
		Piloto p= parrilla.getPiloto();
		textPane.setText("Apuesta hecha por: "+cliente.getUsername()
				+ "\n     Carrera: GP de " + c.getPais()
				+ "\n     Circuito: " + c.toString()
				+ "\n     Piloto: " + parrilla.toString()
				+ "\n     Podiums: " + parrilla.getPiloto().getCarrerasGanadas()   
				+ "\n     Escuderia: "+ p.getEscuderia().getNombre()
				+ "\n     Coche: " + p.getModeloCoche());
		
		getContentPane().add(textPane);
		foto.setBounds(404, 35, 199, 181);
		getContentPane().add(foto);
		
		//imagen origen
		Image img = new ImageIcon(getClass().getResource(p.getImagen())).getImage();
		Image newimg = img.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(newimg); 
		//asigna a componenente JLabel
		foto.setIcon(imageIcon);
	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			jLabelMsg.setText("");
			BLFacade facade = MainGUI.getBusinessLogic();
			Cliente c= facade.getClientByUsername(cliente.getUsername());
			Float a= c.getSaldo();
			Float b= monto;
				if (a<b) {
					jLabelMsg .setForeground(new Color(229, 57, 53));
					jLabelMsg .setText("NO TIENES SUFICIENTE DINERO PARA HACER LA APUESTA");
					jLabelMsg .setVisible(true);
				}
				else if (facade.crearApuesta(b, parrilla, c)) {
					jLabelMsg .setForeground(new Color(0, 128, 0));
					jLabelMsg .setText("LA APUESTA HA SIDO REALIZADA");
					jLabelMsg .setVisible(true);
					this.setVisible(false);
				}
				else {
					jLabelMsg .setForeground(new Color(229, 57, 53));
					jLabelMsg .setText("LA APUESTA NO HA SIDO AÑADIDA");
					jLabelMsg .setVisible(true);
			}
		}catch (Exception w) {
			jLabelMsg .setForeground(new Color(229, 57, 53));
			jLabelMsg .setText("ERROR EN AÑADIR APUESTA");
			jLabelMsg .setVisible(true);
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}