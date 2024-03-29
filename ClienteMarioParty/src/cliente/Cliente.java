package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import interfaz.Login;
import paquetes.Paquete;

public class Cliente {
	private final int PUERTO = 9000;
	private final String HOST = "192.168.0.18";

	private String nombre;

	private Socket socket;
	private DataInputStream in;
	private static DataOutputStream out;
	
	private boolean mensajeRespondido = false;
	
	public Cliente() {
		try {
			socket = new Socket(Login._IPSERVIDOR, Login._PUERTO);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se puedo establecer conexion con el servidor", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public Cliente(String nombre) {
		this.nombre = nombre;

		try {
			socket = new Socket(Login._IPSERVIDOR, Login._PUERTO);
			System.out.println("Se conecto " + nombre);
			//creo un hilo para escuchar al server
			ThreadEscucha escucha = new ThreadEscucha(socket);
			escucha.start();
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

		} catch (IOException e) {
			System.out.println(nombre + " no se pudo conectar");
			System.out.println("El server no se encuentra disponible");
			System.exit(1);
		}
	}
	public String getNombre() {
		return this.nombre;
	}

	public static void escribirMensaje(String mensaje) {
		try {
			out.writeUTF(mensaje);
		} catch (IOException e) {
			System.out.println("No se pudo escribir el mensaje");
		}
	}
	
	public boolean esperarRespuesta() {
		if(socket.isClosed())
			return false;
		try {
			String mensajeRecibido = null;

			synchronized (in) {
				mensajeRecibido = in.readUTF();
				mensajeRespondido = true;
			}
			
			JsonParser parser = new JsonParser();
			JsonObject gsonArr = parser.parse(mensajeRecibido).getAsJsonObject();
			Paquete paquete = new Paquete(gsonArr, socket);

		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void cerrarSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("No se pudo cerrar el cliente " + nombre);
		}
	}

}