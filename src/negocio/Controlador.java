/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vista.Funciones;
import vista.Lista;
import vista.Vista;

/**
 *
 * @author CAMILO
 */
public class Controlador {

    Modelo model1;
    boolean datosCorrectos;
    ArrayList<String>[] listaTransiciones;
    Vista vistaC;
    String funAlfabetoCinta;
    String funAlfabetoPila;
    String simboloFondo;
    String funFinal = "";

    public Controlador() {
        this.datosCorrectos = false;
    }

    public boolean getDatosCorrectos() {
        return datosCorrectos;
    }

    public void setDatosCorrectos(boolean datosCorrectos) {
        this.datosCorrectos = datosCorrectos;
    }

    public ArrayList<String>[] getListaTransiciones() {
        return listaTransiciones;
    }

    public void setListaTransiciones(ArrayList<String>[] listaTransiciones) {
        this.listaTransiciones = listaTransiciones;
    }

    //Metodos de la clase Lista
    public ArrayList<String> agregar(DefaultListModel modeloVista, JTextField jt1, ArrayList<String> array) {
        String funcion = jt1.getText();
        if (fraseCompleta(funFinal, funcion).matches()) {
            jt1.setText("");
            modeloVista.addElement(funcion);
            array.add(funcion);
        } else {
            JOptionPane.showMessageDialog(null, "La funcion de transion, no cumple la extructura"
                    + "\n           Vuelva a intentarlo");
        }

        return array;
    }

    private Matcher fraseCompleta(String buscar, String frase) {
        Pattern busqueda = Pattern.compile(buscar);
        Matcher palabra = busqueda.matcher(frase);
        return palabra;
    }
    
    public String ponerVacio(String cadenaSinVacio){
        cadenaSinVacio = cadenaSinVacio + "Ø";
        return cadenaSinVacio;
    }

    public ArrayList<String> eliminar(DefaultListModel modeloVista, JList<String> jList1, ArrayList<String> array) {
        int fun = jList1.getSelectedIndex();
        modeloVista.remove(fun);
        array.remove(fun);
        return array;
    }

    public void volver(Lista lista, ArrayList<String> array, Funciones funciones, int posicion, JButton boton) {
        if (array.size() != 0) {
            boton.setEnabled(false);
            ArrayList<String>[] arrayCompleto = getListaTransiciones();
            arrayCompleto[posicion] = array;
            setListaTransiciones(arrayCompleto);
            funciones.setBotonesActivos(funciones.getBotonesActivos() - 1);
            funciones.setVisible(true);
            lista.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Debe agregar minimo una funcion de transción", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo de clase Funciones
    public void volverAMenu(Funciones funciones) {
        int contFun = 0;

        if (funciones.getBotonesActivos() == 0) {
            ArrayList<String>[][] arrayModeloAux = new ArrayList[vistaC.getEstados()][vistaC.getEstados()];
            for (int i = 0; i < vistaC.getEstados(); i++) {
                for (int j = 0; j < vistaC.getEstados(); j++) {
                    arrayModeloAux[i][j] = listaTransiciones[contFun];
                    contFun++;
                }
            }
            model1.setFuncionTrans(arrayModeloAux);
            vistaC.setVisible(true);
            funciones.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "No puede volver hasta que escriba todas las funciones de trasción");
        }
    }

    //Metodos de la clase Vista
    public void activacion(JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6) {
        jt2.setEnabled(true);
        jt3.setEnabled(true);
        jt4.setEnabled(true);
        jt5.setEnabled(true);
        jt6.setEnabled(true);
    }

    public void creacionMatriz(JComboBox jComboBox, Vista vista, JPanel jPanel3, JButton jb1, JButton jb2, String[][] matriz) {
        vistaC = vista;
        if (jComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Primero debe elegir algun conjunto de estados", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean invalido = false;
            vista.setEstados(jComboBox.getSelectedIndex() + 1);
            matriz = new String[vista.getEstados()][vista.getEstados()];
            if (vistaC.getPrueba() == true) {
                matriz[0][0] = "1";
                matriz[0][1] = "1";
                matriz[0][2] = "0";
                matriz[1][0] = "0";
                matriz[1][1] = "1";
                matriz[1][2] = "1";
                matriz[2][0] = "0";
                matriz[2][1] = "0";
                matriz[2][2] = "0";
            } else {
                boolean pasable = false;
                String variable = "E";
                String numerosMatriz = "10";

                for (int i = 0; i < vista.getEstados(); i++) {
                    for (int j = 0; j < vista.getEstados(); j++) {
                        matriz[i][j] = JOptionPane.showInputDialog(null, "Digite el valor en la matriz en la posicion[" + i + "][" + j + "]", "Ejemplo: 0 o 1");
                        do {
                            if (matriz[i][j].charAt(0) == numerosMatriz.charAt(0) && matriz[i][j].length() == 1) {
                                pasable = false;
                            } else {
                                if (matriz[i][j].charAt(0) == numerosMatriz.charAt(1) && matriz[i][j].length() == 1) {
                                    pasable = false;
                                } else {
                                    pasable = true;
                                    matriz[i][j] = JOptionPane.showInputDialog(null, "                       El valor introducido genera error\n"
                                            + "Digite de nuevo el valor en la matriz en la posicion[" + i + "][" + j + "]", "Ejemplo: 0 o 1");
                                }
                            }
                        } while (pasable == true);
                        if (matriz[i][j].charAt(0) == variable.charAt(0)) {
                            invalido = true;
                        }
                    }
                }
            }

            if (invalido == true) {
                JOptionPane.showMessageDialog(null, "Falta alguna posicion de la matriz\n          vuelva a intentarlo");
                vaciarMatriz(matriz, vista);
            } else {
                jComboBox.setEnabled(false);
                GridLayout estiloLayout = new GridLayout(vista.getEstados() + 1, vista.getEstados() + 1);
                estiloLayout.setHgap(1);
                estiloLayout.setVgap(1);
                JPanel jPanel4 = new JPanel(estiloLayout);
                jPanel3.add(jPanel4);
                jPanel4.setBounds(62, 70, 360, 90);
                jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                JTextField texto;
                boolean aux = false;

                for (int i = 0; i < vista.getEstados() + 1; i++) {
                    for (int j = 0; j < vista.getEstados() + 1; j++) {
                        texto = new JTextField();
                        texto.setFont(new Font("Arial", 1, 15));
                        texto.setBorder(createLineBorder(new java.awt.Color(0, 0, 0), 2));

                        if (aux == false) {
                            aux = true;
                            texto.setText("Estados");
                            texto.setEditable(false);
                            texto.setBackground(new java.awt.Color(51, 51, 51));
                            texto.setForeground(new java.awt.Color(255, 255, 255));
                            jPanel4.add(texto);
                        } else {
                            if (i == 0 && j > 0) {
                                texto.setText("" + (j - 1));
                                texto.setEditable(false);
                                texto.setBackground(new java.awt.Color(51, 51, 51));
                                texto.setForeground(new java.awt.Color(255, 255, 255));
                                jPanel4.add(texto);
                            } else {
                                if (j == 0 && i > 0) {
                                    texto.setText("" + (i - 1));
                                    texto.setEditable(false);
                                    texto.setBackground(new java.awt.Color(51, 51, 51));
                                    texto.setForeground(new java.awt.Color(255, 255, 255));
                                    jPanel4.add(texto);
                                } else {
                                    texto.setText(matriz[i - 1][j - 1]);
                                    texto.setEditable(false);
                                    jPanel4.add(texto);
                                }
                            }
                        }
                    }
                }
                jb1.setEnabled(false);
                jb2.setEnabled(true);
                vista.setMatriz(matriz);
            }
        }
    }

    public void vaciarMatriz(String[][] matriz, Vista vista) {
        for (int i = 0; i < vista.getEstados(); i++) {
            for (int j = 0; j < vista.getEstados(); j++) {
                matriz[i][j] = null;
            }
        }
    }

    public void validar(JComboBox jComboBox1, JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6, JButton jb2, JButton jb3, String[][] matriz) {
        int cont = 0;
        boolean comprobacion = true;
        boolean comprobar = false;
        String coma = ",";
        int contAux = 0;

        do {
            switch (cont) {
                case 0:
                    comprobacion = false;
                    if (jt2.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "El campo de q0 esta vacio"
                                + "\nEscriba el simbolo deseado para continuar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (jt2.getText().length() != 1) {
                            JOptionPane.showMessageDialog(null, "El numero de simbolos introducidos en q0 excede los esperados"
                                    + "\n                                          vuelva a intentarlo"
                                    + "\n          Los parametros requeridos no pueden ser mayor a 1", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (jt2.getText().charAt(0) - 48 >= 0 && jt2.getText().charAt(0) - 48 <= jComboBox1.getSelectedIndex()) {
                                cont++;
                                comprobacion = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "El estado introducino, no existe"
                                        + "\n           Vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;
                case 1:
                    String estados = "";
                    contAux = 0;

                    for (int i = 0; i < jComboBox1.getSelectedIndex() + 1; i++) {
                        estados = estados + i;
                    }

                    estados = estados + coma;
                    comprobacion = false;
                    if (jt3.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Necesita minimo un estado de aceptacion o final\n"
                                + "         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (jt2.getText().charAt(0) != jt3.getText().charAt(0)) {
                            for (int i = 0; i < jt3.getText().length(); i++) {
                                if (comprobar == false) {
                                    for (int j = 0; j < estados.length() - 1; j++) {
                                        if (estados.charAt(j) == jt3.getText().charAt(i)) {
                                            comprobar = true;
                                            contAux++;
                                            break;
                                        }
                                    }
                                } else {
                                    if (jt3.getText().charAt(i) == estados.charAt(estados.length() - 1)) {
                                        comprobar = false;
                                        contAux++;
                                    }
                                }
                            }

                            if (contAux == jt3.getText().length()) {
                                cont++;
                                comprobacion = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Algun simbolo del conjunto de estados de aceptacion"
                                        + "\n      No concuerda, vuelva a intentarlo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El estado de aceptación no puede ser el mismo que el inicial"
                                    + "\n         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case 2:
                    comprobar = false;
                    comprobacion = false;
                    contAux = 0;
                    String alfabetoCinta = "(";

                    if (jt4.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Necesita minimo un simbolo para el alfabeto de entrada\n"
                                + "         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < jt4.getText().length(); i++) {
                            if (comprobar == false) {
                                comprobar = true;
                                contAux++;
                                alfabetoCinta = alfabetoCinta + jt4.getText().charAt(i);
                            } else {
                                if (jt4.getText().charAt(i) == coma.charAt(0)) {
                                    comprobar = false;
                                    contAux++;
                                    if (i + 1 != jt4.getText().length()) {
                                        alfabetoCinta = alfabetoCinta + "|";
                                    }
                                }
                            }
                        }

                        if (contAux == jt4.getText().length()) {
                            alfabetoCinta = alfabetoCinta + ")";
                            funAlfabetoCinta = alfabetoCinta;
                            comprobacion = true;
                            cont++;
                        } else {
                            JOptionPane.showMessageDialog(null, "Algun simbolo del alfabeto de entrada"
                                    + "\n  No concuerda, vuelva a intentarlo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
                case 3:
                    comprobar = false;
                    comprobacion = false;
                    boolean comp = true;
                    contAux = 0;
                    String alfabetoPila = "(";

                    if (jt5.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Necesita minimo un simbolo para el alfabeto de entrada\n"
                                + "         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < jt5.getText().length(); i++) {
                            if (comprobar == false) {
                                for (int j = 0; j < jt4.getText().length(); j++) {
                                    if (jt4.getText().charAt(j) == jt5.getText().charAt(i)) {
                                        comp = false;
                                    }
                                }
                                if (comp == true) {
                                    comprobar = true;
                                    contAux++;
                                    alfabetoPila = alfabetoPila + jt5.getText().charAt(i);
                                }
                            } else {
                                if (jt4.getText().charAt(i) == coma.charAt(0)) {
                                    comprobar = false;
                                    contAux++;
                                    if (i + 1 != jt4.getText().length()) {
                                        alfabetoPila = alfabetoPila + "|";
                                    }
                                }
                            }
                        }

                        if (contAux == jt5.getText().length()) {
                            alfabetoPila = alfabetoPila + ")";
                            funAlfabetoPila = alfabetoPila;
                            comprobacion = true;
                            cont++;
                        } else {
                            if (comp == false) {
                                JOptionPane.showMessageDialog(null, "Algun simbolo del alfabeto de entrada de la pila"
                                        + "\n  Es igual a uno de Σ y esto no se puede"
                                        + "\n       vuelva a intentarlo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Algun simbolo del alfabeto de entrada de la pila"
                                        + "\n  No concuerda, vuelva a intentarlo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 4:
                    comprobacion = false;
                    contAux = 0;
                    comprobar = false;
                    if (jt6.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "El campo de Z0 esta vacio"
                                + "\nEscriba el simbolo deseado para continuar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (jt6.getText().length() == 1) {
                            for (int i = 0; i < jt4.getText().length(); i++) {
                                if (jt6.getText().charAt(0) == jt4.getText().charAt(i)) {
                                    contAux++;
                                }
                            }
                            //Si no es igual alguno del alfabeto de entrada Σ
                            if (contAux == 0) {
                                for (int i = 0; i < jt5.getText().length(); i++) {
                                    if (jt6.getText().charAt(0) == jt5.getText().charAt(i)) {
                                        contAux++;
                                    }
                                }
                                //Si no es igual alguno del alfabeto de entrada de la pila de Γ
                                if (contAux == 0) {
                                    for (int i = 0; i < jComboBox1.getSelectedIndex(); i++) {
                                        if (jt6.getText().charAt(0) - 48 == i) {
                                            contAux++;
                                        }
                                    }
                                    //Si no es igual a algun estado 
                                    if (contAux == 0) {
                                        comprobacion = true;
                                        simboloFondo = "" + jt6.getText().charAt(0);
                                        cont++;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El simbolo de Zo no puede ser igual a un estado"
                                                + "\n   Escriba el simbolo de Zo de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El simbolo de Zo no puede ser igual al de alguno de Γ"
                                            + "\n   Escriba el simbolo de Zo de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El simbolo de Zo no puede ser igual al de alguno de Σ"
                                        + "\n   Escriba el simbolo de Zo de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El campo de Z0 tiene mas de un simbolo"
                                    + "\nEsto no se puede, dijite de nuevo el simbolo especifico Z0", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
            }
        } while (cont != 5 && comprobacion != false);

        if (comprobacion == true) {
            //Inicio planteamiento de la funcion a analizar en las transiciones
            String aux = "";
            aux = aux + "(((((" + funAlfabetoCinta + "|∈|Ø),)((" + funAlfabetoPila + "|" + simboloFondo + ")))";
            aux = aux + "/)";
            String combinada = "(∈|Ø|" + simboloFondo + "|((" + funAlfabetoPila + ")(" + funAlfabetoPila + "|" + simboloFondo + ")?)";
            aux = aux + combinada + "))";
            funFinal = aux;
            //Final del planteamiento

            model1 = new Modelo(jComboBox1.getSelectedIndex() + 1, jt2.getText(), jt3.getText(), jt4.getText(), jt5.getText(), jt6.getText(), this, matriz);
            setDatosCorrectos(true);
            listaTransiciones = new ArrayList[vistaC.getEstados() * vistaC.getEstados()];
            jb2.setEnabled(false);
            jb3.setEnabled(true);
        }
    }

    public void desactivar(JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6, JTextField jt7, JButton jb3) {
        jt2.setEnabled(false);
        jt3.setEnabled(false);
        jt4.setEnabled(false);
        jt5.setEnabled(false);
        jt6.setEnabled(false);
        jt7.setEnabled(true);
        jb3.setEnabled(true);
    }

    public boolean validarCadena(JTextField jt4, JTextField jt7, JButton jb3) {
        int contAux = 0;
        boolean valido = false;

        if (jt7.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "La cadena esta vacia"
                    + "\nVuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < jt7.getText().length(); i++) {
                for (int j = 0; j < jt4.getText().length(); j++) {
                    if (j % 2 == 0 && jt4.getText().charAt(j) == jt7.getText().charAt(i)) {
                        contAux++;
                    }
                }
            }
            if (contAux == jt7.getText().length()) {
                valido = true;
                jb3.setEnabled(false);
                jt7.setEnabled(false);
                model1.setCadena(jt7.getText());
            } else {
                JOptionPane.showMessageDialog(null, "La cadena tiene un simbolo o varios"
                        + "\nQue no corresponden al alfabeto de entrada(Σ)"
                        + "\n       Vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valido;
    }

    public void pruebaEscritorio() {
        model1.principal();
    }

    public void salir() {
        System.exit(0);
    }

    public void reiniciar(Vista vis) {
        Vista vista = new Vista(false);
        vista.setVisible(true);
        vis.setVisible(false);
    }

    //Metodos de clase modelo
    public void pasar(ArrayList<Pasos> array) {
        vistaC.pasarTabla(array);
    }

}
