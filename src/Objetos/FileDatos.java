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
public class FileDatos implements Serializable{
    private File dir;
    private String orden;
    private ArrayList<String> path;
    
    public FileDatos(File dir, String orden,ArrayList<String> path){
        this.dir=dir;
        this.orden=orden;
        this.path=path;
    }
    
    public FileDatos(){
    }    
    
    public File getDir() {
        return dir;
    }

    public String getOrden() {
        return orden;
    }    

    public ArrayList<String> getPath() {
        return path;
    }
}
