package jua.sergi.brawtx.core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Abstract base class for rendering onto a BrWindow's Canvas using BufferStrategy.
 * Subclasses should implement the render(Graphics) method to draw each frame.
 */
public abstract class BrRenderer {

    protected final BrWindow window;

    /**
     * Constructs a renderer bound to the specified BrWindow.
     * @param window the window to render on
     */
    public BrRenderer(BrWindow window) {
        this.window = window;
    }

    /**
     * Main render method called every frame.
     * Sets up buffer strategy, clears screen, and delegates actual drawing.
     */
    public void renderFrame() {
        BufferStrategy bs = window.getBufferStrategySafe();
        Graphics g = bs.getDrawGraphics();

        try {
            // Clear the screen before drawing
            g.clearRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

            // Delegate actual rendering to subclass
            render(g);
        } finally {
            g.dispose();  // Always dispose graphics context to avoid resource leaks
        }

        // Show the buffer contents
        bs.show();

        // Sync the display on some systems to avoid tearing
        java.awt.Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Abstract method to render content on the given Graphics object.
     * @param g Graphics object used for drawing
     */
    protected abstract void render(Graphics g);
}
