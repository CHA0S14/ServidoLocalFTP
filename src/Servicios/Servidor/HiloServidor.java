/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Servidor;

import Objetos.OrdenCliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHAOS
 */
public class HiloServidor extends Thread {

    ServidorFTP server;
    DatagramSocket socket;

    public HiloServidor(ServidorFTP server, int puerto) {
        this.server = server;
        try {
            socket = new DatagramSocket(puerto);
        } catch (SocketException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        DatagramPacket paquete;
        byte recibidos[] = new byte[1024];
        String IPRemota;

        try {
            boolean finalizar = false;
            while (!finalizar) {

                paquete = new DatagramPacket(recibidos, recibidos.length);

                socket.receive(paquete);

                //Información que podemos obtener de la cabecera del paquete
                IPRemota = paquete.getAddress().getHostName(); 
                
                server.setHost(IPRemota);
                
                String orden = deserializar(paquete);
                
                server.realizarOrden(IPRemota,orden);
            }

            socket.close();

        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
        } catch (IOException ex) {
            System.out.println("Error en la recepción del paquete");
        }
    }

    public String deserializar(DatagramPacket paquete) {
        
        ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(paquete.getData()); // bytes es el byte[]
        try {
            ObjectInputStream is = new ObjectInputStream(bytesDelPaquete);
            OrdenCliente objetoAuxiliar = (OrdenCliente) is.readObject();
            is.close();
            return objetoAuxiliar.getOrden();
        } catch (IOException ex) {
            System.out.println("Error al extraer datos del paquete");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al convertir a Objeto");
        }
        return null;
    }

}
