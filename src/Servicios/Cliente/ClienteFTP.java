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

/**
 *
 * @author dam
 */
public class ClienteFTP {

    private static Boolean continuar = false;

    public void realizarOP(String texto) {
        OrdenCliente orden = new OrdenCliente(texto);
        if (texto.equals("inicio")) {
            Escuchar h = new Escuchar(this, 4999);
            h.start();
        }
        EnviarOrden.Envia(orden, "localhost", 5000);

        /*switch (texto) {
         case "atras":
         System.out.println("ha pulsado atras");
         //pedirPath();
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
         }*/
    }

    public void pedirPath(File dir) {
        ArrayList<String> ficheros = new ArrayList();

        for (final File fileEntry : dir.listFiles()) {
            if (fileEntry.isDirectory() && !fileEntry.isHidden()) {
                ficheros.add("1" + fileEntry.getName());
            } else if (fileEntry.isFile() && !fileEntry.isHidden()){
                ficheros.add("2" + fileEntry.getName());
            }
        }

        Interfaz.setList(ficheros);
    }

    public void setContinuar(Boolean continuar) {
        this.continuar = continuar;
    }

}
