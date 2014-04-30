/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

/**
 *
 * @author MADARME
 */
public class ComprobanteRegalo {
    private Regalo myRegalo;
    private Docente myDocente;

    public ComprobanteRegalo() {
    }

    public ComprobanteRegalo(Regalo myRegalo, Docente myDocente) {
        this.myRegalo = myRegalo;
        this.myDocente = myDocente;
    }

    public Regalo getMyRegalo() {
        return myRegalo;
    }

    public void setMyRegalo(Regalo myRegalo) {
        this.myRegalo = myRegalo;
    }

    public Docente getMyDocente() {
        return myDocente;
    }

    public void setMyDocente(Docente myDocente) {
        this.myDocente = myDocente;
    }

    @Override
    public String toString() {
        return "ComprobanteRegalo{" + "myRegalo=" + myRegalo + ", myDocente=" + myDocente + '}';
    }
    
    
    
    
}
