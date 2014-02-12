/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CHAOS
 */
public class FileDatos implements Serializable {

    private byte[] dir;
    private File[] file;  
    private boolean[] booleans;
    private String orden;
    private ArrayList<String> path;

    public FileDatos(File dir, String orden, ArrayList<String> path) {        
        this.file=dir.listFiles();  
        booleans=new boolean[file.length];
        int i=0;
        for (final File fileEntry : file) {
           booleans[i]=fileEntry.isDirectory();
            i++;
        }
        this.orden = orden;
        this.path = path;
    }
    
    public FileDatos(byte[] dir, String orden, ArrayList<String> path) {        
        this.dir=dir;
        this.orden = orden;
        this.path = path;
    }

    public FileDatos() {
    }

    public String getOrden() {
        return orden;
    }

    public byte[] getDir() {
        return this.dir;
    }

    public ArrayList<String> getPath() {
        return path;
    }
    
    public File[] getFile() {
        return file;
    }    
    
    public boolean[] getBooleans() {
        return booleans;
    }
}
