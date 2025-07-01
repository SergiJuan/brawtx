package jua.sergi.brawtx.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * BrWindow encapsulates a JFrame with a Canvas for rendering,
 * providing convenient constructors and buffer strategy management.
 */
public class BrWindow extends Canvas {

    private final JFrame frame;
    private String title;

    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;

    /**
     * Creates a window with default title, size 500x500, not resizable.
     */
    public BrWindow() {
        this("Default BrWindow", DEFAULT_WIDTH, DEFAULT_HEIGHT, false);
    }

    /**
     * Creates a window with given title, default size 500x500, not resizable.
     */
    public BrWindow(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT, false);
    }

    /**
     * Creates a window with given title and size, not resizable.
     */
    public BrWindow(String title, int width, int height) {
        this(title, width, height, false);
    }

    /**
     * Creates a window with given title, size and resizable option.
     */
    public BrWindow(String title, int width, int height, boolean resizable) {
        this.title = title;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(resizable);

        setPreferredSize(new Dimension(width, height));
        setIgnoreRepaint(true); // Control manual de repintado
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        createBufferStrategy(3);
        frame.setVisible(true);
    }

    /**
     * Returns the BufferStrategy, creating it if necessary.
     * @return BufferStrategy for this Canvas.
     */
    public BufferStrategy getBufferStrategySafe() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        return bs;
    }

    /**
     * Obtains the Graphics object from the BufferStrategy for drawing.
     * @return Graphics object for rendering.
     */
    public Graphics getGraphicsFromBuffer() {
        return getBufferStrategySafe().getDrawGraphics();
    }

    /**
     * Shows the next available buffer and syncs the display.
     */
    public void showBuffer() {
        BufferStrategy bs = getBufferStrategySafe();
        if (bs != null) {
            bs.show();
            Toolkit.getDefaultToolkit().sync();  // útil para evitar tearing en Linux
        }
    }

    /**
     * Returns the JFrame managed by this BrWindow.
     * @return JFrame instance.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the window title.
     * @return current window title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new window title.
     * @param title title to set.
     */
    public void setTitle(String title) {
        this.title = title;
        frame.setTitle(title);
    }

    /**
     * Gets the preferred size of the window.
     * @return Dimension representing window size.
     */
    public Dimension getWindowSize() {
        return getPreferredSize();
    }

    /**
     * Sets the preferred size of the window and repacks the frame.
     * @param width new width.
     * @param height new height.
     */
    public void setWindowSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        frame.pack();
    }

    /**
     * Returns whether the window is resizable.
     * @return true if resizable, false otherwise.
     */
    public boolean isResizable() {
        return frame.isResizable();
    }

    /**
     * Closes the window and releases resources.
     */
    public void close() {
        frame.dispose();
    }

    /**
     * Centers the window on screen.
     */
    public void centerWindow() {
        frame.setLocationRelativeTo(null);
    }

}
