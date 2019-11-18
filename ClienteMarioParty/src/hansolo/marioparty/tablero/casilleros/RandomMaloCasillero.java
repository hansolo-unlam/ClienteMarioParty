package hansolo.marioparty.tablero.casilleros;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

import cliente.Cliente;
import hansolo.marioparty.entidades.Jugador;
import hansolo.marioparty.graficos.Texturas;
import hansolo.marioparty.tablero.Casillero;
import hansolo.marioparty.tablero.SiguienteCasillero;
import hansolo.marioparty.ui.AdministradorUI;

/**
 * Casillero que le hace algo malo al jugador que cae en él
 * 
 * @author facundotourn
 *
 */
public class RandomMaloCasillero extends Casillero {

	public RandomMaloCasillero(int id) {
		super(id, false);
	}

	@Override
	public void efecto(Jugador jugador, AdministradorUI administradorUI, String juego) {
		jugador.setEstadoFinal("malo");
		if (jugador.getMainUser().equals(jugador.getUser())) {
			JsonObject jo = new JsonObject();
			JsonObject jo1 = new JsonObject();
			jo.addProperty("nombre", "RANDOM");
			jo1.addProperty("juego", juego);
			jo.add("data", jo1);
			Cliente.escribirMensaje(jo.toString());
		}
	}

	@Override
	protected void dibujar(Graphics g) {
		g.drawImage(Texturas.casillero_random_malo, x, y, null);
		
//		g.setFont(new Font("Calibri", Font.PLAIN, 20));
//		g.drawString(Integer.toString(id), x + 16, y + 16);
		
		//g.drawImage(Texturas.casillero_random_malo, x+8, y+12, null);
		
	}

}
