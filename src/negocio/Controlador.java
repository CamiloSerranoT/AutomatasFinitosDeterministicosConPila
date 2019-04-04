/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.Font;
import java.awt.GridLayout;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vista.Lista;
import vista.Vista;

/**
 *
 * @author CAMILO
 */
public class Controlador {

    public Controlador() {

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
        if (jComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Primero debe elegir algun conjunto de estados");
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
                jPanel4.setBounds(45, 70, 360, 90);
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

    public void validarMatriz(Vista vista, int i, int j) {
        String numerosMatriz = "10";
        String[][] matrizOpc = vista.getMatriz();

        for (i = 0; i < vista.getEstados(); i++) {
            for (j = 0; j < vista.getEstados(); j++) {
                if (matrizOpc[i][j].charAt(0) == numerosMatriz.charAt(0)) {
                    Lista nueva = new Lista(vista, i, j + 1);
                    vista.setVisible(false);
                }
            }
        }
    }

    public void validar(String jt1, String jt2, String jt3, String jt4, String jt5, String jt6) {

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
