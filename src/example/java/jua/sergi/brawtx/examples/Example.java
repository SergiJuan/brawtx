package jua.sergi.brawtx.examples;

import jua.sergi.brawtx.core.BrWindow;

public class Example {

    public static void main(String[] args) {
        BrWindow window = new BrWindow("Mini Project Test", 800, 600);

        // Create renderer
        SimpleRenderer renderer = new SimpleRenderer(window);

        // Create engine with renderer and optional FPS parameter (default is 60 if not specified)
        SimpleEngine engine = new SimpleEngine(renderer, 30);  // 30 FPS, can omit to use default 60 FPS

        // Start the loop
        engine.start();
    }
}
