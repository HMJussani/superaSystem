package viewRMA;

import Bean.ClientesBean;
import Bean.EquipOSBean;
import Bean.ModelosBean;
import DAO.ClienteDAO;
import DAO.EquipOsDAO;
import DAO.ModelosDAO;
import java.util.ArrayList;
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
    ClienteDAO clientes = new ClienteDAO();

    int conta = 0;

    public TelaInvetarioEquip() {
        initComponents();
        setModelo();

    }

    private void setModelo() {
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo();

        for (int i = 0; i < modelList.size(); i++) {
            String modelo = modelList.get(i).getModel().toString();
            cbModel.addItem(modelo);
        }
    }

    private void setarCampos() {
        int setar = tbProd.getSelectedRow();
        txtOrdemServ.setText(tbProd.getModel().getValueAt(setar, 0).toString());
        txtSerie.setText(tbProd.getModel().getValueAt(setar, 2).toString());
        txtPat.setText(tbProd.getModel().getValueAt(setar, 1).toString());
        for (int i = 0; i < cbModel.getItemCount(); i++) {
            if (cbModel.getItemAt(i).equals(tbProd.getModel().getValueAt(setar, 3).toString())) {
                cbModel.setSelectedIndex(i);                
            }
        }

    }

    private void setarTabela(String idCli) {
        DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
        model.setRowCount(0);
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProduto(idCli);
        for (int i = 0; i < equipList.size(); i++) {
            model.addRow(new Object[]{
                equipList.get(i).getidOrdServ(), // ordensServico.get(i).
                equipList.get(i).getPatEquip(),
                equipList.get(i).getNserie(),
                equipList.get(i).getModel(),});
        }
    }
    
        private void setarTabelaBySerie(String nserie) {
        DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
        model.setRowCount(0);
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProdutoBy("nserie", nserie);
        for (int i = 0; i < equipList.size(); i++) {
            model.addRow(new Object[]{
                equipList.get(i).getidOrdServ(), // ordensServico.get(i).
                equipList.get(i).getPatEquip(),
                equipList.get(i).getNserie(),
                equipList.get(i).getModel(),});
        }
    }

    private void limpar() {
        txtOrdemServ.setText("");
        txtSerie.setText("");
        txtPat.setText("");
        cbModel.setSelectedIndex(0);
    }

    private void getDados() {
        idOrdServ = txtOrdemServ.getText();
        nserie = txtSerie.getText();
        model = cbModel.getSelectedItem().toString();
        patEquip = txtPat.getText();
        idcli = txtIdCli.getText();
         if(boxGarantia.isSelected()){
            garantia=true;
        }else{
            garantia=false;
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
        btnRemover = new javax.swing.JButton();
        txtPat = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        txtIdCli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProd = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtOrdemServ = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        cbModel = new javax.swing.JComboBox<>();
        boxGarantia = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        txtLocalizaSerie = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();

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

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Excluir Equipamento");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Editar Equipamento");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar Equipamento");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
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
                "Ordem Serviço", "Patrimônio", "Número de Série", "Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            tbProd.getColumnModel().getColumn(1).setResizable(false);
            tbProd.getColumnModel().getColumn(2).setResizable(false);
            tbProd.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel7.setText("Modelo");

        jLabel11.setText("Número de Serie");

        jLabel12.setText("Patrimônio");

        jLabel15.setText("Ordem de Serviço");

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

        cbModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo" }));

        boxGarantia.setText("Em garantia");

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
                    .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocalizaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtCliNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxGarantia)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSerie)
                                .addComponent(txtPat)
                                .addComponent(cbModel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOrdemServ, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdCli, txtLocalizaSerie});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel2});

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrdemServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txtPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(boxGarantia)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdCli, txtLocalizaSerie});

        jLabel1.getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        getDados();
        EquipOsDAO equipOs = new EquipOsDAO();
        if (equipOs.adicionarEquipamento(nserie, idOrdServ, model, patEquip, idcli, garantia)) {
            JOptionPane.showMessageDialog(null, "Equipamento adicionando com sucesso");
            setarTabela(idcli);
        }

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtIdCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCliKeyReleased
        conta++;
        if (conta >= 3) {
            ArrayList<ClientesBean> clienteOs = clientes.pesquisarCliente(txtIdCli.getText());
            if (!clienteOs.isEmpty()) {
                txtCliNome.setText(clienteOs.get(0).getIdcli());
                setarTabela(txtIdCli.getText());
                conta = 0;

            } else {
                limpar();
            }
        }

    }//GEN-LAST:event_txtIdCliKeyReleased

    private void tbProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdMouseClicked
        setarCampos();
    }//GEN-LAST:event_tbProdMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        getDados();
        EquipOsDAO equipOs = new EquipOsDAO();
        if (equipOs.editarProduto(nserie, idOrdServ, model, patEquip, idcli, garantia)) {
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso");
            setarTabela(idcli);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        getDados();
        EquipOsDAO equipOs = new EquipOsDAO();
        if (equipOs.excluirProduto(nserie)) {
            JOptionPane.showMessageDialog(null, "Equipamento removido com sucesso");
            setarTabela(idcli);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCliNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliNomeKeyReleased
        conta++;
        if (conta >= 3) {
            ArrayList<ClientesBean> clienteOs = clientes.pesquisarCliente(txtCliNome.getText());
            if (!clienteOs.isEmpty()) {
                txtIdCli.setText(clienteOs.get(0).getIdcli());
                setarTabela(clienteOs.get(0).getIdcli());
                conta = 0;

            } else {
                limpar();
            }
        }
    }//GEN-LAST:event_txtCliNomeKeyReleased

    private void tbProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProdKeyReleased
        setarCampos();
    }//GEN-LAST:event_tbProdKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // imprimirOs();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtLocalizaSerieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizaSerieKeyReleased
        conta++;
        if (conta >= 3) {
            EquipOsDAO equipOs = new EquipOsDAO();
            ArrayList<EquipOSBean> equip = equipOs.pesquisarProdutoBy("nserie", txtLocalizaSerie.getText());
            if (!equip.isEmpty()) {
                setarTabelaBySerie(equip.get(0).getNserie());
                conta = 0;

            } else {
                //JOptionPane.showMessageDialog(null, "Número de Série não encontrado.");
                limpar();
            }
        }
    }//GEN-LAST:event_txtLocalizaSerieKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxGarantia;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cbModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProd;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtLocalizaSerie;
    private javax.swing.JTextField txtOrdemServ;
    private javax.swing.JTextField txtPat;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
