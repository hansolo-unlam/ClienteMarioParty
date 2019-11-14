package hansolo.marioparty.tablero.casilleros;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

import cliente.Cliente;
import hansolo.marioparty.entidades.Jugador;
import hansolo.marioparty.graficos.Texturas;
import hansolo.marioparty.tablero.Casillero;
import hansolo.marioparty.ui.AdministradorUI;
import hansolo.marioparty.ui.ClickListener;
import hansolo.marioparty.ui.ImageButton;

/**
 * Casillero que al caer en él, te permite pagar para sacarle un item o una
 * estrella a otro jugador
 * 
 * @author facundotourn
 *
 */
public class HurtoCasillero extends Casillero {
	public HurtoCasillero(int id) {
		super(id, false);
	}

	@Override
	public void efecto(Jugador jugador, AdministradorUI administradorUI, String juego) {
		if (jugador.getMainUser().equals(jugador.getUser())) {
			List<Jugador> jugadores = jugador.getJuego().getJugadores();

			dibujarBotones(jugador, administradorUI, jugadores, juego);
		}
		System.out.println(jugador.getUser() + " calló en un casillero de hurto");
	}

	private void dibujarBotones(Jugador jugador, AdministradorUI administradorUI, List<Jugador> jugadores,
			String juego) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Selecciona un oponente para robarle hasta 5 monedas");
		for (int i = 0; i < jugadores.size(); i++)

			if (jugadores.get(i) != jugador) {
				Jugador jugadorARobar = jugadores.get(i);
				String btn = "btn" + i;

				switch (i) {

				case 0:
					administradorUI.agregarObjeto(btn,
							new ImageButton(720, 78, 235, 36, Texturas.botonHurto, new ClickListener() {
								@Override
								public void onClick() {

									JsonObject jo = new JsonObject();
									JsonObject jo1 = new JsonObject();
									jo.addProperty("nombre", "HURTO");
									jo1.addProperty("juego", juego);
									jo1.addProperty("aRobar", jugadorARobar.getUser());
									jo.add("data", jo1);
									Cliente.escribirMensaje(jo.toString());
									eliminarBotones(administradorUI, jugadores, jugador);
//									jugador.setAvanzando(true);

								}
							}));
					// fin case 0
					break;

				case 1:

					administradorUI.agregarObjeto(btn,
							new ImageButton(720, 146, 235, 36, Texturas.botonHurto, new ClickListener() {
								@Override
								public void onClick() {

									JsonObject jo = new JsonObject();
									JsonObject jo1 = new JsonObject();
									jo.addProperty("nombre", "HURTO");
									jo1.addProperty("juego", juego);
									jo1.addProperty("aRobar", jugadorARobar.getUser());
									jo.add("data", jo1);
									Cliente.escribirMensaje(jo.toString());
									eliminarBotones(administradorUI, jugadores, jugador);

								}
							}));
					// fin case 1
					break;

				case 2:

					administradorUI.agregarObjeto(btn,
							new ImageButton(720, 214, 235, 36, Texturas.botonHurto, new ClickListener() {
								@Override
								public void onClick() {

									JsonObject jo = new JsonObject();
									JsonObject jo1 = new JsonObject();
									jo.addProperty("nombre", "HURTO");
									jo1.addProperty("juego", juego);
									jo1.addProperty("aRobar", jugadorARobar.getUser());
									jo.add("data", jo1);
									Cliente.escribirMensaje(jo.toString());
									eliminarBotones(administradorUI, jugadores, jugador);
//									jugador.setAvanzando(true);

								}
							}));
					// fin case 2
					break;

				case 3:

					administradorUI.agregarObjeto(btn,
							new ImageButton(720, 280, 235, 36, Texturas.botonHurto, new ClickListener() {
								@Override
								public void onClick() {
//								
									JsonObject jo = new JsonObject();
									JsonObject jo1 = new JsonObject();
									jo.addProperty("nombre", "HURTO");
									jo1.addProperty("juego", juego);
									jo1.addProperty("aRobar", jugadorARobar.getUser());
									jo.add("data", jo1);
									Cliente.escribirMensaje(jo.toString());
									eliminarBotones(administradorUI, jugadores, jugador);
//									jugador.setAvanzando(true);

								}
							}));
					// fin case3
					break;
				}// llave switch

			}
	}

	private void eliminarBotones(AdministradorUI administradorUI, List<Jugador> jugadores, Jugador jugador) {
		for (int i = 0; i < jugadores.size(); i++)
			if (jugadores.get(i) != jugador) {
				administradorUI.removerObjeto("btn" + i);
			}
	}

	@Override
	protected void dibujar(Graphics g) {
		g.drawImage(Texturas.casillero_hurto, x, y, null);

	}

}
