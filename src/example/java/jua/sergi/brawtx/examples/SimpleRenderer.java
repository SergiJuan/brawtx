package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.*;

import java.awt.*;

/**
 * Simple concrete renderer that draws a moving rectangle.
 */
public class SimpleRenderer extends BrRenderer {

    private int x = 0;

    public SimpleRenderer(BrWindow window) {
        super(window);
    }

    @Override
    protected void render(Graphics g) {
        // Clear background with dark gray
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

        // Draw a red rectangle at position x
        g.setColor(Color.RED);
        g.fillRect(x, 50, 100, 50);
    }

    public void moveRight() {
        x += 5;
        if (x > window.getWindowSize().width) {
            x = -100; // reset to left outside screen
        }
    }
}
