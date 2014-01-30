/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Servidor;

import Objetos.EnviarFile;
import Objetos.FileDatos;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class ServidorFTP {

    private static ArrayList<String> path = new ArrayList();
    private static String host;

    public static void main(String[] args) {

        path.add("c:\\users\\Dam\\Documents\\Prueba");
        HiloServidor h = new HiloServidor(new ServidorFTP(), 5000);
        h.start();

    }

    public void realizarOrden(String IPRemota, String orden) {
        switch (orden) {
            case "inicio":
                darCarpetas();
                break;

            case "atras":
                if(path.size()>1){
                    path.remove(path.size()-1);
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
                System.out.println("ha pulsado mkdir");
                break;

            case "rmdir":
                System.out.println("ha pulsado rmdir");
                break;

            default:
                path.add("\\" + orden);
                darCarpetas();
                break;
        }
    }

    private void darCarpetas() {
        String subPath="";
        for (int i = 0; i < path.size(); i++) {
            subPath = subPath + path.get(i);
        }
        FileDatos file = new FileDatos(new File(subPath), "path");

        EnviarFile.Envia(file, host, 4999);

    }

    public void setHost(String host) {
        this.host = host;
    }

}
