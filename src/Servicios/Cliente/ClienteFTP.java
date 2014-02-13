/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente;

import Objetos.EnviarOrden;
import Objetos.OrdenCliente;
import Servicios.Cliente.UI.Interfaz;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dam
 */
public class ClienteFTP {
    
    public ArrayList<String> path;
    public boolean [] booleans;
    
    public void setBooleans(boolean [] u){
        this.booleans=u;
    }

    public void realizarOP(String texto) {
        OrdenCliente orden;
        String serv="10.8.7.3";
        int puertoServ=5000, puertoCli=4999;
        Escuchar h;
        switch (texto) {
            case "upload":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showOpenDialog(Interfaz.frame);
                File file = fc.getSelectedFile();
                h = new Escuchar(this, puertoCli);
                h.start();
                orden = new OrdenCliente(texto,file.getName(), file);
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;

            case "download":
                h = new Escuchar(this, puertoCli);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;

            case "delete":
                h = new Escuchar(this, puertoCli);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;

            case "mkdir":
                String n = (String) JOptionPane.showInputDialog(Interfaz.frame, "Nombre de la carpeta");
                h = new Escuchar(this, puertoCli);
                h.start();
                orden = new OrdenCliente(texto, n);
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;

            case "rmdir":
                h = new Escuchar(this, puertoCli);
                h.start();
                orden = new OrdenCliente(texto, ((String) Interfaz.list.getSelectedValue()).substring(1));
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;

            case "atras":
                if (path.size() > 1) {
                    orden = new OrdenCliente(texto);
                    h = new Escuchar(this, puertoCli);
                    h.start();
                    EnviarOrden.Envia(orden, serv, puertoServ);
                }
                break;

            default:
                orden = new OrdenCliente(texto);
                h = new Escuchar(this, puertoCli);
                h.start();
                EnviarOrden.Envia(orden, serv, puertoServ);
                break;
        }
    }

    public void pedirPath(File[] dir) {
        ArrayList<String> ficheros = new ArrayList();
        
        if(getPath().equals("")){
            Interfaz.dirAct.setText(("Carpeta: \"\\\""));
        }else{
            Interfaz.dirAct.setText("Carpeta: \"" + getPath() + "\"");
        }
        
        int i=0;
        for (final File fileEntry : dir) {
            if (booleans[i]) {
                ficheros.add("1" + fileEntry.getName());                
            } else{
                ficheros.add("2" + fileEntry.getName());
            }
            i++;
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
