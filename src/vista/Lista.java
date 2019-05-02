package vista;

import negocio.Controlador;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Lista extends JFrame implements ActionListener {

    private JList<String> jList1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JButton jb1;
    private JButton jb2;
    private JButton jb3;
    private JLabel jl1;
    private JTextField jt1;
    FlowLayout estilo;
    Controlador control;
    DefaultListModel modeloVista;
    Funciones funciones;
    ArrayList<String> listaTransicion;
    int posicion;
    JButton botonCom;

    public Lista(Funciones fun, int boton, Controlador controlador, JButton botonCompleto) {
        crearVista(495, 533);
        setLocationRelativeTo(null);
        modeloVista = new DefaultListModel();
        funciones = fun;
        jList1.setModel(modeloVista);
        listaTransicion = new ArrayList<String>();
        posicion = boton;
        control = controlador;
        botonCom = botonCompleto;
    }

    public ArrayList<String> getListaTransicion() {
        return listaTransicion;
    }

    public void setListaTransicion(ArrayList<String> listaTransicion) {
        this.listaTransicion = listaTransicion;
    }

    public void crearVista(int x, int y) {
        this.setTitle("Funcion de transición");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        crear();
        insertar();
        this.setLayout(estilo);
        this.setVisible(true);
    }

    public void crear() {
        jPanel1 = new JPanel();
        jList1 = new JList<String>();
        jt1 = new JTextField();
        jb1 = new JButton();
        jb2 = new JButton();
        jb3 = new JButton();
        jl1 = new JLabel();
        jScrollPane1 = new JScrollPane();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 15));

        jt1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        jb1.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        jb1.setText("Agregar");
        jb1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb1.addActionListener(this);

        jb2.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        jb2.setText("Eliminar");
        jb2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb2.addActionListener(this);

        jb3.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        jb3.setText("Volver");
        jb3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb3.addActionListener(this);

        jl1.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        jl1.setForeground(new java.awt.Color(255, 255, 255));
        jl1.setText("Funcion de transición");

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jl1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(36, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jb2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jl1)
                                .addGap(18, 18, 18)
                                .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15))
        );
        estilo = new FlowLayout();
    }

    void insertar() {
        jPanel1.add(jScrollPane1);
        jPanel1.add(jb1);
        jPanel1.add(jb2);
        jPanel1.add(jb3);
        jPanel1.add(jl1);
        jPanel1.add(jt1);
        this.add(jPanel1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            listaTransicion = control.agregar(modeloVista, jt1, listaTransicion);
        } else {
            if (e.getSource() == jb2) {
                listaTransicion = control.eliminar(modeloVista, jList1, listaTransicion);
            } else {
                if (e.getSource() == jb3) {
                    control.volver(this, listaTransicion, funciones, posicion, botonCom);
                }
            }
        }
    }
}
