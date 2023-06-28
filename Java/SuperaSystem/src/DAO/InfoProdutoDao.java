/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Bean.InfoProdutoBean;
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
public class InfoProdutoDao {
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 ArrayList<InfoProdutoBean> infoProd = new ArrayList<>();
 
 public boolean adicionarInfo(String nserie,String id_pedido, String modelo, String patProd){
     boolean sucesso = false; 
     String sql = "insert into tbInfoProd(nserie, id_pedido, model, patProd) values(?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, id_pedido);
            pst.setString(3, modelo);
            pst.setString(4, patProd);         
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
  public ArrayList<String> pesquisarProduto(String nserie){ 
       ArrayList<String> infoProduto = new ArrayList<>();
        String  sql = "select * from tbInfoProd where nserie=?";         
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {            
            infoProduto.add(rs.getString( "nserie"));
            infoProduto.add(rs.getString("id_pedido"));
            infoProduto.add(rs.getString( "model"));           
            infoProduto.add(rs.getString( "patProd"));
                                 
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return infoProduto;
    }
  
    public ArrayList<InfoProdutoBean> pesquisarProduto(){ 
      String sql = "select * from tbInfoProd";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
           while (rs.next()) {
            InfoProdutoBean infoProduto = new InfoProdutoBean();
            infoProduto.setNserie(rs.getString( "nserie"));
            infoProduto.setLoteProd(rs.getString("id_pedido"));
            infoProduto.setModelo(rs.getString( "model"));           
            infoProduto.setPatProd(rs.getString( "patProd"));
            infoProd.add(infoProduto);
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return infoProd;
    }
  
  public boolean editarProduto(String nserie,String id_pedido, String modelo, String patProd){
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbInfoProd set id_pedido=?, model=?,  patProd=? where nserie=?";
        try {
             conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, id_pedido);
            pst.setString(2, modelo);
            pst.setString(3, patProd);   
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

    public boolean excluirProduto(String nserie){
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbInfoProd where nserie=?";
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
                JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nComponente possui O.S. pendente.");
            } catch (HeadlessException | SQLException e2) {
                JOptionPane.showMessageDialog(null, e2);

            } 
        }
        return sucesso;
    } 
}
