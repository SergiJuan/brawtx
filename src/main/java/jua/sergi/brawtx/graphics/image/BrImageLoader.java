package jua.sergi.brawtx.graphics.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class BrImageLoader {

    /**
     * Load an image from the classpath resources folder.
     * @param path Path relative to resources folder, e.g. "assets/Cow.png"
     * @return BrImage wrapper with the loaded image
     */
    public BrImage load(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            BufferedImage img = ImageIO.read(is);
            return new BrImage(img);
        } catch (Exception e) {
            throw new RuntimeException("Error loading image: " + path, e);
        }
    }
}