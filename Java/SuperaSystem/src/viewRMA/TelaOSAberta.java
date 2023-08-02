package viewRMA;

import Acessorios.Acessorios;
import Acessorios.CriarBurnTest;
import Bean.ClientesBean;
import Bean.DefSolBean;
import Bean.EquipOSBean;
import Bean.ModelosBean;
import Bean.OrdServBean;
import Bean.UsuariosBean;
import DAO.ClienteDAO;
import DAO.DefSolDAO;
import DAO.EquipOsDAO;
import DAO.ModelosDAO;
import DAO.OrdServDAO;
import DAO.UsuariosDAO;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.TelaPrincipal;

public class TelaOSAberta extends javax.swing.JInternalFrame {

    private String nserie = null;
    private String idOrdServ = null;
    private String model = null;
    private String patEquip = null;
    private String idcli = null;
    private java.sql.Date dataAbertura = null;
    private Boolean garantia = false;
    private String defeito = null;
    private String tecnico = null;
    private String valor = null;
    private String solucao = null;
    private String perfil = TelaPrincipal.lblLogado.getText();
    Acessorios arquivo = new Acessorios();
    private int conta = 0;

    public TelaOSAberta() {
        initComponents();
        txtDataAbertura.setText(arquivo.setData());
        setTecnico();
        setModelo();
    }

    private void setTecnico() {
        UsuariosDAO userDAO = new UsuariosDAO();
        ArrayList<UsuariosBean> tecList = userDAO.pesquisarUser();
        for (int i = 0; i < tecList.size(); i++) {
            String tec = tecList.get(i).getNome();
            cbTecnico.addItem(tec);

        }
    }

    private void setModelo() {
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo();
        for (int i = 0; i < modelList.size(); i++) {
            String modelo = modelList.get(i).getModel();
            cbModel.addItem(modelo);
        }
    }

    private void getDados() {
        nserie = txtSerialNumber.getText();
        idOrdServ = txtNumOS.getText();
        model = cbModel.getSelectedItem().toString();
        patEquip = txtPat.getText();
        idcli = txtIDcli.getText();
        dataAbertura = getData();
        tecnico = cbTecnico.getSelectedItem().toString();
        valor = txtOsValor.getText();
        defeito = txtDefeito.getText();
        solucao = txtServico.getText();
        if (boxGarantia.isSelected()) {
            garantia = true;
        } else {
            garantia = false;
        }

    }

    private void setBtn(String ordServ) {
        OrdServDAO ordemServico = new OrdServDAO();
        ArrayList<OrdServBean> ordenList = ordemServico.pesquisarOsbyIdOs(ordServ);
        if (!ordenList.isEmpty()) {
            cbTecnico.setSelectedItem(ordenList.get(0).getTecnico());
            txtOsValor.setText(ordenList.get(0).getValor());
            getClientes(ordenList.get(0).getIdcli());
            btnOsAdicionar.setEnabled(false);
            btnAdicionaEquip.setEnabled(ordenList.get(0).getAberta());
            btnEditarEquip.setEnabled(ordenList.get(0).getAberta());
            btnOsAlterar.setEnabled(ordenList.get(0).getAberta());
            btnOsFinaliza.setEnabled(ordenList.get(0).getAberta());
            cbModel.setEnabled(ordenList.get(0).getAberta());
            txtPat.setEnabled(ordenList.get(0).getAberta());
            txtSerialNumber.setEnabled(ordenList.get(0).getAberta());
            boxGarantia.setEnabled(ordenList.get(0).getAberta());
            cbTecnico.setEnabled(ordenList.get(0).getAberta());
            txtOsValor.setEnabled(ordenList.get(0).getAberta());
        }
    }

    private void getDados(String ordServ, int i) {
        EquipOsDAO equipDao = new EquipOsDAO();
        tecnico = cbTecnico.getSelectedItem().toString();
        model = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getModel();
        nserie = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getNserie();
        idOrdServ = ordServ;
        patEquip = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getPatEquip();

    }

    private java.sql.Date getData() {
        java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
        return data;
    }

    private void setarTabela(String idCli) {
        DefaultTableModel model = (DefaultTableModel) tbEquip.getModel();
        model.setRowCount(0);
        String garantia = "Não", estado = "Analizando";
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProdutoBy("idOrdServ", idCli);
        for (int i = 0; i < equipList.size(); i++) {
            if (equipList.get(i).getGarantia()) {
                garantia = "Sim";
            } else {
                garantia = "Não";
            }
            if (equipList.get(i).getAnalizado()) {
                estado = "Resolvido";
            } else {
                estado = "Analizando";
            }
            model.addRow(new Object[]{
                equipList.get(i).getPatEquip(),
                equipList.get(i).getNserie(),
                equipList.get(i).getModel(),
                garantia,
                estado});
        }
    }

    private void limpaTabela() {
        DefaultTableModel model = (DefaultTableModel) tbEquip.getModel();
        model.setRowCount(0);
    }

    private void getClientes(ArrayList<ClientesBean> clientesBean) {
        txtContatoCli.setText(clientesBean.get(0).getContatocli());
        txtEmailCli.setText(clientesBean.get(0).getEmailcli());
        txtCliNome.setText(clientesBean.get(0).getNomecli());
        txtIDcli.setText(clientesBean.get(0).getIdcli());

    }

    private void getClientes(String idcli) {
        ClienteDAO clientesDao = new ClienteDAO();
        ArrayList<ClientesBean> clientesBean = clientesDao.pesquisarCliente("idcli", idcli);
        txtContatoCli.setText(clientesBean.get(0).getContatocli());
        txtEmailCli.setText(clientesBean.get(0).getEmailcli());
        txtCliNome.setText(clientesBean.get(0).getNomecli());
        txtIDcli.setText(clientesBean.get(0).getIdcli());

    }

    private String getOS(String idcli) {
        String ordemDeServico = "";
        OrdServDAO ordemSErv = new OrdServDAO();
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOsBy("idcli", idcli);
        int conta = 0;
        for (int i = 0; i < os.size(); i++) {
            if (os.get(i).getAberta()) {
                ordemDeServico += os.get(i).getIdOrdServ();
                ordemDeServico += "\n";
                conta++;
            }
        }
        if (conta == 0) {
            ordemDeServico = "zero";
        }
        return ordemDeServico;
    }

    private void imprimirOs() {
        String[] itens = {"Seven", "BurnTest", "PDF"};
        Object opcao = (String) JOptionPane.showInputDialog(null, "Oque quer imprimir?", "Ordens de Serviço",
                JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
        System.out.println();
        if (opcao == null) {
            opcao = "vazio";
        }
        switch (opcao.toString()) {
            case "Seven":
                JOptionPane.showMessageDialog(null, " Ainda em construção...", "Não implementado ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource("/imagem/robo.png")));
                break;
            case "BurnTest":
                getDados();
                burnTestTxt(idOrdServ);
                break;
            case "PDF":
                JOptionPane.showMessageDialog(null, " Ainda em construção...", "Não implementado ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource("/imagem/robo.png")));
                break;
            default:
                break;
        }
    }

    private void burnTestTxt(String idOrdServ) {
        CriarBurnTest txtburn = new CriarBurnTest();
        String dir = arquivo.getPath() + "\\" + idOrdServ;
        EquipOsDAO equipOs = new EquipOsDAO();
        ArrayList<EquipOSBean> equipList = equipOs.pesquisarProdutoBy("idOrdServ", idOrdServ);
        int nDir = equipList.size();
        if (!dir.isEmpty()) {
            if (nDir > 1) {
                JOptionPane.showMessageDialog(null, "Serão criados " + nDir + " sub diretórios.");
                if (arquivo.criaDir(dir)) {
                    for (int i = 0; i < nDir; i++) {
                        String path = (dir + "\\" + equipList.get(i).getNserie());
                        getDados(idOrdServ, i);
                        txtburn.criarBurnTxt(path, idOrdServ, model, patEquip, nserie, tecnico);
                    }
                    JOptionPane.showMessageDialog(null, "Arquivos criados com sucesso.");
                }
            } else {
                if (arquivo.criaDir(dir)) {
                    String path = (dir + "\\" + equipList.get(0).getNserie());
                    getDados();
                    txtburn.criarBurnTxt(path, idOrdServ, model, patEquip, nserie, tecnico);
                    JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo não criado.");
        }

    }

    private void setarOs(int linha) {
        DefSolDAO defSolDAO = new DefSolDAO();
        ArrayList<DefSolBean> defSolBean = defSolDAO.listaDefeitos(tbEquip.getValueAt(linha, 1).toString());
        if (!defSolBean.isEmpty()) {
            txtDefeito.setText(defSolBean.get(0).getDefeito());
            txtServico.setText(defSolBean.get(0).getSolucao());
        } else {
            txtDefeito.setText("");
            txtServico.setText("");
        }

        if (tbEquip.getValueAt(linha, 4).equals("Resolvido")) {
            btnEditarEquip.setEnabled(false);
        } else {
            btnEditarEquip.setEnabled(true);
        }

    }

    private void limpar() {
        limpaTabela();
        txtContatoCli.setText(null);
        txtEmailCli.setText(null);
        txtIDcli.setText(null);
        txtDefeito.setText("");
        txtServico.setText("");
        cbTecnico.setSelectedIndex(0);
        txtOsValor.setText(null);
    }

    private void setarCampos(int linha) {
        txtSerialNumber.setText(tbEquip.getValueAt(linha, 1).toString());
        txtPat.setText(tbEquip.getValueAt(linha, 0).toString());
        cbModel.setSelectedItem(tbEquip.getValueAt(linha, 2).toString());
        String garantia = tbEquip.getValueAt(linha, 3).toString();
        if (garantia.equals("Sim")) {
            boxGarantia.setSelected(true);
        } else {
            boxGarantia.setSelected(false);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumOS = new javax.swing.JTextField();
        txtDataAbertura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtIDcli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbTecnico = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        cbModel = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSerialNumber = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtContatoCli = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEmailCli = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDefeito = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtServico = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEquip = new javax.swing.JTable();
        btnAdicionaEquip = new javax.swing.JButton();
        boxGarantia = new javax.swing.JCheckBox();
        btnEditarEquip = new javax.swing.JButton();
        btnOsImprimir = new javax.swing.JButton();
        btnOsFinaliza = new javax.swing.JButton();
        btnOsAlterar = new javax.swing.JButton();
        btnOsAdicionar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordens de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 480));
        setVisible(true);
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

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setText("Cliente");

        jLabel2.setText("Data Abertura:");

        txtNumOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumOSKeyReleased(evt);
            }
        });

        txtDataAbertura.setEditable(false);

        jLabel11.setText("Número OS");

        txtIDcli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDcliKeyReleased(evt);
            }
        });

        jLabel4.setText("Cód. Cliente");

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });
        txtCliNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliNomeKeyReleased(evt);
            }
        });

        jLabel13.setText("Patrimônio");

        jLabel8.setText("Serviço Realizado");

        cbTecnico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tecnico" }));
        cbTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTecnicoActionPerformed(evt);
            }
        });

        jLabel9.setText("Técnico");

        jLabel10.setText("Valor Total");

        txtOsValor.setText("0");

        cbModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modelo" }));
        cbModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModelActionPerformed(evt);
            }
        });

        jLabel7.setText(" Defeito Reclamado");

        jLabel12.setText("Número de Série");

        jLabel5.setText("Contato");

        jLabel14.setText("Email");

        txtEmailCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailCliActionPerformed(evt);
            }
        });
        txtEmailCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailCliKeyReleased(evt);
            }
        });

        txtDefeito.setColumns(20);
        txtDefeito.setRows(5);
        jScrollPane2.setViewportView(txtDefeito);

        txtServico.setColumns(20);
        txtServico.setRows(5);
        jScrollPane1.setViewportView(txtServico);

        tbEquip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Patrimônio", "Número de Série", "Modelo", "Garantia", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
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
        tbEquip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbEquipKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tbEquip);
        tbEquip.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbEquip.getColumnModel().getColumnCount() > 0) {
            tbEquip.getColumnModel().getColumn(0).setResizable(false);
            tbEquip.getColumnModel().getColumn(0).setPreferredWidth(5);
            tbEquip.getColumnModel().getColumn(1).setResizable(false);
            tbEquip.getColumnModel().getColumn(1).setPreferredWidth(5);
            tbEquip.getColumnModel().getColumn(2).setResizable(false);
            tbEquip.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbEquip.getColumnModel().getColumn(3).setResizable(false);
            tbEquip.getColumnModel().getColumn(3).setPreferredWidth(5);
            tbEquip.getColumnModel().getColumn(4).setResizable(false);
            tbEquip.getColumnModel().getColumn(4).setPreferredWidth(5);
        }

        btnAdicionaEquip.setText("Adicionar");
        btnAdicionaEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionaEquipActionPerformed(evt);
            }
        });

        boxGarantia.setText("Garantia");

        btnEditarEquip.setText("Finalizar");
        btnEditarEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEquipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTecnico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbModel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(txtSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtPat)
                        .addGap(10, 10, 10)
                        .addComponent(boxGarantia)
                        .addGap(10, 10, 10)
                        .addComponent(btnAdicionaEquip)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditarEquip))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContatoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDcli, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(txtNumOS, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel14))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                .addComponent(txtEmailCli)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel4, jLabel5, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel14});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTecnico, txtContatoCli, txtIDcli, txtNumOS});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtPat, txtSerialNumber});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionaEquip, btnEditarEquip});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(txtNumOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(txtIDcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtContatoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txtSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionaEquip)
                    .addComponent(boxGarantia)
                    .addComponent(btnEditarEquip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel8)
                        .addGap(40, 40, 40))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(37, 37, 37))
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

        btnOsFinaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/delete.png"))); // NOI18N
        btnOsFinaliza.setToolTipText("Finalizar OS");
        btnOsFinaliza.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsFinaliza.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsFinaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsFinalizaActionPerformed(evt);
            }
        });

        btnOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/update.png"))); // NOI18N
        btnOsAlterar.setToolTipText("Editar OS");
        btnOsAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAlterarActionPerformed(evt);
            }
        });

        btnOsAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/create.png"))); // NOI18N
        btnOsAdicionar.setToolTipText("Emitir OS");
        btnOsAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnOsFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1124, 683);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliNomeKeyReleased
        conta++;
        if (conta >= 3) {
            ClienteDAO clientesDao = new ClienteDAO();
            ArrayList<ClientesBean> cliente = clientesDao.pesquisarCliente(txtCliNome.getText());
            if (!cliente.isEmpty()) {
                getClientes(cliente);
                if (!getOS(cliente.get(0).getIdcli()).equals("zero")) {
                    JOptionPane.showMessageDialog(null, "Encontradas as ordems de serviço: \n" + getOS(cliente.get(0).getIdcli()));
                }
                conta = 0;
            } else {
                limpar();
            }
        }
    }//GEN-LAST:event_txtCliNomeKeyReleased

    private void btnOsAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAdicionarActionPerformed
        getDados();
        OrdServDAO ordemServico = new OrdServDAO();
        if (ordemServico.novaOs(idOrdServ, idcli, dataAbertura, tecnico, valor)) {
            JOptionPane.showMessageDialog(null, "Ordem de Serviço criada com sucesso");
            btnOsAdicionar.setEnabled(false);
        }

    }//GEN-LAST:event_btnOsAdicionarActionPerformed

    private void btnOsAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAlterarActionPerformed
        getDados();
        DefSolDAO defeitos = new DefSolDAO();
        int linha = tbEquip.getSelectedRow();
        

        String[] itens = {"Equipamento", "Ordem Serviço"};
        Object opcao = (String) JOptionPane.showInputDialog(null, "Oque deseja fazer?", "Ordens de Serviço",
                JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

        if (opcao == null) {
            opcao = "vazio";
        }
        switch (opcao.toString()) {
            case "Equipamento":
                String serial = tbEquip.getModel().getValueAt(linha, 1).toString();
                if (defeitos.editaDefeito(serial, defeito, solucao)) {
                    JOptionPane.showMessageDialog(null, "Equipamento alterado com sucesso");
                    //limpar();
                }
                break;

            case "Ordem Serviço":
                OrdServDAO ordemServico = new OrdServDAO();
                getDados();
                if (ordemServico.editarOs(idOrdServ, dataAbertura, true, valor)) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviços alterada com sucesso");
                }
                break;

            default:                
                opcao= "Equipamento";
                break;
        }


    }//GEN-LAST:event_btnOsAlterarActionPerformed

    private void btnOsFinalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsFinalizaActionPerformed
        getDados();
        OrdServDAO ordemServico = new OrdServDAO();
        if (perfil.equals("admin")) {
            String[] itens = {"Finalizar", "Excluir"};
            Object opcao = (String) JOptionPane.showInputDialog(null, "Oque deseja fazer?", "Ordens de Serviço",
                    JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

            if (opcao == null) {
                opcao = "vazio";
            }
            switch (opcao.toString()) {
                case "Finalizar":
                    if (ordemServico.editarOs(idOrdServ, getData(), false, valor)) {
                        limpar();
                        txtNumOS.setText("");
                        JOptionPane.showMessageDialog(null, "Ordem de serviços finalizada com sucesso");
                    }
                    break;

                case "Excluir":
                    if (ordemServico.excluirOs(true, idOrdServ)) {
                        limpar();
                        txtNumOS.setText("");
                        JOptionPane.showMessageDialog(null, "Ordem de serviços excluída com sucesso");
                    }
                    break;

                default:
                    if (ordemServico.editarOs(idOrdServ, getData(), false, valor)) {
                        limpar();
                        txtNumOS.setText("");
                        JOptionPane.showMessageDialog(null, "Ordem de serviços finalizada com sucesso");
                    }
                    break;
            }
        } else {
            if (ordemServico.editarOs(idOrdServ, getData(), false, valor)) {
                limpar();
                txtNumOS.setText("");
                JOptionPane.showMessageDialog(null, "Ordem de serviços finalizada com sucesso");
            }
        }

    }//GEN-LAST:event_btnOsFinalizaActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        //String num_os = JOptionPane.showInputDialog("Número da OS");
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void btnOsImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImprimirActionPerformed
        imprimirOs();
    }//GEN-LAST:event_btnOsImprimirActionPerformed

    private void cbTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTecnicoActionPerformed

    private void cbModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbModelActionPerformed

    private void txtEmailCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailCliActionPerformed

    private void txtEmailCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailCliKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailCliKeyReleased

    private void btnAdicionaEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaEquipActionPerformed
        setTecnico();
        getDados();
        if (defeito.isEmpty()) {
            defeito = "Verificar";
        }
        if (solucao.isEmpty()) {
            solucao = "Verificar";
        }
        EquipOsDAO equipOs = new EquipOsDAO();
        DefSolDAO defSol = new DefSolDAO();
        if (equipOs.adicionarEquipamento(nserie, idOrdServ, model, patEquip, idcli, garantia) && defSol.novoDefeito(nserie, defeito, solucao)) {
            JOptionPane.showMessageDialog(null, "Equipamento adicionando com sucesso");
            setarTabela(idOrdServ);
        }
    }//GEN-LAST:event_btnAdicionaEquipActionPerformed

    private void tbEquipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEquipMouseClicked
        setarOs(tbEquip.getSelectedRow());
        setarCampos(tbEquip.getSelectedRow());
    }//GEN-LAST:event_tbEquipMouseClicked

    private void txtNumOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumOSKeyReleased
        conta++;
        if (conta >= 3) {
            OrdServDAO ordemServico = new OrdServDAO();
            ArrayList<OrdServBean> ordenList = ordemServico.pesquisarOsbyIdOs(txtNumOS.getText());
            if (!ordenList.isEmpty()) {
                setarTabela(ordenList.get(0).getIdOrdServ());
                cbTecnico.setSelectedItem(ordenList.get(0).getTecnico());
                txtOsValor.setText(ordenList.get(0).getValor());
                getClientes(ordenList.get(0).getIdcli());
                btnOsAdicionar.setEnabled(false);
                btnAdicionaEquip.setEnabled(ordenList.get(0).getAberta());
                btnEditarEquip.setEnabled(ordenList.get(0).getAberta());
                btnOsAlterar.setEnabled(ordenList.get(0).getAberta());
                if (!perfil.equals("admin")) {
                    btnOsFinaliza.setEnabled(ordenList.get(0).getAberta());
                }
                cbModel.setEnabled(ordenList.get(0).getAberta());
                txtPat.setEnabled(ordenList.get(0).getAberta());
                txtSerialNumber.setEnabled(ordenList.get(0).getAberta());
                boxGarantia.setEnabled(ordenList.get(0).getAberta());
                cbTecnico.setEnabled(ordenList.get(0).getAberta());
                txtOsValor.setEnabled(ordenList.get(0).getAberta());
                conta = 0;
            } else {
                btnOsAdicionar.setEnabled(true);
                limpar();
            }
        }
    }//GEN-LAST:event_txtNumOSKeyReleased

    private void tbEquipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEquipKeyReleased
        setarOs(tbEquip.getSelectedRow());
        setarCampos(tbEquip.getSelectedRow());
    }//GEN-LAST:event_tbEquipKeyReleased

    private void btnEditarEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquipActionPerformed
        getDados();
        int confirma = JOptionPane.showConfirmDialog(null, "Finalizar a análise deste equipamento?", "Finalizar Análise", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            EquipOsDAO equipDAO = new EquipOsDAO();
            if (equipDAO.finalizarAnalise(nserie)) {
                JOptionPane.showMessageDialog(null, "Análise concluída com sucesso");
                setarTabela(idOrdServ);
            } else {
                if (equipDAO.editarProduto(nserie, model, patEquip, garantia)) {
                    JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso");
                    setarTabela(idOrdServ);
                }
            }
        }
    }//GEN-LAST:event_btnEditarEquipActionPerformed

    private void txtIDcliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDcliKeyReleased
        conta++;
        if (conta >= 3) {
            ClienteDAO clientesDao = new ClienteDAO();
            ArrayList<ClientesBean> cliente = clientesDao.pesquisarCliente("idcli", txtIDcli.getText());
            if (!cliente.isEmpty()) {
                getClientes(cliente);
                if (!getOS(cliente.get(0).getIdcli()).equals("zero")) {
                    JOptionPane.showMessageDialog(null, "Encontradas as ordems de serviço: \n" + getOS(cliente.get(0).getIdcli()));
                }
                conta = 0;
            } else {
                limpar();
            }
        }
    }//GEN-LAST:event_txtIDcliKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxGarantia;
    private javax.swing.JButton btnAdicionaEquip;
    private javax.swing.JButton btnEditarEquip;
    private javax.swing.JButton btnOsAdicionar;
    private javax.swing.JButton btnOsAlterar;
    private javax.swing.JButton btnOsFinaliza;
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JComboBox<String> cbModel;
    private javax.swing.JComboBox<String> cbTecnico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbEquip;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtContatoCli;
    private javax.swing.JTextField txtDataAbertura;
    private javax.swing.JTextArea txtDefeito;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtIDcli;
    private javax.swing.JTextField txtNumOS;
    private javax.swing.JTextField txtOsValor;
    private javax.swing.JTextField txtPat;
    private javax.swing.JTextField txtSerialNumber;
    private javax.swing.JTextArea txtServico;
    // End of variables declaration//GEN-END:variables
}
