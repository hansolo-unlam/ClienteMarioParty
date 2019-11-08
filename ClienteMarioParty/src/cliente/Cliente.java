package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	private final int PUERTO = 9000;
	private final String HOST = "localhost";

	private String nombre;

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public Cliente(String nombre) {
		this.nombre = nombre;

		try {
			socket = new Socket(HOST, PUERTO);
			System.out.println("Se conecto " + nombre);
			//creo un hilo para escuchar al server
			ThreadEscucha escucha = new ThreadEscucha(socket);
			escucha.start();
			out = new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			System.out.println(nombre + " no se pudo conectar");
			System.out.println("El server no se encuentra disponible");
			System.exit(1);
		}
	}
	public String getNombre() {
		return this.nombre;
	}

	public void escribirMensaje(String mensaje) {
		try {
			out.writeUTF(mensaje);
		} catch (IOException e) {
			System.out.println("No se pudo escribir el mensaje");
		}
	}


	public void cerrarSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("No se pudo cerrar el cliente " + nombre);
		}
	}

}