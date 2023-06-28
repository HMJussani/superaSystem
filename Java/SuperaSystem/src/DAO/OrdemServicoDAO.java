/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.OrdemServicoBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    public boolean novaOs(String id_ordemServico, String idcli, Date dataAbertura, Boolean garantia, String defeito, String tecnico, String valor) {
        boolean sucesso = false;
        String sql = "insert into tbordemServico (id_ordemServico, idcli, dataAbertura, garantia, defeito, tecnico, valor)values(?,?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id_ordemServico);
            pst.setString(2, idcli);
            pst.setDate(3, (java.sql.Date) dataAbertura);
            pst.setBoolean(4, garantia);
            pst.setString(5, defeito);
            pst.setString(6, tecnico);
            pst.setString(7, valor);

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
        String sql = "SELECT * FROM tbordemServico";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdemServicoBean os = new OrdemServicoBean();
                os.setId_ordemServico(rs.getString("id_pedido"));
                os.setDataAbertura(rs.getDate("data_os"));
                os.setDefeito(rs.getString("defeito"));
                os.setGarantia(rs.getBoolean("garantia"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setIdcli(rs.getString("idcli"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public ArrayList<OrdemServicoBean> pesquisarOs(String idcli) {
        String sql = "SELECT * FROM tbordemServico where idcli = ?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idcli);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdemServicoBean os = new OrdemServicoBean();
                os.setId_ordemServico(rs.getString("id_pedido"));
                os.setDataAbertura(rs.getDate("data_os"));                
                os.setDefeito(rs.getString("defeito"));
                os.setGarantia(rs.getBoolean("garantia"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public boolean editarOs(String id_ordemServico, String defeito,Date dataFechamento, String solucao, String tecnico, String valor) {
        boolean sucesso = false;
        String sql = "update tbordemServico set dataFechamento=?, defeito=?, tecnico=?, valor=? where id_ordemServico=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);           
            pst.setDate(3, (java.sql.Date) dataFechamento);
            pst.setString(4, defeito);
            pst.setString(5, solucao);
            pst.setString(6, tecnico);
            pst.setString(7, valor);            
            pst.setString(8, id_ordemServico);
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
    public boolean excluirOs(String retirado, String id_ordemServico) {
        boolean sucesso = false;
        conexao = ConexaoDb.getConection();
        if (retirado.equals("Retirado")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String sql = "delete from tbordemServico where id_ordemServico=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id_ordemServico);
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
}
