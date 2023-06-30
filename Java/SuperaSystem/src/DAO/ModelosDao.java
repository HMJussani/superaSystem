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
 * public ModelosBean(int idModel, String tipo, String processador, String gabinete, String model, String mem, String mBoard, String fonteAlimenta, String armazenaModel, String armazenaTipo, String sParalela, String sSerial, String redeLan, String wifi, String expansao) {

 */
public class ModelosDao {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();
    ArrayList<ModelosBean> listaModelos = new ArrayList<>();

    public boolean adicionarModelo(int idModel, String tipo, String processador, String gabinete, String model, String mem, String mBoard, String fonteAlimenta, String armazenaModel, String armazenaTipo, String sParalela, String sSerial, String redeLan, String wifi, String expansao) {
        boolean sucesso = false;
        String sql = "insert into tbmodelo(idModel,tipo,processador,gabinete,model,mem,mBoard,fonteAlimenta,armazenaModel,armazenaTipo,sParalela,sSerial,redeLan,wifi,expansao) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, model);
            pst.setString(2, mem);
            pst.setString(3, mBoard);
            pst.setString(4, power);
            pst.setString(5, storage);
            pst.setString(6, sParalela);
            pst.setString(7, sSerial);
            pst.setString(8, redeLan);
            pst.setString(9, wifi);
            pst.setString(10, tipo);
             pst.setString(11, processador);
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

    public ArrayList<ModelosBean> pesquisarModelo(String model) {
        
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
                produto.setPower(rs.getString("source"));
                produto.setStorage(rs.getString("storage"));
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

    public boolean editarModelo(int idModel, String tipo, String processador, String gabinete, String model, String mem, String mBoard, String fonteAlimenta, String armazenaModel, String armazenaTipo, String sParalela, String sSerial, String redeLan, String wifi, String expansao){
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbmodelo set mem=?, mBoard=?, source=?, storage=?, sParalela=? ,sSerial=?,redeLan=?, wifi=?, tipo=?, processador=?, gabinete=? where model=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, mem);
                pst.setString(2, mBoard);                
                pst.setString(3, power);
                pst.setString(4, storage);
                pst.setString(5, sParalela);
                pst.setString(6, sSerial);
                pst.setString(7, redeLan);
                pst.setString(8, wifi);               
                pst.setString(9, tipo);
                pst.setString(10, processador);
                pst.setString(11, gabinete);
                 pst.setString(12, model);
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


