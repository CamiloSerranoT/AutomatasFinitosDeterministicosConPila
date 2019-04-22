/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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
    ArrayList<String>[][] listaTransiciones;
    Vista vistaC;
    Funciones funci;

    public Controlador() {
        this.datosCorrectos = false;
    }

    public boolean getDatosCorrectos() {
        return datosCorrectos;
    }

    public void setDatosCorrectos(boolean datosCorrectos) {
        this.datosCorrectos = datosCorrectos;
    }

    public void agregar(DefaultListModel modeloVista, JTextField jt1) {
        String funcion = jt1.getText();
        modeloVista.addElement(funcion);
        JOptionPane.showMessageDialog(null, modeloVista);
    }

    public void eliminar(DefaultListModel modeloVista, JList<String> jList1) {
        int fun = jList1.getSelectedIndex();
        modeloVista.remove(fun);
    }

    public void volver(Lista lista, DefaultListModel modeloVis, Vista vista) {
        vista.setVisible(true);
        lista.setVisible(false);
    }

    public void creacionMatriz(JComboBox jComboBox, Vista vista, JPanel jPanel3, JButton jb1, JButton jb2, String[][] matriz) {
        vistaC = vista;
        if (jComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Primero debe elegir algun conjunto de estados", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            vista.setEstados(jComboBox.getSelectedIndex());
            matriz = new String[vista.getEstados()][vista.getEstados()];
            boolean invalido = false;
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

    public void activacion(JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6) {
        jt2.setEnabled(true);
        jt3.setEnabled(true);
        jt4.setEnabled(true);
        jt5.setEnabled(true);
        jt6.setEnabled(true);
    }

    public void vaciarMatriz(String[][] matriz, Vista vista) {
        for (int i = 0; i < vista.getEstados(); i++) {
            for (int j = 0; j < vista.getEstados(); j++) {
                matriz[i][j] = null;
            }
        }
    }

    public void validar(JComboBox jComboBox1, JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6, JButton jb2, JButton jb3) {
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

                    for (int i = 0; i < jComboBox1.getSelectedIndex(); i++) {
                        estados = estados + i;
                    }

                    estados = estados + coma;
                    comprobacion = false;
                    if (jt3.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Necesita minimo un estado de aceptacion o final\n"
                                + "         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
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
                    }
                    break;
                case 2:
                    comprobar = false;
                    comprobacion = false;
                    contAux = 0;

                    if (jt4.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Necesita minimo un simbolo para el alfabeto de entrada\n"
                                + "         vuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < jt4.getText().length(); i++) {
                            if (comprobar == false) {
                                comprobar = true;
                                contAux++;
                            } else {
                                if (jt4.getText().charAt(i) == coma.charAt(0)) {
                                    comprobar = false;
                                    contAux++;
                                }
                            }
                        }

                        if (contAux == jt4.getText().length()) {
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
                                }
                            } else {
                                if (jt4.getText().charAt(i) == coma.charAt(0)) {
                                    comprobar = false;
                                    contAux++;
                                }
                            }
                        }

                        if (contAux == jt5.getText().length()) {
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
            model1 = new Modelo(jComboBox1.getSelectedIndex(), jt2.getText(), jt3.getText(), jt4.getText(), jt5.getText(), jt6.getText());
            setDatosCorrectos(true);
            listaTransiciones = vistaC.getListaTransicion();
            jb2.setEnabled(false);
            jb3.setEnabled(true);
        }
    }

    public boolean validarCadena(JTextField jt4, JTextField jt7) {
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
            } else {
                JOptionPane.showMessageDialog(null, "La cadena tiene un simbolo o varios"
                        + "\nQue no corresponden al alfabeto de entrada(Σ)\nVuelva a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        return valido;
    }

    public void fuciones(Funciones funcion, String boton) {
        funci = funcion;
        Lista lista = new Lista(vistaC, boton);
        funci.setVisible(false);
        lista.setVisible(true);
    }

    public void salir() {
        System.exit(0);
    }

    public void reiniciar(Vista vis) {
        Vista vista = new Vista();
        vista.setVisible(true);
        vis.setVisible(false);
    }

}
