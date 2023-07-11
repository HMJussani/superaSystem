package viewProd;

import Bean.ModelosBean;
import DAO.ModelosDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de gestão de clientes
 *
 * @author HMJussani
 */
public class TelaModelos extends javax.swing.JInternalFrame {

    
    private String model;
    private String mem; 
    private String mBoard;
    private String expansao;
    private String armazenaTipo;
     private String armazenaModel;
    private String fonteAlimenta;
    private String sParalela;
    private String sSerial; 
    private String redeLan;
    private String wifi; 
     private String tipo;
    private String processador;
    private String gabinete;
    int conta = 0;

    public TelaModelos() {
        initComponents();
        setarTabela();
    }

    private void setarTabela() {
        ModelosDAO equipDao = new ModelosDAO();
        DefaultTableModel model = (DefaultTableModel) tbEquip.getModel();
        model.setRowCount(0);
        ArrayList<ModelosBean> modeloEquip = equipDao.pesquisarModelo();

        for (int i = 0; i < modeloEquip.size(); i++) {
            model.addRow(new Object[]{
                modeloEquip.get(i).getModel(),
                modeloEquip.get(i).getmBoard(),
                modeloEquip.get(i).getProcessador(),
                modeloEquip.get(i).getMem(),
                modeloEquip.get(i).getArmazenaModel(),
                modeloEquip.get(i).getFonteAlimenta()

            });
        }
    }

    private void getDados() {
        model = txtModel.getText();
        mem = txtMemoria.getText();
        mBoard = txtMotherBoard.getText();
        processador = txtCPU.getText();
        tipo = txtTipo.getText();
        armazenaModel = txtArmazenamento.getText();
        fonteAlimenta = txtAlimentacao.getText();
        sParalela = txtParalela.getText();
        sSerial = txtSerial.getText();
        gabinete = txtGabinete.getText();
        redeLan = txtRede.getText();
        wifi = txtWifi.getText();
    }

    private void setarCampos(ArrayList<ModelosBean> lista) {
        txtModel.setText(lista.get(0).getModel());
        txtMemoria.setText(lista.get(0).getMem());
        txtMotherBoard.setText(lista.get(0).getmBoard());
        txtAlimentacao.setText(lista.get(0).getFonteAlimenta());
        txtArmazenamento.setText(lista.get(0).getArmazenaModel());
        txtParalela.setText(lista.get(0).getsParalela());
        txtSerial.setText(lista.get(0).getsSerial());
        txtRede.setText(lista.get(0).getRedeLan());
        txtWifi.setText(lista.get(0).getWifi());
        txtTipo.setText(lista.get(0).getTipo());
        txtCPU.setText(lista.get(0).getProcessador());
        txtGabinete.setText(lista.get(0).getGabinete());
        btnAdicionar.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnRemover.setEnabled(true);
    }

    private void setarCampos() {
        int setar = tbEquip.getSelectedRow();
        ModelosDAO computador = new ModelosDAO();
        String modelo = tbEquip.getValueAt(setar, 0).toString();
        ArrayList<ModelosBean> equipamento = computador.pesquisarModelo(modelo);
        if (!equipamento.isEmpty()) {
            setarCampos(equipamento);
        }
    }

    private void limpar() {
        txtModel.setText(null);
        txtGabinete.setText(null);
        txtTipo.setText(null);
        txtMemoria.setText(null);
        txtMotherBoard.setText(null);
        txtCPU.setText(null);
        txtArmazenamento.setText(null);
        txtAlimentacao.setText(null);
        txtParalela.setText(null);
        txtSerial.setText(null);
        txtRede.setText(null);
        txtWifi.setText(null);
        btnAdicionar.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnRemover.setEnabled(false);
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
        btnRemover = new javax.swing.JButton();
        txtCPU = new javax.swing.JTextField();
        txtArmazenamento = new javax.swing.JTextField();
        txtMemoria = new javax.swing.JTextField();
        txtAlimentacao = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMotherBoard = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtParalela = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtRede = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtWifi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtGabinete = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEquip = new javax.swing.JTable();
        txtExpansao = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Modelos");
        setPreferredSize(new java.awt.Dimension(645, 495));

        jLabel2.setText("Processador");

        jLabel3.setText("Memória");

        jLabel4.setText("Armazenamento");

        jLabel5.setText("Alimentação");

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Excluir cliente");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setEnabled(false);
        btnRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Editar cliente");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setEnabled(false);
        btnAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar cliente");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jLabel7.setText("MotherBoard");

        jLabel8.setText("Expansões");

        txtSerial.setText("1");

        jLabel9.setText("Serial");

        txtParalela.setText("0");

        jLabel10.setText("Paralela");

        jLabel11.setText("Modelo");

        jLabel12.setText("Tipo");

        txtTipo.setText("ThinClient");

        jLabel13.setText("Rede LAN");

        txtRede.setText("onboard");

        jLabel14.setText("Rede  WiFi");

        txtWifi.setText("não");

        jLabel15.setText("Gabinete");

        tbEquip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Modelo", "MotherBoard", "Processador ", "Memória", "Armazenamento", "Alimentação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEquip.setColumnSelectionAllowed(true);
        tbEquip.getTableHeader().setReorderingAllowed(false);
        tbEquip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEquipMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbEquip);
        tbEquip.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbEquip.getColumnModel().getColumnCount() > 0) {
            tbEquip.getColumnModel().getColumn(0).setResizable(false);
            tbEquip.getColumnModel().getColumn(1).setResizable(false);
            tbEquip.getColumnModel().getColumn(2).setResizable(false);
            tbEquip.getColumnModel().getColumn(3).setResizable(false);
            tbEquip.getColumnModel().getColumn(4).setResizable(false);
            tbEquip.getColumnModel().getColumn(5).setResizable(false);
        }

        txtExpansao.setText("Sem Expansões");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtExpansao)
                                    .addComponent(txtRede)
                                    .addComponent(txtMotherBoard)
                                    .addComponent(txtCPU)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtArmazenamento))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtWifi)
                                            .addComponent(txtGabinete)
                                            .addComponent(txtTipo)
                                            .addComponent(txtAlimentacao, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtParalela, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(txtMotherBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtGabinete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txtArmazenamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtAlimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(txtRede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtWifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtExpansao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel9)
                        .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtParalela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        getDados();
        ModelosDAO computador = new ModelosDAO();
        if (computador.adicionarModelo(model, mem, mBoard, expansao, armazenaTipo, armazenaModel, fonteAlimenta, sParalela, sSerial, redeLan, wifi, tipo, processador, gabinete)) {
            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso.");
            setarTabela();
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        getDados();
        ModelosDAO computador = new ModelosDAO();
        if (computador.editarModelo(model, mem, mBoard, expansao, armazenaTipo, armazenaModel, fonteAlimenta, sParalela, sSerial, redeLan, wifi, tipo, processador, gabinete)) {
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        String model = txtModel.getText();
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            if (model.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira o Modelo a ser excluído.");
            } else {
                ModelosDAO computador = new ModelosDAO();
                if (computador.excluirModelo(model)) {
                    limpar();
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                    setarTabela();
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void tbEquipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEquipMouseClicked
        setarCampos();
    }//GEN-LAST:event_tbEquipMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbEquip;
    private javax.swing.JTextField txtAlimentacao;
    private javax.swing.JTextField txtArmazenamento;
    private javax.swing.JTextField txtCPU;
    private javax.swing.JTextField txtExpansao;
    private javax.swing.JTextField txtGabinete;
    private javax.swing.JTextField txtMemoria;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtMotherBoard;
    private javax.swing.JTextField txtParalela;
    private javax.swing.JTextField txtRede;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtWifi;
    // End of variables declaration//GEN-END:variables
}
