/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.EquipOSBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA table tbEquip private String nserie ; private String idOrdServ;
 * private String model; private String patEquip;
 */
public class EquipOsDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public boolean adicionarEquipamento(String nserie, String idOrdServ, String model, String patEquip, String idcli, Boolean garantia) {
        boolean sucesso = false;
        String sql = "insert into tbEquip(nserie, idOrdServ, model, patEquip, idcli,garantia) values(?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, idOrdServ);
            pst.setString(3, model);
            pst.setString(4, patEquip);
            pst.setString(5, idcli);
            pst.setBoolean(6, garantia);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Adicionando equipamentos:" +e);
        }
        return sucesso;
    }

    public ArrayList<EquipOSBean> pesquisarProduto(String idcli) {
        String sql = "select * from tbEquip where idcli=?";
        ArrayList<EquipOSBean> equipamentoOS = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idcli);
            rs = pst.executeQuery();
            while (rs.next()) {
                EquipOSBean equipOS = new EquipOSBean();
                equipOS.setNserie(rs.getString("nserie"));
                equipOS.setIdOrdServ(rs.getString("idOrdServ"));
                equipOS.setModel(rs.getString("model"));
                equipOS.setPatEquip(rs.getString("patEquip"));
                equipOS.setIdCli(rs.getString("idcli"));
                equipOS.setGarantia(rs.getBoolean("garantia"));
                equipOS.setAnalizado(rs.getBoolean("analizado"));
                equipamentoOS.add(equipOS);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return equipamentoOS;
    }

       public ArrayList<EquipOSBean> pesquisarProdutoBy(String arg, String valor) {
        String sql = "select * from tbEquip where " + arg + "=? order by nserie;";
        ArrayList<EquipOSBean> equipamentoOS = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, valor);
            rs = pst.executeQuery();
            while (rs.next()) {
                EquipOSBean equipOS = new EquipOSBean();
                equipOS.setNserie(rs.getString("nserie"));
                equipOS.setIdOrdServ(rs.getString("idOrdServ"));
                equipOS.setModel(rs.getString("model"));
                equipOS.setPatEquip(rs.getString("patEquip"));
                equipOS.setIdCli(rs.getString("idcli"));
                equipOS.setGarantia(rs.getBoolean("garantia"));
                equipOS.setAnalizado(rs.getBoolean("analizado"));
                equipamentoOS.add(equipOS);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pesquisando equipamentos: "+e);
        }
        return equipamentoOS;
    }
    
    public ArrayList<EquipOSBean> pesquisarProduto() {
        String sql = "select * from tbEquip";
        ArrayList<EquipOSBean> equipamentoOS = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                EquipOSBean equipOS = new EquipOSBean();
                equipOS.setNserie(rs.getString("nserie"));
                equipOS.setIdOrdServ(rs.getString("idOrdServ"));
                equipOS.setModel(rs.getString("model"));
                equipOS.setPatEquip(rs.getString("patEquip"));
                equipOS.setIdCli(rs.getString("idcli"));
                equipOS.setGarantia(rs.getBoolean("garantia"));
                equipOS.setAnalizado(rs.getBoolean("analizado"));
                equipamentoOS.add(equipOS);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return equipamentoOS;
    }

    public boolean editarProduto(String nserie, String idOrdServ, String model, String patEquip, String idcli, Boolean garantia, Boolean analizado) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbEquip set idOrdServ=?, model=?,  patEquip=?, idcli=?, garantia=?, analizado=? where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, idOrdServ);
                pst.setString(2, model);
                pst.setString(3, patEquip);
                pst.setString(4, idcli);
                pst.setBoolean(5, garantia);
                pst.setBoolean(6, analizado);
                pst.setString(7, nserie);
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    sucesso = true;
                    conexao.close();
                }else{
                    JOptionPane.showMessageDialog(null, "Alteração não realizada.");
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        return sucesso;
    }

    public boolean excluirProduto(String nserie) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbEquip where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nserie);
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    sucesso = true;
                    conexao.close();
                }else{
                    JOptionPane.showMessageDialog(null, "Exclusão não realizada.Equipamento não encontrado.");
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nComponente possui O.S. pendente.");
            } catch (HeadlessException | SQLException e2) {
                JOptionPane.showMessageDialog(null, e2);

            }
        }
        return sucesso;
    }
}
