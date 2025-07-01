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

    private int x = 0;

    private final BrImage playerImage;
    private final BrImageRenderer imageRenderer = new BrImageRenderer();
    private final BrImageLoader loader = new BrImageLoader();
    private final BrTextRenderer textRenderer = new BrTextRenderer();

    private final BrAnimation walkAnimation;

    public SimpleRenderer(BrWindow window) {
        super(window);
        playerImage = loader.load("Cow.png");

        walkAnimation = new BrAnimation(playerImage, 32, 32, new int[]{4, 6}, 100);    }

    @Override
    protected void render(Graphics g) {
        long start = System.nanoTime();
        // Fondo
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, window.getWindowSize().width, window.getWindowSize().height);

        // Draw a red rectangle at position x
        g.setColor(Color.RED);
        g.fillRect(x, 50, 100, 50);

        textRenderer.drawText(g, "Título grande centrado", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 120)
                .font("Consolas", Font.BOLD, 32)
                .color(Color.ORANGE));

        textRenderer.drawText(g, "Título abajo", new BrTextOptions(window)
                .position(BrTextAlign.CENTER, 550)
                .font("Consolas", Font.PLAIN, 20)
                .color(Color.RED));

        walkAnimation.setAnimationRow(1);
        walkAnimation.update();
        // Dibujar frame actual de la animación en la posición (x, 100)
        imageRenderer.drawFrame(g, walkAnimation.getCurrentFrame(), 10, 100, 4, walkAnimation.getFrameWidth(), walkAnimation.getFrameHeight());

        // Dibujar la imagen en la posición x,y
        imageRenderer.draw(g, playerImage, 20, 170, 768, 256);
        long end = System.nanoTime();
        System.out.println("Render time: " + ((end - start) / 1_000_000) + " ms");
    }

    public void moveRight() {
        x += 5;
        if (x > window.getWindowSize().width) {
            x = -100; // reset to left outside screen
        }
    }
}