package jua.sergi.brawtx.inputs;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * BrMouse manages mouse input, tracking button states, cursor position, and wheel movement.
 */
public class BrMouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final Set<Integer> pressedButtons = new HashSet<>();
    private int mouseX = 0;
    private int mouseY = 0;
    private int wheelRotation = 0;

    /**
     * Returns true if the specified mouse button is pressed.
     * @param button Click.*
     */
    public boolean isButtonPressed(int button) {
        return pressedButtons.contains(button);
    }

    /**
     * Returns current mouse X position relative to the component.
     */
    public int getX() {
        return mouseX;
    }

    /**
     * Returns current mouse Y position relative to the component.
     */
    public int getY() {
        return mouseY;
    }

    /**
     * Returns the last wheel rotation amount (negative up, positive down).
     */
    public int getWheelRotation() {
        return wheelRotation;
    }

    /**
     * Resets the wheel rotation to zero.
     * Call this after processing the wheel event.
     */
    public void resetWheelRotation() {
        wheelRotation = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedButtons.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedButtons.remove(e.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheelRotation = e.getWheelRotation();
    }

    // Unused but required by interfaces
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

}