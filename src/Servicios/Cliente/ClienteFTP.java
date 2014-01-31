/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente;

import Objetos.EnviarOrden;
import Objetos.OrdenCliente;
import Servicios.Cliente.UI.Interfaz;
import Servicios.Cliente.UI.Listeners;
import static Servicios.Cliente.UI.Listeners.path;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dam
 */
public class ClienteFTP {

    public void realizarOP(String texto) {
        OrdenCliente orden;
        Escuchar h;
        switch (texto) {
            case "upload":
                System.out.println("ha pulsado subir");
                break;

            case "download":
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "mkdir":
                String n = (String) JOptionPane.showInputDialog(Interfaz.frame, "Nombre de la carpeta");
                Listeners.path.add(n + "/");
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, n);
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "rmdir":
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "atras":
                if (Listeners.path.size() > 1) {
                    path.remove(path.size() - 1);
                    orden = new OrdenCliente(texto);
                    h = new Escuchar(this, 4999);
                    h.start();
                    EnviarOrden.Envia(orden, "localhost", 5000);
                }
                break;

            default:
                orden = new OrdenCliente(texto);
                h = new Escuchar(this, 4999);
                h.start();
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;
        }
    }

    private void does(String texto) {

    }

    public void pedirPath(File dir) {
        ArrayList<String> ficheros = new ArrayList();

        Interfaz.dirAct.setText("Carpeta: \"" + Listeners.getPath() + "\"");

        for (final File fileEntry : dir.listFiles()) {
            if (fileEntry.isDirectory() && !fileEntry.isHidden()) {
                ficheros.add("1" + fileEntry.getName());
            } else if (fileEntry.isFile() && !fileEntry.isHidden()) {
                ficheros.add("2" + fileEntry.getName());
            }
        }
        
        Interfaz.setList(ficheros);
    }
}
