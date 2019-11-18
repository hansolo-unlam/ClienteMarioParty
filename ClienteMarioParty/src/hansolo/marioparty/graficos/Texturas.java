package hansolo.marioparty.graficos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

/*
 * Clase que carga las texturas para que otros objetos puedan tener acceso a ellas.
 */
public class Texturas {
	// Ancho y alto de las texturas
	public static final int width = 64, height = 64;

	// texturas de jugadores
	public static BufferedImage jugador_1, jugador_2, jugador_3, jugador_4;
	public static BufferedImage[] botonHurto;
	// texturas de casilleros
	public static BufferedImage casillero_bifurcacion, casillero_estrella, casillero_moneda_positivo,
			casillero_moneda_negativo, casillero_hurto, casillero_random_malo, casillero_TP, casillero_tienda,
			casillero_item;

	public static BufferedImage casillero_conexion_este_out, casillero_conexion_norte_out, casillero_conexion_sur_out,
			casillero_conexion_oeste_out;
	public static BufferedImage casillero_conexion_este_in, casillero_conexion_norte_in, casillero_conexion_sur_in,
			casillero_conexion_oeste_in;

	// botones
	public static BufferedImage[] btnTirarDado1, btnTirarDado2, btnTirarDado3, btnTirarDado4, btnTerminarTurno;

	// flechas
	public static BufferedImage[] flecha_arriba, flecha_abajo, flecha_izquierda, flecha_derecha;

	// MinijuegoObstaculos
	public static BufferedImage[] mario;
	public static BufferedImage[] luigi;
	public static BufferedImage escenario;
	public static BufferedImage tubo;
	public static BufferedImage suelo;
	public static BufferedImage iconoMario;
	public static BufferedImage iconoLuigi;
	public static BufferedImage iconoPeach;
	public static BufferedImage iconoYoshi;
	public static BufferedImage[] numeros;
	public static BufferedImage iconoResultadoM;
	public static BufferedImage iconoResultadoL;
	public static BufferedImage iconoResultadoY;
	public static BufferedImage iconoResultadoP;
	public static BufferedImage gameOver;
	public static BufferedImage[] start;

	// moneda casillero
	public static BufferedImage moneda;

	public static BufferedImage[] dado;

	public static BufferedImage[] marioCostado;

	public static BufferedImage[] marioFrente;

	public static BufferedImage[] marioEspalda;

	public static BufferedImage[] marioIzquierda;
	
	public static BufferedImage[] marioDerecha;

	public static BufferedImage[] luigiDerecha;

	public static BufferedImage[] luigiFrente;
	
	public static BufferedImage[] luigiEspalda;
	
	public static BufferedImage[] luigiIzquierda;

	/*
	 * Método que carga en todas las BufferedImages sus correspondientes texturas
	 */
	public static void init() {
		// botones
		HojaSprites hojaBotonTirarDado1 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/botones/boton-tirardado1.png"));
		btnTirarDado1 = new BufferedImage[2];
		btnTirarDado1[0] = hojaBotonTirarDado1.recortar(0, 0, 193, 200);
		btnTirarDado1[1] = hojaBotonTirarDado1.recortar(0, 0, 193, 200);

		HojaSprites hojaBotonTirarDado2 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/botones/boton-tirardado2.png"));
		btnTirarDado2 = new BufferedImage[2];
		btnTirarDado2[0] = hojaBotonTirarDado2.recortar(0, 0, 193, 200);
		btnTirarDado2[1] = hojaBotonTirarDado2.recortar(0, 0, 193, 200);

		HojaSprites hojaBotonTirarDado3 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/botones/boton-tirardado3.png"));
		btnTirarDado3 = new BufferedImage[2];
		btnTirarDado3[0] = hojaBotonTirarDado3.recortar(0, 0, 193, 200);
		btnTirarDado3[1] = hojaBotonTirarDado3.recortar(0, 0, 193, 200);

		HojaSprites hojaBotonTirarDado4 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/botones/boton-tirardado4.png"));
		btnTirarDado4 = new BufferedImage[2];
		btnTirarDado4[0] = hojaBotonTirarDado4.recortar(0, 0, 193, 200);
		btnTirarDado4[1] = hojaBotonTirarDado4.recortar(0, 0, 193, 200);
		// btnTirarDado[0] = hojaBotonTerminarTurno.recortar(0, 0, 115, 32);
		// btnTirarDado[1] = hojaBotonTerminarTurno.recortar(0, 32, 115, 32);
		HojaSprites hojaBotonTerminarTurno = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/botones/boton-terminarturno2.png"));
		btnTerminarTurno = new BufferedImage[2];
		btnTerminarTurno[0] = hojaBotonTerminarTurno.recortar(0, 0, 193, 200);
		btnTerminarTurno[1] = hojaBotonTerminarTurno.recortar(0, 0, 193, 200);
		// btnTerminarTurno[0] = hojaBotonTerminarTurno.recortar(0, 0, 115, 32);
		// btnTerminarTurno[1] = hojaBotonTerminarTurno.recortar(0, 32, 115, 32);

		// jugadores
		HojaSprites hojaJugador1 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/jugadores/jugador1/sprite-tablero.png"));
		jugador_1 = hojaJugador1.recortar(0, 0, width, height);

		HojaSprites hojaJugador2 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/jugadores/jugador2/sprite-tablero.png"));
		jugador_2 = hojaJugador2.recortar(0, 0, width, height);

		HojaSprites hojaJugador3 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/jugadores/jugador3/sprite-tablero.png"));
		jugador_3 = hojaJugador3.recortar(0, 0, width, height);

		HojaSprites hojaJugador4 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/jugadores/jugador4/sprite-tablero.png"));
		jugador_4 = hojaJugador4.recortar(0, 0, width, height);

		// casilleros
		HojaSprites hojaCasilleros = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/sprites-casilleros.png"));
		casillero_moneda_positivo = hojaCasilleros.recortar(0 * width, 0 * height, width, height);
		casillero_moneda_negativo = hojaCasilleros.recortar(1 * width, 0 * height, width, height);
		casillero_estrella = hojaCasilleros.recortar(4 * width, 1 * height, width, height);
		casillero_tienda = hojaCasilleros.recortar(2 * width, 1 * height, width, height);
		casillero_random_malo = hojaCasilleros.recortar(4 * width, 0 * height, width, height);
		casillero_TP = hojaCasilleros.recortar(3 * width, 0 * height, width, height);
		casillero_hurto = hojaCasilleros.recortar(3 * width, 1 * height, width, height);
		casillero_item = hojaCasilleros.recortar(1 * width, 1 * height, width, height);

		// conexiones casilleros
		casillero_conexion_norte_in = hojaCasilleros.recortar(1 * width, 2 * height, width, height);
		casillero_conexion_este_in = hojaCasilleros.recortar(0 * width, 2 * height, width, height);
		casillero_conexion_sur_in = hojaCasilleros.recortar(3 * width, 2 * height, width, height);
		casillero_conexion_oeste_in = hojaCasilleros.recortar(2 * width, 2 * height, width, height);

		casillero_conexion_norte_out = hojaCasilleros.recortar(1 * width, 3 * height, width, height);
		casillero_conexion_este_out = hojaCasilleros.recortar(0 * width, 3 * height, width, height);
		casillero_conexion_sur_out = hojaCasilleros.recortar(3 * width, 3 * height, width, height);
		casillero_conexion_oeste_out = hojaCasilleros.recortar(2 * width, 3 * height, width, height);

		// flechas
		HojaSprites hojaFlechas = new HojaSprites(ImageLoader.cargarImagen("recursos/texturas/sprites-flechas.png"));
		flecha_arriba = new BufferedImage[2];
		flecha_arriba[0] = hojaFlechas.recortar(3 * width, 0 * height, width, height);
		flecha_arriba[1] = hojaFlechas.recortar(3 * width, 0 * height, width, height);
		flecha_abajo = new BufferedImage[2];
		flecha_abajo[0] = hojaFlechas.recortar(1 * width, 0 * height, width, height);
		flecha_abajo[1] = hojaFlechas.recortar(1 * width, 0 * height, width, height);
		flecha_izquierda = new BufferedImage[2];
		flecha_izquierda[0] = hojaFlechas.recortar(2 * width, 0 * height, width, height);
		flecha_izquierda[1] = hojaFlechas.recortar(2 * width, 0 * height, width, height);
		flecha_derecha = new BufferedImage[2];
		flecha_derecha[0] = hojaFlechas.recortar(0 * width, 0 * height, width, height);
		flecha_derecha[1] = hojaFlechas.recortar(0 * width, 0 * height, width, height);

		// botones jugadores

		HojaSprites hojaJugador01 = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturas/jugadores/jugador1/hurto1.png"));
		botonHurto = new BufferedImage[3];
		botonHurto[0] = hojaJugador01.recortar(0, 0, 235, 36);
		botonHurto[1] = hojaJugador01.recortar(0, 0, 235, 36);

		
		//-----------Animaciones---------
		
		HojaSprites hojaDado = new HojaSprites(ImageLoader.cargarImagen("C:\\Users\\Lucas\\Pictures\\dado.png"));
		dado = new BufferedImage[6];
		dado[0] = hojaDado.recortar(0, 0, 60, 57);
		dado[1] = hojaDado.recortar(0, 63, 60, 57);
		dado[2] = hojaDado.recortar(0, 125, 60, 57);
		dado[3] = hojaDado.recortar(0, 187, 60, 57);
		dado[4] = hojaDado.recortar(0, 249, 60, 57);
		dado[5] = hojaDado.recortar(0, 311, 60, 49);
		
		HojaSprites hojaMarioDerecha = new HojaSprites(ImageLoader.cargarImagen("recursos/texturaMinijuego/marios.png"));
		marioDerecha = new BufferedImage[4];
		marioDerecha[0] = hojaMarioDerecha.recortar(0, 0, 15, 33);
		marioDerecha[1] = hojaMarioDerecha.recortar(15, 0, 15, 33);
		marioDerecha[2] = hojaMarioDerecha.recortar(30, 0, 15, 33);
		marioDerecha[3] = hojaMarioDerecha.recortar(45, 0, 15, 33);
		
		HojaSprites hojaMarioIzq = new HojaSprites(ImageLoader.cargarImagen("recursos\\texturas\\marioizq.png"));
		marioIzquierda = new BufferedImage[4];
		marioIzquierda[0] = hojaMarioIzq.recortar(0, 0, 25, 35);
		marioIzquierda[1] = hojaMarioIzq.recortar(28, 0, 25, 35);
		marioIzquierda[2] = hojaMarioIzq.recortar(57, 0, 25, 35);
		marioIzquierda[3] = hojaMarioIzq.recortar(87, 0, 25, 35);
		
		HojaSprites hojaMarioFrenteEspalda = new HojaSprites(ImageLoader.cargarImagen("recursos\\texturas\\mario frente-espalda.png"));
		marioFrente = new BufferedImage[4];
		marioEspalda = new BufferedImage[4];
		marioFrente[0] = hojaMarioFrenteEspalda.recortar(0, 0, 22, 35);
		marioFrente[1] = hojaMarioFrenteEspalda.recortar(27, 0, 22, 35);
		marioFrente[2] = hojaMarioFrenteEspalda.recortar(55, 0, 22, 35);
		marioFrente[3] = hojaMarioFrenteEspalda.recortar(87, 0, 22, 35);
		marioEspalda[0] = hojaMarioFrenteEspalda.recortar(116, 0, 22, 35);
		marioEspalda[1] = hojaMarioFrenteEspalda.recortar(146, 0, 22, 35);
		marioEspalda[2] = hojaMarioFrenteEspalda.recortar(174, 0, 22, 35);
		marioEspalda[3] = hojaMarioFrenteEspalda.recortar(206, 0, 18, 35);
		
		HojaSprites hojaLuigiFrente = new HojaSprites(ImageLoader.cargarImagen("recursos\\texturas\\luigi frente.png"));
		luigiFrente = new BufferedImage[4];
		luigiFrente[0] = hojaLuigiFrente.recortar(0, 0, 30, 42);
		luigiFrente[1] = hojaLuigiFrente.recortar(27, 0, 30, 42);
		luigiFrente[2] = hojaLuigiFrente.recortar(55, 0, 30, 42);
		luigiFrente[3] = hojaLuigiFrente.recortar(82, 0, 30, 42);
		
		HojaSprites hojaLuigiEspalda = new HojaSprites(ImageLoader.cargarImagen("recursos\\texturas\\luigi espalda.png"));
		luigiEspalda = new BufferedImage[4];
		luigiEspalda[0] = hojaLuigiEspalda.recortar(0, 0, 30, 42);
		luigiEspalda[1] = hojaLuigiEspalda.recortar(27, 0, 30, 42);
		luigiEspalda[2] = hojaLuigiEspalda.recortar(55, 0, 30, 42);
		luigiEspalda[3] = hojaLuigiEspalda.recortar(82, 0, 30, 42);

		HojaSprites hojaLuigiIzquierda = new HojaSprites(ImageLoader.cargarImagen("recursos\\texturas\\luigi izq.png"));
		luigiIzquierda = new BufferedImage[4];
		luigiIzquierda[0] = hojaLuigiIzquierda.recortar(0, 0, 15, 35);
		luigiIzquierda[1] = hojaLuigiIzquierda.recortar(15, 0, 16, 35);
		luigiIzquierda[2] = hojaLuigiIzquierda.recortar(31, 0, 15, 35);
		luigiIzquierda[3] = hojaLuigiIzquierda.recortar(46, 0, 15, 35);
		
		HojaSprites hojaLuigiDerecha = new HojaSprites(ImageLoader.cargarImagen("recursos/texturaMinijuego/luigis.png"));
		luigiDerecha = new BufferedImage[4];
		luigiDerecha[0] = hojaLuigiDerecha.recortar(0, 0, 15, 35);
		luigiDerecha[1] = hojaLuigiDerecha.recortar(15, 0, 16, 35);
		luigiDerecha[2] = hojaLuigiDerecha.recortar(31, 0, 15, 35);
		luigiDerecha[3] = hojaLuigiDerecha.recortar(46, 0, 15, 35);
		
		// --------------MINIJUEGO------------------------

		iconoMario = ImageLoader.cargarImagen("recursos/texturaMinijuego/iconoMario.png");
		iconoLuigi = ImageLoader.cargarImagen("recursos/texturaMinijuego/iconoLuigi.png");
		iconoPeach = ImageLoader.cargarImagen("recursos/texturaMinijuego/iconoPeach.png");
		iconoYoshi = ImageLoader.cargarImagen("recursos/texturaMinijuego/iconoYoshi.png");
		HojaSprites hojaNumeros = new HojaSprites(ImageLoader.cargarImagen("recursos/texturaMinijuego/numeros.png"));
		numeros = new BufferedImage[10];
		numeros[0] = hojaNumeros.recortar(0, 0, 10, 15);
		numeros[1] = hojaNumeros.recortar(10, 0, 10, 15);
		numeros[2] = hojaNumeros.recortar(20, 0, 10, 15);
		numeros[3] = hojaNumeros.recortar(30, 0, 10, 15);
		numeros[4] = hojaNumeros.recortar(40, 0, 10, 15);
		numeros[5] = hojaNumeros.recortar(50, 0, 10, 15);
		numeros[6] = hojaNumeros.recortar(60, 0, 10, 15);
		numeros[7] = hojaNumeros.recortar(70, 0, 10, 15);
		numeros[8] = hojaNumeros.recortar(80, 0, 10, 15);
		numeros[9] = hojaNumeros.recortar(90, 0, 10, 15);

		escenario = ImageLoader.cargarImagen("recursos/texturaMinijuego/paisaje.png");
		tubo = ImageLoader.cargarImagen("recursos/texturaMinijuego/tubo32.png");
		suelo = ImageLoader.cargarImagen("recursos/texturaMinijuego/piso.png");
		HojaSprites hojaIconoResul = new HojaSprites(
				ImageLoader.cargarImagen("recursos/texturaMinijuego/iconoResultado.png"));
		iconoResultadoM = hojaIconoResul.recortar(0, 0, 32, 32);
		iconoResultadoL = hojaIconoResul.recortar(32, 0, 32, 32);
		iconoResultadoP = hojaIconoResul.recortar(64, 0, 32, 32);
		iconoResultadoY = hojaIconoResul.recortar(96, 0, 32, 32);
		gameOver = ImageLoader.cargarImagen("recursos/texturaMinijuego/resultado.png");

		HojaSprites hojaStart = new HojaSprites(ImageLoader.cargarImagen("recursos/texturaMinijuego/start.png"));
		start = new BufferedImage[23];
		int dy = 3;
		for (int i = 0; i < start.length - 1; i++) {
			start[i] = hojaStart.recortar(0, i * 15 + dy, 118, 15);
		}
		// -------------------MINIJUEGO----------------------------------------

		moneda = ImageLoader.cargarImagen("recursos/texturas/Carteles/monedaCasillero.png");

	}
}
