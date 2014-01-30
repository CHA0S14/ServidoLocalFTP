/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente.UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author CHAOS
 */
public class RoundJButton extends JButton {

    public RoundJButton(String label) {
        super(label);

// These statements enlarge the button so that it 
// becomes a circle rather than an oval.
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

// This call causes the JButton not to paint 
        // the background.
// This allows us to paint a round background.
        setContentAreaFilled(false);
    }

// Paint the round background and label.
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(null);
        }
        g.fillOval(0, 0, getSize().width - 2, getSize().height - 2);

// This call will paint the label and the 
        // focus rectangle.
        super.paintComponent(g);
    }

// Paint the border of the button using a simple stroke.
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 2, getSize().height - 2);
    }

// Hit detection.
    Shape shape;

    @Override
    public boolean contains(int x, int y) {
// If the button has changed size, 
        // make a new shape object.
        if (shape == null
                || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0,
                    getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
