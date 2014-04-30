/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufps.util.*;

/**
 *
 * @author MADARME
 */
public class SistemaProfesores {

    private ListaCD<Docente> lista = new ListaCD<Docente>();
    private final String dir = "http://sandbox1.ufps.edu.co/~madarme/web/profesores.txt";
    private Pila<Regalo> regalos = new Pila<>();
    private Cola<DocenteTC> colaTC = new Cola<>();
    private Cola<DocenteCT> colaCT = new Cola<>();
    private ListaCD<ComprobanteRegalo> comprobantes = new ListaCD<>();

    public SistemaProfesores(int cantidadRegalos) {

        ArchivoLeerURL file = new ArchivoLeerURL(dir);
        Object v[] = file.leerArchivo();
        for (Object dato : v) {
            String datos[] = dato.toString().split(";");
            Docente d = null;
            int codigo = Integer.parseInt(datos[0]);
            if (datos[2].equals("TC")) {
                d = new DocenteTC(codigo, datos[1], datos[3]);
            } else {
                d = new DocenteCT(codigo, datos[1], datos[3]);
            }
            this.lista.addFin(d);
        }

        this.crearRegalos(cantidadRegalos);
        this.pasarAcolas();

        Cola<Docente> auxCT = new Cola<Docente>();

        while (!this.colaCT.esVacio()) {

            Docente d = this.colaCT.deColar();
            auxCT.enColar(d);

        }

        Cola<Docente> auxTC = new Cola<Docente>();
        while (!this.colaCT.esVacio()) {

            Docente d = this.colaCT.deColar();
            auxTC.enColar(d);

        }

        this.ordenarCola(auxCT);
        this.ordenarCola(auxTC);

        while (!auxTC.esVacio()) {

            Docente d = auxTC.deColar();
            colaTC.enColar((DocenteTC) d);

        }
        while (!auxCT.esVacio()) {

            Docente d = auxCT.deColar();
            colaCT.enColar((DocenteCT) d);

        }

        this.procesarRegalos();

        try {
            this.enviarCorreos();
        } catch (InterruptedException ex) {
            Logger.getLogger(SistemaProfesores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarCorreos() throws InterruptedException {

        String emailUsuarioEmisor = "ejemplo.email.ufps@gmail.com";//Correo
        String clave = "nfrbdxklxggkgoko";
        ServicioEmail s = new ServicioEmail(emailUsuarioEmisor, clave);

        for (ComprobanteRegalo c : this.comprobantes) {
            Docente d = c.getMyDocente();
            Regalo r = c.getMyRegalo();
            s.enviarEmail(d.getEmail(), "Regalo:" + r.getId(), r.toString());
            Thread.sleep(1000);
        }


    }

    public void procesarRegalos() {
        boolean turno = true;
        while (!this.regalos.esVacio() && (!this.colaTC.esVacio() || !this.colaCT.esVacio())) {

            Regalo r = this.regalos.pop();
            Docente d = null;
            if (turno) {
                if (!this.colaTC.esVacio()) {
                    d = this.colaTC.deColar();
                    this.comprobantes.addFin(new ComprobanteRegalo(r, d));
                } else {
                    this.regalos.push(r);
                }
            } else {
                if (!this.colaCT.esVacio()) {
                    d = this.colaCT.deColar();
                    this.comprobantes.addFin(new ComprobanteRegalo(r, d));
                } else {
                    this.regalos.push(r);
                }
            }
            turno = !turno;

        } 
    }

    private void pasarAcolas() {
        for (Docente d : this.lista) {
            if (d instanceof DocenteTC) {
                this.colaTC.enColar((DocenteTC) d);
            } else {
                this.colaCT.enColar((DocenteCT) d);
            }

        }

    }

    public String getListadoComprobantes() {
        String msg = "";
        for (ComprobanteRegalo d : this.comprobantes) {
            msg += d.toString() + "\n";
        }
        return (msg);
    }

    private void crearRegalos(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            this.regalos.push(new Regalo(i));
        }
    }

    public String toString() {

        String msg = "";
        for (Docente d : this.lista) {
            msg += d.toString() + "\n";
        }
        return (msg);
    }

    public void ordenarCola(Cola<Docente> x) {

        Pila<Docente> aux = new Pila<Docente>();
        Pila<Docente> aux2 = new Pila<Docente>();
        Docente d = null, a = null;

        while (!x.esVacio()) {
            d = x.deColar();

            if (aux.esVacio()) {
                aux.push(d);
            } else {

                while (!aux.esVacio()) {
                    a = aux.pop();
                    if (d.compareTo(a) > 0) {
                        aux.push(a);
                        aux.push(d);
                        break;
                    } else {
                        aux2.push(a);
                    }
                }

                if (aux.esVacio()) {
                    aux.push(d);
                }
            }

            while (!aux2.esVacio()) {
                aux.push(aux2.pop());
            }

        }
        while (!aux.esVacio()) {
            aux2.push(aux.pop());
        }


        while (!aux2.esVacio()) {
            x.enColar(aux2.pop());
        }

    }
}
//        

