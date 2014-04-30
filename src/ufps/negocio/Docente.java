/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;
import ufps.util.Cola;
import ufps.util.*;
/**
 *
 * @author MADARME
 */
public class Docente implements Comparable{
    
    protected int codigo;
    protected String nombre;
    protected String email;

    public Docente() {
    }

    public Docente(int codigo, String nombre, String email) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Docente{" + "codigo=" + codigo + ", nombre=" + nombre + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Docente other = (Docente) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
       Docente x=(Docente)o;
       return(this.codigo-x.codigo);
        
    }
    public void  pruebametodo(){
    
    
    }
    
}