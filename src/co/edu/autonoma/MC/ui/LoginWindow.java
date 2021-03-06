/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.ui;

import co.edu.autonoma.MC.cliente.elements.Cliente;
import co.edu.autonoma.MC.juego.elements.Jugador;
import co.edu.autonoma.MC.juego.elements.Mundo;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuela Cardona
 */
public class LoginWindow extends javax.swing.JFrame {

    Cliente cliente;
    
    /**
     * Creates new form Login
     * @param cliente
     */
    public LoginWindow(Cliente cliente) {
        initComponents();
        
        this.cliente = cliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelBienvenida = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        txtFNombre = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        labelImgJugador = new javax.swing.JLabel();
        labelImgJugadora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        labelBienvenida.setFont(new java.awt.Font("Pristina", 1, 36)); // NOI18N
        labelBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        labelBienvenida.setText(" Stone-Paper-Scissors World");

        labelNombre.setFont(new java.awt.Font("Pristina", 1, 24)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre.setText("Nombre de usuario ");

        txtFNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFNombreActionPerformed(evt);
            }
        });

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        labelImgJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Jugador.png"))); // NOI18N

        labelImgJugadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Jugadora.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(labelImgJugador)
                        .addGap(35, 35, 35)
                        .addComponent(labelImgJugadora))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(txtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelBienvenida)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNombre)
                        .addGap(196, 196, 196))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(labelBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImgJugador)
                    .addComponent(labelImgJugadora))
                .addGap(40, 40, 40)
                .addComponent(labelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnEntrar)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        
        String nombreJugador = txtFNombre.getText();
        
        this.cliente.setNombre(nombreJugador.trim());
        
        if(this.cliente.getNombre().isEmpty()){
            JOptionPane.showMessageDialog(this, "Completar los datos del login", "Login", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        boolean conexion = this.cliente.conectar();
        
        if(conexion){
            this.cliente.iniciarEntradaMulticast();
            
            Jugador jugadorLocal = this.cliente.crearJugador();
            
            if(jugadorLocal!=null){
                this.cliente.empezarPPT();
                
                Mundo mundo = new Mundo(7,32,500,500);
                
                mundo.setEstadoJuego(this.cliente.getEstadoJuego());
//                mundo.setJugadorLocal(jugadorLocal);
//                
//                jugadorLocal.setGraphicContainer(mundo);

                MundoWindow mundoWindow = new MundoWindow();
                mundoWindow.setCliente(this.cliente);
                mundoWindow.setMundo(mundo);
                
                mundo.setGraphicContainer(mundoWindow);
                
                mundoWindow.iniciarChat();
                
                mundoWindow.setTitle("ROCK-PAPER-SCISSORS WORLD");
                mundoWindow.setLocationRelativeTo(this);
                mundoWindow.setVisible(true);

                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "LOGIN WINDOWS=> No se puede unir a la sesion actual, nombre de jugador ya existente");
                this.cliente.cerrarConexiones();
                this.dispose();
            }            
        }else{
            JOptionPane.showMessageDialog(this, "LOGIN WINDOWS=> Error conectando con el servidor solicitado", "Conexión", JOptionPane.INFORMATION_MESSAGE);
        } 
        
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFNombreActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBienvenida;
    private javax.swing.JLabel labelImgJugador;
    private javax.swing.JLabel labelImgJugadora;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTextField txtFNombre;
    // End of variables declaration//GEN-END:variables
}
