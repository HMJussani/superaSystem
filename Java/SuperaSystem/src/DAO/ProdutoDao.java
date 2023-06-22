/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.ComponenteBean;
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
 
 public boolean adicionarProduto(String nserie,String memoria, String modelo, String motherBoard, String patProd, String armazenamento,String alimentacao, String saidaParalela, String saidaSerial,
 String redeLan, String wifi,String lote)throws SQLException {
     boolean sucesso = false;   
     String sql = "insert into tbproduto(String nserie,String memoria, String modelo, String motherBoard, String patProd, String armazenamento,String alimentacao, String saidaParalela, String saidaSerial," +
"String redeLan, String wifi,String lote) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, memoria);
            pst.setString(3, modelo);
            pst.setString(4, motherBoard);
            pst.setString(5, patProd); 
            pst.setString(6, armazenamento);
            pst.setString(7, alimentacao);
            pst.setString(8, saidaParalela);
             pst.setString(9, saidaSerial); 
            pst.setString(10, redeLan);
            pst.setString(11, wifi);
            pst.setString(12, lote);
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
 
  public ProdutosBean pesquisarProduto(String nserie)throws SQLException {        
       String sql = "select * from tbproduto where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {
            produto.setNserie(rs.getString( "nserie"));
            produto.setMemoria(rs.getString(" memoria"));
            produto.setModelo(rs.getString( "modelo"));
            produto.setMotherBoard(rs.getString( "motherBoard"));
            produto.setPatProd(rs.getString( "patProd")); 
            produto.setArmazenamento(rs.getString(" armazenamento"));
            produto.setAlimentacao(rs.getString( "alimentacao"));
            produto.setSaidaParalela(rs.getString( "saidaParalela"));
             produto.setSaidaSerial(rs.getString( "saidaSerial")); 
            produto.setRedeLan(rs.getString(  "redeLan"));
            produto.setWifi(rs.getString(" wifi"));
            produto.setLote(rs.getString(  "lote"));         
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return produto;
    }
  
  
  public boolean editarProduto(String nserie,String memoria, String modelo, String motherBoard, String patProd, String armazenamento,String alimentacao, String saidaParalela, String saidaSerial,
 String redeLan, String wifi,String lote)throws SQLException {
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbpcomponente set  memoria=?, modelo=?, motherBoard=?, armazenamento=?, alimentacao=?, saidaParalela=?, saidaSerial=?,redeLan=?,wifi=?, lote=? where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, memoria);
            pst.setString(2, modelo);
            pst.setString(3, motherBoard);           
            pst.setString(4, armazenamento);
            pst.setString(5, alimentacao);
            pst.setString(6, saidaParalela);
             pst.setString(7, saidaSerial); 
            pst.setString(8, redeLan);
            pst.setString(9, wifi);
            pst.setString(10, lote);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {                   
                    sucesso = true;
                    conexao.close();
                }       
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }        
       
        }
        return sucesso;
    }

    public boolean excluirProduto(String nserie)throws SQLException {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste Componente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbcomponente where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nserie);
                int apagado = pst.executeUpdate();
                if (apagado > 0) {                   
                    JOptionPane.showMessageDialog(null, "Componente removido com sucesso");
                    sucesso = true;
                    conexao.close();
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
