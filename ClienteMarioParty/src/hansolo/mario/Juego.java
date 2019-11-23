package hansolo.mario;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import audio.MusicPlayer;
import states.*;
import hansolo.marioparty.entidades.Jugador;
import hansolo.marioparty.graficos.*;
import hansolo.marioparty.input.MouseManager;
import hansolo.marioparty.tablero.Tablero;
import hansolo.marioparty.input.KeyManager;
import states.MinijuegoState;
import states.TableroState;

public class Juego implements Runnable {
	private Ventana ventana;
	private int width = 1000;
	private int height = 768;

	private boolean ejecutando = false; // boolean que setea en true el método start() y en false el método stop()
	private Thread thread;

	// private List<Jugador> jugadores; // lista de los jugadores que están
	// participando del juego

	// estados
	// private MenuState menuState;
	private TableroState tableroState;
	private MinijuegoState minijuegoState;

	// cuando tengamos mas minijuegos se cargarian en el vector
	// private Minijuego[] minijuegos = new Minijuego[1];

	// input
	private MouseManager mouseManager;
	private KeyManager keyManager;

	private List<Jugador> jugadores = new ArrayList<Jugador>();
	// Gráficos
	private BufferStrategy bs;
	private Graphics g;

	private BufferedImage background;
	private Tablero tablero = new Tablero("./recursos/map0.txt", this);
	
	private MusicPlayer musicPlayer = new MusicPlayer("./recursos/audio/musica background tablero.wav");

	private String id;
	private String user;

	public Juego(ArrayList<String> users, String id, String user) {
		Texturas.init();
		this.id = id;
		this.user = user;
		this.ventana = new Ventana();
		for (int i = 0; i < users.size(); i++) {
			Jugador jugador = new Jugador(i + 1, users.get(i), this);
			jugador.setMainUser(user);
			jugadores.add(jugador);
		}
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
		
	}

	/*
	 * Entry point del objeto. Esto es lo que ejecuta el launcher. Cuando ejecuta
	 * "thread.start()" se manda a ejecutar el metodo run()
	 */
	public synchronized void start() {
		// Si ya está corriendo, salimos
		if (ejecutando)
			return;

		ejecutando = true;
		thread = new Thread(this);
		thread.start();
		musicPlayer.start();
	}

	public synchronized void stop() {
		// Si no está corriendo, salimos
		if (!ejecutando)
			return;

		ejecutando = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * Este método es donde está la salsa.
	 */
	@Override
	public void run() {
		init();

		// A cuantos fps queremos limitar
		int fps = 60;

		// Cuanto tiempo debería llevarme COMO MÁXIMO procesar y dibujar cada tick para
		// cumplir (en nanosegundos)
		double tiempoPorTick = 1000000000 / fps;

		// Valor que uso para ver si ya tengo que hacer un tick (si es menor a 1 todavía
		// falta)
		double delta = 0;

		// el tiempo en nanosegundos al momento en el que está ejecutando el ciclo
		long nanosegundosAhora;

		// el tiempo en nanosegundos de la última vez que ejecutó el ciclo
		// (nanosegundosAhora del ciclo anterior)
		long nanosegundosUltimaVez = System.nanoTime();

		// acá voy acumulando los nanosegundos que pasan entre tick y tick
		long timer = 0;

		// La cantidad de ticks que hice
		int ticks = 0;

		while (ejecutando) {
			nanosegundosAhora = System.nanoTime();
			delta += (nanosegundosAhora - nanosegundosUltimaVez) / tiempoPorTick;
			timer += nanosegundosAhora - nanosegundosUltimaVez;
			nanosegundosUltimaVez = nanosegundosAhora;

			if (delta >= 1) {
				// actualiza variables
				calcular();

				// dibuja el juego
				dibujar();

				ticks++;
				delta--;
			}

			// cada 1 segundo muestro por consola la cantidad de ticks
			if (timer >= 1000000000) {
				System.out.println("fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public String getId() {
		return id;
	}

	private void init() {

		// creo la ventana
		// ventana = new Ventana(title, width, height);
		background = ImageLoader.cargarImagen("recursos/texturas/background/background5.png");
		// agrego los keyListener a la ventana
		ventana.getFrame().addKeyListener(keyManager);

		ventana.getFrame().addMouseListener(mouseManager);
		ventana.getFrame().addMouseMotionListener(mouseManager);
		ventana.getCanvas().addMouseListener(mouseManager);
		ventana.getCanvas().addMouseMotionListener(mouseManager);

		// cargo las texturas
//		Texturas.init();

		// inicializo los estados
		tableroState = new TableroState(this, tablero);
		minijuegoState = new MinijuegoState(this);
		// minijuegos[0] = new JuegoDados(this);

		State.setState(tableroState);
	}

	private void calcular() {

		keyManager.calcular();

		if (State.getState() != null)
			State.getState().calcular();
	}

	// cliente
	public void dibujar() {
		bs = ventana.getCanvas().getBufferStrategy();

		// si es la primera vez que ejecuta y el canvas no tiene un bs, le creo un bs al
		// canvas
		if (bs == null) {
			ventana.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// limpiar pantalla
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		g.drawImage(background, 20, 20, null);

		// mando a dibujar el state (y a partir del state, todo el resto)
		if (State.getState() != null)
			State.getState().dibujar(g);

		// terminó de dibujar
		bs.show();
		g.dispose();

	}

	/*
	 * Método que le permite a un jugador terminar su turno, no hace otra cosa que
	 * ejecutar un handle definido en el JuegoState
	 */
	public void pasarTurno() {
		tableroState.handleTerminoTurno();
	}

	public MusicPlayer getMusicPlayer() {
		return musicPlayer;
	}

	public void iniciarMinijuego() {
		// aca deberiamos seleccionar un minijuego al azar para llamar
		// minijuegos[0].getFrame().setVisible(true);
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
//
//	public void setJugadores(List<Jugador> jugadores) {
//		this.jugadores = jugadores;
//	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}

//	public void premiar(int posiciones[]) {
//		int monedas = 10;
//		for (int i = 0; i < posiciones.length; i++) {
//			this.jugadores.get(posiciones[i]).setMonedas(monedas + this.jugadores.get(posiciones[i]).getMonedas());
//			monedas = monedas % 2 + (monedas / 2);
//		}
//
//	}

	public TableroState getJuegoState() {
		return tableroState;
	}

	public void setJuegoState(TableroState tableroState) {
		this.tableroState = tableroState;
	}

	public void ubicarEstrella(int posicion) {

		tablero.ubicarEstrella(posicion);

	}

	public void informarTurno(int index) {

		tableroState.setTieneTurno(index);
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void informarMovimientos(int cant) {
		tableroState.getTieneTurno().setCantMovimientos(cant);
		tableroState.setSubEstado(EnumEstadoJuego.VIENDO_DADO);
	}

	public void informarAvanzar() {
		tableroState.getTieneTurno().startAvanzar();
		tableroState.setSubEstado(EnumEstadoJuego.MOVIENDOSE);
	}

	public void actualizarMonedas(int monedas) {
		tableroState.getTieneTurno().setMonedas(monedas);
		;
	}

	public void informarDireccion(char direccion) {

		tableroState.getTieneTurno().setDireccion(direccion);
		tableroState.getTieneTurno().setAvanzando(true);

	}

	public void hurto(String robado) {
		int i = 0;
		while (!jugadores.get(i).getUser().equals(robado)) {
			i++;
		}
		if (jugadores.get(i).getMonedas() >= 5) {
			jugadores.get(i).setMonedas(jugadores.get(i).getMonedas() - 5);
			tableroState.getTieneTurno().setMonedas(tableroState.getTieneTurno().getMonedas() + 5);
		} else {
			tableroState.getTieneTurno()
					.setMonedas(tableroState.getTieneTurno().getMonedas() + jugadores.get(i).getMonedas());
			jugadores.get(i).setMonedas(0);
		}
	}

	public void randomMalo(int indice) {
		JFrame frame = new JFrame();
		switch (indice) {
		case 0:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.9));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdio el 10% de sus monedas");
			break;

		case 1:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.8));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdio el 20% de tus monedas");
			break;

		case 2:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.7));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdio el 30% de tus monedas");
			break;

		case 3:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.9));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdio el 10% de tus monedas");
			break;

		case 4:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.8));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdiste el 20% de tus monedas");
			break;

		case 5:
			tableroState.getTieneTurno().setMonedas((int) (tableroState.getTieneTurno().getMonedas() * 0.7));
			JOptionPane.showMessageDialog(frame,
					tableroState.getTieneTurno().getUser() + " perdio el 30% de tus monedas");
			break;

		case 6:
			if (tableroState.getTieneTurno().getEstrellas() > 0) {
				tableroState.getTieneTurno().setEstrellas(tableroState.getTieneTurno().getEstrellas() - 1);
				JOptionPane.showMessageDialog(frame, tableroState.getTieneTurno().getUser() + " perdio una estrella");
			} else
//				JOptionPane.showMessageDialog(frame, "Safaste maestro");
				break;

		case 7:
			tableroState.getTieneTurno().setPierdeTurno(true);
			JOptionPane.showMessageDialog(frame, tableroState.getTieneTurno().getUser() + " perdio un turno");
		}

	}

	public void TP(String moverseHacia) {
		int i = 0;
		while (!jugadores.get(i).getUser().equals(moverseHacia)) {
			i++;
		}
		tableroState.getTieneTurno().setPosicion(jugadores.get(i).getPosicion());
		tableroState.getTieneTurno().setX(jugadores.get(i).getPosicion().getX());
		tableroState.getTieneTurno().setY(jugadores.get(i).getPosicion().getY());

	}

	public void compraEstrella() {
		tableroState.getTieneTurno().setMonedas(tableroState.getTieneTurno().getMonedas()-30);
		tableroState.getTieneTurno().setEstrellas(tableroState.getTieneTurno().getEstrellas()+1);
		
	}

}
