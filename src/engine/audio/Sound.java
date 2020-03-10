package engine.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class which represents a Sound.
 * @author 6177000
 *
 */
public class Sound {
	
	private Clip clip = null;
	private FloatControl volume;
	
	/**
	 * Given the path, will load the sound file and set the volume.
	 * 
	 * @param path string value
	 */
	public Sound(String path) {
		try {
			InputStream audio = Sound.class.getResourceAsStream(path);
			InputStream buffer = new BufferedInputStream(audio);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(buffer);
			AudioFormat format = audioInputStream.getFormat();
			AudioFormat decodedFormat = new AudioFormat(Encoding.PCM_SIGNED,
					format.getSampleRate(),
					16,
					format.getChannels(),
					format.getChannels() * 2,
					format.getSampleRate(),
					false);
			AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);
			
			clip = AudioSystem.getClip();
			clip.open(decodedAudioInputStream);
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
		} catch (UnsupportedAudioFileException|IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the sound clip.
	 */
	public void play() {
		if(clip == null)
			return;
		
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning())
			clip.start();
	}
	
	/**
	 * Stops the sound clip.
	 */
	public void stop() {
		if(clip.isRunning())
			clip.stop();
	}
	
	/** 
	 * Closes the sound clip.
	 */
	public void close() {
		stop();
		clip.drain();
		clip.close();
	}
	
	/**
	 * Loops the sound clip continuously.
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		play();
	}
	
	/**
	 * Sets the volume of the sound clip.
	 * 
	 * @param volume float value
	 */
	public void setVolume(float volume) {
		this.volume.setValue(volume);
	}
	
	/**
	 * Returns if the sound clip is currently running.
	 * 
	 * @return boolean value
	 */
	public boolean isRunning() {
		return clip.isRunning();
	}
}
