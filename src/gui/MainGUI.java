package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
/**
 * @author Software Engineering teachers
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Cliente;
import java.awt.Toolkit;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
    private JTextField loginUsername;
    private JTextField registerUsername;
    private JTextField registerEmail;
    private JPasswordField registerPassword1;
    private JPasswordField registerPassword2;
    private JPasswordField loginPassword;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/gui/Fotos/appLogo.png")));
		
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
		this.setSize(672, 379);
		this.setContentPane(getJContentPane());
		this.setTitle("Run & Bet");
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
			
			JLabel lblNewLabel = new JLabel(); 
			lblNewLabel.setText("RUN & BET");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
			lblNewLabel.setBounds(172, 17, 415, 42);
			jContentPane.add(lblNewLabel);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBounds(0, 70, 656, 12);
			jContentPane.add(separator);
			
			JLabel lblNewLabel_1 = new JLabel();
			lblNewLabel_1.setText("Usuario:");
			lblNewLabel_1.setBounds(43, 126, 61, 16);
			jContentPane.add(lblNewLabel_1);
			
			JLabel lblPass = new JLabel();
			lblPass.setText("Contraseña:");
			lblPass.setBounds(43, 163, 90, 16);
			jContentPane.add(lblPass);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setOrientation(SwingConstants.VERTICAL);
			separator_1.setForeground(Color.BLACK);
			separator_1.setBounds(321, 70, 2, 270);
			jContentPane.add(separator_1);
			
			loginUsername = new JTextField();
			//textField.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
			loginUsername.setBounds(132, 121, 130, 25);
			jContentPane.add(loginUsername);
			loginUsername.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel();
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(134, 109, 131, 32);
			jContentPane.add(lblNewLabel_2);
			
			JLabel label = new JLabel("User:");
			label.setBounds(360, 108, 61, 16);
			jContentPane.add(label);
			
			JLabel lblPass_1 = new JLabel();
			lblPass_1.setText("Contraseña:");
			lblPass_1.setBounds(360, 143, 118, 16);
			jContentPane.add(lblPass_1);
			
			JLabel lblRepass = new JLabel(); 
			lblRepass.setText("Repetir contraseña:");
			lblRepass.setBounds(360, 178, 131, 16);
			jContentPane.add(lblRepass);
			
			JLabel lblEmail = new JLabel();
			lblEmail.setText("Email:");
			lblEmail.setBounds(360, 213, 61, 16);
			jContentPane.add(lblEmail);
			
			registerUsername = new JTextField();
			registerUsername.setColumns(10);
			registerUsername.setBounds(486, 103, 130, 25);
			jContentPane.add(registerUsername);
			
			registerEmail = new JTextField();
			registerEmail.setColumns(10);
			registerEmail.setBounds(486, 208, 130, 25);
			jContentPane.add(registerEmail);
			
			JLabel lblRegister = new JLabel(); 
			lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblRegister.setBounds(425, 109, 131, 32);
			jContentPane.add(lblRegister);
			
			registerPassword1 = new JPasswordField();
			registerPassword1.setBounds(486, 138, 130, 25);
			jContentPane.add(registerPassword1);
			
			registerPassword2 = new JPasswordField();
			registerPassword2.setBounds(486, 174, 130, 25);
			jContentPane.add(registerPassword2);
			
			loginPassword = new JPasswordField();
			loginPassword.setBounds(132, 158, 130, 25);
			jContentPane.add(loginPassword);
			
			JButton btnLogin = new JButton(); 
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnLogin.setForeground(new Color(255, 255, 255));
			btnLogin.setBackground(new Color(255, 153, 0));
			btnLogin.setText("Inciar Sesión");
			btnLogin.setBounds(30, 276, 267, 42);
			
			JLabel msgLabel = new JLabel("");
			msgLabel.setBounds(44, 225, 253, 21);
			jContentPane.add(msgLabel);
			
			JLabel msgLabel1 = new JLabel("");
			msgLabel1.setBounds(370, 244, 254, 21);
			jContentPane.add(msgLabel1);
			
			JLabel logo = new JLabel("New label");
			logo.setBounds(182, 0, 90, 67);
			jContentPane.add(logo);
			
			//imagen origen
			Image img = new ImageIcon(getClass().getResource("/gui/Fotos/logo.png")).getImage();
			//escala imagen
			Image newimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(newimg); 
			//asigna a componenente JLabel
			logo.setIcon(imageIcon);
			
			
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//try {
					System.out.println("Evento producido sobre componente: " +e.getActionCommand());
					String us=loginUsername.getText();
					String ps= new String(loginPassword.getPassword());
					if (us.equals("") || ps.equals("")) {
						System.out.println("\n\n *** ENTRA ***\n\n" );
						msgLabel.setForeground(new Color(229, 57, 53));
						msgLabel.setText("RELLENA TODOS LOS CAMPOS");
						msgLabel.setVisible(true);
					}
					else if (appFacadeInterface.doLogin(us, ps)) {
						if (appFacadeInterface.isAdmin(us, ps)) {
							JFrame a = new AdminGUI();
							a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							a.setVisible(true);
							Close(e);
						} else {
							Cliente user = appFacadeInterface.getClientByUsername(us);
							JFrame a = new ClientGUI(user);
							a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							a.setVisible(true);
							jContentPane.setVisible(false);
							Close(e);
						}
					 } else {
						msgLabel.setForeground(new Color(229, 57, 53));
						msgLabel.setText("USUARIO NO ENCONTRADO");
						msgLabel.setVisible(true);
					}
					//}catch (Exception e2) {
						msgLabel.setForeground(new Color(229, 57, 53));
						msgLabel.setText("ERROR EN LOGIN");
						msgLabel.setVisible(true);
					//}
				}
			});
			jContentPane.add(btnLogin);
			
			JButton btnRegistrarse = new JButton(); 
			btnRegistrarse.setForeground(new Color(255, 255, 255));
			btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnRegistrarse.setBackground(new Color(255, 153, 0));
			btnRegistrarse.setText("Registrarse");
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					System.out.println("Evento producido sobre componente: " +e.getActionCommand());
					String us=registerUsername.getText();
					String ps= new String(registerPassword1.getPassword());
					String ps2= new String(registerPassword2.getPassword());
					String em=registerEmail.getText();
					
					//VERIFICO QUE PASSWORDS COINCIDEN
					if (verificarPasswords(ps, ps2)) {
						
						//VERIFICO EL EMAIL CON EXPRESION REGULAR
						if (verificarEmail(em)) {
							boolean b=appFacadeInterface.doRegister(us, ps, em);
							
							//doRegister DEVUELVE FALSE SI YA ESTÃ� EXISTE
							if (b) {
								msgLabel1.setForeground(new Color(0, 128, 0));
								msgLabel1.setText("USUARIO REGISTRADO");
								msgLabel1.setVisible(true);
								registerUsername.setText("");
								registerEmail.setText("");
								registerPassword1.setText("");
								registerPassword2.setText("");
							} else {
								msgLabel1.setForeground(new Color(229, 57, 53));
								msgLabel1.setText("USER o EMAIL YA REGISTRADOS");
								msgLabel1.setVisible(true);
								registerUsername.setText("");
								registerEmail.setText("");
								
							}
						} else {
							msgLabel1.setForeground(new Color(229, 57, 53));
							msgLabel1.setText("EMAIL INCORRECTO");
							msgLabel1.setVisible(true);
							registerEmail.setText("");
						}
					} else {
						msgLabel1.setForeground(new Color(229, 57, 53));
						msgLabel1.setText("LAS CONTRASEÑAS NO COINCIDEN");
						msgLabel1.setVisible(true);
						registerPassword1.setText("");
						registerPassword2.setText("");
					}
				}
			});
			btnRegistrarse.setBounds(357, 276, 267, 42);
			jContentPane.add(btnRegistrarse);
			
			

			
			
		}
		return jContentPane;
	}
	

	private void Close(ActionEvent e) {
		this.setVisible(false);
	}
	public boolean verificarPasswords(String ppass1, String ppass2) {
		return ppass1.equals(ppass2);
	}
	
	
	
	
	public boolean verificarEmail(String pemail) {
        // Patrn para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(pemail);
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
        
	}
} // @jve:decl-index=0:visual-constraint="0,0"