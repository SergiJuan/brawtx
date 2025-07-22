package jua.sergi.brawtx.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BrUIManager manages and renders all UI components,
 * and handles input dispatching to them.
 */
public class BrUIManager implements MouseListener, MouseMotionListener {

    private final List<BrUIComponent> components = new ArrayList<>();

    /**
     * Adds a component to the manager.
     */
    public void addComponent(BrUIComponent component) {
        components.add(component);
    }

    /**
     * Removes a component from the manager.
     */
    public void removeComponent(BrUIComponent component) {
        components.remove(component);
    }

    /**
     * Clears all components.
     */
    public void clear() {
        components.clear();
    }

    /**
     * Renders all visible components.
     */
    public void render(Graphics2D g) {
        for (BrUIComponent component : components) {
            if (component.isVisible()) {
                component.render(g);
            }
        }
    }

    /**
     * Updates all components.
     */
    public void update() {
        for (BrUIComponent component : components) {
            if (component.isVisible()) {
                component.update();
            }
        }
    }

    /**
     * Must be registered to a component like BrWindow to receive events.
     */
    public void attachTo(Component c) {
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    // Mouse input dispatch

    @Override
    public void mouseMoved(MouseEvent e) {
        for (BrUIComponent component : components) {
            if (component instanceof BrUIButton button) {
                button.handleMouseMove(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (BrUIComponent component : components) {
            if (component.contains(e.getX(), e.getY())) {
                component.onClick(e.getX(), e.getY());
                break; // Sólo el primero que colisiona recibe el evento
            }
        }
    }

    // No usamos por ahora
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
