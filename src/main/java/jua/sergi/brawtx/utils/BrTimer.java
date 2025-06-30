package jua.sergi.brawtx.utils;

/**
 * BrTimer helps track frame timing and FPS for loops.
 * It measures time elapsed between frames (delta time) and counts frames per second.
 */
public class BrTimer {

    private long lastTime;
    private long timer;
    private int frames;
    private int fps;
    private double deltaTime;  // seconds elapsed between frames
    private boolean firstUpdate = true;

    /**
     * Starts or resets the timer.
     */
    public void start() {
        lastTime = System.nanoTime();
        timer = System.currentTimeMillis();
        frames = 0;
        fps = 0;
        firstUpdate = true;
    }

    /**
     * Call once per frame to update FPS and delta time.
     */
    public void update() {
        long now = System.nanoTime();
        deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;
        frames++;

        long elapsed = System.currentTimeMillis() - timer;

        if (elapsed >= 1000) {
            fps = frames;
            frames = 0;
            timer += 1000;
            firstUpdate = false;
        } else if (firstUpdate) {
            // Estimate FPS based on deltaTime before first second passes
            if (deltaTime > 0) {
                fps = (int) Math.round(1.0 / deltaTime);
            }
        }
    }

    /**
     * Gets the current FPS value.
     * @return frames per second
     */
    public int getFPS() {
        return fps;
    }

    /**
     * Gets the time elapsed between the last two frames in seconds.
     * Useful for frame-independent movement.
     * @return delta time in seconds
     */
    public double getDeltaTime() {
        return deltaTime;
    }
}
