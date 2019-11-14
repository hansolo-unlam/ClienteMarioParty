package hansolo.marioparty.tablero.casilleros;

import java.awt.Font;
import java.awt.Graphics;

import com.google.gson.JsonObject;

import cliente.Cliente;
import hansolo.mario.Juego;
import hansolo.marioparty.entidades.Jugador;
import hansolo.marioparty.graficos.Texturas;
import hansolo.marioparty.tablero.Casillero;
import hansolo.marioparty.ui.AdministradorUI;

/**
 * Casillero que, al caer en él, te da o te quita una cierta cantidad de monedas
 * (+3 o -3)
 * 
 * @author facundotourn
 *
 */
public class MonedaCasillero extends Casillero {
	private int cantMonedas; // positivo o negativo

	public MonedaCasillero(int id, int signo) {
		super(id, false);
		this.cantMonedas = 3 * signo;
	}

	@Override
	public void efecto(Jugador jugador, AdministradorUI administradorUI, String juego) {
		if (jugador.getMainUser().equals(jugador.getUser())) {
			JsonObject jo = new JsonObject();
			JsonObject jo1 = new JsonObject();
			jo.addProperty("nombre", "MONEDAS");
			jo1.addProperty("juego", juego);
			jo1.addProperty("cant", this.cantMonedas);
			jo.add("data", jo1);
			Cliente.escribirMensaje(jo.toString());
		}
	}

	public int getMoneda() {
		return this.cantMonedas;
	}

	@Override
	protected void dibujar(Graphics g) {
		if (this.cantMonedas > 0)
			g.drawImage(Texturas.casillero_moneda_positivo, x, y, null);
		if (this.cantMonedas < 0)
			g.drawImage(Texturas.casillero_moneda_negativo, x, y, null);

//		g.setFont(new Font("Calibri", Font.PLAIN, 20));
//		g.drawString(Integer.toString(id), x + 16, y + 16);
	}

}
