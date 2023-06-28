/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
public class ProdutoDao {
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 
 public boolean adicionarProduto(String nserie, String loteProd, String patProd, String model, String mem, String mBoard,String storage, String power, String sParalela, String sSerial, String redeLan, String wifi){
     boolean sucesso = false;   
     String sql = "insert into tbproduto(nserie, loteProd, patProd, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values(?,?,?,?,?,?,?,?,?,?,?,?);";
     
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, loteProd);
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
            JOptionPane.showMessageDialog(null,"Adicionando Produtos: " + e);
        }         
       return sucesso;
    }
 
  public  ArrayList<String> pesquisarProduto(String nserie) { 
       ArrayList<String> produto = new ArrayList<>(); 
       String sql = "select * from tbproduto where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {
            produto.add(rs.getString( "nserie"));
            produto.add(rs.getString( "loteProd"));
            produto.add(rs.getString( "patProd"));
            produto.add(rs.getString( "model"));
            produto.add(rs.getString( "mem")); 
            produto.add(rs.getString( "mBoard"));
            produto.add(rs.getString( "source"));
            produto.add(rs.getString( "storage"));
            produto.add(rs.getString( "sParalela")); 
            produto.add(rs.getString(  "sSerial"));
            produto.add(rs.getString( "redeLan"));
            produto.add(rs.getString(  "wifi"));         
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Pesquisando Produtos: " + e);
        }         
        return produto;
    }
  public  ArrayList<String> pesquisarProduto() { 
       ArrayList<String> produto = new ArrayList<>(); 
       String sql = "select * from tbproduto";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);           
            rs = pst.executeQuery();
           while (rs.next()) {
            produto.add(rs.getString( "nserie"));
            produto.add(rs.getString(" loteProd"));
            produto.add(rs.getString( "patProd"));
            produto.add(rs.getString( "model"));
            produto.add(rs.getString( "mem")); 
            produto.add(rs.getString(" mBoard"));
            produto.add(rs.getString( "source"));
            produto.add(rs.getString( "storage"));
            produto.add(rs.getString( "sParalela")); 
            produto.add(rs.getString(  "sSerial"));
            produto.add(rs.getString(" redeLan"));
            produto.add(rs.getString(  "wifi"));         
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Pesquisando Produtos: " + e);
        }         
        return produto;
    }
  
  
  public boolean editarProduto(String nserie, String mem, String mBoard,String storage, String power, String sParalela, String sSerial, String redeLan, String wifi){
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbproduto set mem=?, mBoard=?, storage=?, source=?, sParalela=? ,sSerial=?,redeLan=?, wifi=? where nserie=?";
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
            JOptionPane.showMessageDialog(null,"Editando Produtos: " + e);
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
                JOptionPane.showMessageDialog(null, "Excluindo Produtos: " + e2);

            } 
        }
        return sucesso;
    } 
}
