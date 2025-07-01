package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.audio.BrAudioPlayer;
import jua.sergi.brawtx.core.BrWindow;
import jua.sergi.brawtx.inputs.BrKeyboard;
import jua.sergi.brawtx.inputs.BrMouse;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Example {

    public static void main(String[] args) {
        BrWindow window = new BrWindow("Mini Project Test", 800, 600);

        // Create renderer
        SimpleRenderer renderer = new SimpleRenderer(window);

        // Create engine with renderer and optional FPS parameter (default is 60 if not specified)

        BrKeyboard keyboard = new BrKeyboard();
        BrMouse mouse = new BrMouse();
        window.addKeyListener(keyboard);
        window.addMouseListener(mouse);
        window.setFocusable(true);
        window.requestFocus();

        SimpleEngine engine = new SimpleEngine(renderer, 60, keyboard, mouse);  // 30 FPS, can omit to use default 60 FPS

        BrAudioPlayer bgMusic = null;
        try {
            bgMusic = new BrAudioPlayer("background.wav");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        bgMusic.loop();
        bgMusic.setVolume(0.7f); // 50%
        //bgMusic.pause();
        //bgMusic.resume();
        //bgMusic.stop();

        // Start the loop
        engine.start();
    }
}
