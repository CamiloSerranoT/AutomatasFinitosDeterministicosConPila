/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author CAMILO
 */
public class Pasos {

    String cadena;
    String estado;
    String funcion;
    String pila;

    public Pasos(String cadena, String estado, String funcion, String pila) {
        this.cadena = cadena;
        this.estado = estado;
        this.funcion = funcion;
        this.pila = pila;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getPila() {
        return pila;
    }

    public void setPila(String pila) {
        this.pila = pila;
    }
}
