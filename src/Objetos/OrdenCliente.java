/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import Files.ArrayToBytes;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author CHAOS
 */
public class OrdenCliente implements Serializable{
    String orden;
    String nombre;
    byte[] file;
    
    public OrdenCliente(){
        
    }
    
    public OrdenCliente(String orden){
        this.orden=orden;
    }
    
    public OrdenCliente(String orden, String nombre){
        this.nombre=nombre;
        this.orden=orden;
    }    
    
    public OrdenCliente(String orden,String nombre, File file) {
        this.orden = orden;
        this.nombre = nombre;
        this.file=ArrayToBytes.getBytes(file.getPath());
    }

    public String getOrden() {
        return orden;
    }  
    
    public String getNombre() {
        return nombre;
    } 

    public byte[] getFile() {
        return file;
    }
}
