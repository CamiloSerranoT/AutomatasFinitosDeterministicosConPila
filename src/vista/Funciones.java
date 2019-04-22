/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CAMILO
 */
public class Funciones extends JFrame implements ActionListener{

    Vista vista;
    FlowLayout estilo;
    JPanel jPanel1;
    JPanel jPanel2;
    JLabel jl1;
    JButton jb1;

    public Funciones(Vista vist) {
        vista = vist;
        crearVista(500, 350);
    }

    public void crearVista(int x, int y) {
        this.setTitle("Funciones de transición");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        crear();
        crearSubPanel();
        insertar();
        this.setLayout(estilo);
        this.setVisible(true);
    }

    public void crear() {
        estilo = new FlowLayout();
        jPanel1 = new JPanel();
        jl1 = new JLabel();
        jb1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 15));

        jl1.setFont(new Font("Castellar", 1, 24));
        jl1.setForeground(new java.awt.Color(255, 255, 255));
        jl1.setText("funciones de transición");

        jb1.setFont(new Font("Segoe UI Black", 0, 11));
        jb1.setText("Volver");
        jb1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb1.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(20, Short.MAX_VALUE)
                                .addComponent(jl1)
                                .addGap(20, 20, 20))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                                .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void crearSubPanel() {
        GridLayout estiloLayout = new GridLayout(vista.getEstados() + 1, vista.getEstados() + 1);
        estiloLayout.setHgap(1);
        estiloLayout.setVgap(1);
        jPanel2 = new JPanel(estiloLayout);
        jPanel1.add(jPanel2);
        jPanel2.setBounds(38, 70, 394, 140);
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JButton texto;
        JTextField jt2;
        boolean aux = false;
        int cont = 0;
        String[][] matrizAux = vista.getMatriz();
        ArrayList<String>[][] listaTransiciones = vista.getListaTransicion();

        for (int i = 0; i < vista.getEstados() + 1; i++) {
            for (int j = 0; j < vista.getEstados() + 1; j++) {
                texto = new JButton();
                texto.setFont(new Font("Segoe UI Black", 0, 11));
                texto.setBorder(createLineBorder(new java.awt.Color(0, 0, 0), 2));

                jt2 = new JTextField();
                jt2.setFont(new Font("Arial", 1, 15));
                jt2.setBorder(createLineBorder(new java.awt.Color(0, 0, 0), 2));

                if (aux == false) {
                    aux = true;
                    jt2.setText("Estados");
                    jt2.setEditable(false);
                    jt2.setBackground(new java.awt.Color(51, 51, 51));
                    jt2.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel2.add(jt2);
                } else {
                    if (i == 0 && j > 0) {
                        jt2.setText("" + (j - 1));
                        jt2.setEditable(false);
                        jt2.setBackground(new java.awt.Color(51, 51, 51));
                        jt2.setForeground(new java.awt.Color(255, 255, 255));
                        jPanel2.add(jt2);
                    } else {
                        if (j == 0 && i > 0) {
                            jt2.setText("" + (i - 1));
                            jt2.setEditable(false);
                            jt2.setBackground(new java.awt.Color(51, 51, 51));
                            jt2.setForeground(new java.awt.Color(255, 255, 255));
                            jPanel2.add(jt2);
                        } else {
                            if (matrizAux[i - 1][j - 1].charAt(0) - 48 == 1) {
                                texto.setText("" + cont);
                                cont++;
                                texto.setEnabled(true);
                                texto.addActionListener(this);
                                jPanel2.add(texto);
                            } else {
                                texto.setText("" + cont);
                                cont++;
                                texto.setEnabled(false);
                                jPanel2.add(texto);
                            }
                        }
                    }
                }
            }
        }

    }

    void insertar() {
        jPanel1.add(jl1);
        jPanel1.add(jb1);
        this.add(jPanel1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            vista.setVisible(true);
            this.setVisible(false);
        }else{
            
        }
    }
}
