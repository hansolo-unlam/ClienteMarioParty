package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.google.gson.JsonObject;

import cliente.Cliente;

public class Sala {

	private JFrame frame;
	private int WIDTH = 410;
	private int HEIGHT = 310;

	private JLabel lblJugadores;
	private JTextPane textPane;
	private JButton btnSalirButton;
	

	public Sala(String nombre, Cliente cliente) {
		init(nombre, cliente);
		
	}

	private void init(String nombreSala, Cliente cliente) {
		frame = new JFrame(nombreSala);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "¿Queres salir de la sala?", "¿Cerrar sala?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	JsonObject jo = new JsonObject();
					JsonObject jo1 = new JsonObject();
					jo.addProperty("nombre", "SALIR_SALA");
					jo1.addProperty("nombreSala", nombreSala);
					jo.add("data", jo1);
					cliente.escribirMensaje(jo.toString());
					frame.hide();
		        }
		    }
		});
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		frame.setLayout(null);

		lblJugadores = new JLabel("JUGADORES");
		lblJugadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadores.setBounds(31, 22, 138, 32);
		lblJugadores.setVisible(true);

		frame.add(lblJugadores);

		textPane = new JTextPane();
		textPane.setBounds(41, 52, 138, 198);
		textPane.setEditable(false);

		frame.add(textPane);

		btnSalirButton = new JButton("Salir de la sala");
		btnSalirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Tocaste salir");
				JsonObject jo = new JsonObject();
				JsonObject jo1 = new JsonObject();
				jo.addProperty("nombre", "SALIR_SALA");
				jo1.addProperty("nombreSala", nombreSala);
				jo.add("data", jo1);
				cliente.escribirMensaje(jo.toString());
				frame.hide();
			}
		});
		btnSalirButton.setBounds(241, 182, 120, 23);
		frame.add(btnSalirButton);

		JButton btnComenzarButton = new JButton("Comenzar");
		btnComenzarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JsonObject jo = new JsonObject();
				JsonObject jo1 = new JsonObject();
				jo.addProperty("nombre", "INICIAR_PARTIDA");
				jo1.addProperty("", "");
				jo.add("data", jo1);
				cliente.escribirMensaje(jo.toString());
				System.out.println("Empezar partida");
			}
		});
		btnComenzarButton.setBounds(239, 216, 122, 23);
		frame.add(btnComenzarButton);
		frame.setVisible(true);
	}


}
