package hansolo.marioparty.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import animations.Animation;
import hansolo.mario.Juego;
//import hansolo.marioparty.admin.Usuario;
import hansolo.marioparty.graficos.Texturas;
//import hansolo.marioparty.items.DadoSimple;
//import hansolo.marioparty.items.Item;
import hansolo.marioparty.tablero.Casillero;

public class Jugador {
	private int x;
	private int y;

	private BufferedImage spriteTablero;
	private BufferedImage[] spriteCaminarDerecha;
	private BufferedImage[] spriteCaminarFrente;
	private BufferedImage[] spriteCaminarEspalda;
	private Animation animationI;
	private Animation animationD;
	private Animation animationF;
	private Animation animationE;

	private int numero;
	private String user;
	// las monedas se guardan aca o en el server?
	private int monedas, estrellas;
	private Casillero posicion;
	// private List<Item> items;

	private int cantMovimientos;

	public String getUser() {
		return user;
	}

	private boolean avanzando = false;
	private boolean pierdeTurno = false;

	private Juego juego;
	// esta variable me permite saber si este jugador es el que esta jugando en esta
	// ventana
	private String mainUser;
	private boolean isBifurcacion = false;
	private char direccion;
	private BufferedImage[] spriteCaminarIzquierda;

	public void setBifurcacion(boolean isBifurcacion) {
		this.isBifurcacion = isBifurcacion;
	}

	public String getMainUser() {
		return mainUser;
	}

	public void setMainUser(String mainUser) {
		this.mainUser = mainUser;
	}

	public Jugador(int numero, String user, Juego juego) {
		this.numero = numero;
		this.posicion = juego.getTablero().getStart();
		this.x = this.posicion.getX();
		this.y = this.posicion.getY();

		this.monedas = 30;
		this.estrellas = 0;
		// this.items = new ArrayList<Item>();

		this.juego = juego;

		this.user = user;
		this.cantMovimientos = 0;

		cargarSprites();
	}

	public void calcular() {
		// si el jugador esta parado en el [x,y] de su casillero y le quedan
		// movimientos,
		if (estoyParadoEnMiPosicion() && avanzando) {// cantMovimientos != 0) {
			cantMovimientos--;

			if (posicion.isEfectoPasandoSobre())
				juego.getJuegoState().activarEfectoCasillero();
			if (cantMovimientos == 0) {
				// Antes de terminar el turno, debería ejecutar el efecto del casillero en donde
				// terminé
				juego.getJuegoState().activarEfectoCasillero();
				avanzando = false;

				juego.pasarTurno();
				// System.out.println("ACÁ DEBERÍA TERMINAR EL TURNO");
			} else {
				if (!isBifurcacion) {
					switch (posicion.getSiguiente().getDireccion()) {
					case N:
						this.direccion = 'n';
						break;

					case O:
						this.direccion = 'o';
						break;
					case E:
						this.direccion = 'e';
						break;
					case S:
						this.direccion = 's';
						break;

					}
					posicion = posicion.getSiguiente().getCasillero();
					}
				isBifurcacion = false;
				// posicion = posicion.getSiguiente().getCasillero();
			}

			// si no estoy parado en mi posicion y me quedan movimientos, tengo que ir hacia
			// mi posicion
		} else if (!estoyParadoEnMiPosicion() && avanzando) { // cantMovimientos != 0) {
			avanzarHaciaPosicion();
		}
	}

//	private void settearSiguiente(Casillero posicion) {
//		if (!isBifurcacion)
//			posicion = posicion.getSiguiente().getCasillero();
//		isBifurcacion = false;
//	}

	public void dibujar(Graphics g) {
		if (avanzando) {
			switch (direccion) {
			case 'n':
				this.animationE.tick();
				g.drawImage(animationE.getCurrentFrame(), x+20, y+10, null);
				break;
				
			case 'e':
				this.animationD.tick();
				g.drawImage(animationD.getCurrentFrame(), x+25, y+10, null);
				break;
				
			case 'o':
				this.animationI.tick();
				g.drawImage(animationI.getCurrentFrame(), x+15, y+10, null);
				break;
				
			case 's':
				this.animationF.tick();
				g.drawImage(animationF.getCurrentFrame(), x+20, y+10, null);
				break;

			}
			
		} else
			g.drawImage(spriteTablero, x, y, null);
		g.drawString(String.valueOf(cantMovimientos), x, y - 20);
	}

	public void cargarSprites() {
		switch (numero) {
		case 1:
			spriteCaminarDerecha = Texturas.marioDerecha;
			spriteCaminarEspalda = Texturas.marioEspalda;
			spriteCaminarFrente = Texturas.marioFrente;
			spriteCaminarIzquierda = Texturas.marioIzquierda;
			spriteTablero = Texturas.jugador_1;
			this.animationF = new Animation(100, spriteCaminarFrente);
			this.animationE = new Animation(100, spriteCaminarEspalda);
			this.animationD = new Animation(100, spriteCaminarDerecha);
			this.animationI = new Animation(100, spriteCaminarIzquierda);
			break;
		case 2:
			spriteCaminarDerecha = Texturas.luigiDerecha;
			spriteCaminarFrente = Texturas.luigiFrente;
			spriteCaminarEspalda = Texturas.luigiEspalda;
			spriteCaminarIzquierda = Texturas.luigiIzquierda;
			spriteTablero = Texturas.jugador_2;
			this.animationF = new Animation(100, spriteCaminarFrente);
			this.animationE = new Animation(100, spriteCaminarEspalda);
			this.animationD = new Animation(100, spriteCaminarDerecha);
			this.animationI = new Animation(100, spriteCaminarIzquierda);
			break;
//		case 3:
//			spriteTablero = Texturas.jugador_3;
//			break;
//		case 4:
//			spriteTablero = Texturas.jugador_4;
//			break;
		}
	}

	private void avanzarHaciaPosicion() {
		if (posicion.getX() > x)
			x++;
		else
			x--;

		if (posicion.getY() > y)
			y++;
		else
			y--;
	}

//	public static void settearPosicion(int x1, int y1) {
//		x=x1;
//		y=y1;
//	}
	private boolean estoyParadoEnMiPosicion() {
		return this.x == posicion.getX() && this.y == posicion.getY();
	}

	/**
	 * Método que tira el dado del jugador. Debería ser acá donde se le deja elegir
	 * al jugador cuál de sus dados tirar. Si es que agregamos más dados.
	 * 
	 * @return int número que salió en el dado
	 */
//	public void tirarDado() {
//		// mensaje al server
//		// cantMovimientos = DadoSimple.tirar();
//	}

	public void startAvanzar() {
		// Setteo la posición siguiente y lo hago avanzar un pixel así no entra en el
		// primer if del .dibujar();
		switch (posicion.getSiguiente().getDireccion()) {
		case N:
			this.direccion = 'n';
			break;

		case O:
			this.direccion = 'o';
			break;
		case E:
			this.direccion = 'e';
			break;
		case S:
			this.direccion = 's';
			break;

		}
		posicion.getSiguiente().getDireccion();
		posicion = posicion.getSiguiente().getCasillero();

		avanzarHaciaPosicion();
		avanzando = true;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

//	public Usuario getUser() {
//		return user;
//	}
//
//	public void setUser(Usuario user) {
//		this.user = user;
//	}

	public Casillero getPosicion() {
		return posicion;
	}

	public void setPosicion(Casillero posicion) {
		this.posicion = posicion;
	}

	public int getCantMovimientos() {
		return cantMovimientos;
	}

	public void setCantMovimientos(int cantMovimientos) {
		this.cantMovimientos = cantMovimientos;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAvanzando() {
		return avanzando;
	}

	public void setAvanzando(boolean avanzando) {
		this.avanzando = avanzando;
	}

	public Juego getJuego() {
		return juego;
	}

	public boolean isPierdeTurno() {
		return pierdeTurno;
	}

	public void setPierdeTurno(boolean pierdeTurno) {
		this.pierdeTurno = pierdeTurno;
	}

	public void setDireccion(char direccion) {

		switch (direccion) {
		case 'n':
			this.direccion = direccion;
			posicion = posicion.getNorte().getCasillero();
			break;
		case 's':
			this.direccion = direccion;
			posicion = posicion.getSur().getCasillero();
			break;

		case 'e':
			this.direccion = direccion;
			posicion = posicion.getEste().getCasillero();
			break;

		case 'o':
			this.direccion = direccion;
			posicion = posicion.getOeste().getCasillero();
			break;
		}

	}

}
