/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Servidor;

import Files.ArrayToBytes;
import Objetos.EnviarFile;
import Objetos.FileDatos;
import Objetos.OrdenCliente;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author dam
 */
public class ServidorFTP {

    private static final ArrayList<Cliente> clientes = new ArrayList();
    private static String raiz;

    public static void main(String[] args) {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(new JFrame("filechooser"));

        raiz = fc.getSelectedFile().getPath();

        HiloServidor h = new HiloServidor(new ServidorFTP(), 5000);
        h.start();

    }

    public void realizarOrden(String IPRemota, OrdenCliente ordenC) {
        String orden = ordenC.getOrden();
        String nombre;
        File file;
        int i = buscarCliente(IPRemota);
        switch (orden) {
            case "inicio":
                clientes.add(new Cliente(raiz, IPRemota));
                darCarpetas(clientes.size() - 1);
                break;

            case "atras":
                if (clientes.get(i).getPath().size() > 1) {
                    clientes.get(i).quitarCarpeta();
                    darCarpetas(i);
                }
                break;

            case "upload":             
                ArrayToBytes.getFileServer(ordenC.getFile(), getPath(i) + "\\" + ordenC.getNombre());
                darCarpetas(i);
                break;

            case "download":
                nombre = ordenC.getNombre();
                byte[] arch=ArrayToBytes.getBytes(getPath(i) + "\\" + nombre);
                
                FileDatos pedido= new FileDatos(arch, "download", clientes.get(i).getPath()); 
                
                EnviarFile.Envia(pedido, clientes.get(i).getHost(), 4999);
                break;

            case "mkdir":
                nombre = ordenC.getNombre();
                crearFichero(nombre, i);
                break;

            case "rmdir":
            case "delete":
                nombre = ordenC.getNombre();
                file = new File(getPath(i) + "\\" + nombre);
                borrarFichero(file, i);
                break;

            default:
                clientes.get(i).añadirCarpeta(orden);
                darCarpetas(i);
                break;
        }
    }

    private void darCarpetas(int cliente) {
        String subPath = getPath(cliente);
        FileDatos file = new FileDatos(new File(subPath), "path", clientes.get(cliente).getPath());

        EnviarFile.Envia(file, clientes.get(cliente).getHost(), 4999);
    }

    private void crearFichero(String nombre, int cliente) {
        File file = new File(getPath(cliente) + "\\" + nombre);
        file.mkdir();
        clientes.get(cliente).añadirCarpeta(nombre);
        darCarpetas(cliente);
    }

    private String getPath(int cliente) {
        String subPath = "";
        for (int i = 0; i < clientes.get(cliente).getPath().size(); i++) {
            subPath = subPath + clientes.get(cliente).getPath().get(i);
        }
        return subPath;
    }

    private void borrarFichero(File dir, int i) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    borrarFichero(f, i);
                } else {
                    f.delete();
                }
            }
        }
        dir.delete();
        darCarpetas(i);
    }

    private int buscarCliente(String host) {
        int numero = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getHost().equals(host)) {
                numero = i;
            }
        }
        return numero;
    }
}
