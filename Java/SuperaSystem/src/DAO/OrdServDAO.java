/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.OrdServBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author hmjussani
 */
public class OrdServDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    java.sql.Connection conexao = ConexaoDb.getConection();
   

    public boolean novaOs(String idOrdServ, String idcli, Date dataAbertura, String tecnico, String valor) {
        boolean sucesso = false;
        String sql = "insert into tbOrdServ (idOrdServ, idcli, dataAbertura, tecnico, valor)values(?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idOrdServ);
            pst.setString(2, idcli);
            pst.setDate(3, (java.sql.Date) dataAbertura);
            pst.setString(4, tecnico);
            pst.setString(5, valor);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Ordem de serviço já existente.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return sucesso;
    }

    public ArrayList<OrdServBean> pesquisarOs() {
        String sql = "SELECT * from tbOrdServ join tbclientes on tbOrdServ.idcli = tbclientes.idcli;";
         ArrayList<OrdServBean> ordemServico = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdServBean os = new OrdServBean();
                os.setIdOrdServ(rs.getString("idOrdServ"));
                os.setDataAbertura(rs.getDate("dataAbertura"));
                os.setDataFechamento(rs.getDate("dataFechamento"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setIdcli(rs.getString("idcli"));
                os.setAberta(rs.getBoolean("aberta"));
                os.setNomeCli(rs.getString("nomecli"));
                ordemServico.add(os);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public ArrayList<OrdServBean> pesquisarOs(String idCli) {
        String sql = "select * from tbOrdServ where idcli = ?";
         ArrayList<OrdServBean> ordemServico = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idCli);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdServBean os = new OrdServBean();
                os.setIdOrdServ(rs.getString("idOrdServ"));
                os.setDataAbertura(rs.getDate("dataAbertura"));
                os.setDataFechamento(rs.getDate("dataFechamento"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setAberta(rs.getBoolean("aberta"));                
                ordemServico.add(os);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public ArrayList<OrdServBean> pesquisarOsbyIdOs(String idOrdServ) {
        String sql = "select * from tbOrdServ join tbclientes on tbOrdServ.idcli = tbclientes.idcli where idOrdServ = ?";
         ArrayList<OrdServBean> ordemServico = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idOrdServ);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrdServBean os = new OrdServBean();
                os.setIdcli(rs.getString("idcli"));
                os.setIdOrdServ(rs.getString("idOrdServ"));
                os.setDataAbertura(rs.getDate("dataAbertura"));
                os.setDataFechamento(rs.getDate("dataFechamento"));
                os.setTecnico(rs.getString("tecnico"));
                os.setValor(rs.getString("valor"));
                os.setAberta(rs.getBoolean("aberta"));
                os.setNomeCli(rs.getString("nomecli"));
                ordemServico.add(os);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ordemServico;
    }

    public boolean editarOs(String idOrdServ, Date dataFechamento, boolean aberta, String valor) {
        boolean sucesso = false;
        String sql = "update tbOrdServ set dataFechamento=?, aberta=?, valor=? where idOrdServ=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setDate(1, (java.sql.Date) dataFechamento);
            pst.setBoolean(2, aberta);
            pst.setString(3, valor);
            pst.setString(4, idOrdServ);
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

    public boolean excluirOs(boolean finalizado, String idOrdServ) {
        boolean sucesso = false;
        conexao = ConexaoDb.getConection();
        if (finalizado) {
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String sql = "delete from tbOrdServ where idOrdServ=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, idOrdServ);
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
