package jua.sergi.brawtx.graphics.text;

import jua.sergi.brawtx.core.BrWindow;
import jua.sergi.brawtx.graphics.text.align.BrTextAlign;
import jua.sergi.brawtx.graphics.text.align.BrVerticalAlign;

import java.awt.*;

public class BrTextOptions {

    public final BrWindow window;

    public Font font = new Font("Arial", Font.PLAIN, 20);
    public Color color = Color.WHITE;
    public int x = 0;
    public int y = 0;

    public BrTextOptions(BrWindow window) {
        this.window = window;
    }

    // --- Posición flexible ---

    public BrTextOptions position(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public BrTextOptions position(BrTextAlign xAlign, int y) {
        Dimension size = window.getWindowSize();
        this.y = y;

        switch (xAlign) {
            case LEFT -> this.x = 0;
            case CENTER -> this.x = size.width / 2;
            case RIGHT -> this.x = size.width;
        }
        return this;
    }

    public BrTextOptions position(int x, BrVerticalAlign yAlign) {
        Dimension size = window.getWindowSize();
        this.x = x;

        switch (yAlign) {
            case TOP -> this.y = 0;
            case MIDDLE -> this.y = size.height / 2;
            case BOTTOM -> this.y = size.height;
        }
        return this;
    }

    public BrTextOptions position(BrTextAlign xAlign, BrVerticalAlign yAlign) {
        Dimension size = window.getWindowSize();

        switch (xAlign) {
            case LEFT -> this.x = 0;
            case CENTER -> this.x = size.width / 2;
            case RIGHT -> this.x = size.width;
        }

        switch (yAlign) {
            case TOP -> this.y = 0;
            case MIDDLE -> this.y = size.height / 2;
            case BOTTOM -> this.y = size.height;
        }

        return this;
    }

    // --- Fuente y color ---

    public BrTextOptions font(String name, int style, int size) {
        this.font = new Font(name, style, size);
        return this;
    }

    public BrTextOptions color(Color color) {
        this.color = color;
        return this;
    }

    public BrTextOptions color(String hex) {
        this.color = Color.decode(hex);
        return this;
    }
}
