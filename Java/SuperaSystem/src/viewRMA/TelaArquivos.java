package viewRMA;

import Acessorios.Arquivos;
import Bean.EquipOSBean;
import Bean.OrdServBean;
import DAO.EquipOsDAO;
import DAO.OrdServDAO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Tela de gestão de invetario
 *
 * @author HMJussani
 */
public class TelaArquivos extends javax.swing.JInternalFrame {

    private int conta = 0;
    Arquivos arquivos = new Arquivos();


    public TelaArquivos() {
        initComponents();
        txtLocalArquivo.setText(System.getProperty("user.home") + "\\Documents\\");
    }

    

    private String getOrdSErv(String ordSErv) {
        String os = "";
        OrdServDAO ordemServico = new OrdServDAO();
        ArrayList<OrdServBean> osList = ordemServico.pesquisarOsbyIdOs(ordSErv);
        if (!osList.isEmpty()) {
            os = (osList.get(0).getIdOrdServ());
        }
        return os;
    }

    private ArrayList<String> getEquip(String idOrdServ) {
        ArrayList<String> equips = new ArrayList<>();
        EquipOsDAO equipDao = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipDao.pesquisarProdutoBy("idOrdServ", idOrdServ);
        if (!equipList.isEmpty()) {
            for (int i = 0; i < equipList.size(); i++) {
                equips.add(i, equipList.get(i).getPatEquip());
            }
        }
        return equips;
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
        jLabel1 = new javax.swing.JLabel();
        txtLocalArquivo = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtOrdServ = new javax.swing.JTextField();
        btnCriaDir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtArqFormatado = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Arquivos");
        setPreferredSize(new java.awt.Dimension(822, 695));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Local do Arquivo"));

        jLabel1.setText("Local do arquivo");

        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Ordem de Serviço");

        txtOrdServ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrdServKeyReleased(evt);
            }
        });

        btnCriaDir.setText("Criar Dir");
        btnCriaDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaDirActionPerformed(evt);
            }
        });

        jLabel3.setText("Formato Arquivo");

        txtArqFormatado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArqFormatadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocalArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrdServ, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArqFormatado, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLocalizar)
                    .addComponent(btnCriaDir))
                .addGap(304, 304, 304))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLocalArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOrdServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtArqFormatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriaDir))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        txtLocalArquivo.setText(arquivos.getPath());
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnCriaDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaDirActionPerformed
        String dir = txtLocalArquivo.getText() + txtArqFormatado.getText();
        int nDir = getEquip(txtOrdServ.getText()).size();
        if (!dir.isEmpty()) {
            if (nDir > 1) {
                JOptionPane.showMessageDialog(null, "Serão criados " + nDir + " sub diretórios.");
                if (arquivos.criaDir(dir)) {
                    for (int i = 0; i < nDir; i++) {
                        arquivos.criaDir(dir + "\\" + getEquip(txtOrdServ.getText()).get(i));
                    }
                    JOptionPane.showMessageDialog(null, "Diretórios criados com sucesso.");
                }
            } else {
                if (arquivos.criaDir(dir)) {
                    JOptionPane.showMessageDialog(null, "Diretório criado com sucesso.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Diretório não criado.");
        }
    }//GEN-LAST:event_btnCriaDirActionPerformed

    private void txtOrdServKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdServKeyReleased
        conta++;
        if (conta >= 3) {
            OrdServDAO ordemServico = new OrdServDAO();
            ArrayList<OrdServBean> osList = ordemServico.pesquisarOsbyIdOs(txtOrdServ.getText());
            if (!osList.isEmpty()) {
                txtArqFormatado.setText(osList.get(0).getIdOrdServ() + " - " + arquivos.setData());
                conta = 0;
            }

        }

    }//GEN-LAST:event_txtOrdServKeyReleased

    private void txtArqFormatadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArqFormatadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArqFormatadoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriaDir;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtArqFormatado;
    private javax.swing.JTextField txtLocalArquivo;
    private javax.swing.JTextField txtOrdServ;
    // End of variables declaration//GEN-END:variables
}
