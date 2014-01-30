/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author CHAOS
 */
public class FileDatos implements Serializable{
    private File dir;
    private String orden;
    
    public FileDatos(File dir, String orden){
        this.dir=dir;
        this.orden=orden;
    }
    
    public FileDatos(){
    }    
    
    public File getDir() {
        return dir;
    }

    public String getOrden() {
        return orden;
    }    
}
