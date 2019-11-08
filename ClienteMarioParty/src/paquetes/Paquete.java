package paquetes;

import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.JsonObject;

import interfaz.Lobby;

public class Paquete {

	protected String tipo;
	protected int id;
	protected int idPartida;

	private JsonObject jsonObject;
	private String cabecera;
	private JsonObject data;
	/*
	 * public Paquete(int id, String tipo, int idPartida) { this.tipo = tipo;
	 * this.id = id; this.idPartida = idPartida; }
	 * 
	 * public Paquete(int id, String tipo) { this.id = id; this.tipo = tipo; }
	 */

	public Paquete(JsonObject jsonObject, Socket socketCliente) {
		this.jsonObject = jsonObject;
		verificarPaquete();

	}

	private void verificarPaquete() {
		cabecera = jsonObject.get("nombre").getAsString();
		data = jsonObject.get("data").getAsJsonObject();

		switch (cabecera) {

		case "SALA_CREADA":
			String salaCreada = data.get("salaCreada").getAsString();
			Lobby.agregarSala(salaCreada);
			break;

		case "SALA_ELIMINADA":
			String salaEliminada = data.get("salaEliminada").getAsString();
			Lobby.eliminarSala(salaEliminada);
			break;

		case "NUEVO_USUARIO":
			int cant = data.get("cant").getAsInt();
			ArrayList<String> salasNombres = new ArrayList<String>();
			//cargo un arraylist con las salas previas y seteo la lista del lobby
			for (int i = 0; i < cant; i++) {
				String sala = data.get("sala" + i).getAsString();
				salasNombres.add(sala);
			}

			Lobby.setSalasNombres(salasNombres);
			break;
		}
	}
}
