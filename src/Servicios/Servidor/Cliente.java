/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios.Servidor;

import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class Cliente {
    private final ArrayList<String> path = new ArrayList();
    private final String host;
    
    public Cliente(String path, String host){
        this.path.add(path);
        this.host=host;
    }
    
    public void añadirCarpeta(String carpeta){
        path.add("\\" + carpeta);
    }
    
    public void quitarCarpeta(){
        path.remove(path.size()-1);
    }
    
    public String getHost(){
        return this.host;
    }
    
    public ArrayList<String> getPath(){
        return this.path;
    }
}
