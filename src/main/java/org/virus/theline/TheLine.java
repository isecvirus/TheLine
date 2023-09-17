package org.virus.theline;

import Component.Panel.PanelBorder;
import Window.LineSettings;
import Window.Window;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author virus
 */
public class TheLine {

    private static Window window;
    private static PanelBorder pb = new PanelBorder();
    private static LineSettings ls = new LineSettings();

    public static void main(String[] args) {
       
        ls.setLocationRelativeTo(null);
        ls.setVisible(true);

        pb.setColor(Color.decode(ls.getColor()));
        pb.setArc(ls.getArc());
        pb.setStroke(ls.getStroke());
        pb.setCustomLength(ls.getLength());
        pb.setCustomSpacing(ls.getSpacing());

        pb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown() && e.isShiftDown()) {
                    pb.setVisible(false);
                    
                    ls.setVisible(true);
                    update();
                }
            }
        });
        
        window = new Window();
        window.maxSize();
        window.setArc(ls.getArc());
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setType(java.awt.Window.Type.UTILITY);
        
        pb.setVisible(false);
        
        window.add(pb);
        window.open();

        ls.onDone(done -> {
            update();
            ls.setVisible(false);
            pb.setVisible(true);
        });
    }

    private static void update() {
        pb.setColor(Color.decode(ls.getColor()));
        pb.setArc(ls.getArc());
        pb.setStroke(ls.getStroke());
        pb.setCustomLength(ls.getLength());
        pb.setCustomSpacing(ls.getSpacing());
        pb.setStyle(PanelBorder.STROKE_STYLE.CUSTOM);
    }
}
