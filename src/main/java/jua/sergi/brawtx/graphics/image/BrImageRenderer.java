package jua.sergi.brawtx.graphics.image;

import java.awt.*;

public class BrImageRenderer {

    // Draw at original size
    public void draw(Graphics g, BrImage image, int x, int y) {
        g.drawImage(image.getRaw(), x, y, null);
    }

    // Draw scaled to width and height
    public void draw(Graphics g, BrImage image, int x, int y, int width, int height) {
        g.drawImage(image.getRaw(), x, y, width, height, null);
    }

    // Draw with scale multiplier (e.g. 2.0 = double size)
    public void draw(Graphics g, BrImage image, int x, int y, double scale) {
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        g.drawImage(image.getRaw(), x, y, width, height, null);
    }

    // Draw already cropped animation frame at fixed size
    public void drawFrame(Graphics g, Image frame, int x, int y, int width, int height) {
        g.drawImage(frame, x, y, width, height, null);
    }

    // Draw already cropped animation frame scaled by factor
    public void drawFrame(Graphics g, Image frame, int x, int y, double scale, int originalWidth, int originalHeight) {
        int width = (int) (originalWidth * scale);
        int height = (int) (originalHeight * scale);
        g.drawImage(frame, x, y, width, height, null);
    }
}
