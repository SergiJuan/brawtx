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

    private final SimpleRenderer simpleRenderer;
    private final BrKeyboard keyboard;
    private final BrMouse mouse;
    private final BrTimer timer = new BrTimer();

    /**
     * Constructs the engine with a renderer and starts the timer.
     * @param renderer renderer used for drawing and movement
     */
    public SimpleEngine(SimpleRenderer renderer, int fps, BrKeyboard keyboard, BrMouse mouse) {
        super(renderer, fps);
        this.simpleRenderer = renderer;
        this.keyboard = keyboard;
        this.mouse = mouse;
        timer.start();
    }

    /**
     * Updates the game logic: moves the rectangle and updates timer.
     */
    @Override
    protected void update() {
        simpleRenderer.moveRight();

        if (keyboard.isKeyPressed(Key.A)) {
            System.out.println("AAAAAAAA");
        }

        if (mouse.isButtonPressed(Click.LEFT_CLICK)) {
            System.out.println("LEEEEEEFT <-----");
        }
        // Update timer each frame to track FPS and delta time
        timer.update();

        // Debug output for FPS and delta time
        System.out.println("FPS: " + timer.getFPS() + " | Delta Time: " + timer.getDeltaTime());
    }
}
