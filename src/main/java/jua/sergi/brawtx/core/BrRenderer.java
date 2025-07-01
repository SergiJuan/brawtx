package jua.sergi.brawtx.core;

import jua.sergi.brawtx.utils.BrTimer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class BrRenderer {

    protected final BrWindow window;
    private boolean showDebugFPS;

    private final BrTimer timer = new BrTimer();

    public BrRenderer(BrWindow window) {
        this(window, false);
    }

    public BrRenderer(BrWindow window, boolean showDebugFPS) {
        this.window = window;
        this.showDebugFPS = showDebugFPS;
        timer.start();
    }

    public void renderFrame() {
        BufferStrategy bs = window.getBufferStrategySafe();
        Graphics g = bs.getDrawGraphics();

        try {
            g.clearRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            render(g2d);

            if (showDebugFPS) {
                drawFPSCounter(g2d);
            }
        } finally {
            g.dispose();
        }

        bs.show();
        Toolkit.getDefaultToolkit().sync();

        timer.update();
    }

    private void drawFPSCounter(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(4, 4, 48, 18);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.drawString(timer.getFPS() + " FPS", 8, 17);
    }

    protected abstract void render(Graphics g);

    public void setShowDebugFPS(boolean show) {
        this.showDebugFPS = show;
    }

    public boolean getShowDebugFPS() {
        return showDebugFPS;
    }

    // Puedes agregar acceso al BrTimer si quieres exponerlo:
    public BrTimer getTimer() {
        return timer;
    }
}
