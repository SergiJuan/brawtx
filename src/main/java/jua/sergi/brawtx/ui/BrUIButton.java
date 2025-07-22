package jua.sergi.brawtx.ui;

import java.awt.*;

/**
 * BrUIButton is a customizable UI button with text and interactive state.
 */
public class BrUIButton extends BrUIComponent {

    private String text;
    private Runnable onClick;

    // Appearance
    private Font font = new Font("Consolas", Font.BOLD, 16);
    private int padding = 10;
    private int borderRadius = 6;

    private Color backgroundColor = new Color(60, 60, 60);
    private Color hoverColor = new Color(80, 80, 80);
    private Color disabledColor = new Color(100, 100, 100);
    private Color textColor = Color.WHITE;
    private Color borderColor = Color.BLACK;

    private boolean hovered = false;
    private float hoverScale = 1.0f;
    private boolean scaleFromCenter = true;

    public BrUIButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
    }

    @Override
    public void render(Graphics2D g) {
        if (!visible) return;

        float scale = (hovered && enabled) ? hoverScale : 1.0f;

        int scaledWidth = (int) (width * scale);
        int scaledHeight = (int) (height * scale);

        int drawX = x;
        int drawY = y;

        if (scaleFromCenter && scale != 1.0f) {
            drawX -= (scaledWidth - width) / 2;
            drawY -= (scaledHeight - height) / 2;
        }

        // Background
        g.setColor(!enabled ? disabledColor : hovered ? hoverColor : backgroundColor);
        g.fillRoundRect(drawX, drawY, scaledWidth, scaledHeight, borderRadius, borderRadius);

        // Border
        g.setColor(borderColor);
        g.drawRoundRect(drawX, drawY, scaledWidth, scaledHeight, borderRadius, borderRadius);

        // Text
        g.setFont(font);
        g.setColor(textColor);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = drawX + (scaledWidth - textWidth) / 2;
        int textY = drawY + (scaledHeight + textHeight) / 2 - padding / 2;

        g.drawString(text, textX, textY);
    }


    public void handleMouseMove(int mouseX, int mouseY) {
        hovered = contains(mouseX, mouseY);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if (enabled && visible && hovered && onClick != null) {
            onClick.run();
        }
    }

    // Fluent-style setters (withXXX) for chaining

    public BrUIButton withFont(Font font) {
        this.font = font;
        return this;
    }

    public BrUIButton withPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public BrUIButton withBorderRadius(int radius) {
        this.borderRadius = radius;
        return this;
    }

    public BrUIButton withBackgroundColor(Color color) {
        this.backgroundColor = color;
        return this;
    }

    public BrUIButton withHoverColor(Color color) {
        this.hoverColor = color;
        return this;
    }

    public BrUIButton withHoverScale(float scale) {
        this.hoverScale = scale;
        return this;
    }

    public BrUIButton withScaleFromCenter(boolean scaleFromCenter) {
        this.scaleFromCenter = scaleFromCenter;
        return this;
    }

    public BrUIButton withDisabledColor(Color color) {
        this.disabledColor = color;
        return this;
    }

    public BrUIButton withTextColor(Color color) {
        this.textColor = color;
        return this;
    }

    public BrUIButton withBorderColor(Color color) {
        this.borderColor = color;
        return this;
    }

    public BrUIButton onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    // Getters and setters (por si no usas encadenamiento)

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public int getPadding() {
        return padding;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public boolean isHovered() {
        return hovered;
    }
}
