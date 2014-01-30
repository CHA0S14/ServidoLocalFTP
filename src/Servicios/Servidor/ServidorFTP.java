/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Servidor;

import Objetos.EnviarFile;
import Objetos.FileDatos;
import java.io.File;
import java.net.ServerSocket;

/**
 *
 * @author dam
 */
public class ServidorFTP {

    private static String path = "c:\\users\\CHAOS\\Documents\\Prueba";
    private static String host;
    
    public static void main(String[] args) {
        
        HiloServidor h = new HiloServidor(new ServidorFTP(),5000);
        h.start();
        
    }
    
    public void realizarOrden(String IPRemota, String orden){
        switch (orden) {
            case "inicio":
                darCarpetas(path);                
                break;
            
            case "atras":
                System.out.println("ha pulsado atras");
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
                System.out.println("a decidido moverse a: " + orden);
                break;
        }
    }
    
    private void darCarpetas(String path){
        FileDatos file = new FileDatos(new File(path),"path");

        EnviarFile.Envia(file,host,4999);
        
    }
    
    public void setHost(String host){
        this.host=host;
    }

}
