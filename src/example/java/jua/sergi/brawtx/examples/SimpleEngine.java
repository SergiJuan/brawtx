package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.*;
import jua.sergi.brawtx.inputs.BrKeyboard;
import jua.sergi.brawtx.inputs.BrMouse;
import jua.sergi.brawtx.inputs.Click;
import jua.sergi.brawtx.inputs.Key;
import jua.sergi.brawtx.utils.BrTimer;

/**
 * Simple engine that moves a rectangle horizontally,
 * printing FPS and delta time to console.
 */
public class SimpleEngine extends BrEngine {

    private final SimpleRenderer simpleRenderer; // Renderer responsible for drawing and movement
    private final BrKeyboard keyboard;           // Keyboard input handler
    private final BrMouse mouse;                 // Mouse input handler

    /**
     * Constructs the engine with a renderer and starts the timer.
     * @param renderer renderer used for drawing and movement
     * @param fps target frames per second
     * @param keyboard keyboard input instance
     * @param mouse mouse input instance
     */
    public SimpleEngine(SimpleRenderer renderer, int fps, BrKeyboard keyboard, BrMouse mouse) {
        super(renderer, fps);
        this.simpleRenderer = renderer;
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    /**
     * Updates the game logic every frame.
     * Moves the rectangle, checks keyboard and mouse inputs,
     * and prints FPS and delta time to the console.
     */
    @Override
    protected void update() {
        // Move rectangle to the right each update
        simpleRenderer.moveRight();

        // Check if 'A' key is pressed and print a message
        if (keyboard.isKeyPressed(Key.A)) {
            System.out.println("A");
        }

        // Check if left mouse button is clicked and print a message
        if (mouse.isButtonPressed(Click.LEFT_CLICK)) {
            System.out.println("LEFT <-----");
        }

        // Print current FPS and delta time for debugging
        System.out.println("FPS: " + renderer.getTimer().getFPS() + " | Delta Time: " + renderer.getTimer().getDeltaTime());
    }
}
