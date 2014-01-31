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
import java.util.ArrayList;

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
                System.out.println("ha pulsado subir");
                break;

            case "download":
                System.out.println("ha pulsado bajar");
                break;

            case "mkdir":
                nombre = ordenC.getNombre();
                crearFichero(nombre);
                break;

            case "rmdir":
                nombre = ordenC.getNombre();
                File file = new File(getPath() + "\\" + nombre);
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
        FileDatos file = new FileDatos(new File(subPath), "path");

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

    public void borrarFichero(File dir) {
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

}
