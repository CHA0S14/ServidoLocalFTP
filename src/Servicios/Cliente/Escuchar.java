/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente;

import Objetos.FileDatos;
import Objetos.OrdenCliente;
import Servicios.Cliente.ClienteFTP;
import Servicios.Servidor.HiloServidor;
import java.io.ByteArrayInputStream;
import java.io.File;
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
public class Escuchar extends Thread{

    private final ClienteFTP cliente;
    private DatagramSocket socket;
    private String orden;

    public Escuchar(ClienteFTP cliente, int puerto) {
        this.cliente = cliente;
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

        try {
            boolean finalizar = false;

            paquete = new DatagramPacket(recibidos, recibidos.length);

            socket.receive(paquete);

            File file = deserializar(paquete);

            if (orden.equals("path")) {
                cliente.pedirPath(file);
            } else {
                System.out.println("en construccion");
            }
           
            cliente.setContinuar(true);

            socket.close();

        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
        } catch (IOException ex) {
            System.out.println("Error en la recepción del paquete");
        }
    }

    public File deserializar(DatagramPacket paquete) {

        ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(paquete.getData()); // bytes es el byte[]
        try {
            ObjectInputStream is = new ObjectInputStream(bytesDelPaquete);

            FileDatos objetoAuxiliar = (FileDatos) is.readObject();
            orden = objetoAuxiliar.getOrden();
            is.close();
            return objetoAuxiliar.getDir();

        } catch (IOException ex) {
            System.out.println("Error al extraer datos del paquete");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al convertir a Objeto");
        }
        return null;
    }

}
