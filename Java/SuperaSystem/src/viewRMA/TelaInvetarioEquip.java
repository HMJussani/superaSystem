package viewRMA;

import Bean.ClientesBean;
import Bean.EquipOSBean;
import Bean.ModelosBean;
import DAO.ClienteDAO;
import DAO.EquipOsDAO;
import DAO.ModelosDAO;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de gestão de invetario
 *
 * @author HMJussani
 */
public class TelaInvetarioEquip extends javax.swing.JInternalFrame {

    String nserie = null;
    String model = null;
    String patEquip = null;
    String idcli = null;
    String idOrdServ = null;
    Boolean garantia = false;
    Boolean analizado = false;
    ClienteDAO clientes = new ClienteDAO();

    int conta = 0;

    public TelaInvetarioEquip() {
        initComponents();
        setModelo();
        setarTabela();

    }

    private void setModelo() {
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo();

        for (int i = 0; i < modelList.size(); i++) {
            String modelo = modelList.get(i).getModel().toString();
           // cbModel.addItem(modelo);
        }
    }

    private void setarCampos() {
     //   int setar = tbProd.getSelectedRow();
     //   txtOrdemServ.setText(tbProd.getModel().getValueAt(setar, 0).toString());
    //    txtSerie.setText(tbProd.getModel().getValueAt(setar, 2).toString());
     //   txtPat.setText(tbProd.getModel().getValueAt(setar, 1).toString());
    //    for (int i = 0; i < cbModel.getItemCount(); i++) {
    //        if (cbModel.getItemAt(i).equals(tbProd.getModel().getValueAt(setar, 3).toString())) {
    //            cbModel.setSelectedIndex(i);
    //        }
    //    }

    }

    private void setarTabela(String arg, String valor) {
        DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
        model.setRowCount(0);
        String estado = "Analizando";
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProdutoBy(arg, valor);
        for (int i = 0; i < equipList.size(); i++) {
           if(equipList.get(i).getAnalizado()){
                estado = "Resolvido";
            }else{
                estado = "Analizando";
            }
            model.addRow(new Object[]{
                equipList.get(i).getidOrdServ(), 
                equipList.get(i).getPatEquip(),
                equipList.get(i).getNserie(),
                equipList.get(i).getModel(),
                estado});
        }
    }

    private void setarTabela() {
        DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
        model.setRowCount(0);
        String estado = "Analizando";
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProduto();
        for (int i = 0; i < equipList.size(); i++) {
              if(equipList.get(i).getAnalizado()){
                estado = "Resolvido";
            }else{
                estado = "Analizando";
            }
            model.addRow(new Object[]{
                equipList.get(i).getidOrdServ(), 
                equipList.get(i).getPatEquip(),
                equipList.get(i).getNserie(),
                equipList.get(i).getModel(),
                estado});
        }
    }
    private void limpar() {
       // txtOrdemServ.setText("");
       // txtSerie.setText("");
      //  txtPat.setText("");
      //  cbModel.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
        model.setRowCount(0);
    }

    private void getDados() {
       // idOrdServ = txtOrdemServ.getText();
       // nserie = txtSerie.getText();
      //  model = cbModel.getSelectedItem().toString();
      //  patEquip = txtPat.getText();
        idcli = txtIdCli.getText();
        
       // if (boxGarantia.isSelected()) {
      //      garantia = true;
      //  } else {
      //      garantia = false;
      //  }

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
        btnPesquisa = new javax.swing.JButton();
        txtIdCli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProd = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtLocalizaSerie = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtLocalizaPat = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produtos");
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

        jLabel1.setText("Pesquisa Cliente");
        jLabel1.setToolTipText("");

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/read.png"))); // NOI18N
        btnPesquisa.setToolTipText("Pesquisa");
        btnPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisa.setPreferredSize(new java.awt.Dimension(80, 80));
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        txtIdCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdCliKeyReleased(evt);
            }
        });

        tbProd = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ordem Serviço", "Patrimônio", "Número de Série", "Modelo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProd.setColumnSelectionAllowed(true);
        tbProd.getTableHeader().setReorderingAllowed(false);
        tbProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdMouseClicked(evt);
            }
        });
        tbProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbProdKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbProd);
        tbProd.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbProd.getColumnModel().getColumnCount() > 0) {
            tbProd.getColumnModel().getColumn(0).setResizable(false);
            tbProd.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbProd.getColumnModel().getColumn(1).setResizable(false);
            tbProd.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbProd.getColumnModel().getColumn(2).setResizable(false);
            tbProd.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbProd.getColumnModel().getColumn(3).setResizable(false);
            tbProd.getColumnModel().getColumn(4).setResizable(false);
            tbProd.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel2.setText("Código Cliente");
        jLabel2.setToolTipText("");
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 16));

        jLabel3.setText("       Cliente");
        jLabel3.setToolTipText("");
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 16));

        txtCliNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliNomeKeyReleased(evt);
            }
        });

        jLabel13.setText("Número de Serie");

        txtLocalizaSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizaSerieKeyReleased(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/print.png"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir OS");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel14.setText("Patrimônio");

        txtLocalizaPat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalizaPatActionPerformed(evt);
            }
        });
        txtLocalizaPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizaPatKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocalizaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtCliNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLocalizaPat, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel14, jLabel2, jLabel3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdCli, txtLocalizaPat, txtLocalizaSerie});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtLocalizaPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdCli, txtLocalizaPat, txtLocalizaSerie});

        jLabel1.getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 1124, 683);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCliKeyReleased
        conta++;
        if (conta >= 3) {
            txtCliNome.setText("");
            txtLocalizaPat.setText("");
            txtLocalizaSerie.setText("");
            ArrayList<ClientesBean> clienteOs = clientes.pesquisarCliente("idcli",txtIdCli.getText());
            if (!clienteOs.isEmpty()) {
                txtCliNome.setText(clienteOs.get(0).getNomecli());
                setarTabela("idCli",clienteOs.get(0).getIdcli());
                conta = 0;
            } else {
                limpar();
                setarTabela();
            }
        }

    }//GEN-LAST:event_txtIdCliKeyReleased

    private void tbProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdMouseClicked
        setarCampos();
    }//GEN-LAST:event_tbProdMouseClicked

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        setarTabela();        
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCliNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliNomeKeyReleased
        conta++;
        if (conta >= 3) {
            txtLocalizaPat.setText("");
            txtLocalizaSerie.setText("");
            txtIdCli.setText("");
            ArrayList<ClientesBean> clienteOs = clientes.pesquisarCliente(txtCliNome.getText());
            if (!clienteOs.isEmpty()) {
                txtIdCli.setText(clienteOs.get(0).getIdcli());
                setarTabela("idCli",clienteOs.get(0).getIdcli());
                conta = 0;
            } else {
                limpar();
                setarTabela();
            }
        }
    }//GEN-LAST:event_txtCliNomeKeyReleased

    private void tbProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProdKeyReleased
        setarCampos();
    }//GEN-LAST:event_tbProdKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
       JOptionPane.showMessageDialog(null, " Ainda em construção...", "Não implementado ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource("/imagem/robo.png")));
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtLocalizaSerieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizaSerieKeyReleased
        conta++;
        if (conta >= 3) {
            txtLocalizaPat.setText("");
            txtIdCli.setText("");
            txtCliNome.setText("");
            EquipOsDAO equipOs = new EquipOsDAO();
            ArrayList<EquipOSBean> equip = equipOs.pesquisarProdutoBy("nserie", txtLocalizaSerie.getText());
            if (!equip.isEmpty()) {
                setarTabela("nserie", txtLocalizaSerie.getText()); 
                
                conta = 0;
            } else {
                limpar();
                setarTabela();
            }
        }
    }//GEN-LAST:event_txtLocalizaSerieKeyReleased

    private void txtLocalizaPatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizaPatKeyReleased
        conta++;
        if (conta >= 3) {
            txtLocalizaSerie.setText("");
            txtIdCli.setText("");
            txtCliNome.setText("");
            EquipOsDAO equipOs = new EquipOsDAO();
            ArrayList<EquipOSBean> equip = equipOs.pesquisarProdutoBy("patEquip", txtLocalizaPat.getText());
            if (!equip.isEmpty()) {                
                setarTabela("patEquip", txtLocalizaPat.getText());                 
                conta = 0;
            } else {
                limpar();
                setarTabela();
            }
        }
    }//GEN-LAST:event_txtLocalizaPatKeyReleased

    private void txtLocalizaPatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizaPatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizaPatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProd;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtLocalizaPat;
    private javax.swing.JTextField txtLocalizaSerie;
    // End of variables declaration//GEN-END:variables
}
