package viewRMA;

import Acessorios.Acessorios;
import Acessorios.CriarTxt;
import Bean.DefSolBean;
import Bean.EquipOSBean;
import Bean.ModelosBean;
import Bean.OrdServBean;
import Bean.UsuariosBean;
import DAO.DefSolDAO;
import DAO.EquipOsDAO;
import DAO.ModelosDAO;
import DAO.OrdServDAO;
import DAO.UsuariosDAO;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaOrcamento extends javax.swing.JInternalFrame {

    private String nserie = null;
    private String idOrdServ = null;
    private String model = null;
    private String patEquip = null;
    private String idcli = null;
    private java.sql.Date dataAbertura = null;
    private Boolean garantia = false;
    private Boolean fechada = false;
    private String defeito = null;
    private String tecnico = null;
    private String valor = null;
    private String solucao = null;
    Acessorios arquivo = new Acessorios();
    private int conta = 0;

    public TelaOrcamento() {
        initComponents();
        setTecnico();
        setModelo();
       
    }

    private void setTecnico() {
        UsuariosDAO userDAO = new UsuariosDAO();
        ArrayList<UsuariosBean> tecList = userDAO.pesquisarUser();
        for (int i = 0; i < tecList.size(); i++) {
            String tec = tecList.get(i).getNome();
            
        }
    }

    private void setModelo() {
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo();
        for (int i = 0; i < modelList.size(); i++) {
            String modelo = modelList.get(i).getModel();
            
        }
    }

    private void getDados() {
       

    }
    
    private void getDados(String ordServ, int i) {
        EquipOsDAO equipDao = new EquipOsDAO();
      //  tecnico = cbTecnico.getSelectedItem().toString();
        model = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getModel();
        nserie = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getNserie();
        idOrdServ = ordServ;
        patEquip = equipDao.pesquisarProdutoBy("idOrdServ", ordServ).get(i).getPatEquip();
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
        CriarTxt txtburn = new CriarTxt();
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
                        getDados(idOrdServ,i);
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
            
        } else {           
           
        }

        if (tbEquip.getValueAt(linha, 4).equals("Resolvido")) {
           
        } else {
           
        }

    }

    private void limpar() {
        limpaTabela();
        
        txtOsValor.setText(null);
    }

    private void setarCampos(int linha) {
       
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
        txtNumOS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEquip = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        chekBat = new javax.swing.JCheckBox();
        chekSuporte = new javax.swing.JCheckBox();
        cbMother = new javax.swing.JComboBox<>();
        cbProc = new javax.swing.JComboBox<>();
        cbMem = new javax.swing.JComboBox<>();
        cbArm = new javax.swing.JComboBox<>();
        cbAlim = new javax.swing.JComboBox<>();
        cbGab = new javax.swing.JComboBox<>();
        cbLan = new javax.swing.JComboBox<>();
        cbWifi = new javax.swing.JComboBox<>();
        chekPar = new javax.swing.JCheckBox();
        chekSer = new javax.swing.JCheckBox();
        cbPainel = new javax.swing.JComboBox<>();
        chekPainel = new javax.swing.JCheckBox();
        chekMother = new javax.swing.JCheckBox();
        chekProc = new javax.swing.JCheckBox();
        chekMem = new javax.swing.JCheckBox();
        chekArm = new javax.swing.JCheckBox();
        chekAlim = new javax.swing.JCheckBox();
        chekGab = new javax.swing.JCheckBox();
        chekLan = new javax.swing.JCheckBox();
        chekwifi = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        btnOsAdicionar = new javax.swing.JButton();
        btnOsImprimir = new javax.swing.JButton();
        btnOsFinaliza = new javax.swing.JButton();
        btnOsAlterar = new javax.swing.JButton();

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

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        txtNumOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumOSKeyReleased(evt);
            }
        });

        jLabel11.setText("Número OS");

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
                false, false, false, false, false
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione"));

        jLabel7.setText("MotherBoard");

        jLabel3.setText("Processador");

        jLabel5.setText("Armazenamento");

        jLabel15.setText("Rede LAN");

        jLabel16.setText("Rede  WiFi");

        jLabel6.setText("Alimentação");

        jLabel4.setText("Memória");

        jLabel17.setText("Gabinete");

        jLabel14.setText("Painel");

        chekBat.setText("Bateria Setup");

        chekSuporte.setText("Suporte XY23");

        cbMother.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PlacaMãe" }));
        cbMother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMotherActionPerformed(evt);
            }
        });

        cbProc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Processador" }));

        cbMem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Memória" }));

        cbArm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Storage" }));

        cbAlim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Source" }));

        cbGab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gabinete" }));

        cbLan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rede Lan" }));

        cbWifi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "WiFi" }));

        chekPar.setText("Cabo Paralela DB25");
        chekPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekParActionPerformed(evt);
            }
        });

        chekSer.setText("Cabo Serial DB9");

        cbPainel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Painel" }));

        chekPainel.setText("Painel");

        chekMother.setText("PlacaMâe");

        chekProc.setText("Processador");

        chekMem.setText("Memória");

        chekArm.setText("Armazenamento");

        chekAlim.setText("Alimentação");

        chekGab.setText("Gabinete");

        chekLan.setText("Rede Lan");

        chekwifi.setText("WiFi");

        jLabel10.setText("Valor Total");

        txtOsValor.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbMother, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbArm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAlim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chekMother)
                    .addComponent(chekProc)
                    .addComponent(chekMem)
                    .addComponent(chekArm)
                    .addComponent(chekAlim)
                    .addComponent(chekGab))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbLan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbWifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chekLan)
                            .addComponent(chekwifi)
                            .addComponent(chekPainel)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chekPar)
                                    .addComponent(chekSer))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chekBat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chekSuporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbAlim, cbArm, cbGab, cbMem, cbMother, cbProc});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chekBat, chekPar, chekSer, chekSuporte});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(cbMother, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chekMother)
                    .addComponent(jLabel15)
                    .addComponent(cbLan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chekLan))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(cbWifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chekwifi))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel3)
                        .addComponent(cbProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chekProc)
                        .addComponent(jLabel16)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel14)
                            .addComponent(cbPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chekPainel)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(cbMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chekMem))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(cbArm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chekArm)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chekPar)
                                .addComponent(chekBat)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(cbAlim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chekAlim)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chekSer)
                                .addComponent(chekSuporte)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(chekGab)
                            .addComponent(cbGab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumOS))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(txtNumOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnOsAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/create.png"))); // NOI18N
        btnOsAdicionar.setToolTipText("Emitir OS");
        btnOsAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));

        btnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/print.png"))); // NOI18N
        btnOsImprimir.setToolTipText("Imprimir OS");
        btnOsImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsImprimir.setPreferredSize(new java.awt.Dimension(80, 80));

        btnOsFinaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/delete.png"))); // NOI18N
        btnOsFinaliza.setToolTipText("Finalizar OS");
        btnOsFinaliza.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsFinaliza.setPreferredSize(new java.awt.Dimension(80, 80));

        btnOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/update.png"))); // NOI18N
        btnOsAlterar.setToolTipText("Editar OS");
        btnOsAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsAlterar.setPreferredSize(new java.awt.Dimension(80, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(btnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnOsFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 822, 695);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

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
               
               
               
                conta = 0;
            } else {
                
                limpar();
            }
        }
    }//GEN-LAST:event_txtNumOSKeyReleased

    private void tbEquipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEquipKeyReleased
        setarOs(tbEquip.getSelectedRow());
        setarCampos(tbEquip.getSelectedRow());
    }//GEN-LAST:event_tbEquipKeyReleased

    private void cbMotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMotherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMotherActionPerformed

    private void chekParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekParActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chekParActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsAdicionar;
    private javax.swing.JButton btnOsAlterar;
    private javax.swing.JButton btnOsFinaliza;
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JComboBox<String> cbAlim;
    private javax.swing.JComboBox<String> cbArm;
    private javax.swing.JComboBox<String> cbGab;
    private javax.swing.JComboBox<String> cbLan;
    private javax.swing.JComboBox<String> cbMem;
    private javax.swing.JComboBox<String> cbMother;
    private javax.swing.JComboBox<String> cbPainel;
    private javax.swing.JComboBox<String> cbProc;
    private javax.swing.JComboBox<String> cbWifi;
    private javax.swing.JCheckBox chekAlim;
    private javax.swing.JCheckBox chekArm;
    private javax.swing.JCheckBox chekBat;
    private javax.swing.JCheckBox chekGab;
    private javax.swing.JCheckBox chekLan;
    private javax.swing.JCheckBox chekMem;
    private javax.swing.JCheckBox chekMother;
    private javax.swing.JCheckBox chekPainel;
    private javax.swing.JCheckBox chekPar;
    private javax.swing.JCheckBox chekProc;
    private javax.swing.JCheckBox chekSer;
    private javax.swing.JCheckBox chekSuporte;
    private javax.swing.JCheckBox chekwifi;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbEquip;
    private javax.swing.JTextField txtNumOS;
    private javax.swing.JTextField txtOsValor;
    // End of variables declaration//GEN-END:variables
}
