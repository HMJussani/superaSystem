package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import viewRMA.TelaOSFechada;
import viewProd.TelaModelos;
import viewRMA.TelaInvetarioEquip;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import viewProd.TelaComponentes;
import viewRMA.TelaArquivos;
import viewRMA.TelaInvetarioOs;
import viewRMA.TelaOSAberta;
import viewRMA.TelaOrcamento;

/**
 * Tela principal do sistema
 *
 * @author Rick
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public TelaPrincipal() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagem/Duke.png")).getImage()); //muda o icone padrao
        if ((screenSize.width >= 1280) && (screenSize.height >= 960)) {
            setExtendedState(MAXIMIZED_BOTH);
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

        desktop = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblLogado = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadCli = new javax.swing.JMenuItem();
        menCadComp = new javax.swing.JMenuItem();
        menComps = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menuTecnica = new javax.swing.JMenu();
        menTecOS = new javax.swing.JMenuItem();
        menTecOSFechada = new javax.swing.JMenuItem();
        menuTecGeraArq = new javax.swing.JMenuItem();
        MenuTecOrc = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        menRelSer = new javax.swing.JMenuItem();
        menRelEquip = new javax.swing.JMenuItem();
        menSobre = new javax.swing.JMenu();
        menSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema para gestão de serviços - Ver 1.1");
        setFocusable(false);
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(204, 204, 204));
        desktop.setForeground(new java.awt.Color(204, 204, 204));
        desktop.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuário:");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usuário");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Data:");

        lblData.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblData.setText("Data");

        lblLogado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLogado.setText("Logado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLogado)
                        .addContainerGap(985, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblData)
                        .addGap(36, 36, 36))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblData)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogado)))
                .addGap(33, 33, 33))
        );

        menCad.setText("Cadastros");

        menCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menCadCli.setText("Clientes");
        menCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCliActionPerformed(evt);
            }
        });
        menCad.add(menCadCli);

        menCadComp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menCadComp.setText("Componentes");
        menCadComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCompActionPerformed(evt);
            }
        });
        menCad.add(menCadComp);

        menComps.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menComps.setText("Modelos de Equipamentos");
        menComps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCompsActionPerformed(evt);
            }
        });
        menCad.add(menComps);

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menCadUsu.setText("Usuários");
        menCadUsu.setEnabled(false);
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        Menu.add(menCad);

        menuTecnica.setText("Técnica");

        menTecOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menTecOS.setText("Ordens de Serviço Abertas");
        menTecOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menTecOSActionPerformed(evt);
            }
        });
        menuTecnica.add(menTecOS);

        menTecOSFechada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menTecOSFechada.setText("Ordens de Serviço Fechadas");
        menTecOSFechada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menTecOSFechadaActionPerformed(evt);
            }
        });
        menuTecnica.add(menTecOSFechada);

        menuTecGeraArq.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuTecGeraArq.setText("Gerador de Arquivos");
        menuTecGeraArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTecGeraArqActionPerformed(evt);
            }
        });
        menuTecnica.add(menuTecGeraArq);

        MenuTecOrc.setText("Orçamentos");
        MenuTecOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuTecOrcActionPerformed(evt);
            }
        });
        menuTecnica.add(MenuTecOrc);

        Menu.add(menuTecnica);

        menRel.setText("Relatório");

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        menRel.add(menRelCli);

        menRelSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRelSer.setText("Serviços");
        menRelSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelSerActionPerformed(evt);
            }
        });
        menRel.add(menRelSer);

        menRelEquip.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRelEquip.setText("Equipamentos");
        menRelEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelEquipActionPerformed(evt);
            }
        });
        menRel.add(menRelEquip);

        Menu.add(menRel);

        menSobre.setText("Sobre");
        menSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menSobreMouseClicked(evt);
            }
        });
        Menu.add(menSobre);

        menSair.setText("Sair");
        menSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menSairMouseClicked(evt);
            }
        });
        Menu.add(menSair);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1152, 864));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        usuario.setSize(desktop.getSize());
        desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCliActionPerformed
        TelaCliente cliente = new TelaCliente();
        cliente.setVisible(true);
        cliente.setSize(desktop.getSize());
        desktop.add(cliente);
    }//GEN-LAST:event_menCadCliActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        JOptionPane.showMessageDialog(null, " Ainda em construção...", "Não implementado ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource("/imagem/robo.png")));
    }//GEN-LAST:event_menRelCliActionPerformed

    private void menRelSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelSerActionPerformed
        TelaInvetarioOs invetarioOs = new TelaInvetarioOs();
        invetarioOs.setVisible(true);
        invetarioOs.setSize(desktop.getSize());
        desktop.add(invetarioOs);
    }//GEN-LAST:event_menRelSerActionPerformed

    private void menSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menSairMouseClicked
        int sair = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menSairMouseClicked

    private void menSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menSobreMouseClicked
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_menSobreMouseClicked

    private void menCompsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCompsActionPerformed
        TelaModelos comps = new TelaModelos();
        comps.setVisible(true);
        comps.setSize(desktop.getSize());
        desktop.add(comps);
    }//GEN-LAST:event_menCompsActionPerformed

    private void menTecOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menTecOSActionPerformed
        TelaOSAberta os = new TelaOSAberta();
        os.setVisible(true);
        os.setSize(desktop.getSize());
        desktop.add(os);
    }//GEN-LAST:event_menTecOSActionPerformed

    private void menTecOSFechadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menTecOSFechadaActionPerformed
        TelaOSFechada os = new TelaOSFechada();
        os.setVisible(true);
        os.setSize(desktop.getSize());
        desktop.add(os);
    }//GEN-LAST:event_menTecOSFechadaActionPerformed

    private void menuTecGeraArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTecGeraArqActionPerformed
        TelaArquivos tela = new TelaArquivos();
        tela.setVisible(true);
        tela.setSize(desktop.getSize());
        desktop.add(tela);

    }//GEN-LAST:event_menuTecGeraArqActionPerformed

    private void menRelEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelEquipActionPerformed
        TelaInvetarioEquip prods = new TelaInvetarioEquip();
        prods.setVisible(true);
        prods.setSize(desktop.getSize());
        desktop.add(prods);

    }//GEN-LAST:event_menRelEquipActionPerformed

    private void MenuTecOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuTecOrcActionPerformed
        TelaOrcamento orcs = new TelaOrcamento();
        orcs.setVisible(true);
        orcs.setSize(desktop.getSize());
        desktop.add(orcs);
    }//GEN-LAST:event_MenuTecOrcActionPerformed

    private void menCadCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCompActionPerformed
        TelaComponentes comp = new TelaComponentes();
        comp.setVisible(true);
        comp.setSize(desktop.getSize());
        desktop.add(comp);
    }//GEN-LAST:event_menCadCompActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenuItem MenuTecOrc;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblLogado;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadComp;
    public static javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenuItem menComps;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelCli;
    private javax.swing.JMenuItem menRelEquip;
    private javax.swing.JMenuItem menRelSer;
    private javax.swing.JMenu menSair;
    private javax.swing.JMenu menSobre;
    private javax.swing.JMenuItem menTecOS;
    private javax.swing.JMenuItem menTecOSFechada;
    private javax.swing.JMenuItem menuTecGeraArq;
    private javax.swing.JMenu menuTecnica;
    // End of variables declaration//GEN-END:variables
}
