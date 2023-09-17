package Component.Panel;

import javax.swing.*;
import java.awt.*;

public class PanelBorder extends JPanel {

    private Color color;
    private int arc = 10;
    private int stroke = 1;
    private float CustomLength = 5f;
    private float CustomSpacing = 0f;
    private BasicStroke strokeStyle = new BasicStroke(stroke);

    public float getCustomLength() {
        return CustomLength;
    }

    public void setCustomLength(float CustomLength) {
        this.CustomLength = CustomLength;
        repaint();
    }

    public float getCustomSpacing() {
        return CustomSpacing;
    }

    public void setCustomSpacing(float CustomSpacing) {
        this.CustomSpacing = CustomSpacing;
        repaint();
    }

    public PanelBorder() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        this.color = Color.BLACK;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public void setArc(int a) {
        this.arc = a;
        repaint();
    }

    public void setStroke(int s) {
        this.stroke = s;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (stroke > 0) {

            g2d.setStroke(strokeStyle);
            g2d.drawRoundRect(
                    stroke / 2,
                    stroke / 2,
                    getWidth() - stroke,
                    getHeight() - stroke,
                    arc,
                    arc
            );
        }

        g2d.dispose();
    }

    public BasicStroke getStyle() {
        return strokeStyle;
    }

    public void setStyle(STROKE_STYLE ss) {
        switch (ss) {
            case SOLID:
                strokeStyle = new BasicStroke(
                        stroke
                );
                break;
            case DOTTED:
                strokeStyle = new BasicStroke(
                        stroke,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        0f,
                        new float[]{1f, 10f},
                        0f
                );
                break;
            case DASHED:
                strokeStyle = new BasicStroke(
                        stroke,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_ROUND,
                        0f,
                        new float[]{30f, 15f}, // line length, 
                        0f
                );
                break;
            case CUSTOM:
                strokeStyle = new BasicStroke(
                        stroke,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_ROUND,
                        0f,
                        new float[]{getCustomLength(), getCustomSpacing()},
                        0f
                );
                break;
        }
        repaint();
    }

    public enum STROKE_STYLE {
        SOLID,
        DOTTED,
        DASHED,
        
        CUSTOM
    }
}