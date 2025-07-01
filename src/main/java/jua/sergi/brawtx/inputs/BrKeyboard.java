package jua.sergi.brawtx.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * BrKeyboard handles keyboard input,
 * tracking which keys are currently pressed.
 */
public class BrKeyboard implements KeyListener {

    private final Set<Integer> pressedKeys = new HashSet<>();

    /**
     * Returns true if the specified key code is currently pressed.
     * @param keyCode Key.*
     */
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    /**
     * Clears the current state of pressed keys.
     */
    public void clear() {
        pressedKeys.clear();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used here
    }
}
