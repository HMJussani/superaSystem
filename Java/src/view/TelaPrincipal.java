/* Supervisório para bomba de vácuo
 * Henrique Marega Jussani
 * Versão 1.0 data: 04/07/2018
 * 
 ** Protocolo de comunicação:
 * #xxxx,dd.mm.yy - valor e data
 * # - inicio da palavra
 * x - valor medido
 * dmy - dia mes e ano
 * $hh:mm:ss,bvxx - hora e status
 * $ - inicio da palavra
 * hms - hora, min, seg
 * b - bomba lig ou desl
 * v - valvula aberta ou fechada
 * x - futuro
 */
package view;

import conectaBancoDados.ConexaoDb;
import infraestrutura.Telegram;
import infraestrutura.auxliarGetSet;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.ImageIcon;

/**
 *
 * @author Rick
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    
    public void chamaTela(){
         TelaMedicao telaMed = new TelaMedicao();
        Desktop.add(telaMed);
        telaMed.setVisible(true);
    }

    public TelaPrincipal() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagem/Duke.png")).getImage()); //muda o icone padrao
        conexao = ConexaoDb.getConection();
        if (conexao == null) {
            //  lblConectou.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/dbNaoConectou.png")));  
        } else {
            Telegram telegram = new Telegram();
            if (auxliarGetSet.isConectado()&& auxliarGetSet.isCheckNet()) {
                lblTelegram.setEnabled(true);
                telegram.mensagem("Sistema Inicializado!");
                lblCheckNet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led2.png")));
                lblNet.setForeground(Color.BLACK);
                lblNet.setText("Internet: OK!");
                auxliarGetSet.setNetConect(true);

            } else {
                lblNet.setForeground(Color.red);
                lblNet.setText("Internet: FALHA!");
                lblTelegram.setEnabled(false);
                lblCheckNet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led1.png")));
            }           
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMedidos = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lblTelegram = new javax.swing.JLabel();
        lblUsbCon = new javax.swing.JLabel();
        ledSerialCon = new javax.swing.JLabel();
        lblCheckTel = new javax.swing.JLabel();
        lblNet = new javax.swing.JLabel();
        lblCheckNet = new javax.swing.JLabel();
        checkNet = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ControlVac");
        setBackground(new java.awt.Color(51, 102, 255));
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Desktop.setPreferredSize(new java.awt.Dimension(780, 409));
        Desktop.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        txtMedidos.setColumns(24);
        txtMedidos.setLineWrap(true);
        txtMedidos.setRows(15);
        jScrollPane1.setViewportView(txtMedidos);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTelegram.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelegram.setText("Checando Telegram");

        lblUsbCon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsbCon.setText(" Conexão USB ");

        ledSerialCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led0.png"))); // NOI18N
        ledSerialCon.setToolTipText("Verde = Conectado");

        lblCheckTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led0.png"))); // NOI18N

        lblNet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNet.setText("Internet: ok");

        lblCheckNet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led0.png"))); // NOI18N

        checkNet.setText("Conectar Internet");
        checkNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ledSerialCon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsbCon)
                .addGap(57, 57, 57)
                .addComponent(lblCheckTel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelegram)
                .addGap(18, 18, 18)
                .addComponent(lblCheckNet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkNet)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ledSerialCon)
                    .addComponent(lblUsbCon)
                    .addComponent(lblCheckTel)
                    .addComponent(lblTelegram)
                    .addComponent(lblCheckNet)
                    .addComponent(lblNet)
                    .addComponent(checkNet))
                .addContainerGap())
        );

        jMenu1.setText("Arquivo");

        jMenu3.setText("Configurações");

        jMenuItem2.setText("Comunicação Serial");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);
        jMenu3.add(jSeparator4);

        jMenuItem5.setText("Acerta Relógio");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenu1.add(jMenu3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Usuários");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator3);

        jMenuItem1.setText("Medição");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);

        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1060, 606));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       chamaTela();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       // ConfigSerial conf = new ConfigSerial();
       // conf.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ConfigSerial conf = new ConfigSerial();
        conf.setVisible(true);
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaUsuario usuarios = new TelaUsuario();
        Desktop.add(usuarios);
        usuarios.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        AcertaHora acerta = new AcertaHora(this, true);
        acerta.setModal(true);
        acerta.setVisible(true); //Chama a dialog
        acerta = null; //Deixa o garbage collector agir
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
    }//GEN-LAST:event_formFocusGained

    private void checkNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNetActionPerformed
        auxliarGetSet.setCheckNet(true);
    }//GEN-LAST:event_checkNetActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JCheckBox checkNet;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    public static javax.swing.JLabel lblCheckNet;
    public static javax.swing.JLabel lblCheckTel;
    public static javax.swing.JLabel lblNet;
    public static javax.swing.JLabel lblTelegram;
    private javax.swing.JLabel lblUsbCon;
    public static javax.swing.JLabel ledSerialCon;
    public static javax.swing.JTextArea txtMedidos;
    // End of variables declaration//GEN-END:variables
}
