/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import conectaBancoDados.ConexaoDb;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import DAO.UsuariosDAO;

/**
 *
 * @author Rick
 */
public class TelaTrocaSenha extends javax.swing.JDialog {

    /**
     * Creates new form TelaTrocaSenha
     */
    private String user;
    private String pass;
    private String dbPass;
    private String newPass;

    private boolean trocaSenha() {
        boolean sucesso = false;
        UsuariosDAO userDao = new UsuariosDAO();
        if (getDados()) {
            if (userDao.editaUser(user, newPass)) {
                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                sucesso = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dados de verificação ou nova senha inválidos.");
        }
        return sucesso;
    }

    private Boolean getDados() {
        Boolean sucesso = false;
        user = txtUser.getText();
        UsuariosDAO userDao = new UsuariosDAO();
        pass = new String(txtAntigaSenha.getPassword());
        dbPass = userDao.pesquisarUser("login", user).get(0).getPass();
        newPass = new String(txtNovaSenha.getPassword());
        if ((pass.equals(dbPass)) && (!newPass.isEmpty()) && (!user.isEmpty())) {
            sucesso = true;
        }
        return sucesso;
    }

    private boolean conectado() {
        boolean sucesso = false;
        Connection con = ConexaoDb.getConection();
        String conectado = con.toString();
        if (!conectado.isEmpty()) {
            sucesso = true;
        }

        return sucesso;
    }

    public TelaTrocaSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnSalvar.setEnabled(false);
        if (conectado()) {
            lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/dbConectou.png")));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAntigaSenha = new javax.swing.JPasswordField();
        btnSalvar = new javax.swing.JButton();
        lblBanco = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNovaSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mude a sua senha:");
        setResizable(false);

        jLabel1.setText("Confime seu Login:");

        jLabel2.setText("Confirme a Senha ATUAL:");

        txtAntigaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAntigaSenhaKeyReleased(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        btnSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSalvarKeyReleased(evt);
            }
        });

        lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/bderro.png"))); // NOI18N

        jLabel3.setText("Digite sua nova Senha:");

        txtNovaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNovaSenhaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(txtAntigaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                                .addGap(37, 37, 37)
                                .addComponent(lblBanco))
                            .addComponent(jLabel3)
                            .addComponent(txtNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAntigaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(435, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAntigaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAntigaSenhaKeyReleased
        if (evt.getKeyCode() > 3) {
            btnSalvar.setEnabled(getDados());
        }

    }//GEN-LAST:event_txtAntigaSenhaKeyReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (trocaSenha()) {
            this.dispose();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSalvarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarKeyReleased
       int tecla = evt.getKeyCode();
        if ((tecla == KeyEvent.VK_ENTER)&&(tecla>=3)) {
            if (trocaSenha()) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnSalvarKeyReleased

    private void txtNovaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNovaSenhaKeyReleased
      if (evt.getKeyCode() > 3) {
            btnSalvar.setEnabled(getDados());
        }
    }//GEN-LAST:event_txtNovaSenhaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaTrocaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTrocaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTrocaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTrocaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaTrocaSenha dialog = new TelaTrocaSenha(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel lblBanco;
    private javax.swing.JPasswordField txtAntigaSenha;
    private javax.swing.JPasswordField txtNovaSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
