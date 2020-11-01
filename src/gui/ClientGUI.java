package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Apuesta;
import domain.Carrera;
import domain.Cliente;
import java.awt.Toolkit;

public class ClientGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();
	private Cliente clienteGlobal;

	public ClientGUI(Cliente cg) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientGUI.class.getResource("/gui/Fotos/appLogo.png")));
		BLFacade facade = MainGUI.getBusinessLogic();
		clienteGlobal= facade.getClientByUsername(cg.getUsername());;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
	
/*
* -------------------------------------------------------------------------------------------------------
*/	
		JLabel lblCliente_1 = new JLabel();
		lblCliente_1.setText("MENU DE CLIENTE");
		lblCliente_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente_1.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCliente_1.setBounds(0, 11, 543, 42);
		getContentPane().add(lblCliente_1);
		this.setSize(new Dimension(560, 207));
		this.setTitle("Menu de Cliente");
/*
* -------------------------------------------------------------------------------------------------------
*/
		JButton btnVerPerfil = new JButton("Ver Perfil");
		btnVerPerfil.setBackground(SystemColor.controlHighlight);
		btnVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerPerfil.setBounds(20, 60, 250, 30);
		btnVerPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new C_PerfilGUI(clienteGlobal);
				a.setVisible(true);
			}
		});
		getContentPane().add(btnVerPerfil);
/*
 * -------------------------------------------------------------------------------------------------------
 */
		JButton btnVerCarreraActiva = new JButton("Ver Carrera Activa");
		btnVerCarreraActiva.setBackground(SystemColor.controlHighlight);
		btnVerCarreraActiva.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerCarreraActiva.setBounds(270, 60, 250, 30);
		btnVerCarreraActiva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new C_VerCarreraGUI();
				a.setVisible(true);
			}
		});
		getContentPane().add(btnVerCarreraActiva);

/*
* -------------------------------------------------------------------------------------------------------
*/		
		JButton btnApostar = new JButton("Apostar");
		btnApostar.setBackground(SystemColor.controlHighlight);
		btnApostar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnApostar.setBounds(20, 90, 250, 30);
		btnApostar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new C_BetGUI(clienteGlobal);
				a.setVisible(true);
			}
		});
		//if (tieneApuestas(clienteGlobal)) btnApostar.setEnabled(false);
		getContentPane().add(btnApostar);
/*
* -------------------------------------------------------------------------------------------------------
*/	
		JButton btnCerrarSesion = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesion.setBackground(SystemColor.controlHighlight);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCerrarSesion.setBounds(270, 120, 250, 30);
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MainGUI();
				jButtonClose_actionPerformed(e);
				a.setVisible(true);
			}
		});
/*
* -------------------------------------------------------------------------------------------------------
*/
		BLFacade facade = MainGUI.getBusinessLogic();
		Carrera c=facade.getCarreraActiva();
		if (c==null) {
			btnVerCarreraActiva.setEnabled(false);
			btnApostar.setEnabled(false);
		}
		else if (!c.isApostable()) {
			btnApostar.setEnabled(false);
		}
		getContentPane().add(btnCerrarSesion);
/*
* -------------------------------------------------------------------------------------------------------
*/
		JButton btnHistorial = new JButton("Historial de Apuestas");
		btnHistorial.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHistorial.setBackground(SystemColor.controlHighlight);
		btnHistorial.setBounds(20, 120, 250, 30);
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new UserBetsHistoryGUI(clienteGlobal);
				jButtonClose_actionPerformed(e);
				a.setVisible(true);
			}
		});
		getContentPane().add(btnHistorial);
		
		JButton btnMonedero = new JButton("Monedero");
		btnMonedero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMonedero.setBackground(SystemColor.controlHighlight);
		btnMonedero.setBounds(270, 90, 250, 30);
		btnMonedero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame a = new C_CargarMonederoGUI(clienteGlobal);
				jButtonClose_actionPerformed(e);
				a.setVisible(true);
			}
		});
		getContentPane().add(btnMonedero);
	}

	public boolean tieneApuestas(Cliente c) {
		BLFacade facade = MainGUI.getBusinessLogic();
		Cliente c1= facade.getClientByUsername(c.getUsername());
		ArrayList<Apuesta>apuesta=c1.getApuestas();
		if (apuesta!=null) for (Apuesta a: apuesta) if (a.getEstado()==0) return true;
		return false;
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}