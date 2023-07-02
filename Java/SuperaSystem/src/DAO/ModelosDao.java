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
 * @author RMA public ModelosBean( String tipo, String processador, String
 * gabinete, String model, String mem, String mBoard, String fonteAlimenta,
 * String armazenaModel, String armazenaTipo, String sParalela, String sSerial,
 * String redeLan, String wifi, String expansao) {
 *
 */
public class ModelosDao {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public boolean adicionarModelo(String model, String mem, String mBoard, String expansao, String armazenaTipo, String armazenaModel, String fonteAlimenta, String sParalela, String sSerial, String redeLan, String wifi, String tipo, String processador, String gabinete) {
        boolean sucesso = false;
        String sql = "insert into tbmodelo(model, mem, mBoard,expansao, armazenaTipo, armazenaModel,fonteAlimenta, sParalela, sSerial , redeLan, wifi, tipo, processador,gabinete) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, model);
            pst.setString(2, mem);
            pst.setString(3, mBoard);
            pst.setString(4, expansao);
            pst.setString(5, armazenaTipo);
            pst.setString(6, armazenaModel);
            pst.setString(7, fonteAlimenta);
            pst.setString(8, sParalela);
            pst.setString(9, sSerial);
            pst.setString(10, redeLan);
            pst.setString(11, wifi);
            pst.setString(12, tipo);
            pst.setString(13, processador);
            pst.setString(14, gabinete);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Modelo já existente.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Adicionando Produtos: " + e);
        }
        return sucesso;
    }

    public ArrayList<ModelosBean> pesquisarModelo(String model) {
        ArrayList<ModelosBean> listaModelos = new ArrayList<>();
        String sql = "select * from tbmodelo where model=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, model);
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelosBean produto = new ModelosBean();
                produto.setModel(rs.getString("model"));
                produto.setMem(rs.getString("mem"));
                produto.setmBoard(rs.getString("mBoard"));
                produto.setFonteAlimenta(rs.getString("fonteAlimenta"));
                produto.setArmazenaModel(rs.getString("armazenaModel"));
                produto.setArmazenaTipo(rs.getString("armazenaTipo"));
                produto.setExpansao(rs.getString("expansao"));
                produto.setsParalela(rs.getString("sParalela"));
                produto.setsSerial(rs.getString("sSerial"));
                produto.setRedeLan(rs.getString("redeLan"));
                produto.setWifi(rs.getString("wifi"));
                produto.setTipo(rs.getString("tipo"));
                produto.setProcessador(rs.getString("processador"));
                produto.setGabinete(rs.getString("gabinete"));
                listaModelos.add(produto);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pesquisando Produtos: " + e);
        }
        return listaModelos;
    }

    public ArrayList<ModelosBean> pesquisarModelo() {
        ArrayList<ModelosBean> listaModelos = new ArrayList<>();
        String sql = "select * from tbmodelo";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelosBean produto = new ModelosBean();
                produto.setModel(rs.getString("model"));
                produto.setMem(rs.getString("mem"));
                produto.setmBoard(rs.getString("mBoard"));
                produto.setFonteAlimenta(rs.getString("fonteAlimenta"));
                produto.setArmazenaModel(rs.getString("armazenaModel"));
                produto.setArmazenaTipo(rs.getString("armazenaTipo"));
                produto.setExpansao(rs.getString("expansao"));
                produto.setsParalela(rs.getString("sParalela"));
                produto.setsSerial(rs.getString("sSerial"));
                produto.setRedeLan(rs.getString("redeLan"));
                produto.setWifi(rs.getString("wifi"));
                produto.setTipo(rs.getString("tipo"));
                produto.setProcessador(rs.getString("processador"));
                produto.setGabinete(rs.getString("gabinete"));
                listaModelos.add(produto);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Pesquisando Produtos: " + e);
        }
        return listaModelos;
    }

    public boolean editarModelo(String model, String mem, String mBoard, String expansao, String armazenaTipo, String armazenaModel, String fonteAlimenta, String sParalela, String sSerial, String redeLan, String wifi, String tipo, String processador, String gabinete) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbmodelo set mem=?, mBoard=?,expansao=?, armazenaTipo=?, armazenaModel=?,fonteAlimenta=?, sParalela=?, sSerial=? , redeLan=?, wifi=?, tipo=?, processador=?,gabinete=? where model=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, mem);
                pst.setString(2, mBoard);
                pst.setString(3, expansao);
                pst.setString(4, armazenaTipo);
                pst.setString(5, armazenaModel);
                pst.setString(6, fonteAlimenta);
                pst.setString(7, sParalela);
                pst.setString(8, sSerial);
                pst.setString(9, redeLan);
                pst.setString(10, wifi);
                pst.setString(11, tipo);
                pst.setString(12, processador);
                pst.setString(13, gabinete);
                pst.setString(14, model);
                int editado = pst.executeUpdate();
                if (editado > 0) {
                    sucesso = true;
                    conexao.close();
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Modelo já existente.");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Editando Produtos: " + e);
            }
        }
        return sucesso;
    }

    public boolean excluirModelo(String model) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste Modelo?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbmodelo where model=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, model);
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
