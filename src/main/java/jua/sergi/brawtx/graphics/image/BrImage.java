package jua.sergi.brawtx.graphics.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrImage {
    private final Image image;

    public BrImage(Image image) {
        this.image = image;
    }

    public Image getRaw() {
        return image;
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    /**
     * Creates a new BrImage from a sub-rectangle of the current image.
     */
    public BrImage subImage(int x, int y, int width, int height) {
        BufferedImage subImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = subImg.getGraphics();
        g.drawImage(image, 0, 0, width, height, x, y, x + width, y + height, null);
        g.dispose();
        return new BrImage(subImg);
    }
}
