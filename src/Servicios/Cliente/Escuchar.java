/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Cliente;

import Files.ArrayToBytes;
import Objetos.FileDatos;
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
public class Escuchar extends Thread {

    private final ClienteFTP cliente;
    private static DatagramSocket socket;
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
        byte recibidos[] = new byte[50000];

        try {
            paquete = new DatagramPacket(recibidos, recibidos.length);
            socket.receive(paquete);

            ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(paquete.getData()); // bytes es el byte[]

            ObjectInputStream is = new ObjectInputStream(bytesDelPaquete);

            FileDatos objetoAuxiliar = (FileDatos) is.readObject();

            orden = objetoAuxiliar.getOrden();
            is.close();

            if (orden.equals("path")) {
                File[] file = deserializar2(objetoAuxiliar);
                cliente.pedirPath(file);
            } else {
                deserializar(objetoAuxiliar);
            }

            socket.close();
        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
        } catch (IOException ex) {
            System.out.println("Error en la recepción del paquete");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escuchar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deserializar(FileDatos objetoAuxiliar) {
        cliente.setPath(objetoAuxiliar.getPath());
        ArrayToBytes.getFileCliente(objetoAuxiliar.getDir());
    }

    public File[] deserializar2(FileDatos objetoAuxiliar) {

        cliente.setBooleans(objetoAuxiliar.getBooleans());
        cliente.setPath(objetoAuxiliar.getPath());
        return objetoAuxiliar.getFile();
    }
}
