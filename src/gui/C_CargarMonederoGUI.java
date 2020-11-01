package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Cliente;


public class C_CargarMonederoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
	private Cliente cl;

    private static BLFacade appFacadeInterface;
    private JTextField montoaRecargar;
	DefaultComboBoxModel<String> modelComboBoxPago = new DefaultComboBoxModel<String>();
	private JTextField textField;
	private JPasswordField pin;
	private boolean Control= true;


	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * This is the default constructor
	 */
	public C_CargarMonederoGUI(Cliente pcl) {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(C_CargarMonederoGUI.class.getResource("/gui/Fotos/appLogo.png")));
		this.cl=pcl;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(434, 331);
		this.setContentPane(getJContentPane());
		this.setTitle("Cargar Monedero");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JButton atras = new JButton("Cerrar"); //$NON-NLS-1$ //$NON-NLS-2$
			atras.setBackground(SystemColor.controlHighlight);
			atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						JFrame a = new ClientGUI(cl);
						a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						a.setVisible(true);
						Close(e);
						}
					}
			);
			
			

			BLFacade facade=MainGUI.getBusinessLogic();
			
			Cliente clientedb = facade.getClientByUsername(cl.getUsername());
			
			atras.setBounds(37, 236, 142, 29);
			jContentPane.add(atras);
			
			JButton btnAceptar = new JButton("A\u00F1adir fondos"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAceptar.setBackground(SystemColor.controlHighlight);
			btnAceptar.setBounds(240, 236, 142, 29);
			jContentPane.add(btnAceptar);
			
			JLabel pinlabel = new JLabel("CVC:");
			pinlabel.setBounds(37, 176, 43, 16);
			jContentPane.add(pinlabel);
			
			JLabel lblTarjeta = new JLabel("Numero de Tarjeta:"); //$NON-NLS-1$ //$NON-NLS-2$
			lblTarjeta.setBounds(37, 96, 178, 16);
			jContentPane.add(lblTarjeta);

			modelComboBoxPago.addElement(clientedb.getTarjeta());
			
			
			JLabel lblNewLabel_1 = new JLabel("Monto a introduccir:");
			lblNewLabel_1.setBounds(37, 134, 171, 16);
			jContentPane.add(lblNewLabel_1);
			
			
			JLabel lblNewLabel_2 = new JLabel("Saldo actual: "+clientedb.getSaldo()+"€");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_2.setBounds(-2, 46, 428, 39);
			jContentPane.add(lblNewLabel_2);
			
			montoaRecargar = new JTextField();
			montoaRecargar.setBounds(177, 132, 130, 20);
			jContentPane.add(montoaRecargar);
			montoaRecargar.setColumns(10);
			
			JLabel lblCargarMonedero = new JLabel("Cargar monedero");
			lblCargarMonedero.setHorizontalAlignment(SwingConstants.CENTER);
			lblCargarMonedero.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblCargarMonedero.setBounds(-12, 11, 440, 26);
			jContentPane.add(lblCargarMonedero);
			JLabel msg = new JLabel("");
			msg.setHorizontalAlignment(SwingConstants.CENTER);
			msg.setBounds(-2, 208, 428, 14);
			jContentPane.add(msg);
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(177, 94, 205, 20);
	
			JLabel TAR = new JLabel("");
			TAR.setHorizontalAlignment(SwingConstants.CENTER);
			TAR.setBounds(36, 203, 346, 16);
			jContentPane.add(TAR);
			JLabel TAR2 = new JLabel("");
			TAR2.setHorizontalAlignment(SwingConstants.CENTER);
			TAR2.setBounds(36, 219, 346, 14);
			jContentPane.add(TAR2);
			if (cl.getTarjeta().contentEquals("")) {
				textField.setForeground(new Color(229, 57, 53));
				textField.setText("NO HAY TARJETA ASIGNADA");
				textField.setVisible(true);
				btnAceptar.setEnabled(false);
				TAR .setForeground(new Color(229, 57, 53));
				TAR .setText("Para asignar un numero de tarjeta ve al apartado ");
				TAR .setVisible(true);
				TAR2 .setForeground(new Color(229, 57, 53));
				TAR2 .setText("perfil y asigna un numero de tarjeta");
				TAR2 .setVisible(true);
			}
			else textField.setText(cl.getTarjeta());
			jContentPane.add(textField);
			textField.setColumns(10);
			
			
			
			pin = new JPasswordField();
			pin.setBounds(177, 174, 130, 20);
			jContentPane.add(pin);
			
			
			
			btnAceptar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					try {
						
						if (pin.getText().length()!=3) {
							msg .setForeground(new Color(229, 57, 53));
							msg .setText("EL CVC DEBE TENER 3 DIGITOS");
							msg .setVisible(true);
						} else {
							if (montoaRecargar.getText().isEmpty()) {
								msg .setForeground(new Color(229, 57, 53));
								msg .setText("TIENES QUE INGRASAR UN MONTO");
								msg .setVisible(true);
							} else {
								BLFacade facade = MainGUI.getBusinessLogic();
								float monto = Float.parseFloat(montoaRecargar.getText());
								boolean result =  facade.añadirFondos(clientedb, monto);
								if (result) {
									msg .setForeground(new Color(0, 128, 0));
									msg .setText("EL DINERO HA SIDO AÑADIDO");
									msg .setVisible(true);
								} else {
									msg .setForeground(new Color(229, 57, 53));
									msg .setText("ERROR EN LA TRANSACCIÓN");
									msg .setVisible(true);
								}
								Cliente clienteActualizado= facade.getClientByUsername(clientedb.getUsername());
								lblNewLabel_2.setText("Saldo actual: "+clienteActualizado.getSaldo()+"€");
							}
						}
					} catch (Exception e1) {
						msg .setForeground(new Color(229, 57, 53));
						msg .setText("ERROR EN INGRESAR MONTO");
						msg .setVisible(true);
					}
				}
			});
		}

		return jContentPane;
	}

	private void Close(ActionEvent e) {
		this.dispose();
	}
}