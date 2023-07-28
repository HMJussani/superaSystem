/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import conectaBancoDados.ConexaoDb;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Bean.UsuariosBean;
import DAO.UsuariosDAO;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Rick
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    boolean sucesso = false;
    UsuariosBean usuario = new UsuariosBean();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private boolean conectado() {

        Connection conexao = null;
        JOptionPane.showMessageDialog(null, "Supera Sistema \n" + "Sua resolução atual: " + screenSize.width + "X" + screenSize.height);

        try {
            conexao = ConexaoDb.getConection();

            if (conexao == null) {
                return sucesso;
            } else {
                sucesso = true;
                conexao.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return sucesso;
    }

    private void conecta(boolean sucesso) {
        if (sucesso) {
            UsuariosDAO userDao = new UsuariosDAO();

            String pass = String.valueOf(txtSenha.getPassword());
            String user = txtUser.getText();
            try {
                if (userDao.checaUser(user, pass)) {
                    lblConectou.setText("Conectado!!");
                    lblConectou.setForeground(Color.green);
                    if (pass.equals("senha123")) {
                        usuario.setPass(pass);
                        usuario.setUser(user);
                        JOptionPane.showMessageDialog(null, "Usuário logando com senha Padrão, Mude sua senha agora!");
                        TelaTrocaSenha trocaSenha = new TelaTrocaSenha(this, true);
                        trocaSenha.setVisible(true);
                    } else {
                        checaPerfil(user);
                    }
                } else {
                    lblConectou.setText("Não Conectado!!");
                    lblConectou.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            TelaPrincipal.menRel.setEnabled(true);
            TelaPrincipal.menCadUsu.setEnabled(true);
            TelaPrincipal.lblUsuario.setText("Sem DB");
            TelaPrincipal.lblUsuario.setForeground(Color.red);
            this.dispose();
        }
    }

    private void checaPerfil(String login) {
        UsuariosDAO userDao = new UsuariosDAO();
        String perfil = userDao.pesquisarUser("login",login).get(0).getPerfil();
        if (perfil.equals("admin")) {
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            TelaPrincipal.menRel.setEnabled(true);
            TelaPrincipal.menCadUsu.setEnabled(true);
            TelaPrincipal.lblUsuario.setText(userDao.pesquisarUser("login",login).get(0).getNome());
            TelaPrincipal.lblLogado.setText(perfil);
            TelaPrincipal.lblUsuario.setForeground(Color.red);

            this.dispose();
        } else {
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            TelaPrincipal.lblUsuario.setText(userDao.pesquisarUser("login",login).get(0).getNome());
            TelaPrincipal.menRel.setEnabled(true);
            TelaPrincipal.menCadUsu.setEnabled(true);
            this.dispose();
        }
    }

    public TelaLogin() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagem/Duke.png")).getImage()); //muda o icone padrao
        if (conectado()) {
            lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/dbConectou.png")));

        } else {
            // JOptionPane.showMessageDialog(null, "Não conectado com o Banco de Dados.");
            // btnLogin.setEnabled(false);
            txtSenha.setEnabled(false);
            txtUser.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblBanco = new javax.swing.JLabel();
        lblConectou = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SuperaCoamo");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuário:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 11, -1, -1));

        jLabel2.setText("Senha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 61, -1, -1));
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 30, 238, -1));

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 81, 238, -1));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnLoginKeyReleased(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 119, 80, -1));

        lblBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/bderro.png"))); // NOI18N
        getContentPane().add(lblBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 89));

        lblConectou.setText("Não conectado.");
        getContentPane().add(lblConectou, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        setSize(new java.awt.Dimension(394, 192));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        conecta(sucesso);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        int tecla = evt.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            conecta(sucesso);
        }


    }//GEN-LAST:event_txtSenhaKeyReleased

    private void btnLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyReleased
        int tecla = evt.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            conecta(sucesso);
        }
    }//GEN-LAST:event_btnLoginKeyReleased

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
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel lblBanco;
    public static javax.swing.JLabel lblConectou;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
