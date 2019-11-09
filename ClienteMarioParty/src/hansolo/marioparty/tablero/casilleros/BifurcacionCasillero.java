package hansolo.marioparty.tablero.casilleros;

import java.awt.Graphics;
import java.util.Random;

import com.google.gson.JsonObject;

import cliente.Cliente;
import hansolo.marioparty.entidades.Jugador;
import hansolo.marioparty.graficos.Texturas;
import hansolo.marioparty.tablero.Casillero;
import hansolo.marioparty.ui.AdministradorUI;
import hansolo.marioparty.ui.ClickListener;
import hansolo.marioparty.ui.ImageButton;

/**
 * Casillero que, cuando pasas o caés, te pregunta a cual de los next querés
 * moverte
 * 
 * @author facundotourn
 *
 */
public class BifurcacionCasillero extends Casillero {

	public BifurcacionCasillero(int id) {
		super(id, true);
	}

	/**
	 * El efecto de la bifurcación consiste en confirmar en qué dirección quiere
	 * avanzar el jugador. De esta forma, dado que la bifurcación ejecuta el efecto
	 * cuando un jugador cae o pasa por ella, el jugador nunca va a terminar un
	 * turno parado sobre una bifurcación.
	 */
	@Override
	public void efecto(Jugador jugador, AdministradorUI administradorUI, String juego) {
		jugador.setCantMovimientos(jugador.getCantMovimientos() + 1);
		jugador.setAvanzando(false);
		jugador.setBifurcacion(true);
		if (jugador.getUser().equals(jugador.getMainUser()))
			dibujarBotones(jugador, administradorUI, juego);
	}

	public void dibujarBotones(Jugador jugador, AdministradorUI administradorUI, String juego) {
		if (norte != null) {
			Casillero c = norte.getCasillero();
			administradorUI.agregarObjeto("btnIrArriba", new ImageButton(c.getX(), c.getY(), Texturas.width,
					Texturas.height, Texturas.flecha_arriba, new ClickListener() {

						@Override
						public void onClick() {
							JsonObject jo = new JsonObject();
							JsonObject jo1 = new JsonObject();
							jo.addProperty("nombre", "BIFURCACION");
							jo1.addProperty("juego", juego);
							jo1.addProperty("direccion", "n");
							jo1.addProperty("casillero", id);
							jo.add("data", jo1);
							Cliente.escribirMensaje(jo.toString());
//							jugador.setPosicion(c);
//							jugador.setAvanzando(true);
							eliminarBotones(administradorUI);
						}

					}));
		}
		if (sur != null) {
			Casillero c = sur.getCasillero();
			administradorUI.agregarObjeto("btnIrAbajo", new ImageButton(c.getX(), c.getY(), Texturas.width,
					Texturas.height, Texturas.flecha_abajo, new ClickListener() {

						@Override
						public void onClick() {
							JsonObject jo = new JsonObject();
							JsonObject jo1 = new JsonObject();
							jo.addProperty("nombre", "BIFURCACION");
							jo1.addProperty("juego", juego);
							jo1.addProperty("direccion", "s");
							jo1.addProperty("casillero", id);
							jo.add("data", jo1);
							Cliente.escribirMensaje(jo.toString());
//					jugador.setPosicion(c);
//					jugador.setAvanzando(true);
							eliminarBotones(administradorUI);
						}

					}));
		}
		if (oeste != null) {
			Casillero c = oeste.getCasillero();
			administradorUI.agregarObjeto("btnIrIzquierda", new ImageButton(c.getX(), c.getY(), Texturas.width,
					Texturas.height, Texturas.flecha_izquierda, new ClickListener() {

						@Override
						public void onClick() {
							JsonObject jo = new JsonObject();
							JsonObject jo1 = new JsonObject();
							jo.addProperty("nombre", "BIFURCACION");
							jo1.addProperty("juego", juego);
							jo1.addProperty("direccion", "o");
							jo1.addProperty("casillero", id);
							jo.add("data", jo1);
							Cliente.escribirMensaje(jo.toString());
//					jugador.setPosicion(c);
//					jugador.setAvanzando(true);
							eliminarBotones(administradorUI);
						}

					}));

		}
		if (este != null) {
			Casillero c = este.getCasillero();
			administradorUI.agregarObjeto("btnIrDerecha", new ImageButton(c.getX(), c.getY(), Texturas.width,
					Texturas.height, Texturas.flecha_derecha, new ClickListener() {

						@Override
						public void onClick() {
							JsonObject jo = new JsonObject();
							JsonObject jo1 = new JsonObject();
							jo.addProperty("nombre", "BIFURCACION");
							jo1.addProperty("juego", juego);
							jo1.addProperty("direccion", "e");
							jo1.addProperty("casillero", id);
							jo.add("data", jo1);
							Cliente.escribirMensaje(jo.toString());
//							jugador.setPosicion(c);
//							jugador.setAvanzando(true);
							eliminarBotones(administradorUI);
						}

					}));
		}
	}

	private void eliminarBotones(AdministradorUI administradorUI) {
		administradorUI.removerObjeto("btnIrArriba");
		administradorUI.removerObjeto("btnIrAbajo");
		administradorUI.removerObjeto("btnIrIzquierda");
		administradorUI.removerObjeto("btnIrDerecha");
	}

	@Override
	protected void dibujar(Graphics g) {
//		g.setFont(new Font("Calibri", Font.PLAIN, 20));
//		g.drawString(Integer.toString(id), x + 16, y + 16);
	}

}
