package jua.sergi.brawtx.graphics.text;

import java.awt.*;

public class BrTextRenderer {

    public void drawText(Graphics g, String text, BrTextOptions options) {
        g.setFont(options.font);
        g.setColor(options.color);

        FontMetrics fm = g.getFontMetrics();

        int drawX = options.x;
        if (options.x == (options.window.getWindowSize().width / 2)) {
            drawX = options.x - fm.stringWidth(text) / 2;
        }

        int drawY = options.y + fm.getAscent();

        g.drawString(text, drawX, drawY);
    }

}
