/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.ProdutosBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class ProdutoDao {
 ProdutosBean produto = null; 
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 
 public boolean adicionarProduto(String nserie, String loteCompra, String patProd, String model, String mem, String mBoard,String storage, String power, String sParalela, String sSerial, String redeLan, String wifi){
     boolean sucesso = false;   
     String sql = "insert into tbproduto(nserie, loteCompra, patProd, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, loteCompra);
            pst.setString(3, patProd);
            pst.setString(4, model);
            pst.setString(5, mem); 
            pst.setString(6, mBoard);
            pst.setString(7, power);
            pst.setString(8, storage);            
            pst.setString(9, sParalela);
            pst.setString(10, sSerial); 
            pst.setString(11, redeLan);
            pst.setString(12, wifi);
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
 
  public ProdutosBean pesquisarProduto(String nserie) {        
       String sql = "select * from tbproduto where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {
            produto.setNserie(rs.getString( "nserie"));
            produto.setLoteCompra(rs.getString(" loteCompra"));
            produto.setPatProd(rs.getString( "patProd"));
            produto.setModel(rs.getString( "model"));
            produto.setMem(rs.getString( "mem")); 
            produto.setmBoard(rs.getString(" mBoard"));
            produto.setPower(rs.getString( "source"));
            produto.setStorage(rs.getString( "storage"));
            produto.setsParalela(rs.getString( "sParalela")); 
            produto.setsSerial(rs.getString(  "sSerial"));
            produto.setRedeLan(rs.getString(" redeLan"));
            produto.setWifi(rs.getString(  "wifi"));         
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return produto;
    }
  
  
  public boolean editarProduto(String nserie, String loteCompra, String patProd, String model, String mem, String mBoard,String storage, String power, String sParalela, String sSerial, String redeLan, String wifi){
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbpproduto set  loteCompra=?, patProd=?, model=?, mem=?, mBoard=?, storage=?, source=?, sParalela=? ,sSerial=?,redeLan=?, wifi=? where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, loteCompra);
            pst.setString(2, patProd);
            pst.setString(3, model);           
            pst.setString(4, mem);
            pst.setString(5, mBoard);
            pst.setString(6, storage);
             pst.setString(7, power); 
            pst.setString(8, sParalela);
            pst.setString(9, sSerial);
            pst.setString(10, redeLan);
            pst.setString(11, wifi);
            int editado = pst.executeUpdate();
            if (editado > 0) {                   
                    sucesso = true;
                    conexao.close();
                }       
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
             }  
        }
        return sucesso;
    }

    public boolean excluirProduto(String nserie){
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste Produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbproduto where nserie=?";
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
                JOptionPane.showMessageDialog(null, e2);

            } 
        }
        return sucesso;
    } 
}
