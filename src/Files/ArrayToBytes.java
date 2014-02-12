/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dam
 */
public class ArrayToBytes {

    public static byte[] getBytes(String path) {
        FileInputStream fileInputStream = null;
        File file = new File(path);
        byte[] fileArray = new byte[(int) file.length()];

        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileArray);
            fileInputStream.close();
        } catch (Exception e) {
            
        }
        return fileArray;        
    }
    
    public static void getFileServer(byte[] fileArray,String path){        
        try{
         FileOutputStream fileOuputStream = new FileOutputStream(path);
         fileOuputStream.write(fileArray);
         fileOuputStream.close();

         } catch (Exception e) {
         //Manejar Error
         }
    }
    
    public static void getFileCliente(byte[] fileArray){
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(new JFrame("filechooser"));
        String raiz = fc.getSelectedFile().getPath();
        
        String n = (String) JOptionPane.showInputDialog(new JFrame("Nombre"),"Introduzca el nombre del archivo");
        
        try{
         FileOutputStream fileOuputStream = new FileOutputStream(raiz+"\\"+n);
         fileOuputStream.write(fileArray);
         fileOuputStream.close();

         } catch (Exception e) {
         //Manejar Error
         }
    }
}
