package viewRMA;

import Acessorios.Arquivos;
import Acessorios.CriaOsPdf;
import Bean.ClientesBean;
import Bean.DefSolBean;
import Bean.EquipOSBean;
import Bean.OrdServBean;
import DAO.ClienteDAO;
import DAO.DefSolDAO;
import DAO.EquipOsDAO;
import DAO.OrdServDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaOSFechada extends javax.swing.JInternalFrame {

    EquipOsDAO infoProduto = new EquipOsDAO();
    OrdServDAO ordemSErv = new OrdServDAO();
    DefSolDAO defSolDao = new DefSolDAO();
    private int conta = 0;
    private String idOs = "";

    private void setaTabelaEquipamento(String idOrdServ) {
        DefaultTableModel model = (DefaultTableModel) tblEquipamentos.getModel();
        model.setRowCount(0);
        ArrayList<EquipOSBean> infoProd = infoProduto.pesquisarProdutoBy("idOrdServ", idOrdServ);

        for (int i = 0; i < infoProd.size(); i++) {
            model.addRow(new Object[]{
                infoProd.get(i).getNserie(),
                infoProd.get(i).getPatEquip(),
                infoProd.get(i).getModel()
            });
        }
    }

    private void setaTabelaOs(String idOs) {
        DefaultTableModel model = (DefaultTableModel) tbOsCli.getModel();
        model.setRowCount(0);
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOsbyIdOs(idOs);
        String aberta = "Aberta";
        for (int i = 0; i < os.size(); i++) {
            if (!os.get(i).getAberta()) {
                aberta = "Finalizada";
            } else {
                aberta = "Aberta";
            }
            model.addRow(new Object[]{
                os.get(i).getIdOrdServ(),
                os.get(i).getNomeCli(),
                aberta,
                os.get(i).getDataAbertura(),
                os.get(i).getDataFechamento()
            });
        }
    }

    private void setaTabelaOsByCli(String idcli, String nomeCli) {
        DefaultTableModel model = (DefaultTableModel) tbOsCli.getModel();
        model.setRowCount(0);
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOs(idcli);
        String aberta = "Aberta";
        for (int i = 0; i < os.size(); i++) {
            if (!os.get(i).getAberta()) {
                aberta = "Finalizada";
            } else {
                aberta = "Aberta";
            }
            model.addRow(new Object[]{
                os.get(i).getIdOrdServ(),
                nomeCli,
                aberta,
                os.get(i).getDataAbertura(),
                os.get(i).getDataFechamento()
            });
        }
    }

    private void setaTabelaOs() {
        DefaultTableModel model = (DefaultTableModel) tbOsCli.getModel();
        model.setRowCount(0);
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOs();
        String aberta = "Aberta";
        for (int i = 0; i < os.size(); i++) {
            if (!os.get(i).getAberta()) {
                aberta = "Finalizada";
            } else {
                aberta = "Aberta";
            }
            model.addRow(new Object[]{
                os.get(i).getIdOrdServ(),
                os.get(i).getNomeCli(),
                aberta,
                os.get(i).getDataAbertura(),
                os.get(i).getDataFechamento()
            });
        }
    }

    public TelaOSFechada() {
        initComponents();
        setaTabelaOs();
        //btnOsImprimir.setEnabled(false);
    }

    private void getEquip() {
        int setar = tblEquipamentos.getSelectedRow();
        String nserie = tblEquipamentos.getValueAt(setar, 0).toString();
        ArrayList<DefSolBean> equip = defSolDao.listaDefeitos(nserie);
        String defeito = equip.get(0).getDefeito();
        String solucao = equip.get(0).getSolucao();
        JOptionPane.showMessageDialog(null, "Defeito: " + defeito + "\nSolução: " + solucao);
    }

    private String getOS(String idcli) {
        String ordemDeServico = "";
        OrdServDAO ordemSErv = new OrdServDAO();
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOs(idcli);
        int conta = 0;
        for (int i = 0; i < os.size(); i++) {
            if (os.get(i).getAberta()) {
                ordemDeServico += os.get(i).getIdOrdServ();
                ordemDeServico += "\n";
                conta++;
            }
        }
        if (conta == 0) {
            ordemDeServico = "Nenhuma ordem de serviço aberta para este cliente.";
        }
        return ordemDeServico;
    }

    private Boolean imprimirOs(String num_os, String path) {
        Boolean sucesso = false;
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOsbyIdOs(idOs);
        if (os.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ordem de serviço não encontrada.");
        } else {
            CriaOsPdf ordServPdf = new CriaOsPdf();
            ordServPdf.criaPdf(num_os, path);
            sucesso = true;

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEquipamentos = new javax.swing.JTable();
        btnOsImprimir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbOsCli = new javax.swing.JTable();
        btnOsLocalizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordens de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 480));
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordem de Serviço"));

        jLabel1.setText("Cliente:");

        txtOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOsKeyReleased(evt);
            }
        });

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel11.setText("Nº OS:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtCliPesquisar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 283, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamento"));

        tblEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Serial", "Patrimonio", "Modelo"
            }
        ));
        tblEquipamentos.getTableHeader().setReorderingAllowed(false);
        tblEquipamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEquipamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEquipamentos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        btnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/print.png"))); // NOI18N
        btnOsImprimir.setToolTipText("Imprimir OS");
        btnOsImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImprimir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsImprimirActionPerformed(evt);
            }
        });

        tbOsCli.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null}
            },
            new String [] {
                "Número OS", "Cliente", "Status", "Data Abertura", "Data Fechamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbOsCli.setColumnSelectionAllowed(true);
        tbOsCli.getTableHeader().setReorderingAllowed(false);
        tbOsCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOsCliMouseClicked(evt);
            }
        });
        tbOsCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbOsCliKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbOsCli);
        tbOsCli.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbOsCli.getColumnModel().getColumnCount() > 0) {
            tbOsCli.getColumnModel().getColumn(0).setResizable(false);
            tbOsCli.getColumnModel().getColumn(1).setResizable(false);
            tbOsCli.getColumnModel().getColumn(2).setResizable(false);
            tbOsCli.getColumnModel().getColumn(3).setResizable(false);
            tbOsCli.getColumnModel().getColumn(4).setResizable(false);
        }

        btnOsLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/read.png"))); // NOI18N
        btnOsLocalizar.setToolTipText("Imprimir OS");
        btnOsLocalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsLocalizar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsLocalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnOsLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsLocalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        conta++;
        if (conta >= 3) {
            ClienteDAO clientes = new ClienteDAO();
            ArrayList<ClientesBean> cliente = clientes.pesquisarCliente(txtCliPesquisar.getText());
            if (!cliente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Encontradas as ordems de serviço abertas: \n" + getOS(cliente.get(0).getIdcli()));
                setaTabelaOsByCli(cliente.get(0).getIdcli(), cliente.get(0).getNomecli());
                conta = 0;
            }

        }
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblEquipamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquipamentosMouseClicked
        getEquip();
    }//GEN-LAST:event_tblEquipamentosMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        //String num_os = JOptionPane.showInputDialog("Número da OS");
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnOsImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImprimirActionPerformed
        Arquivos arquivo = new Arquivos();
        if (idOs.isEmpty()) {
            idOs = JOptionPane.showInputDialog("Digite o número da OS");
        }
        String path = arquivo.getPath();
        if (imprimirOs(idOs, path)) {
            JOptionPane.showMessageDialog(null, "Ordem de serviço gerada com sucesso.");
          idOs = "";
        }

    }//GEN-LAST:event_btnOsImprimirActionPerformed

    private void tbOsCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOsCliMouseClicked
        int linha = tbOsCli.getSelectedRow();
        idOs = (String) tbOsCli.getValueAt(linha, 0);
        setaTabelaEquipamento(idOs);
        btnOsImprimir.setEnabled(true);
    }//GEN-LAST:event_tbOsCliMouseClicked

    private void txtOsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOsKeyReleased
        conta++;
        if (conta >= 3) {
            ArrayList<OrdServBean> os = ordemSErv.pesquisarOsbyIdOs(txtOs.getText());
            if (!os.isEmpty()) {
                txtCliPesquisar.setText(os.get(0).getNomeCli());
                setaTabelaOs(os.get(0).getIdOrdServ());
                conta = 0;
            }

        }
    }//GEN-LAST:event_txtOsKeyReleased

    private void btnOsLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsLocalizarActionPerformed
        setaTabelaOs();
        txtCliPesquisar.setText("");
        txtOs.setText("");
    }//GEN-LAST:event_btnOsLocalizarActionPerformed

    private void tbOsCliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbOsCliKeyPressed
        int linha = tbOsCli.getSelectedRow();
        String idOs = (String) tbOsCli.getValueAt(linha, 0);
        setaTabelaEquipamento(idOs);
    }//GEN-LAST:event_tbOsCliKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JButton btnOsLocalizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbOsCli;
    private javax.swing.JTable tblEquipamentos;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtOs;
    // End of variables declaration//GEN-END:variables
}
