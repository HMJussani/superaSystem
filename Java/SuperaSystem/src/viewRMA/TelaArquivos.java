package viewRMA;

import Acessorios.Acessorios;
import Acessorios.CriarTxt;
import Bean.EquipOSBean;
import Bean.OrdServBean;
import DAO.EquipOsDAO;
import DAO.OrdServDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Tela de gestão de invetario
 *
 * @author HMJussani
 */
public class TelaArquivos extends javax.swing.JInternalFrame {

    private int conta = 0;
    String idOrdServ = null;
    String model = null;
    String pat = null;
    String nserie = null;
    String tecnico = null;
    Acessorios acessorios = new Acessorios();

    public TelaArquivos() {
        initComponents();
        txtLocalArquivo.setText(System.getProperty("user.home") + "\\Documents\\");
    }

    private String getOrdSErv(String ordSErv) {
        String os = null;
        OrdServDAO ordemServico = new OrdServDAO();
        ArrayList<OrdServBean> osList = ordemServico.pesquisarOsbyIdOs(ordSErv);
        if (!osList.isEmpty()) {
            os = osList.get(0).getTecnico();            
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

    private void getDados(String ordServ, String patEquip, int i) {
        EquipOsDAO equipDao = new EquipOsDAO();
        tecnico = getOrdSErv(ordServ);
        model = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getModel();
        nserie = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getNserie();
        idOrdServ = ordServ;
        pat = patEquip;
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
        btnTxtBurn = new javax.swing.JButton();
        btnTxtSeven = new javax.swing.JButton();

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

        btnTxtBurn.setText("TXT BrurnTest");
        btnTxtBurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTxtBurnActionPerformed(evt);
            }
        });

        btnTxtSeven.setText("TXT Seven");
        btnTxtSeven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTxtSevenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                            .addComponent(txtArqFormatado, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTxtBurn)
                        .addGap(30, 30, 30)
                        .addComponent(btnTxtSeven)
                        .addGap(30, 30, 30)
                        .addComponent(btnCriaDir)))
                .addGap(18, 18, 18)
                .addComponent(btnLocalizar)
                .addGap(304, 304, 304))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCriaDir, btnTxtBurn, btnTxtSeven});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLocalArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOrdServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtArqFormatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTxtBurn)
                    .addComponent(btnCriaDir)
                    .addComponent(btnTxtSeven))
                .addContainerGap(67, Short.MAX_VALUE))
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
                .addContainerGap(374, Short.MAX_VALUE))
        );

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        txtLocalArquivo.setText(acessorios.getPath());
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnCriaDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaDirActionPerformed
        String dir = txtLocalArquivo.getText() + txtArqFormatado.getText();
        int nDir = getEquip(txtOrdServ.getText()).size();
        if (!dir.isEmpty()) {
            if (nDir > 1) {
                JOptionPane.showMessageDialog(null, "Serão criados " + nDir + " sub diretórios.");
                if (acessorios.criaDir(dir)) {
                    for (int i = 0; i < nDir; i++) {
                        acessorios.criaDir(dir + "\\" + getEquip(txtOrdServ.getText()).get(i));
                    }
                    JOptionPane.showMessageDialog(null, "Diretórios criados com sucesso.");
                }
            } else {
                if (acessorios.criaDir(dir)) {
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
                txtArqFormatado.setText(osList.get(0).getIdOrdServ() + " - " + acessorios.setData());
                conta = 0;
            }else{
                txtArqFormatado.setText(txtLocalArquivo.getText() + " - " + acessorios.setData());
            }
            
        }

    }//GEN-LAST:event_txtOrdServKeyReleased

    private void txtArqFormatadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArqFormatadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArqFormatadoKeyReleased

    private void btnTxtBurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTxtBurnActionPerformed

        CriarTxt txtSeven = new CriarTxt();
        String dir = txtLocalArquivo.getText() + txtArqFormatado.getText();
        int nDir = getEquip(txtOrdServ.getText()).size();
        if (!dir.isEmpty()) {
            if (nDir > 1) {
                JOptionPane.showMessageDialog(null, "Serão criados " + nDir + " sub diretórios.");
                if (acessorios.criaDir(dir)) {
                    for (int i = 0; i < nDir; i++) {
                        String path = (dir + "\\" + getEquip(txtOrdServ.getText()).get(i));
                        getDados(txtOrdServ.getText(), getEquip(txtOrdServ.getText()).get(i),i);
                        txtSeven.criarBurnTxt(path, idOrdServ, model, pat, nserie, tecnico);
                    }
                    JOptionPane.showMessageDialog(null, "Arquivos criados com sucesso.");
                }
            } else {
                if (acessorios.criaDir(dir)) {
                    String path = (dir + "\\" + getEquip(txtOrdServ.getText()).get(0));
                        getDados(txtOrdServ.getText(), getEquip(txtOrdServ.getText()).get(0),0);
                        txtSeven.criarBurnTxt(path, idOrdServ, model, pat, nserie, tecnico);
                    JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo não criado.");
        }


    }//GEN-LAST:event_btnTxtBurnActionPerformed

    private void btnTxtSevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTxtSevenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTxtSevenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriaDir;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnTxtBurn;
    private javax.swing.JButton btnTxtSeven;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtArqFormatado;
    private javax.swing.JTextField txtLocalArquivo;
    private javax.swing.JTextField txtOrdServ;
    // End of variables declaration//GEN-END:variables
}
