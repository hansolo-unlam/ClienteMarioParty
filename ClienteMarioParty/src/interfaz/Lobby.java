package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.DataInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.glass.events.WindowEvent;

import cliente.Cliente;

public class Lobby {
	public String elegido;
	private final static int WIDHT = 140;
	private final static int HEIGHT = 30;
	private static int desplazamiento = HEIGHT;
	private static int desplazamientoX = 0;

	private JButton btnCrearSala;


	private static JFrame frame = new JFrame("Lobby MarioParty");;
	private static JPanel contentPane = new JPanel();;

	private JLabel lblJugadores;
	private JLabel lblSalas;
	private static JTextPane textPane = new JTextPane();;

	private static Cliente cliente;

	private static ArrayList<String> salasNombres = new ArrayList<String>();
	private static ArrayList<String> userNames = new ArrayList<String>();

	private static String nombreUser;
	
	public static void setUserNames(ArrayList<String> users) {
		userNames = users;
		String texto = "";
		for (String user : userNames) {
			texto = texto + user + "\n" + "\n";
		}
		textPane.setText(texto);
	}

	private int cantSalas = 0;

	public Lobby(String nombre, String nombreUser) {
		//nombreUser = JOptionPane.showInputDialog("Ingresar nombre");
		this.nombreUser = nombreUser;
				
		this.cliente = new Cliente(this.nombreUser);
		JsonObject jo = new JsonObject();
		JsonObject jo1 = new JsonObject();
		jo.addProperty("nombre", "LOGIN_2");
		jo1.addProperty("usuario", this.nombreUser);
		jo.add("data", jo1);
		Cliente.escribirMensaje(jo.toString());
				
				
//		MensajeEspera mensajeEspera = new MensajeEspera();
//		mensajeEspera.setVisible(true);
//		new Thread(mensajeEspera).start();
//		cliente.esperarRespuesta();
//		mensajeEspera.stop();
				
		//PERMITE INGRESAR AUNQUE NO TENGA PERMISO
		//ESPERAR RESPUESTA DEL SERVIDOR - BILELLO
		init(nombre);
	}

	private void init(String nombre) {
		frame.setName(nombre);
		frame.setResizable(false);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		// cuando cierro la ventana le aviso al server que me desconecto
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame, "¿Queres salir del MarioParty?", "¿Cerrar lobby?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					JsonObject jo = new JsonObject();
					JsonObject jo1 = new JsonObject();
					jo.addProperty("nombre", "SALIR");
					jo1.addProperty("usuario",nombreUser);
					jo.add("data", jo1);
					Cliente.escribirMensaje(jo.toString());
					System.exit(0);
				}
				else  {
					
				}
			}
		});

		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		lblJugadores = new JLabel("JUGADORES EN LINEA");
		lblJugadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadores.setBounds(41, 22, 138, 32);

		contentPane.add(lblJugadores);

		lblSalas = new JLabel("SALAS");
		lblSalas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setBounds(300, 22, 138, 32);

		contentPane.add(lblSalas);

		
		textPane.setBounds(25, 52, 180, 400);
		textPane.setEditable(false);

		contentPane.add(textPane);

		btnCrearSala = new JButton("Crear nueva sala");
		
	

	
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingresar nombre de la sala");
				// creo el mensaje para el server
				JsonObject jo = new JsonObject();
				JsonObject jo1 = new JsonObject();
				
				jo.addProperty("nombre", "NUEVA_SALA");
				jo1.addProperty("nombreSala", nombre);
				jo1.addProperty("user", nombreUser);
				jo.add("data", jo1);
				Cliente.escribirMensaje(jo.toString());
				frame.setVisible(false);
				Sala sala = new Sala(nombre, cliente, nombreUser);
			}	
		});
		

		btnCrearSala.setBounds(241, 380, 160, 50);
		contentPane.add(btnCrearSala);
		frame.setVisible(true);
		dibujarBotonesSalas(salasNombres);
	}

	public static void agregarSala(String nombre) {
		salasNombres.add(nombre);
		dibujarBotonesSalas(salasNombres);

	}

	public static ArrayList<JButton> btnsSala = new ArrayList<JButton>();
	
	//cada vez que agrego o elimino un boton redibujo todo
	public static void dibujarBotonesSalas(ArrayList<String> salasNombres) {
		int cont = 0;
		//elimino los botones anterioroes
		for (JButton btn : btnsSala) {
			btn.setVisible(false);
			contentPane.remove(btn);
		}
		btnsSala.clear();
		// por cada sala existente creo un nuevo boton
		for (String nombre : salasNombres) {
			cont++;
			btnsSala.add(new JButton(nombre));
			if ((cont - 1) % 7 == 0)
				desplazamiento = 0;
			if (cont > 7) {
				desplazamientoX = WIDHT * ((cont - 1) / 7) + 30;
			}

			btnsSala.get(cont - 1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Ingresar a la sala");
					JsonObject jo = new JsonObject();
					JsonObject jo1 = new JsonObject();
					jo.addProperty("nombre", "INGRESAR_SALA");
					jo1.addProperty("salaSolicitada", nombre);
					jo1.addProperty("user", nombreUser);
					jo.add("data", jo1);
					Cliente.escribirMensaje(jo.toString());
					
					//ESPERAR RESPUESTA DEL SERVIDOR - BILELLO
					frame.setVisible(false);
					Sala sala2 = new Sala(nombre, cliente, nombreUser);
				}
			});

			btnsSala.get(cont - 1).setBounds(240 + desplazamientoX, 60 + desplazamiento, WIDHT, HEIGHT);
			desplazamiento += HEIGHT + 10;
			contentPane.add(btnsSala.get(cont - 1));
			frame.repaint();
		}

	}

	public static void setSalasNombres(ArrayList<String> salasNombres) {
		Lobby.salasNombres = salasNombres;
		dibujarBotonesSalas(salasNombres);
	}


	public static void eliminarSala(String salaEliminada) {
		salasNombres.remove(salaEliminada);
		//redibujo sin la sala eliminada
		dibujarBotonesSalas(salasNombres);

	}

	public static void setVisible() {
		frame.setVisible(true);
	}


}
