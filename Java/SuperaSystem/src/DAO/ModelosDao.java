/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.ModelosBean;
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
 * @author RMA
 */
public class ModelosDao {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();
    ArrayList<ModelosBean> listaModelos = new ArrayList<>();

    public boolean adicionarModelo(String tipo, String processador, String gabinete, String model, String mem, String mBoard, String power, String storage, String sParalela, String sSerial, String redeLan, String wifi) {
        boolean sucesso = false;
        String sql = "insert into tbmodelo(model, mem, mBoard, processador,source, storage,sParalela,sSerial,redeLan,wifi,tipo,gabinete) values(?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, model);
            pst.setString(2, mem);
            pst.setString(3, mBoard);
            pst.setString(4, processador);
            pst.setString(5, power);
            pst.setString(6, storage);
            pst.setString(7, sParalela);
            pst.setString(8, sSerial);
            pst.setString(9, redeLan);
            pst.setString(10, wifi);
            pst.setString(11, tipo);
            pst.setString(12, gabinete);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Adicionando Produtos: " + e);
        }
        return sucesso;
    }

    public ArrayList<String> pesquisarModelo(String model) {
        ArrayList<String> produto = new ArrayList<>();
        String sql = "select * from tbmodelo where model=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, model);
            rs = pst.executeQuery();
            while (rs.next()) {                
                produto.add(rs.getString("model"));
                produto.add(rs.getString("mem"));
                produto.add(rs.getString("mBoard"));
                produto.add(rs.getString("processador"));
                produto.add(rs.getString("source"));
                produto.add(rs.getString("storage"));
                produto.add(rs.getString("sParalela"));
                produto.add(rs.getString("sSerial"));
                produto.add(rs.getString("redeLan"));
                produto.add(rs.getString("wifi"));
                produto.add(rs.getString("tipo"));               
                produto.add(rs.getString("gabinete"));
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pesquisando Produtos: " + e);
        }
        return produto;
    }

    public ArrayList<ModelosBean> pesquisarModelo() {

        String sql = "select * from tbmodelo";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelosBean modelos = new ModelosBean();
                modelos.setTipo(rs.getString("tipo"));
                modelos.setProcessador(rs.getString("processador"));
                modelos.setGabinete(rs.getString("gabinete"));
                modelos.setModel(rs.getString("model"));
                modelos.setMem(rs.getString("mem"));
                modelos.setmBoard(rs.getString("mBoard"));
                modelos.setPower(rs.getString("source"));
                modelos.setStorage(rs.getString("storage"));
                modelos.setsParalela(rs.getString("sParalela"));
                modelos.setsSerial(rs.getString("sSerial"));
                modelos.setRedeLan(rs.getString("redeLan"));
                modelos.setWifi(rs.getString("wifi"));
                listaModelos.add(modelos);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pesquisando Produtos: " + e);
        }
        return listaModelos;
    }

    public boolean editarModelo(String nserie, String mem, String mBoard, String storage, String power, String sParalela, String sSerial, String redeLan, String wifi) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbmodelo set tipo=?, processador=?, gabinete=?, mem=?, mBoard=?, storage=?, source=?, sParalela=? ,sSerial=?,redeLan=?, wifi=? where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, mem);
                pst.setString(2, mBoard);
                pst.setString(3, storage);
                pst.setString(4, power);
                pst.setString(5, sParalela);
                pst.setString(6, sSerial);
                pst.setString(7, redeLan);
                pst.setString(8, wifi);
                pst.setString(9, nserie);
                int editado = pst.executeUpdate();
                if (editado > 0) {
                    sucesso = true;
                    conexao.close();
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Editando Produtos: " + e);
            }
        }
        return sucesso;
    }

    public boolean excluirModelo(String nserie) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste Produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbmodelo where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nserie);
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    sucesso = true;
                    conexao.close();
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nProduto possui O.S. pendente.");
            } catch (HeadlessException | SQLException e2) {
                JOptionPane.showMessageDialog(null, "Excluindo Produtos: " + e2);

            }
        }
        return sucesso;
    }
}
