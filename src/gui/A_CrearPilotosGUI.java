package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import domain.Escuderia;
import domain.Piloto;

public class A_CrearPilotosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Carrera> modelEvents = new DefaultComboBoxModel<Carrera>();

	private JTextField jTextFieldName = new JTextField();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonInsert = new JButton("A\u00F1adir Coche");
	private JButton jButtonClose = new JButton("Cerrar");
	private JLabel jLabelMsg = new JLabel();
	private final JTextField textFieldPeso = new JTextField();
	private final JTextField textFieldEdad = new JTextField();
	private final JLabel lblNewLabel = new JLabel("Nombre:");
	private final JLabel lblNewLabel_1 = new JLabel("Peso:");
	private final JLabel lblNewLabel_2 = new JLabel("Edad: ");
	private JTextField textFieldDNI;
	private JButton frame;
	
	private JComboBox<Escuderia> comboBoxEscuderia= new JComboBox<Escuderia>();
	DefaultComboBoxModel<Escuderia> modelEscuderia= new DefaultComboBoxModel<Escuderia>();
	private final JLabel lblNewLabel_5 = new JLabel("kg");

	public A_CrearPilotosGUI(JButton j) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_CrearPilotosGUI.class.getResource("/gui/Fotos/appLogo.png")));
		frame= j;
		textFieldEdad.setBounds(292, 151, 46, 20);
		textFieldEdad.setColumns(10);
		textFieldPeso.setBounds(292, 120, 46, 20);
		textFieldPeso.setColumns(10);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(561, 304));
		this.setTitle("A\u00F1adir Corredores");
		jTextFieldName.setBounds(new Rectangle(292, 25, 214, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonInsert.setBackground(SystemColor.controlHighlight);

		jButtonInsert.setBounds(new Rectangle(196, 210, 130, 30));

		jButtonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBackground(SystemColor.controlHighlight);
		jButtonClose.setBounds(new Rectangle(376, 210, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(196, 179, 300, 20));
		jLabelMsg.setForeground(new Color(0, 128, 0));

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonInsert, null);
		this.getContentPane().add(jTextFieldName, null);
		
		getContentPane().add(textFieldPeso);
		
		getContentPane().add(textFieldEdad);
		lblNewLabel.setBounds(196, 28, 86, 14);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(196, 126, 46, 14);
		
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(196, 154, 46, 14);
		
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DNI:");
		lblNewLabel_3.setBounds(196, 61, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(292, 58, 214, 20);
		getContentPane().add(textFieldDNI);
		textFieldDNI.setColumns(10);
		comboBoxEscuderia.setBackground(SystemColor.controlHighlight);
		comboBoxEscuderia.setBounds(292, 89, 214, 20);
		
		getContentPane().add(comboBoxEscuderia);
		
		JLabel lblNewLabel_4 = new JLabel("Escuderia:");
		lblNewLabel_4.setBounds(196, 92, 86, 14);
		getContentPane().add(lblNewLabel_4);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<Escuderia> escuderias= facade.getescuderias();
		for(Escuderia e: escuderias) modelEscuderia.addElement(e);
		comboBoxEscuderia.setModel(modelEscuderia);
		comboBoxEscuderia.repaint();
		jLabelMsg.setText("");
		getContentPane().add(comboBoxEscuderia);
		lblNewLabel_5.setBounds(340, 123, 46, 14);
		
		getContentPane().add(lblNewLabel_5);
		JLabel logo = new JLabel("New label");
		logo.setBounds(27, 25, 137, 215);
		getContentPane().add(logo);
		
		//imagen origen
		Image img = new ImageIcon(getClass().getResource("/gui/Fotos/piloto.jpg")).getImage();
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
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es"))) offset += 4;
		else offset += 5;
		
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
			jLabelMsg.setText("");
			String Nombre= jTextFieldName.getText();
			String DNI= textFieldDNI.getText();
			String peso= textFieldPeso.getText();
			String edad= textFieldEdad.getText();
			boolean control=true;
			
			if (DNI.equals("") || peso.equals("") || edad.equals("")  || Nombre.equals("") ) {
				jLabelMsg .setForeground(new Color(128, 0, 0));
				jLabelMsg .setText("TIENES QUE RELLENAR TODOS LOS DATOS");
				jLabelMsg .setVisible(true);
			}
			else if (validarDNI(DNI)==false) {
				jLabelMsg .setForeground(new Color(128, 0, 0));
				jLabelMsg .setText("EL DNI ES INCORRECTO");
				jLabelMsg .setVisible(true);
				control= false;
				textFieldDNI.setText("");
			}
			else if(control) {
				try {
					@SuppressWarnings("unused")
					float fpeso= Float.parseFloat(peso);
					if (fpeso<=30.0 && fpeso>=250.0) {
						jLabelMsg .setForeground(new Color(128, 0, 0));
						jLabelMsg .setText("EL PESO INTRODUCIDO NO ES VALIDO");
						jLabelMsg .setVisible(true);
						control=false;
						textFieldPeso.setText("");
					}
				}catch (Exception e3) {
					jLabelMsg .setForeground(new Color(128, 0, 0));
					jLabelMsg .setText("EL PESO INTRODUCIDO NO ES VALIDO");
					jLabelMsg .setVisible(true);
					control=false;
					textFieldPeso.setText("");
				}
				
				if (control) {
					try {
						int iedad= Integer.parseInt(edad);
						if (iedad>120) {
							jLabelMsg .setForeground(new Color(128, 0, 0));
							jLabelMsg .setText("NO EXISTE NADIE TAN VIEJO EN LA FAZ DE LA TIERRA");
							jLabelMsg .setVisible(true);
							control= false;
							textFieldEdad.setText("");
						}
						if (iedad<18) {
							jLabelMsg .setForeground(new Color(128, 0, 0));
							jLabelMsg .setText("NO PUEDES INSERTAR UN MENOR DE EDAD");
							jLabelMsg .setVisible(true);
							control= false;
							textFieldEdad.setText("");
						}
					}catch (Exception e4) {
						jLabelMsg .setForeground(new Color(128, 0, 0));
						jLabelMsg .setText("LA EDAD NTRODUCIDA NO ES VALIDA");
						jLabelMsg .setVisible(true);
						control= false;
						textFieldEdad.setText("");
					}
					
				}
				if(control) {
					Piloto piloto= new Piloto(textFieldDNI.getText(), jTextFieldName.getText(), textFieldPeso.getText(), textFieldEdad.getText(), (Escuderia)comboBoxEscuderia.getSelectedItem());
					BLFacade facade = MainGUI.getBusinessLogic();
					if (facade.IsPilotoAñadido(piloto)) {
						jLabelMsg .setForeground(new Color(0, 128, 0));
						jLabelMsg.setText("EL PILOTO HA SIDO AÑADIDO");
						JFrame a = new A_PilotosACocheGUI(piloto, frame);
						a.setVisible(true);
						this.setVisible(false);
						jLabelMsg .setVisible(true);
						jTextFieldName.setText("");
						textFieldDNI.setText("");
						textFieldPeso.setText("");
						textFieldEdad.setText("");
					}
					else {
						jLabelMsg .setForeground(new Color(128, 0, 0));
						jLabelMsg .setText("EL PILOTO YA HA SIDO AÑADIDO, ANTERIORMENTE");
						jLabelMsg .setVisible(true);
						jTextFieldName.setText("");
						textFieldDNI.setText("");
						textFieldPeso.setText("");
						textFieldEdad.setText("");
					}
				}
			}
		}catch (Exception w) {
			System.out.println("ERROR EN AÑADIR PILOTO");
		}
			
		}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	public static boolean validarDNI(String nif) {
		boolean correcto = false;
	    Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
	    Matcher matcher = pattern.matcher(nif);
	    if (matcher.matches()) {
	        String letra = matcher.group(2);
	        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	        int index = Integer.parseInt(matcher.group(1));
	        index = index % 23;
	        String reference = letras.substring(index, index + 1);
	        if (reference.equalsIgnoreCase(letra)) correcto = true;
	        else correcto = false;
	    } else correcto = false;

	    return correcto;
	}
}