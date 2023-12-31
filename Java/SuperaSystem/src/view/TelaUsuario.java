package view;

import Bean.UsuariosBean;
import DAO.UsuariosDAO;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de gestão de usuários
 *
 * @author Professor José de Assis
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    private int iduser = 0;
    private String login = TelaPrincipal.lblUsuario.getText();
    private String pass = null;
    private String perfil = null;
    private String nome = null;
    private String email = null;
    private String logado = null;
    int tecla = 0;

    public TelaUsuario() {
        initComponents();
        logado = getPerfil();
        setarTabela(acesso(logado), login);
        setaTxt(acesso(logado));
        btnUsuCreate.setEnabled(acesso(logado));
        btnUsuDelete.setEnabled(false);
        btnUsuUpdate.setEnabled(!acesso(logado));
        if (!acesso(logado)) {
            tbUser.setRowSelectionInterval(0, 0);
            setarCampos();
        }
    }

    private boolean getDados() {
        boolean sucesso = true;
        login = txtUser.getText();
        pass = new String(txtPass.getPassword());
        perfil = cboUserPerfil.getSelectedItem().toString();
        nome = txtUsuNome.getText();
        email = txtUserEmail.getText();
        if (login.isEmpty() || pass.isEmpty() || nome.isEmpty()) {
            sucesso = false;
        }
        return sucesso;
    }

    private void setaTxt(Boolean acesso) {
        btnUsuDelete.setEnabled(acesso);
        btnUsuCreate.setEnabled(acesso);
        txtUsuNome.setEnabled(acesso);
        txtUserEmail.setEnabled(acesso);
        txtUser.setEnabled(acesso);
        cboUserPerfil.setEnabled(acesso);

    }

    private void setarTabela(Boolean acesso, String login) {
        UsuariosDAO userDao = new UsuariosDAO();
        DefaultTableModel model = (DefaultTableModel) tbUser.getModel();
        model.setRowCount(0);
        if (acesso) {
            ArrayList<UsuariosBean> usuarios = userDao.pesquisarUser();
            for (int i = 0; i < usuarios.size(); i++) {
                model.addRow(new Object[]{
                    usuarios.get(i).getNome(),
                    usuarios.get(i).getUser(),
                    usuarios.get(i).getPerfil(),
                    usuarios.get(i).getEmail()
                });
            }
        } else {
            ArrayList<UsuariosBean> usuarios = userDao.pesquisarUser("usuario", login);
            for (int i = 0; i < usuarios.size(); i++) {
                model.addRow(new Object[]{
                    usuarios.get(i).getNome(),
                    usuarios.get(i).getUser(),
                    usuarios.get(i).getPerfil(),
                    usuarios.get(i).getEmail()
                });
            }
        }
    }

    private void setarCampos() {
        int setar = tbUser.getSelectedRow();
        txtUsuNome.setText(tbUser.getModel().getValueAt(setar, 0).toString());
        txtUser.setText(tbUser.getModel().getValueAt(setar, 1).toString());
        txtUserEmail.setText(tbUser.getModel().getValueAt(setar, 3).toString());
        cboUserPerfil.setSelectedItem(tbUser.getModel().getValueAt(setar, 2));
        btnUsuCreate.setEnabled(false);
        btnUsuDelete.setEnabled(acesso(logado));
        btnUsuUpdate.setEnabled(true);
    }

    private String getPerfil() {
        UsuariosDAO userDao = new UsuariosDAO();
        String estePerfil = "user";
        estePerfil = userDao.pesquisarUser("usuario", login).get(0).getPerfil();
        return estePerfil;
    }

    private Boolean acesso(String logado) {
        Boolean sucesso = false;
        if (logado.equals("admin")) {
            sucesso = true;
        }
        return sucesso;
    }

    private void limpar() {
        txtUsuNome.setText(null);
        txtUser.setText(null);
        txtPass.setText(null);
        txtUserEmail.setText(null);
        txtUserEmail.setEnabled(true);
        cboUserPerfil.setSelectedItem(0);
    }

    private Boolean editaUsuer() {
        Boolean sucesso = false;
        UsuariosDAO userDao = new UsuariosDAO();
        if (getDados()) {
            if (acesso(logado)) {
                String procurado = tbUser.getModel().getValueAt(tbUser.getSelectedRow(), 1).toString();
                iduser = Integer.parseInt(userDao.pesquisarUser("login", procurado).get(0).getIduser());
                if (userDao.editaUser(iduser, nome, login, "senha123", perfil, email)) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso.");
                    setarTabela(acesso(logado), login);
                    sucesso = true;
                }
            } else {
                if (userDao.editaUser(login, pass)) {
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso.");
                    sucesso = true;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Campos obrigatórios vazios. Verifique.");
        }
        return sucesso;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        cboUserPerfil = new javax.swing.JComboBox<>();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUser = new javax.swing.JTable();
        txtUserEmail = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(995, 580));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel2.setText("* Nome");

        jLabel3.setText("*Login");

        jLabel4.setText("*Senha");

        jLabel5.setText("Email");

        jLabel6.setText("* Perfil");

        txtUsuNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuNomeKeyReleased(evt);
            }
        });

        cboUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user", "Tecnico", "Vendedor", "Comprador" }));
        cboUserPerfil.setSelectedIndex(2);
        cboUserPerfil.setToolTipText("");
        cboUserPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUserPerfilActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setEnabled(false);
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setEnabled(false);
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setEnabled(false);
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos obrigatórios");

        tbUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Login", "Perfil", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUser);
        if (tbUser.getColumnModel().getColumnCount() > 0) {
            tbUser.getColumnModel().getColumn(0).setResizable(false);
            tbUser.getColumnModel().getColumn(1).setResizable(false);
            tbUser.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbUser.getColumnModel().getColumn(2).setResizable(false);
            tbUser.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbUser.getColumnModel().getColumn(3).setResizable(false);
        }

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuNome))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel6)
                        .addComponent(cboUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 1124, 683);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        getDados();
        UsuariosDAO userDao = new UsuariosDAO();
        if (userDao.criaUser(nome, login, pass, perfil, email)) {
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso.");
            setarTabela(acesso(logado), login);
            limpar();
        }
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        editaUsuer();

    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        UsuariosDAO userDao = new UsuariosDAO();
        getDados();
        if (userDao.excluiUser(login)) {
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");
            setarTabela(acesso(logado), login);
            limpar();
        }
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void cboUserPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUserPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUserPerfilActionPerformed

    private void tbUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUserMouseClicked
        setarCampos();
    }//GEN-LAST:event_tbUserMouseClicked

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        tecla++;
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (tecla >= 3)) {
            if (editaUsuer()) {
                txtPass.setText(null);
                tecla = 0;
            }
        }
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtUsuNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuNomeKeyReleased
        int tecla = evt.getKeyCode();
        if (tecla >= 3) {
            btnUsuCreate.setEnabled(true);
            btnUsuDelete.setEnabled(false);
            btnUsuUpdate.setEnabled(false);
        }
    }//GEN-LAST:event_txtUsuNomeKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUserPerfil;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUser;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUsuNome;
    // End of variables declaration//GEN-END:variables

}
