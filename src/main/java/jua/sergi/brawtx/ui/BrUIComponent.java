package jua.sergi.brawtx.ui;

import java.awt.*;

/**
 * BrUIComponent is the abstract base class for all UI components.
 * It defines position, size, visibility, and basic rendering logic.
 */
public abstract class BrUIComponent {

    protected int x, y, width, height;
    protected boolean visible = true;
    protected boolean enabled = true;

    /**
     * Creates a UI component at the given position with the given size.
     */
    public BrUIComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Renders the component. Called every frame if visible.
     * @param g the Graphics2D context to draw with.
     */
    public abstract void render(Graphics2D g);

    /**
     * Updates the component state (animations, logic, etc).
     */
    public void update() {
        // Optional to override
    }

    /**
     * Called when the component is clicked.
     * @param mouseX mouse X coordinate
     * @param mouseY mouse Y coordinate
     */
    public void onClick(int mouseX, int mouseY) {
        // Optional to override
    }

    /**
     * Checks if the mouse is inside the component bounds.
     */
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
