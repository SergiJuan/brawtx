package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.*;
import jua.sergi.brawtx.graphics.*;
import jua.sergi.brawtx.graphics.image.BrImage;
import jua.sergi.brawtx.graphics.image.BrImageLoader;
import jua.sergi.brawtx.graphics.image.BrImageRenderer;
import jua.sergi.brawtx.graphics.text.BrTextOptions;
import jua.sergi.brawtx.graphics.text.BrTextRenderer;
import jua.sergi.brawtx.graphics.text.align.BrTextAlign;
import jua.sergi.brawtx.ui.BrUIButton;
import jua.sergi.brawtx.ui.BrUIManager;

import java.awt.*;

public class SimpleRenderer extends BrRenderer {

    private int x = 0;  // Horizontal position for moving rectangle and animation
    private int xcow = 0;  // Horizontal position for moving cow animation
    private boolean animcowmirror = false;  // Flag to flip cow animation horizontally

    private final BrImage playerImage;  // Loaded image for the player/character
    private final BrImageRenderer imageRenderer = new BrImageRenderer(); // Renderer for images and animation frames
    private final BrImageLoader loader = new BrImageLoader();  // Utility to load images from files
    private final BrTextRenderer textRenderer = new BrTextRenderer(); // Renderer for drawing text on screen

    private final BrAnimation walkAnimation; // Animation instance for walking frames

    private final BrUIManager uiManager = new BrUIManager();

    public SimpleRenderer(BrWindow window) {
        super(window, true);
        playerImage = loader.load("Cow.png");  // Load the player image from file

        // Create a walking animation using frames 4 and 6 from the sprite sheet
        // Each frame is 32x32 pixels, frame duration is 100 milliseconds
        walkAnimation = new BrAnimation(playerImage, 32, 32, new int[]{4, 6}, 100);

        BrUIButton myButton = new BrUIButton(340, 420, 120, 50, "Leave")
                .withFont(new Font("Arial", Font.BOLD, 23))
                .withBackgroundColor(new Color(190, 3, 3))
                .withHoverColor(new Color(219, 47, 47))
                .withHoverScale(1.05f)
                .withBorderRadius(12)
                .withPadding(6)
                .withTextColor(Color.WHITE)
                .onClick(() ->
                        System.exit(0)
                );

        uiManager.addComponent(myButton);
        uiManager.attachTo(window);
    }

    @Override
    protected void render(Graphics g) {
        long start = System.nanoTime();

        // Fill the entire background with dark gray color
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

        // Draw a red rectangle moving horizontally at y=50, size 100x50
        g.setColor(Color.RED);
        g.fillRect(x, 50, 100, 50);

        // Draw centered orange title text near the top (y=120)
        textRenderer.drawText(g, "Large Centered Title", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 120)
                .font("Consolas", Font.BOLD, 32)
                .color(Color.ORANGE));

        // Draw centered red subtitle text near the bottom (y=550)
        textRenderer.drawText(g, "Bottom Centered Title", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 550)
                .font("Consolas", Font.PLAIN, 20)
                .color(Color.RED));

        // Set the animation to the second row of frames (row index 1)
        walkAnimation.setAnimationRow(1);
        // Update the animation frame based on elapsed time
        walkAnimation.update();

        Graphics2D g2d = (Graphics2D) g; // ← Para usar con uiManager
        uiManager.render(g2d);

        // Draw the current frame of the walking animation at position (xcow, 450)
        // Scale by 4 times original size
        if (animcowmirror) {
            // Draw normally (not mirrored)
            imageRenderer.drawFrame(g, walkAnimation.getCurrentFrame(), xcow, 450, 4, walkAnimation.getFrameWidth(), walkAnimation.getFrameHeight());
        } else {
            // Draw mirrored horizontally to simulate walking direction
            imageRenderer.drawFrameMirrorHorizontal(g, walkAnimation.getCurrentFrame(), xcow, 450, 4, walkAnimation.getFrameWidth(), walkAnimation.getFrameHeight());
        }

        // Draw the full player image at fixed position (20, 170) scaled to 768x256 pixels
        imageRenderer.draw(g, playerImage, 20, 170, 768, 256);

        long end = System.nanoTime();
        // Print render time in milliseconds for performance monitoring
        System.out.println("Render time: " + ((end - start) / 1_000_000) + " ms");
    }

    /**
     * Moves the red rectangle and animation to the right by 5 pixels.
     * Resets to the left outside the screen if it goes off the right edge.
     */
    public void moveRight() {
        x += 5;
        if (x > window.getWindowSize().width) {
            x = -100; // Reset position just outside left edge
        }
    }

    private int direction = 1; // Movement direction for cow animation: 1 = right, -1 = left

    /**
     * Moves the cow animation horizontally according to direction.
     * Changes direction and mirrors animation when reaching screen edges.
     */
    public void moveCow() {
        xcow += 5 * direction;

        // Change direction to left if reached right boundary (650)
        if (xcow > 650) {
            direction = -1;
            xcow = 650; // Clamp position to right edge
            animcowmirror = true; // Flip animation to face left
        }
        // Change direction to right if reached left boundary (0)
        if (xcow < 0) {
            direction = 1;
            xcow = 0; // Clamp position to left edge
            animcowmirror = false; // Flip animation to face right
        }
    }
}