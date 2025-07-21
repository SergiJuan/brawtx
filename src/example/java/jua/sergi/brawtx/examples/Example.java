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
        // Create a window with title and size
        BrWindow window = new BrWindow("Mini Project Test", 800, 600);

        // Create the renderer associated with the window
        SimpleRenderer renderer = new SimpleRenderer(window);

        // Create input handlers for keyboard and mouse
        BrKeyboard keyboard = new BrKeyboard();
        BrMouse mouse = new BrMouse();

        // Register input listeners on the window
        window.addKeyListener(keyboard);
        window.addMouseListener(mouse);
        window.setFocusable(true);
        window.requestFocus();

        // Set window icon from resources
        window.setIconFromResource("icon.png");

        // Create the game engine with renderer, FPS, and input handlers
        SimpleEngine engine = new SimpleEngine(renderer, 120, keyboard, mouse);  // 30 FPS, defaults to 60 if omitted

        BrAudioPlayer bgMusic = null;
        try {
            // Load background music from WAV file
            bgMusic = new BrAudioPlayer("background.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Handle audio loading exceptions
            throw new RuntimeException(e);
        }

        // Play background music in loop
        bgMusic.loop();

        // Set volume (0.7f = 70%)
        bgMusic.setVolume(0.7f);

        // Start the main game loop
        engine.start();
    }
}
