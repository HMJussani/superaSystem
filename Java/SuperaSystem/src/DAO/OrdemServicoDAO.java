/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ClientesBean;
import Bean.OrdemServicoBean;
import Bean.ProdutosBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
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
    

    public ArrayList<ClientesBean> pesquisarCliente(String nome) {
         ArrayList<ClientesBean> clienteList = new ArrayList<>();
         String sql = "select * from tbclientes";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomeCli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEmailcli(rs.getString("emailcli"));
                cliente.setCidadecli(rs.getString("cidadecli"));
                cliente.setEstadocli(rs.getString("estadocli"));
                clienteList.add(cliente);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return clienteList;
    }

    public boolean emitirOs(String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, String valor, String idcli, ArrayList<ProdutosBean> equipamentos) {
        boolean sucesso = false;        
        String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
        try {
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
    public ArrayList<OrdemServicoBean> pesquisarOs(String num_os) {
               
        String sql = "select os,date_format(data_os,'%d/%m/%Y - %H:%i'),tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli from tbos where os= " + num_os;
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                OrdemServicoBean ordemSrv = new OrdemServicoBean();
                ordemSrv.setTipo(rs.getString(1));
               // ordemSrv.set(rs.getString(2));
                ordemSrv.setTipo(rs.getString(3));
                ordemSrv.setSituacao(rs.getString(4));
                ordemSrv.setEquipamento(rs.getString(5));
                ordemSrv.setDefeito(rs.getString(6));
                ordemSrv.setServico(rs.getString(7));
                ordemSrv.setTecnico(rs.getString(8));
                ordemSrv.setValor(rs.getString(9));
                ordemSrv.setIdcli(rs.getString(10));
                ordemServico.add(ordemSrv);
                conexao.close();
            } else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
            }
        } catch (SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS Inválida");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
        return ordemServico;       
    }

    /**
     * Método responsável pela edição de uma Ordem de Seviço
     *
     * @return
     */
    public boolean editarOs(String OS,String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, String valor, String idcli) {
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
