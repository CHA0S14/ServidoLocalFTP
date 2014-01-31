/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Servidor;

import Objetos.EnviarFile;
import Objetos.FileDatos;
import Objetos.OrdenCliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam
 */
public class ServidorFTP {

    private static final ArrayList<String> path = new ArrayList();
    private static String host;

    public static void main(String[] args) {

        path.add("c:\\users\\CHAOS\\Documents\\Prueba");
        HiloServidor h = new HiloServidor(new ServidorFTP(), 5000);
        h.start();

    }

    public void realizarOrden(String IPRemota, OrdenCliente ordenC) {
        String orden = ordenC.getOrden();
        String nombre;
        File file;
        switch (orden) {
            case "inicio":
                darCarpetas();
                break;

            case "atras":
                if (path.size() > 1) {
                    path.remove(path.size() - 1);
                    darCarpetas();
                }
                break;

            case "upload":
                file = ordenC.getFile();
                crearFile(file);
                break;            

            case "download":
                nombre=ordenC.getNombre();
                file = new File(getPath()+"\\"+nombre);
                FileDatos pedido = new FileDatos(file, "download", path);
                EnviarFile.Envia(pedido, host, 4999);
                break;

            case "mkdir":
                nombre = ordenC.getNombre();
                crearFichero(nombre);
                break;

            case "rmdir":
            case "delete":
                nombre = ordenC.getNombre();
                file = new File(getPath() + "\\" + nombre);
                borrarFichero(file);
                break;

            default:
                path.add("\\" + orden);
                darCarpetas();
                break;
        }
    }

    private void darCarpetas() {
        String subPath = getPath();
        FileDatos file = new FileDatos(new File(subPath), "path", path);

        EnviarFile.Envia(file, host, 4999);
    }

    public void setHost(String host) {
        ServidorFTP.host = host;
    }

    private void crearFichero(String nombre) {
        File file = new File(getPath() + "\\" + nombre);
        file.mkdir();
        path.add("\\" + nombre);
        darCarpetas();
    }

    private String getPath() {
        String subPath = "";
        for (int i = 0; i < path.size(); i++) {
            subPath = subPath + path.get(i);
        }
        return subPath;
    }

    private void borrarFichero(File dir) {
        File[] files = dir.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    borrarFichero(f);
                } else {
                    f.delete();
                }
            }
        }
        dir.delete();
        darCarpetas();
    }

    private void crearFile(File original){
        FileInputStream in = null;
        File dest = new File(getPath() + "\\" + original.getName());
        
        
        try { 
            dest.createNewFile();
            in = new FileInputStream(original);
            FileOutputStream out = new FileOutputStream(dest);
            int c;
            while( (c = in.read() ) != -1)
                out.write(c);
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
        
        darCarpetas();
    }
}
