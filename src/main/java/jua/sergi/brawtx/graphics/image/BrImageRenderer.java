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

    // Draw image mirrored horizontally at original size
    public void drawMirrorHorizontal(Graphics g, BrImage image, int x, int y) {
        Image img = image.getRaw();
        int w = image.getWidth();
        int h = image.getHeight();
        // Swap x coordinates to flip horizontally
        g.drawImage(img, x + w, y, x, y + h, 0, 0, w, h, null);
    }

    // Draw image mirrored vertically at original size
    public void drawMirrorVertical(Graphics g, BrImage image, int x, int y) {
        Image img = image.getRaw();
        int w = image.getWidth();
        int h = image.getHeight();
        // Swap y coordinates to flip vertically
        g.drawImage(img, x, y + h, x + w, y, 0, 0, w, h, null);
    }

    // Draw image mirrored horizontally with scale
    public void drawMirrorHorizontal(Graphics g, BrImage image, int x, int y, double scale) {
        Image img = image.getRaw();
        int w = (int)(image.getWidth() * scale);
        int h = (int)(image.getHeight() * scale);
        g.drawImage(img, x + w, y, x, y + h, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // Draw image mirrored vertically with scale
    public void drawMirrorVertical(Graphics g, BrImage image, int x, int y, double scale) {
        Image img = image.getRaw();
        int w = (int)(image.getWidth() * scale);
        int h = (int)(image.getHeight() * scale);
        g.drawImage(img, x, y + h, x + w, y, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // Draw frame mirrored horizontally at fixed size
    public void drawFrameMirrorHorizontal(Graphics g, Image frame, int x, int y, int width, int height) {
        g.drawImage(frame, x + width, y, x, y + height, 0, 0, width, height, null);
    }

    // Draw frame mirrored vertically at fixed size
    public void drawFrameMirrorVertical(Graphics g, Image frame, int x, int y, int width, int height) {
        g.drawImage(frame, x, y + height, x + width, y, 0, 0, width, height, null);
    }

    // Draw frame mirrored horizontally with scale
    public void drawFrameMirrorHorizontal(Graphics g, Image frame, int x, int y, double scale, int originalWidth, int originalHeight) {
        int width = (int)(originalWidth * scale);
        int height = (int)(originalHeight * scale);
        g.drawImage(frame, x + width, y, x, y + height, 0, 0, originalWidth, originalHeight, null);
    }

    // Draw frame mirrored vertically with scale
    public void drawFrameMirrorVertical(Graphics g, Image frame, int x, int y, double scale, int originalWidth, int originalHeight) {
        int width = (int)(originalWidth * scale);
        int height = (int)(originalHeight * scale);
        g.drawImage(frame, x, y + height, x + width, y, 0, 0, originalWidth, originalHeight, null);
    }
}
