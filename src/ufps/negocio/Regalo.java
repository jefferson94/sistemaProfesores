/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

/**
 *
 * @author MADARME
 */
public class Regalo {
    
    
    private int id;
    private final String descripcion="Bono del exito";

    public Regalo() {
    }

    public Regalo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Regalo{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
