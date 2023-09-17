/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Window;

import Component.Panel.PanelBorder;
import Effect.Shadow.ShadowRenderer;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.virus.theline.TheLine;

/**
 *
 * @author virus
 */
public class LineSettings extends javax.swing.JDialog {

    /**
     * Creates new form LineSettings
     */
    public LineSettings() {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("------: " + ex.getMessage());
        }

        initComponents();

        SwingUtilities.updateComponentTreeUI(this);

        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);

        exit.addActionListener(l -> {
            dispose();
            System.exit(0);
        });

        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updatePreviewColor();
            }
        };

        redValue.setValue(255);
        redPreview.setArc(10);
        redSlider.addChangeListener(sliderListener);
        redValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                int value = (int) source.getValue();
                redSlider.setValue(value);

                updatePreviewColor();
            }
        });
        redc.setEffectColor(new Color(255, 255, 255, 34));

        greenValue.setValue(0);
        greenPreview.setArc(10);
        greenSlider.addChangeListener(sliderListener);
        greenValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                int value = (int) source.getValue();
                greenSlider.setValue(value);

                updatePreviewColor();
            }
        });
        greenBorder.setColor(new Color(0, 0, 0));
        greenPreview.setBackground(new Color(0, 0, 0));
        greenc.setBackground(new Color(0, 0, 0));
        greenc.setEffectColor(new Color(255, 255, 255, 34));

        blueValue.setValue(0);
        bluePreview.setArc(10);
        blueSlider.addChangeListener(sliderListener);
        blueValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                int value = (int) source.getValue();
                blueSlider.setValue(value);

                updatePreviewColor();
            }
        });
        blueBorder.setColor(new Color(0, 0, 0));
        bluePreview.setBackground(new Color(0, 0, 0));
        bluec.setBackground(new Color(0, 0, 0));
        bluec.setEffectColor(new Color(255, 255, 255, 34));

        opacitySlider.setValue(255);
        opacitySlider.addChangeListener(sliderListener);

        globalController.setArc(10);
        previewColor.setArc(10);

        previewColor.setBackground(new Color(255, 0, 0));
        previewColor.setToolTipText("Preview");

        spinnerReadonly(redValue);
        spinnerReadonly(greenValue);
        spinnerReadonly(blueValue);

        spinnerReadonly(stroke);
        spinnerReadonly(arc);
        spinnerReadonly(length);
        spinnerReadonly(spacing);

        preview.setArc(0);
        preview.setStyle(PanelBorder.STROKE_STYLE.CUSTOM);
        preview.setColor(new Color(255, 0, 0));

        stroke.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                int value = (int) source.getValue();
                preview.setStroke(value);

                updatePreviewColor();
            }
        });

        arc.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                int value = (int) source.getValue();
                preview.setArc(value);

                updatePreviewColor();
            }
        });

        length.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                float value = (float) source.getValue();
                preview.setCustomLength(value);

                updatePreviewColor();
            }
        });

        spacing.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                float value = (float) source.getValue();
                preview.setCustomSpacing(value);

                updatePreviewColor();
            }
        });

        redc.addActionListener(l -> {
            Color color = JColorChooser.showDialog(this, "Pick red value:", new Color(getRed(), 0, 0));
            if (color != null) {
                int r = color.getRed();

                redSlider.setValue(r);
                updatePreviewColor();
            }
        });

        greenc.addActionListener(l -> {
            Color color = JColorChooser.showDialog(this, "Pick green value:", new Color(0, getGreen(), 0));
            if (color != null) {
                int g = color.getGreen();

                greenSlider.setValue(g);
                updatePreviewColor();
            }
        });

        bluec.addActionListener(l -> {
            Color color = JColorChooser.showDialog(this, "Pick blue value:", new Color(0, 0, getBlue()));
            if (color != null) {
                int b = color.getBlue();

                blueSlider.setValue(b);
                updatePreviewColor();
            }
        });
    }

    private void spinnerReadonly(JSpinner s) {
        JFormattedTextField textField = ((JSpinner.DefaultEditor) s.getEditor()).getTextField();
        textField.setEditable(false);
        textField.setFocusable(false);
        s.setFocusable(false);
    }

    private int getRed() {
        return (int) redSlider.getValue();
    }

    private int getGreen() {
        return (int) greenSlider.getValue();
    }

    private int getBlue() {
        return (int) blueSlider.getValue();
    }

    private int getAlpha() {
        return (int) opacitySlider.getValue();
    }

    private void updatePreviewColor() {
        int r = getRed();
        int g = getGreen();
        int b = getBlue();
        int a = getAlpha();

        Color color = new Color(r, g, b, a);

        redSlider.setValue(r);
        redValue.setValue(r);
        redBorder.setColor(new Color(r, 0, 0));
        redc.setBackground(new Color(r, 0, 0));

        greenSlider.setValue(g);
        greenValue.setValue(g);
        greenBorder.setColor(new Color(0, g, 0));
        greenc.setBackground(new Color(0, g, 0));

        blueSlider.setValue(b);
        blueValue.setValue(b);
        blueBorder.setColor(new Color(0, 0, b));
        bluec.setBackground(new Color(0, 0, b));

        preview.setColor(color);

        previewColor.setBackground(color);

        preview.setStyle(PanelBorder.STROKE_STYLE.CUSTOM);
        previewColor.repaint();
    }

    public void onDone(ActionListener event) {
        done.addActionListener(event);
    }

    public int getStroke() {
        return (int) stroke.getValue();
    }

    public int getArc() {
        return (int) arc.getValue();
    }

    public String getColor() {
        return String.format("#%02x%02x%02x",
                (int) redValue.getValue(),
                (int) greenValue.getValue(),
                (int) blueValue.getValue()
        );
    }

    public float getLength() {
        return (float) length.getValue();
    }

    public float getSpacing() {
        return (float) spacing.getValue();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blueBorder = new Component.Panel.PanelBorder();
        blueValue = new javax.swing.JSpinner();
        blueSlider = new javax.swing.JSlider();
        bluePreview = new Component.Panel.Panel();
        greenBorder = new Component.Panel.PanelBorder();
        greenValue = new javax.swing.JSpinner();
        greenSlider = new javax.swing.JSlider();
        greenPreview = new Component.Panel.Panel();
        redBorder = new Component.Panel.PanelBorder();
        redValue = new javax.swing.JSpinner();
        redSlider = new javax.swing.JSlider();
        redPreview = new Component.Panel.Panel();
        globalController = new Component.Panel.Panel();
        redc = new Button.Button();
        greenc = new Button.Button();
        bluec = new Button.Button();
        previewColor = new Component.Panel.Panel();
        jSeparator4 = new javax.swing.JSeparator();
        stroke = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        arc = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        exit = new Button.Button();
        done = new Button.Button();
        jSeparator5 = new javax.swing.JSeparator();
        preview = new Component.Panel.PanelBorder();
        spacing = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        length = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        blueBorder1 = new Component.Panel.PanelBorder();
        opacitySlider = new javax.swing.JSlider();


        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);

        blueBorder.setForeground(new java.awt.Color(34, 34, 34));
        blueBorder.setColor(null);

        blueValue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        blueSlider.setMajorTickSpacing(1);
        blueSlider.setMaximum(255);
        blueSlider.setMinorTickSpacing(2);
        blueSlider.setSnapToTicks(true);
        blueSlider.setValue(0);

        javax.swing.GroupLayout bluePreviewLayout = new javax.swing.GroupLayout(bluePreview);
        bluePreview.setLayout(bluePreviewLayout);
        bluePreviewLayout.setHorizontalGroup(
            bluePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        bluePreviewLayout.setVerticalGroup(
            bluePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout blueBorderLayout = new javax.swing.GroupLayout(blueBorder);
        blueBorder.setLayout(blueBorderLayout);
        blueBorderLayout.setHorizontalGroup(
            blueBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blueBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(bluePreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueValue, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        blueBorderLayout.setVerticalGroup(
            blueBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blueBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(blueBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bluePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(blueBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(blueValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        greenBorder.setForeground(new java.awt.Color(34, 34, 34));
        greenBorder.setColor(null);

        greenValue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        greenSlider.setMajorTickSpacing(1);
        greenSlider.setMaximum(255);
        greenSlider.setMinorTickSpacing(2);
        greenSlider.setSnapToTicks(true);
        greenSlider.setValue(0);

        javax.swing.GroupLayout greenPreviewLayout = new javax.swing.GroupLayout(greenPreview);
        greenPreview.setLayout(greenPreviewLayout);
        greenPreviewLayout.setHorizontalGroup(
            greenPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        greenPreviewLayout.setVerticalGroup(
            greenPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout greenBorderLayout = new javax.swing.GroupLayout(greenBorder);
        greenBorder.setLayout(greenBorderLayout);
        greenBorderLayout.setHorizontalGroup(
            greenBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(greenBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(greenPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenValue, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        greenBorderLayout.setVerticalGroup(
            greenBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(greenBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(greenBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(greenPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(greenBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(greenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(greenValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        redBorder.setForeground(new java.awt.Color(255, 0, 0));
        redBorder.setColor(null);

        redValue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        redSlider.setMajorTickSpacing(1);
        redSlider.setMaximum(255);
        redSlider.setMinorTickSpacing(2);
        redSlider.setSnapToTicks(true);
        redSlider.setValue(255);

        redPreview.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout redPreviewLayout = new javax.swing.GroupLayout(redPreview);
        redPreview.setLayout(redPreviewLayout);
        redPreviewLayout.setHorizontalGroup(
            redPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        redPreviewLayout.setVerticalGroup(
            redPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout redBorderLayout = new javax.swing.GroupLayout(redBorder);
        redBorder.setLayout(redBorderLayout);
        redBorderLayout.setHorizontalGroup(
            redBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(redBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(redPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redValue, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        redBorderLayout.setVerticalGroup(
            redBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(redBorderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(redBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(redPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(redBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(redValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        redc.setBackground(new java.awt.Color(255, 0, 0));

        greenc.setBackground(new java.awt.Color(134, 134, 134));

        bluec.setBackground(new java.awt.Color(134, 134, 134));

        javax.swing.GroupLayout previewColorLayout = new javax.swing.GroupLayout(previewColor);
        previewColor.setLayout(previewColorLayout);
        previewColorLayout.setHorizontalGroup(
            previewColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );
        previewColorLayout.setVerticalGroup(
            previewColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout globalControllerLayout = new javax.swing.GroupLayout(globalController);
        globalController.setLayout(globalControllerLayout);
        globalControllerLayout.setHorizontalGroup(
            globalControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalControllerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(globalControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, globalControllerLayout.createSequentialGroup()
                        .addComponent(redc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greenc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bluec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(previewColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        globalControllerLayout.setVerticalGroup(
            globalControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalControllerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(globalControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bluec, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        stroke.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        stroke.setFocusable(false);
        stroke.setRequestFocusEnabled(false);

        arc.setModel(new javax.swing.SpinnerNumberModel(0, 0, 25, 1));
        arc.setFocusable(false);
        arc.setRequestFocusEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stroke");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Arc");

        exit.setBackground(new java.awt.Color(216, 82, 60));
        exit.setText("Exit");

        done.setBackground(new java.awt.Color(39, 182, 93));
        done.setText("Done");

        javax.swing.GroupLayout previewLayout = new javax.swing.GroupLayout(preview);
        preview.setLayout(previewLayout);
        previewLayout.setHorizontalGroup(
            previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        previewLayout.setVerticalGroup(
            previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        spacing.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(25.0f), Float.valueOf(1.0f)));
        spacing.setFocusable(false);
        spacing.setRequestFocusEnabled(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Spacing");

        length.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(5.0f), Float.valueOf(1.0f), Float.valueOf(50.0f), Float.valueOf(1.0f)));
        length.setFocusable(false);
        length.setRequestFocusEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Length");

        blueBorder1.setForeground(new java.awt.Color(34, 34, 34));
        blueBorder1.setColor(null);

        opacitySlider.setMajorTickSpacing(1);
        opacitySlider.setMaximum(255);
        opacitySlider.setMinorTickSpacing(2);
        opacitySlider.setSnapToTicks(true);
        opacitySlider.setValue(0);

        javax.swing.GroupLayout blueBorder1Layout = new javax.swing.GroupLayout(blueBorder1);
        blueBorder1.setLayout(blueBorder1Layout);
        blueBorder1Layout.setHorizontalGroup(
            blueBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blueBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(opacitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        blueBorder1Layout.setVerticalGroup(
            blueBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blueBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(opacitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(length)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spacing))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(stroke)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arc))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(globalController, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(redBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blueBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(greenBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(preview, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blueBorder1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stroke, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(redBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(blueBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(globalController, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner arc;
    private Component.Panel.PanelBorder blueBorder;
    private Component.Panel.PanelBorder blueBorder1;
    private Component.Panel.Panel bluePreview;
    private javax.swing.JSlider blueSlider;
    private javax.swing.JSpinner blueValue;
    private Button.Button bluec;
    private Button.Button done;
    private Button.Button exit;
    private Component.Panel.Panel globalController;
    private Component.Panel.PanelBorder greenBorder;
    private Component.Panel.Panel greenPreview;
    private javax.swing.JSlider greenSlider;
    private javax.swing.JSpinner greenValue;
    private Button.Button greenc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSpinner length;
    private javax.swing.JSlider opacitySlider;
    private Component.Panel.PanelBorder preview;
    private Component.Panel.Panel previewColor;
    private Component.Panel.PanelBorder redBorder;
    private Component.Panel.Panel redPreview;
    private javax.swing.JSlider redSlider;
    private javax.swing.JSpinner redValue;
    private Button.Button redc;
    private javax.swing.JSpinner spacing;
    private javax.swing.JSpinner stroke;
    // End of variables declaration//GEN-END:variables
}
