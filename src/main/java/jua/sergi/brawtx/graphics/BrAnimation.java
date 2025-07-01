package jua.sergi.brawtx.graphics;

import jua.sergi.brawtx.graphics.image.BrImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrAnimation {

    private final BrImage spriteSheet;
    private final int frameWidth;
    private final int frameHeight;
    private final int frameTimeMs; // duration of each frame in milliseconds

    private final int rows;              // number of animation rows
    private final int[] framesPerRow;    // number of frames in each row

    private int currentRow = 0;          // currently selected animation row
    private int currentFrame = 0;

    private long lastUpdateTime = 0;

    public BrAnimation(BrImage spriteSheet, int frameWidth, int frameHeight, int[] framesPerRow, int frameTimeMs) {
        this.spriteSheet = spriteSheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.framesPerRow = framesPerRow;
        this.frameTimeMs = frameTimeMs;
        this.rows = framesPerRow.length;
    }

    /**
     * Sets the active animation row.
     * Resets current frame to 0.
     */
    public void setAnimationRow(int row) {
        if (row < 0 || row >= rows) return; // invalid row
        if (row != currentRow) {
            currentRow = row;
            currentFrame = 0;
            lastUpdateTime = System.currentTimeMillis();
        }
    }

    /**
     * Updates the animation based on elapsed time.
     */
    public void update() {
        long now = System.currentTimeMillis();
        if (now - lastUpdateTime >= frameTimeMs) {
            currentFrame++;
            if (currentFrame >= framesPerRow[currentRow]) {
                currentFrame = 0;
            }
            lastUpdateTime = now;
        }
    }

    /**
     * Returns the currently selected frame from the sprite sheet.
     */
    public Image getCurrentFrame() {
        return getFrame(currentRow, currentFrame);
    }

    /**
     * Returns a specific frame from the given row and column.
     */
    public Image getFrame(int row, int frame) {
        BufferedImage sheet = (BufferedImage) spriteSheet.getRaw();

        int x = frame * frameWidth;
        int y = row * frameHeight;

        return sheet.getSubimage(x, y, frameWidth, frameHeight);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}
