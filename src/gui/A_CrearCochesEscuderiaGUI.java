package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import domain.Carrera;
import domain.Coche;
import domain.Escuderia;
import java.awt.Toolkit;

public class A_CrearCochesEscuderiaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();

	private JTextField jTextFieldName = new JTextField();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonInsert = new JButton("A\u00F1adir Coche");
	private JButton jButtonClose = new JButton("Cerrar");
	private JLabel jLabelMsg = new JLabel();
	private final JLabel lblNewLabel = new JLabel("Modelo:");
	private final JLabel lblNewLabel_1 = new JLabel("Escuderia:");
	private JTextField textFieldCaballos;

	private JComboBox<Escuderia> comboBoxEscuderia= new JComboBox<Escuderia>();
	DefaultComboBoxModel<Escuderia> modelEscuderia= new DefaultComboBoxModel<Escuderia>();
	
	private JButton ParameterButton;
	private JTextField textFieldMatricula;
	public A_CrearCochesEscuderiaGUI(JButton jp) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_CrearCochesEscuderiaGUI.class.getResource("/gui/Fotos/appLogo.png")));
		ParameterButton=jp;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(554, 266));
		this.setTitle("A\u00F1adir Coche");
		jTextFieldName.setBounds(new Rectangle(128, 57, 218, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonInsert.setBackground(SystemColor.controlHighlight);

		jButtonInsert.setBounds(new Rectangle(33, 176, 130, 30));

		jButtonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(new Rectangle(208, 176, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(33, 145, 305, 20));
		jLabelMsg.setForeground(new Color(0, 128, 0));

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonInsert, null);
		this.getContentPane().add(jTextFieldName, null);
		lblNewLabel.setBounds(32, 60, 46, 14);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(32, 30, 106, 14);
		
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Caballos:");
		lblNewLabel_3.setBounds(32, 120, 86, 14);
		getContentPane().add(lblNewLabel_3);
		
		textFieldCaballos = new JTextField();
		textFieldCaballos.setBounds(128, 117, 51, 20);
		getContentPane().add(textFieldCaballos);
		textFieldCaballos.setColumns(10);
		comboBoxEscuderia.setBounds(128, 27, 218, 20);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<Escuderia> escuderias= facade.getescuderias();
		for(Escuderia e: escuderias) modelEscuderia.addElement(e);
		comboBoxEscuderia.setModel(modelEscuderia);
		comboBoxEscuderia.repaint();
		jLabelMsg.setText("");
		getContentPane().add(comboBoxEscuderia);
		
		JLabel lblNewLabel_2 = new JLabel("Matricula:");
		lblNewLabel_2.setBounds(33, 90, 85, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(128, 88, 106, 20);
		getContentPane().add(textFieldMatricula);
		
		JLabel logo = new JLabel("New label");
		logo.setBounds(374, 23, 130, 183);
		getContentPane().add(logo);
		
		//imagen origen
		Image img = new ImageIcon(getClass().getResource("/gui/Fotos/coche.jpg")).getImage();
		//escala imagen
		Image newimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(newimg); 
		//asigna a componenente JLabel
		logo.setIcon(imageIcon);
	}


	public static void paintDaysWithEvents(JCalendar jCalendar) {
		BLFacade facade = MainGUI.getBusinessLogic();

		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
			
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		//int today=calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:dates){
	 		calendar.setTime(d);
	 		System.out.println(d); 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
	 		calendar.set(Calendar.DAY_OF_MONTH, 1);
	 		calendar.set(Calendar.MONTH, month);
	 	
	}
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			Coche coche= new Coche(textFieldMatricula.getText(), jTextFieldName.getText(), textFieldCaballos.getText(), (Escuderia)comboBoxEscuderia.getSelectedItem());
			
			if(textFieldMatricula.getText().equals("") || jTextFieldName.getText().equals("") || textFieldCaballos.getText().equals("")) {
				jLabelMsg .setForeground(new Color(229, 57, 53));
				jLabelMsg .setText("TIENES QUE RELLENAR TODOS LOS CAMPOS");
				jLabelMsg .setVisible(true);
			}	
			else{
				boolean c=true;
				if (!comprobarMatricula(textFieldMatricula.getText())) {
					jLabelMsg .setForeground(new Color(229, 57, 53));
					jLabelMsg .setText("MATRICULA INCORRECTA");
					jLabelMsg .setVisible(true);
					textFieldMatricula.setText("");
					c=false;
				}
				if (c) {
					try {
						int i= Integer.parseInt(textFieldCaballos.getText());
						if(i>3000) {
							jLabelMsg .setForeground(new Color(229, 57, 53));
							jLabelMsg .setText("SON DEMASIADOS CABALLOS PARA UN COCHE");
							jLabelMsg .setVisible(true);
							textFieldCaballos.setText("");
							c=false;
						}
					} catch(Exception e5) {
						jLabelMsg .setForeground(new Color(229, 57, 53));
						jLabelMsg .setText("LOS CABALLOS SON INCORRECTOS");
						jLabelMsg .setVisible(true);
						textFieldCaballos.setText("");
						c=false;
					}
					
					if (c) {	
						if (facade.crearCoche(coche)) {
							jLabelMsg .setForeground(new Color(0, 128, 0));
							jLabelMsg .setText("EL COCHE HA SIDO AÑADIDO");
							if (ParameterButton!=null)ParameterButton.setEnabled(true);
							jLabelMsg .setVisible(true);
						}
						
						else {
							jLabelMsg .setForeground(new Color(229, 57, 53));
							jLabelMsg .setText("MATRICULA DUPLICADA. YA HA SIDO AÑADIDA");
							jLabelMsg .setVisible(true);
							textFieldMatricula.setText("");
						}
					}
				}
			}
		}catch (Exception w) {
			System.out.println("ERROR EN AÑADIR COCHE");
		}
	}
	
		

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	boolean comprobarMatricula (String matricula){

	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        System.out.println("Matrícula válida");
	        return true;

	    }else{
	        System.out.println("Matrícula inválida");
	        return false;
	    }      
	}
}