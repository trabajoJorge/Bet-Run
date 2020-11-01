package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.Escuderia;
import java.awt.Toolkit;

public class A_CrearEscuderiaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();

	private JTextField jTextFieldName = new JTextField();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonInsert = new JButton("A\u00F1adir Escuderia");
	private JButton jButtonClose = new JButton("Cerrar");
	private JButton ParameterButton;
	private JLabel jLabelMsg = new JLabel();
	private final JLabel lblNewLabel = new JLabel("Nombre de la Escuderia:");

	public A_CrearEscuderiaGUI(JButton jButton) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_CrearEscuderiaGUI.class.getResource("/gui/Fotos/appLogo.png")));
		ParameterButton= jButton;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(458, 196));
		this.setTitle("A\u00F1adir Escuderia");
		jTextFieldName.setBounds(new Rectangle(197, 41, 208, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonInsert.setBackground(SystemColor.controlHighlight);

		jButtonInsert.setBounds(new Rectangle(30, 98, 169, 30));

		jButtonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(new Rectangle(236, 98, 169, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(40, 67, 305, 20));
		jLabelMsg.setForeground(new Color(0, 128, 0));

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonInsert, null);
		this.getContentPane().add(jTextFieldName, null);
		lblNewLabel.setBounds(30, 44, 169, 14);
		
		getContentPane().add(lblNewLabel);
	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			jLabelMsg.setText("");

			Escuderia escuderia= new Escuderia(jTextFieldName.getText().toString());
			BLFacade facade = MainGUI.getBusinessLogic();
			if(jTextFieldName.getText().equals("")) {
				jLabelMsg.setForeground(new Color(229, 57, 53));
				jLabelMsg.setText("TIENES QUE RELLENAR TODOS LOS CAMPOS");
				jLabelMsg.setVisible(true);
			}
			else if (facade.crearEscuderia(escuderia)) {
				jLabelMsg .setForeground(new Color(0, 128, 0));
				jLabelMsg .setText("LA ESCUDERÍA HA SIDO AÑADIDA");
				ParameterButton.setEnabled(true);
				jLabelMsg .setVisible(true);
			}
			else {
				jLabelMsg .setForeground(new Color(229, 57, 53));
				jLabelMsg .setText("LA ESCUDERÍA YA HA SIDO AÑADIDA");
				jLabelMsg .setVisible(true);
			}
		}catch (Exception w) {
			jLabelMsg .setForeground(new Color(229, 57, 53));
			jLabelMsg .setText("ERROR EN AÑADIR ESCUDERÍA");
			jLabelMsg .setVisible(true);
		}
			
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}