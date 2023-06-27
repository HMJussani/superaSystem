/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ClientesBean;
import Bean.OrdemServicoBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author hmjussani
 */
public class OrdemServicoDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    java.sql.Connection conexao = ConexaoDb.getConection();
    ArrayList<OrdemServicoBean> ordemServico = new ArrayList<>();
    ClienteDAO clientes = new ClienteDAO();

    public ArrayList<String> pesquisarCliente(String nome) {
        ArrayList<String> clienteList = new ArrayList<>();
        String sql = "select * from tbclientes where nomecli=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
             pst.setString(1, nome);
            rs = pst.executeQuery();
            while (rs.next()) {               
                clienteList.add(rs.getString("idcli"));
                clienteList.add(rs.getString("nomeCli"));
                clienteList.add(rs.getString("contatocli"));
                clienteList.add(rs.getString("emailcli"));
                clienteList.add(rs.getString("cidadecli"));
                clienteList.add(rs.getString("estadocli"));
                
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return clienteList;
    }

    public boolean emitirOs(String id_pedido, String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, String valor, String idcli) {
        boolean sucesso = false;
        String sql = "insert into tbpedido(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id_pedido);
            pst.setString(1, tipo);
            pst.setString(2, situacao);
            pst.setString(3, equipamento);
            pst.setString(4, defeito);
            pst.setString(5, servico);
            pst.setString(6, tecnico);
            pst.setString(7, valor);
            pst.setString(8, idcli);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return sucesso;
    }
    
    public ArrayList<OrdemServicoBean> pesquisarOs() {
        String sql = "select * from tbpedido";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdemServicoBean os = new OrdemServicoBean();
                os.setId_pedido(rs.getString("id_pedido"));
                os.setData_os(rs.getDate("data_os"));
                os.setQtde_equipamento(rs.getInt("qtde_equipamento"));
                os.setDefeito(rs.getString("defeito"));
                os.setServico(rs.getString("servico"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setIdcli(rs.getString("idcli"));
                os.setSituacao(rs.getString("situacao"));
                os.setOrcamento(rs.getString("orcamento"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

   
    public ArrayList<OrdemServicoBean> pesquisarOs(String idcli ) {
        String sql = "select * from tbpedido where idcli = ?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idcli);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdemServicoBean os = new OrdemServicoBean();
                os.setId_pedido(rs.getString("id_pedido"));
                os.setData_os(rs.getDate("data_os"));
                os.setQtde_equipamento(rs.getInt("qtde_equipamento"));
                os.setDefeito(rs.getString("defeito"));
                os.setServico(rs.getString("servico"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setIdcli(rs.getString("idcli"));
                os.setSituacao(rs.getString("situacao"));
                os.setOrcamento(rs.getString("orcamento"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public boolean editarOs(String OS, String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, String valor, String idcli) {
        boolean sucesso = false;
        String sql = "update tbos set tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, situacao);
            pst.setString(3, equipamento);
            pst.setString(4, defeito);
            pst.setString(5, servico);
            pst.setString(6, tecnico);
            pst.setString(7, valor);
            pst.setString(8, idcli);
            pst.setString(8, OS);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return sucesso;
    }

    /**
     * Método responsável pela exclusão de uma Ordem de Serviço
     *
     * @return
     */
    public boolean excluirOs(String retirado, String OS) {
        boolean sucesso = false;
        conexao = ConexaoDb.getConection();
        if (retirado.equals("Retirado")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String sql = "delete from tbos where os=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, OS);
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        sucesso = true;
                        conexao.close();
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para excluir esta OS é necessário modificar\no status para \"Retirado\"");
        }
        return sucesso;
    }

    /**
     * Método responsável pela impressão da Ordem de Serviço com JasperReports
     */
    private void imprimirOs() {
        conexao = ConexaoDb.getConection();
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                // filtro.put("os", Integer.parseInt(txtOs.getText()));
                //  JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/os.jasper"), filtro, conexao);
                //  JasperViewer.viewReport(print, false);
                conexao.close();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * Método usado para recuperar o número da OS
     */
    private void recuperarOs() {
        String sql = "select max(os) from tbos";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                //   txtOs.setText(rs.getString(1));
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
}
