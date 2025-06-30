package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.*;
import jua.sergi.brawtx.utils.BrTimer;

/**
 * Simple engine that moves a rectangle horizontally,
 * printing FPS and delta time to console.
 */
public class SimpleEngine extends BrEngine {

    private final SimpleRenderer simpleRenderer;
    private final BrTimer timer = new BrTimer();

    private int stop = 0;

    /**
     * Constructs the engine with a renderer and starts the timer.
     * @param renderer renderer used for drawing and movement
     */
    public SimpleEngine(SimpleRenderer renderer, int fps) {
        super(renderer, fps);
        this.simpleRenderer = renderer;
        timer.start();
    }

    /**
     * Updates the game logic: moves the rectangle and updates timer.
     */
    @Override
    protected void update() {
        simpleRenderer.moveRight();

        // Update timer each frame to track FPS and delta time
        timer.update();

        // Debug output for FPS and delta time
        System.out.println("FPS: " + timer.getFPS() + " | Delta Time: " + timer.getDeltaTime());

        // Stop the loop when "stop" reaches 200.
        if (this.stop == 200) {
            stop();
        } else {
            this.stop++;
        }
    }
}
