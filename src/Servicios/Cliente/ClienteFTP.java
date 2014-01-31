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
import Servicios.Servidor.ServidorFTP;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dam
 */
public class ClienteFTP {
    
    public ArrayList<String> path;

    public void realizarOP(String texto) {
        OrdenCliente orden;
        Escuchar h;
        switch (texto) {
            case "upload":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showOpenDialog(Interfaz.frame);
                File file = fc.getSelectedFile();
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, file);
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "download":
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "delete":
                h = new Escuchar(this, 4999);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, "localhost", 5000);
                break;

            case "mkdir":
                String n = (String) JOptionPane.showInputDialog(Interfaz.frame, "Nombre de la carpeta");
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
                if (path.size() > 1) {
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

    public void crearArchivo(File original) {
        FileInputStream in = null;
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(Interfaz.frame);
        File dest = new File(fc.getSelectedFile().getPath() + "\\" + original.getName());

        try {
            dest.createNewFile();
            in = new FileInputStream(original);
            FileOutputStream out = new FileOutputStream(dest);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServidorFTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorFTP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pedirPath(File dir) {
        ArrayList<String> ficheros = new ArrayList();
        
        if(getPath().equals("")){
            Interfaz.dirAct.setText(("Carpeta: \"\\\""));
        }else{
            Interfaz.dirAct.setText("Carpeta: \"" + getPath() + "\"");
        }
        
        
        for (final File fileEntry : dir.listFiles()) {
            if (fileEntry.isDirectory() && !fileEntry.isHidden()) {
                ficheros.add("1" + fileEntry.getName());
            } else if (fileEntry.isFile() && !fileEntry.isHidden()) {
                ficheros.add("2" + fileEntry.getName());
            }
        }

        Interfaz.setList(ficheros);
    }
    
    public void setPath(ArrayList<String> path){
        this.path = path;
        path.set(0,"\\");
    }
    
    public String getPath() {
        String subPath = "";
        for (int i = 1; i < path.size(); i++) {
            subPath = subPath + path.get(i);
        }
        return subPath;
    }
}
