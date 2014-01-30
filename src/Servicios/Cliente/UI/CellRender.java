/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente.UI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

class MyCellRender extends DefaultListCellRenderer {

    private final JLabel label;
    private final Color textSelectionColor = Color.BLACK;
    private final Color backgroundSelectionColor = Color.CYAN;
    private final Color textNonSelectionColor = Color.BLACK;
    private final Color backgroundNonSelectionColor = Color.WHITE;

    MyCellRender() {
        label = new JLabel();
        label.setOpaque(true);        
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean selected,
            boolean expanded) {       
        
        
        String text=value.toString();
        
        label.setText(text.substring(1));
        
        ImageIcon icon;
        
        if(text.startsWith("1")){
            icon=new ImageIcon(getClass().getResource("Imagenes/dir.png"));
        }else{
            icon=new ImageIcon(getClass().getResource("Imagenes/file.png"));
        }
        
        label.setIcon(icon);

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }
}