package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonObject;

import cliente.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private static Login frame;
	
	
	public static String _IPSERVIDOR; // PERDON PROFE
	public static int _PUERTO; // IDEM ANTERIOR
	
	private static boolean datosValidos;
	private Cliente cliente;
	private JPasswordField contraseña;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(args.length == 0)
						for (String string : args) 
							JOptionPane.showMessageDialog(null, string);
					else
						JOptionPane.showMessageDialog(null, "SIN ARGUMENTOS");
					_IPSERVIDOR = "localhost"; //args[0];
					_PUERTO = 9000; //Integer.parseInt(args[1]);
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		cliente = new Cliente();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
					if (JOptionPane.showConfirmDialog(frame, "¿Queres salir del MarioParty?", "¿Cerrar?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						JsonObject jo = new JsonObject();
						JsonObject jo1 = new JsonObject();
						jo.addProperty("nombre", "SALIR");
						jo1.addProperty("usuario", "invitado");
						jo.add("data", jo1);
						Cliente.escribirMensaje(jo.toString());
						cliente.cerrarSocket();
						frame.dispose();
						System.exit(0);
					}
				}
			});

		datosValidos = false;
		
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Iniciar sesion");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 265, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 23, 69, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 58, 69, 14);
		panel.add(lblNewLabel_1);
		
		usuario = new JTextField();
		usuario.setBounds(89, 20, 141, 20);
		panel.add(usuario);
		usuario.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							
				verificarDatos();
				
			}
		});
		btnIniciarSesion.setBounds(130, 89, 100, 23);
		panel.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRegistrarse.setBounds(10, 89, 100, 23);
		panel.add(btnRegistrarse);
		
		contraseña = new JPasswordField();
		contraseña.setBounds(89, 55, 141, 20);
		panel.add(contraseña);
	}
	
	private void verificarDatos() {
		
		
		
		JsonObject jo = new JsonObject();
		JsonObject jo1 = new JsonObject();
		jo.addProperty("nombre", "LOGIN");
		jo1.addProperty("usuario", getUsuario());
		jo1.addProperty("contraseña", getContraseña());
		jo.add("data", jo1);
		
		Cliente.escribirMensaje(jo.toString());
		cliente.esperarRespuesta();
		if(datosValidos) {
			jo = new JsonObject();
			jo1 = new JsonObject();
			jo.addProperty("nombre", "SALIR_LOGIN");
			jo1.addProperty("usuario", "");
			jo.add("data", jo1);
			Cliente.escribirMensaje(jo.toString());
			cliente.cerrarSocket();
			
			
			this.setVisible(false);
			frame.dispose();
			new Lobby("Lobby MarioParty", getUsuario());
			
		}
		else
			informarDatosIncorrectos();
		
	}
	
	public void informarDatosIncorrectos() {
		usuario.setText("");
		contraseña.setText("");
		JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos!\nIntente nuevamente.", "Fallo de inicio de sesion", JOptionPane.ERROR_MESSAGE);
	}

	public String getUsuario() {
		return usuario.getText();
	}

	public String getContraseña() {
		String password = new String(contraseña.getPassword());
		return password;
	}
	
	
	public static void setDatosValidos(boolean valido) {
		datosValidos = valido;
	}
	
	public static boolean getDatosValidos() {
		return datosValidos;
	}
}
