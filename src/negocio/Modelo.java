/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import javax.swing.JTextField;

/**
 *
 * @author CAMILO
 */
public class Modelo {

    public int conjuntoEstados;
    public String qInicial;
    public String f;
    public String alfabetoEntrada;
    public String alfabetoEnPila;
    public String simboloFinalPila;

    public int getConjuntoEstados() {
        return conjuntoEstados;
    }

    public void setConjuntoEstados(int conjuntoEstados) {
        this.conjuntoEstados = conjuntoEstados;
    }

    public String getqInicial() {
        return qInicial;
    }

    public void setqInicial(String qInicial) {
        this.qInicial = qInicial;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getAlfabetoEntrada() {
        return alfabetoEntrada;
    }

    public void setAlfabetoEntrada(String alfabetoEntrada) {
        this.alfabetoEntrada = alfabetoEntrada;
    }

    public String getAlfabetoEnPila() {
        return alfabetoEnPila;
    }

    public void setAlfabetoEnPila(String alfabetoEnPila) {
        this.alfabetoEnPila = alfabetoEnPila;
    }

    public String getSimboloFinalPila() {
        return simboloFinalPila;
    }

    public void setSimboloFinalPila(String simboloFinalPila) {
        this.simboloFinalPila = simboloFinalPila;
    }

    public Modelo(int estados, String jt2, String jt3, String jt4, String jt5, String jt6) {
        this.conjuntoEstados = estados;
        this.qInicial = jt2;
        this.f = jt3;
        this.alfabetoEntrada = jt4;
        this.alfabetoEnPila = jt5;
        this.simboloFinalPila = jt6;
    }

}
