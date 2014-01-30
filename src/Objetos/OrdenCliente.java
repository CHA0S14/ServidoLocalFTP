/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import java.io.Serializable;

/**
 *
 * @author CHAOS
 */
public class OrdenCliente implements Serializable{
    String orden;
    
    public OrdenCliente(){
        
    }
    
    public OrdenCliente(String orden){
        this.orden=orden;
    }

    public String getOrden() {
        return orden;
    }  
}
