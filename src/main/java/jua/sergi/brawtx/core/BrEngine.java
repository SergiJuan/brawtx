package jua.sergi.brawtx.core;

/**
 * BrEngine provides a controllable update-render loop running at a fixed FPS.
 * It supports pause, resume, and frameskip to maintain smooth execution.
 * Update and render calls are delegated and must be implemented by subclasses.
 */
public abstract class BrEngine implements Runnable {

    protected final BrRenderer renderer; // Renderer used for drawing frames
    private Thread thread;                // Thread running the loop
    private volatile boolean running = false;  // Loop running flag
    private volatile boolean paused = false;   // Pause state flag

    private int targetFPS;          // Target frames per second
    private double timePerFrame;    // Nanoseconds per frame

    private static final int DEFAULT_FPS = 60;
    private static final int MAX_FRAMESKIP = 5;

    /**
     * Constructs the engine with a renderer and target FPS.
     * @param renderer Renderer used to draw frames.
     * @param targetFPS Desired frames per second (if <= 0, default 60 FPS is used).
     */
    public BrEngine(BrRenderer renderer, int targetFPS) {
        this.renderer = renderer;
        setTargetFPS(targetFPS);
    }

    /**
     * Constructs the engine with a renderer
     * @param renderer Renderer used to draw frames.
     */
    public BrEngine(BrRenderer renderer) {
        this(renderer, DEFAULT_FPS);  // 60 fps default
    }

    /**
     * Sets the target FPS and updates internal timing.
     * @param fps desired frames per second.
     */
    public final void setTargetFPS(int fps) {
        if (fps <= 0) {
            targetFPS = DEFAULT_FPS;
        } else {
            targetFPS = fps;
        }
        timePerFrame = 1_000_000_000.0 / targetFPS;
    }

    /**
     * Starts the update-render loop in a new thread if not already running.
     */
    public synchronized void start() {
        if (running) return;
        running = true;
        paused = false;
        thread = new Thread(this, "Loop Thread");
        thread.start();
    }

    /**
     * Stops the update-render loop and waits for the thread to finish.
     */
    public synchronized void stop() {
        if (!running) return;
        running = false;
        paused = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Pauses the loop (update and render calls will be skipped).
     */
    public void pause() {
        paused = true;
    }

    /**
     * Resumes the loop if it was paused.
     */
    public void resume() {
        paused = false;
    }

    /**
     * Returns true if the loop is currently paused.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Main update-render loop running at fixed FPS.
     * Supports pause and limits frameskip to avoid spiral of death.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / timePerFrame;
            lastTime = now;

            int loops = 0;
            while (delta >= 1 && loops < MAX_FRAMESKIP) {
                if (!paused) {
                    update();
                    renderer.renderFrame();
                }
                delta--;
                loops++;
            }

            if (paused) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            } else {
                Thread.yield();
            }
        }
    }

    /**
     * Abstract method for updating logic.
     * Must be implemented by subclasses.
     */
    protected abstract void update();
}
