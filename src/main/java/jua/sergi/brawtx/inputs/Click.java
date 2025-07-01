package jua.sergi.brawtx.inputs;

/**
 * BrMouseButton provides readable names for mouse buttons.
 */
public final class Click {

    public static final int LEFT_CLICK = java.awt.event.MouseEvent.BUTTON1;
    public static final int MIDDLE_CLICK = java.awt.event.MouseEvent.BUTTON2;
    public static final int RIGHT_CLICK = java.awt.event.MouseEvent.BUTTON3;

    // Optional: additional mouse buttons (if supported by hardware)
    public static final int BUTTON4 = 4;
    public static final int BUTTON5 = 5;


    private Click() {
        // No instantiable
    }
}
