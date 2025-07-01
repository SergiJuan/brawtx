package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.*;
import jua.sergi.brawtx.graphics.*;
import jua.sergi.brawtx.graphics.image.BrImage;
import jua.sergi.brawtx.graphics.image.BrImageLoader;
import jua.sergi.brawtx.graphics.image.BrImageRenderer;
import jua.sergi.brawtx.graphics.text.BrTextOptions;
import jua.sergi.brawtx.graphics.text.BrTextRenderer;
import jua.sergi.brawtx.graphics.text.align.BrTextAlign;

import java.awt.*;

public class SimpleRenderer extends BrRenderer {

    private int x = 0;  // Horizontal position for moving rectangle and animation

    private final BrImage playerImage;  // Loaded image for the player/character
    private final BrImageRenderer imageRenderer = new BrImageRenderer(); // Renderer for images and animation frames
    private final BrImageLoader loader = new BrImageLoader();  // Image loader utility
    private final BrTextRenderer textRenderer = new BrTextRenderer(); // Renderer for drawing text on screen

    private final BrAnimation walkAnimation; // Animation instance for walking frames

    public SimpleRenderer(BrWindow window) {
        super(window, true);
        playerImage = loader.load("Cow.png");  // Load the player image from file

        // Create an animation using frames from the loaded image.
        // Frame size is 32x32 pixels, using frames 4 and 6 from the sprite sheet,
        // with a frame duration of 100 ms
        walkAnimation = new BrAnimation(playerImage, 32, 32, new int[]{4, 6}, 100);
    }

    @Override
    protected void render(Graphics g) {
        long start = System.nanoTime();

        // Fill the background with a dark gray color
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

        // Draw a red rectangle at horizontal position x and fixed vertical position 50
        g.setColor(Color.RED);
        g.fillRect(x, 50, 100, 50);

        // Draw centered orange title text near the top
        textRenderer.drawText(g, "Large Centered Title", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 120)
                .font("Consolas", Font.BOLD, 32)
                .color(Color.ORANGE));

        // Draw centered red subtitle text near the bottom
        textRenderer.drawText(g, "Bottom Centered Title", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 550)
                .font("Consolas", Font.PLAIN, 20)
                .color(Color.RED));

        // Set animation to row 1 (second row) and update animation frame
        walkAnimation.setAnimationRow(1);
        walkAnimation.update();

        // Draw current animation frame at position (10, 100)
        // Scale factor 4x for width and height
        imageRenderer.drawFrame(g, walkAnimation.getCurrentFrame(), 10, 100, 4, walkAnimation.getFrameWidth(), walkAnimation.getFrameHeight());

        // Draw the full player image at position (20, 170), scaled to 768x256 pixels
        imageRenderer.draw(g, playerImage, 20, 170, 768, 256);

        long end = System.nanoTime();
        System.out.println("Render time: " + ((end - start) / 1_000_000) + " ms");
    }

    /**
     * Moves the red rectangle and animation to the right by 5 pixels.
     * If it goes off screen to the right, it resets to the left outside the screen.
     */
    public void moveRight() {
        x += 5;
        if (x > window.getWindowSize().width) {
            x = -100; // Reset position to just outside the left edge
        }
    }
}
