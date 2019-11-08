package cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import paquetes.Paquete;



public class ThreadEscucha extends Thread{

	private Socket socket;
	private DataInputStream in;

	public ThreadEscucha(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void run() {
		/*
		 * Aca tendria que procesar el paquete que le llega al jugador a traves de su
		 * input
		 */

		/**
		 * Por lo que entendi isConnected verifica que este conectado, pero si se cerro
		 * el socket no se "limpia" esa conexion entonces por eso tambien pregunto si no
		 * esta cerrada la conexion
		 */
		while (socket.isConnected()) {
			
			if(socket.isClosed())
				break;
			try {
				String mensajeRecibido = null;

				synchronized (in) {
					mensajeRecibido = in.readUTF();
				}
				
				JsonParser parser = new JsonParser();
				JsonObject gsonArr = parser.parse(mensajeRecibido).getAsJsonObject();
				Paquete paquete = new Paquete(gsonArr, socket);
				

				/*Gson gson = new Gson();

				Paquete inputPaquete = gson.fromJson(mensajeRecibido, Paquete.class);*/

			} catch (IOException e) {
			}
		}

		// Una vez leido el paquete viene la magia de Gson y deberia comprobar que tipo
		// de paquetes es para poder procesar ese paquete
	}

}
