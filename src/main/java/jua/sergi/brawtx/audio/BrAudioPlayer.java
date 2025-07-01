package jua.sergi.brawtx.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BrAudioPlayer {

    private Clip clip;
    private FloatControl volumeControl;
    private boolean paused = false;
    private int pauseFramePosition = 0;

    /**
     * Load audio from resource path (inside jar/resources)
     */
    public BrAudioPlayer(String resourcePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL audioSrc = getClass().getClassLoader().getResource(resourcePath);
        if (audioSrc == null) throw new IOException("Audio resource not found: " + resourcePath);

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        audioStream.close();

        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
    }

    /**
     * Plays the clip once from the beginning.
     */
    public void play() {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
        paused = false;
    }

    /**
     * Loops the clip continuously.
     */
    public void loop() {
        if (clip == null) return;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        paused = false;
    }

    /**
     * Stops and closes the clip.
     */
    public void stop() {
        if (clip == null) return;
        clip.stop();
        clip.flush();
        paused = false;
    }

    /**
     * Pauses the clip.
     */
    public void pause() {
        if (clip == null || paused) return;
        pauseFramePosition = clip.getFramePosition();
        clip.stop();
        paused = true;
    }

    /**
     * Resumes playback from pause.
     */
    public void resume() {
        if (clip == null || !paused) return;
        clip.setFramePosition(pauseFramePosition);
        clip.start();
        paused = false;
    }

    /**
     * Sets volume from 0.0 (mute) to 1.0 (max).
     */
    public void setVolume(float volume) {
        if (volumeControl == null) return;
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        // Convert linear 0.0-1.0 to decibel scale
        float gain = (max - min) * volume + min;
        volumeControl.setValue(gain);
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
