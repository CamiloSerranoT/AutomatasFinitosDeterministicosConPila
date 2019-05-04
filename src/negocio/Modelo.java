/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    public String cadena;
    ArrayList<String>[][] funcionTrans;
    Controlador control;
    String[][] matriz;
    ArrayList<Pasos> paso;

    public Modelo(int estados, String jt2, String jt3, String jt4, String jt5, String jt6, Controlador controlAux, String[][] matrizAux) {
        this.conjuntoEstados = estados;
        this.qInicial = jt2;
        this.f = jt3;
        this.alfabetoEntrada = jt4;
        this.alfabetoEnPila = jt5;
        this.simboloFinalPila = jt6;
        this.control = controlAux;
        this.matriz = matrizAux;
        paso = new ArrayList();
    }

    public ArrayList<String>[][] getFuncionTrans() {
        return funcionTrans;
    }

    public void setFuncionTrans(ArrayList<String>[][] funcionTrans) {
        this.funcionTrans = funcionTrans;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public void principal() {
        int estadoActual = 0;
        ArrayList<String> pilaTemporal = new ArrayList();
        pilaTemporal.add(simboloFinalPila);
        int posicionCadena = 0;
        boolean salir = false;
        Pasos condicionInicial = new Pasos(getCadena(), qInicial, "", simboloFinalPila);
        paso.add(condicionInicial);
        int j = 0;
        boolean comprobar = false;
        boolean com = false;
        int cont = 0;
        String vacio = "e/";
        String cadenaOpc = "";

        do {
            comprobar = true;
            if (posicionCadena == cadena.length()) {
                salir = true;
            } else {
                for (int i = 0; i < conjuntoEstados; i++) {
                    if (matriz[posicionCadena][i].charAt(0) == 1 && posicionCadena != i) {
                        j = i;
                        i = conjuntoEstados + 1;
                    }
                }
                cont = 0;
                do {
                    if (funcionTrans[estadoActual][j].get(cont).charAt(0) == cadena.charAt(posicionCadena)) {
                        if (funcionTrans[estadoActual][j].get(cont).charAt(2) == pilaTemporal.get(pilaTemporal.size() - 1).charAt(0)) {
                            comprobar = true;
                            cadenaOpc = funcionTrans[estadoActual][j].get(cont);
                            estadoActual++;
                            pilaTemporal.remove(pilaTemporal.size() - 1);
                            for (int i = cadenaOpc.length() - 1; i > 0; i--) {
                                if (cadenaOpc.charAt(i) == vacio.charAt(1)) {
                                    i = -1;
                                } else {
                                    String opc = Character.toString(cadenaOpc.charAt(i));
                                    pilaTemporal.add(opc);
                                }
                            }
                            String opcCadena = Character.toString(cadena.charAt(posicionCadena));
                            String opcEs = "" + estadoActual;
                            String opcPila = "|";
                            for (int i = pilaTemporal.size() - 1; i >= 0; i++) {
                                opcPila = opcPila + pilaTemporal.get(i).charAt(0) + "|";
                            }
                            Pasos nuevo = new Pasos(opcCadena, opcEs, cadenaOpc, opcPila);
                            paso.add(nuevo);
                        }
                    }
                    if (funcionTrans[estadoActual][j].get(cont).charAt(0) == vacio.charAt(0)) {
                        if (funcionTrans[estadoActual][j].get(cont).charAt(2) == pilaTemporal.get(pilaTemporal.size() - 1).charAt(0)) {
                            comprobar = true;
                            cadenaOpc = funcionTrans[estadoActual][j].get(cont);
                            estadoActual++;
                            pilaTemporal.remove(pilaTemporal.size() - 1);
                            String opcCadena = Character.toString(cadena.charAt(posicionCadena));
                            String opcEs = "" + estadoActual;
                            String opcPila = "|";
                            for (int i = pilaTemporal.size() - 1; i >= 0; i++) {
                                opcPila = opcPila + pilaTemporal.get(i).charAt(0) + "|";
                            }
                            Pasos nuevo = new Pasos(opcCadena, opcEs, cadenaOpc, opcPila);
                            paso.add(nuevo);
                        }
                    }
                    cont++;
                } while (cont != funcionTrans[estadoActual][j].size() && comprobar != true);

                if (comprobar == false) {
                    cont = 0;
                    do {
                        if (funcionTrans[estadoActual][j].get(cont).charAt(0) == cadena.charAt(posicionCadena)) {
                            if (funcionTrans[estadoActual][j].get(cont).charAt(2) == pilaTemporal.get(pilaTemporal.size() - 1).charAt(0)) {
                                com = true;
                                cadenaOpc = funcionTrans[estadoActual][j].get(cont);
                                pilaTemporal.remove(pilaTemporal.size() - 1);
                                for (int i = cadenaOpc.length() - 1; i > 0; i--) {
                                    if (cadenaOpc.charAt(i) == vacio.charAt(1)) {
                                        i = -1;
                                    } else {
                                        String opc = Character.toString(cadenaOpc.charAt(i));
                                        pilaTemporal.add(opc);
                                    }
                                }
                                String opcCadena = Character.toString(cadena.charAt(posicionCadena));
                                String opcEs = "" + estadoActual;
                                String opcPila = "|";
                                for (int i = pilaTemporal.size() - 1; i >= 0; i++) {
                                    opcPila = opcPila + pilaTemporal.get(i).charAt(0) + "|";
                                }
                                Pasos nuevo = new Pasos(opcCadena, opcEs, cadenaOpc, opcPila);
                                paso.add(nuevo);
                            }
                        }
                        if (funcionTrans[estadoActual][j].get(cont).charAt(0) == vacio.charAt(0)) {
                            if (funcionTrans[estadoActual][j].get(cont).charAt(2) == pilaTemporal.get(pilaTemporal.size() - 1).charAt(0)) {
                                com = true;
                                cadenaOpc = funcionTrans[estadoActual][j].get(cont);
                                pilaTemporal.remove(pilaTemporal.size() - 1);
                                String opcCadena = Character.toString(cadena.charAt(posicionCadena));
                                String opcEs = "" + estadoActual;
                                String opcPila = "|";
                                for (int i = pilaTemporal.size() - 1; i >= 0; i++) {
                                    opcPila = opcPila + pilaTemporal.get(i).charAt(0) + "|";
                                }
                                Pasos nuevo = new Pasos(opcCadena, opcEs, cadenaOpc, opcPila);
                                paso.add(nuevo);
                            }
                        }
                        cont++;
                    } while (cont != funcionTrans[estadoActual][j].size() && com != true);

                    if (com == true) {
                        JOptionPane.showMessageDialog(null, "La cadena establecida, no es adecuada con los parametros puestos"
                                + "", "ERROR", JOptionPane.ERROR_MESSAGE);
                        salir = true;
                    }
                }
            }
        } while (salir == false);
        control.pasar(paso);
    }

}
