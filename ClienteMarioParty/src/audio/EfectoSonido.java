package audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class EfectoSonido {

	private String path;
	private Clip clip;

	public EfectoSonido(String path) {
		this.path = path;
		
		try {
			File archivoAudio = new File(path);
			AudioInputStream ais = AudioSystem.getAudioInputStream(archivoAudio);
			AudioFormat formato = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, formato);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reproducir() {
		clip.start();
	}
	
	public void resetear() {
		clip.setFramePosition(0);
	}
	
}
