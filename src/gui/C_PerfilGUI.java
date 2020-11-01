package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Software Engineering teachers
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import businessLogic.BLFacade;
import domain.Apuesta;
import domain.Cliente;
import java.awt.Toolkit;


public class C_PerfilGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
	private static Cliente cl;

    private static BLFacade appFacadeInterface;
    private JTextField textFieldNombre;
    private JTextField textFieldUsername;
    private JTextField textFieldEmail;
    private JTextField textFieldMonto;
    private JTextField textFieldTarjeta;
    JButton btnModificar = new JButton("Modificar");
	JButton btnConfirmar = new JButton("Guardar");
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * This is the default constructor
	 */
	public C_PerfilGUI(Cliente pcl) {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(C_PerfilGUI.class.getResource("/gui/Fotos/appLogo.png")));
		cl=pcl;
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
		this.setSize(1203, 346);
		this.setContentPane(getJContentPane());
		this.setTitle("Ver perfil");
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
			
			JButton atras = new JButton("Cancelar");
			atras.setBackground(SystemColor.controlHighlight);
			atras.setBounds(74, 248, 129, 29);
			jContentPane.add(atras);
			atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close1();
					}
				}
			); 
			

			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//setUndecorated(true);
			//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			

			BLFacade facade=MainGUI.getBusinessLogic();
			
			Cliente clientedb = facade.getClientByUsername(cl.getUsername());
			btnModificar.setBackground(SystemColor.controlHighlight);
			
			//Estado inicial de los botones sobrepuestos
			btnModificar.setVisible(true);
			btnConfirmar.setBackground(SystemColor.controlHighlight);
			btnConfirmar.setVisible(false);
			
			
			
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (facade.confirmarEditarPerfilCliente(textFieldNombre.getText(), textFieldUsername.getText(), textFieldEmail.getText(), textFieldTarjeta.getText())){
						textFieldNombre.setEditable(false);
						textFieldEmail.setEditable(false);
						textFieldTarjeta.setEditable(false);
						btnModificar.setVisible(true);
						btnConfirmar.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Error de guardado.");
					}
				}
			});
			btnConfirmar.setBounds(343, 248, 129, 29);
			jContentPane.add(btnConfirmar);
			
			
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldNombre.setEditable(true);
					textFieldEmail.setEditable(true);
					textFieldTarjeta.setEditable(true);
					btnModificar.setVisible(false);
					btnConfirmar.setVisible(true);
				}
			});
			btnModificar.setBounds(343, 248, 129, 29);
			jContentPane.add(btnModificar);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(54, 100, 178, 16);
			jContentPane.add(lblNombre);
			
			JLabel lblUsername = new JLabel("Username: ");
			lblUsername.setBounds(54, 68, 178, 16);
			jContentPane.add(lblUsername);
			
			JLabel lblEmail = new JLabel("Email: ");
			lblEmail.setBounds(54, 134, 178, 16);
			jContentPane.add(lblEmail);
			
			JLabel lblTarjeta = new JLabel("Numero Tarjeta: ");
			lblTarjeta.setBounds(54, 168, 129, 16);
			jContentPane.add(lblTarjeta);
			
			JLabel lblMonto = new JLabel("Saldo: ");
			lblMonto.setBounds(54, 200, 178, 16);
			jContentPane.add(lblMonto);
			
			textFieldNombre = new JTextField();
			textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNombre.setBounds(210, 97, 278, 26);
			jContentPane.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			textFieldNombre.setEditable(false);
			textFieldNombre.setText(clientedb.getName());
			
			textFieldUsername = new JTextField();
			textFieldUsername.setText((String) null);
			textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldUsername.setColumns(10);
			textFieldUsername.setBounds(211, 63, 278, 26);
			jContentPane.add(textFieldUsername);
			textFieldUsername.setEditable(false);
			textFieldUsername.setText(clientedb.getUsername());
			
			textFieldEmail = new JTextField();
			textFieldEmail.setText((String) null);
			textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldEmail.setColumns(10);
			textFieldEmail.setBounds(210, 129, 278, 26);
			jContentPane.add(textFieldEmail);
			textFieldEmail.setText(clientedb.getEmail());
			textFieldEmail.setEditable(false);
			
			textFieldTarjeta= new JTextField();
			textFieldTarjeta.setText((String) null);
			textFieldTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldTarjeta.setColumns(10);
			textFieldTarjeta.setBounds(211, 163, 278, 26);
			jContentPane.add(textFieldTarjeta);
			textFieldTarjeta.setEditable(false);
			textFieldTarjeta.setText(clientedb.getTarjeta());

			textFieldMonto = new JTextField();
			textFieldMonto.setText((String) null);
			textFieldMonto.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldMonto.setColumns(10);
			textFieldMonto.setBounds(211, 195, 278, 26);
			jContentPane.add(textFieldMonto);
			textFieldMonto.setEditable(false);
			textFieldMonto.setText(clientedb.getSaldo()+" €");
			
			JLabel lblVerPerfil = new JLabel("Ver perfil");
			lblVerPerfil.setHorizontalAlignment(SwingConstants.CENTER);
			lblVerPerfil.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
			lblVerPerfil.setBounds(0, 11, 1187, 26);
			jContentPane.add(lblVerPerfil);
			
			JPanel panel = new JPanel();
			panel.setBounds(574,91, 556, 186);
			jContentPane.add(panel);
			
			JLabel lblHistorialDeApuestas = new JLabel("Historial de apuestas en el mes:");
			lblHistorialDeApuestas.setBounds(574, 89, 346, 16);
			jContentPane.add(lblHistorialDeApuestas);
			
			JLabel lblNewLabel = new JLabel("Gasto mensual:");
			lblNewLabel.setBounds(574, 66, 164, 14);
			jContentPane.add(lblNewLabel);
			
			JFrame JFrameActivo = this;
			
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                JFrameActivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                JFrameActivo.setVisible(true);
	                
	                XYDataset ds = createDataset();
	                JFreeChart chart = ChartFactory.createXYLineChart("", "Fecha", "Cantidad", ds, PlotOrientation.VERTICAL, true, true, false);
	                //chart.getXYPlot().getRangeAxis().setRange(0, 10000000);
	             
	             // Create an NumberAxis
	                NumberAxis xAxis = new NumberAxis();
	                xAxis.setTickUnit(new NumberTickUnit(1));
	                
	                
	                Calendar today = Calendar.getInstance();
	        		int month=today.get(Calendar.MONTH);
	        		int ano=today.get(Calendar.YEAR);
	                //RANGO DEL EJE X
	                xAxis.setLowerBound(1);
	                
	                //Controlo el final del eje X según la cantidad de días del mes
	                switch(month+1) {
	                	case 1:	
	                	case 3:
	                	case 5:
	                	case 7:
	                	case 8:
	                	case 10:
	                	case 12: {
	                        xAxis.setUpperBound(31);
	                        break;
	                	}
	                	case 4:
	                	case 6:
	                	case 9:
	                	case 11: {
	                        xAxis.setUpperBound(30);
	                        break;
	                	}
	                	case 2: {
	                		GregorianCalendar calendar = new GregorianCalendar();
	                		if (calendar.isLeapYear(ano)) {
	                			System.out.println("Febrero de año bisiesto");
	                        	xAxis.setUpperBound(29);
	                		}  else {
	                			System.out.println("Febrero de año no bisiesto");
	                        	xAxis.setUpperBound(28);
	                		}
	                		break;
	                	}
	                	
	                }
	                

	                // Assign it to the chart
	                XYPlot plot = (XYPlot) chart.getPlot();
	                plot.setDomainAxis(xAxis);
	                
	                JPanel panelGrafica = new JPanel();
	                panelGrafica.setBounds(574, 91, 556, 186);
	                getContentPane().add(panelGrafica);
	                
	                ChartPanel cp = new ChartPanel(chart);
	                panelGrafica.setBackground(new Color(240,240,240));
	                panelGrafica.add(cp);
	                
	                cp.setPreferredSize(new Dimension(550, 170));
	                panelGrafica.repaint();
	               //chart.getXYPlot().getRangeAxis().setRange(0, 10);
	                
	                
	            }
	        });
		}
		return jContentPane;
	}
		@SuppressWarnings("deprecation")
		private static XYDataset createDataset() {

	    	//Obtenemos el mes para consultar las apuestas del mes
	    	Calendar today = Calendar.getInstance();
			int month=today.get(Calendar.MONTH);
			
	        DefaultXYDataset ds = new DefaultXYDataset();
	        double dias[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	        double cantidades[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	        
	        BLFacade facade = MainGUI.getBusinessLogic();
	        ArrayList<Apuesta> apuestas = facade.getApuestasCliente(cl);
	        System.out.println("\n\n ****"+ facade.numeroApuestasClienteTotal(cl)+" **** \n\n");
	        for(Apuesta a: apuestas) {
	        	if (a.getFechaApuesta().getMonth() == month) {
	        		cantidades[a.getFechaApuesta().getDate()-1]= cantidades[a.getFechaApuesta().getDate()-1]+a.getDineroApostado();
	        		for(int i=0; i<31; i++) {
	        			 System.out.println("\n - "+cantidades[i]);
	                }
	        	}
	        }
	        double data[][] = new double[2][31];
	        
	        for(int i=0; i<31; i++) {
	        	data[0][i]=dias[i];
	        	data[1][i]=cantidades[i];
	        }

	        //ds.addSeries("Evolución de las apuestas del mes "+Month.of(month), data);
	        String[] monthName = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Deciembre"};
	        ds.addSeries("Gasto del mes de "+monthName[month], data);
	        return ds;
	    }

	private void close1(){
		this.dispose();
	}	
}