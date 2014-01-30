package Objetos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class RecibeUDPObjetos {

    /*static Objeto recibe(int puerto) {
        DatagramSocket socket;
        DatagramPacket paquete = null;
        byte recibidos[] = new byte[1024];
        String IPRemota = "";
        int puertoRemoto;

        try {
            socket = new DatagramSocket(puerto);

            paquete = new DatagramPacket(recibidos, recibidos.length);

            socket.receive(paquete);

            //Información que podemos obtener de la cabecera del paquete
            IPRemota = paquete.getAddress().getHostName();
            puertoRemoto = paquete.getPort();

            socket.close();
        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
        } catch (IOException ex) {
            System.out.println("Error en la recepción del paquete");
        }

        return deserializar(paquete);
    }

    static Objeto deserializar(DatagramPacket paquete) {
        ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(paquete.getData()); // bytes es el byte[]
        try {
            //La clase ObjectInputStream recupera objetos en su forma original
            ObjectInputStream is = new ObjectInputStream(bytesDelPaquete);
            Objeto objetoAuxiliar = (Objeto) is.readObject();
            is.close();
            return objetoAuxiliar;
        } catch (IOException ex) {
            System.out.println("Error al extraer datos del paquete");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al convertir a Objeto");
        }
        return null;
    }*/
}
