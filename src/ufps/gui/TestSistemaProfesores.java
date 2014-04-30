/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.gui;
import ufps.negocio.*;

/**
 *
 * @author MADARME
 */
public class TestSistemaProfesores {
    public static void main(String nada[])
    {
        SistemaProfesores p=new SistemaProfesores(10);
        
        //System.out.println(p.toString());
        System.out.println(p.getListadoComprobantes());
       
        
    }
    
    
    
}
