package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import java.awt.Window.Type;

public class MensajeEspera extends JFrame implements Runnable {

	private JPanel contentPane;
	private boolean mensajeRespondido;
	
	private JProgressBar progressBar;
	

	public MensajeEspera() {
		setType(Type.UTILITY);
		
		setTitle("Esperando respuesta del servidor..");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 84);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(5, 5, 424, 40);
		contentPane.add(progressBar);
		
		
	}

	@Override
	public void run() {
		int valorCarga = 0;
	
		while(!mensajeRespondido) {
			valorCarga += 20;
			if(valorCarga > 100)
				valorCarga = 0;
			
			progressBar.setValue(valorCarga);
		}
		
		this.setVisible(false);
		this.dispose();
		
	}
	
	public void stop() {
		mensajeRespondido = true;
	}

}
