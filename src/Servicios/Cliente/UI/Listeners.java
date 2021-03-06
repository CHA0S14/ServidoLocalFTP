/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente.UI;

import Servicios.Cliente.ClienteFTP;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;

/**
 *
 * @author dam
 */
public class Listeners extends MouseAdapter {

    private static ClienteFTP cliente;    

    public Listeners(ClienteFTP cliente) {
        Listeners.cliente = cliente;        
    }

    public Listeners() {
        
    }    

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {

            JButton boton = (JButton) e.getSource();

            cliente.realizarOP(boton.getName());

        } else if (e.getSource() instanceof JList) {

            JList list = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                String valor = (String) list.getSelectedValue();

                if (valor.startsWith("1")) {

                    cliente.realizarOP(valor.substring(1));

                }
            }else{
                botones(Integer.valueOf(((String) list.getSelectedValue()).substring(0,1)));
            }

            Interfaz.fichAct.setText("Fichero actual: " + ((String) list.getSelectedValue()).substring(1));

        }
    }    
    
    public static void botones(int valor){
        switch(valor){
            case 1:
                Interfaz.delD.setEnabled(true);
                Interfaz.bajar.setEnabled(false);
                Interfaz.del.setEnabled(false);
                break;
            case 2:
                Interfaz.delD.setEnabled(false);
                Interfaz.bajar.setEnabled(true);
                Interfaz.del.setEnabled(true);
                break;
            default:
                Interfaz.delD.setEnabled(false);
                Interfaz.bajar.setEnabled(false);
                Interfaz.del.setEnabled(false);
                break;
        }
    }

}
