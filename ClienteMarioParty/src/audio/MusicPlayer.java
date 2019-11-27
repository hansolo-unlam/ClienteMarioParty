package audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicPlayer extends Thread{
	
	private Clip clip;

	private String path;
	
	public MusicPlayer(String path) {
		this.path = path;
	}
	
	private void reproducirSonido(String path) {
		try {
			
			File archivoAudio = new File(path);
			AudioInputStream ais = AudioSystem.getAudioInputStream(archivoAudio);
			AudioFormat formato = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, formato);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-18);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pausar() {
		clip.stop();
	}
	
	public void reiniciar() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	@Override
	public void start() {
		reproducirSonido(this.path);
	}

}
