package paquetes;

import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.JsonObject;

import hansolo.mario.Juego;
import hansolo.marioparty.tablero.Tablero;
import interfaz.Lobby;
import interfaz.Sala;

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

		case "SALAS_PREVIAS":
			int cant = data.get("cant").getAsInt();
			ArrayList<String> salasNombres = new ArrayList<String>();
			// cargo un arraylist con las salas previas y seteo la lista del lobby
			for (int i = 0; i < cant; i++) {
				String sala = data.get("sala" + i).getAsString();
				salasNombres.add(sala);
			}

			Lobby.setSalasNombres(salasNombres);
			break;

		case "USERS_CONECTADOS":
			cant = data.get("cant").getAsInt();
			ArrayList<String> users = new ArrayList<String>();
			// cargo un arraylist con las salas previas y seteo la lista del lobby
			for (int i = 0; i < cant; i++) {
				String user = data.get("user" + i).getAsString();
				users.add(user);
			}

			Lobby.setUserNames(users);
			break;

		case "USERS_EN_SALA":
			cant = data.get("cant").getAsInt();
			String sala = data.get("sala").getAsString();
			ArrayList<String> usersEnSala = new ArrayList<String>();
			for (int i = 0; i < cant; i++) {
				String user = data.get("user" + i).getAsString();
				usersEnSala.add(user);
			}
			Sala.setUserNames(sala, usersEnSala);
			break;

		case "INICIO_PARTIDA":
			sala = data.get("sala").getAsString();
			cant = data.get("cant").getAsInt();
			JsonObject jugadores = data.get("jugadores").getAsJsonObject();
			ArrayList<String> jugadoresEnSala = new ArrayList<String>();
			for (int i = 0; i < cant; i++) {
				String jugador = jugadores.get("jugador" + i).getAsString();
				jugadoresEnSala.add(jugador);
			}
			Sala.iniciarPartida(sala, jugadoresEnSala);
			break;
			
		case "JUGADOR_DESCONECTADO":
			String juego = data.get("juego").getAsString();
			String user = data.get("user").getAsString();
			Sala.eliminarJugador(juego, user);
			break;

		case "ESTRELLA":
			int posicion = data.get("posicion").getAsInt();
			 juego = data.get("juego").getAsString();
			Sala.ubicarEstrella(posicion, juego);
			break;

		case "TURNO":
			int index = data.get("jugador").getAsInt();
			juego = data.get("juego").getAsString();
			System.out.println("tiene turno");
			Sala.informarTurno(index, juego);
			break;

		case "POSICION":
			
			juego = data.get("juego").getAsString();
			char direccion = data.get("direccion").getAsCharacter();
			Sala.informarDireccion(direccion, juego);
			break;
			
		case "MOVIMIENTOS":
			cant = data.get("cant").getAsInt();
			juego = data.get("juego").getAsString();
			Sala.informarMovimientos(cant, juego);
			break;
			
		case "AVANZAR":
			juego = data.get("juego").getAsString();
			Sala.informarAvanzar(juego);
			break;
			
		case "ACTUALIZAR_MONEDAS":
			juego = data.get("juego").getAsString();
			cant = data.get("cant").getAsInt();
			Sala.actualizarMonedas(juego, cant);
			break;
			
		case "HURTO":
			juego = data.get("juego").getAsString();
			String robado = data.get("robado").getAsString();
			Sala.hurto(juego, robado);
			break;
			
		case "RANDOM":
			juego = data.get("juego").getAsString();
			int indice = data.get("indice").getAsInt();
			Sala.randomMalo(juego, indice);
			break;
			
		case "TP":
			juego = data.get("juego").getAsString();
			String moverseHacia = data.get("moverseHacia").getAsString();
			Sala.TP(juego, moverseHacia);
			break;
			
		case "COMPRA_ESTRELLA":
			juego = data.get("juego").getAsString();
			Sala.compraEstrella(juego);
			break;
		/*
		 * case "ACK_INGRESAR_SALA": Sala sala2 = new Sala(nombre, cliente); break;
		 */
		}

	}
}
