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
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class InfoProdutoDao {
 InfoProdutoBean infoProduto; 
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 
 public boolean adicionarInfo(String nserie,String loteProd, String modelo, String patProd){
     boolean sucesso = false; 
     String sql = "insert into tbInfoProd(nserie, loteProd, modelo, patProd) values(?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, loteProd);
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
 
  public InfoProdutoBean pesquisarProduto(String nserie)throws SQLException {        
       String sql = "select * from tbInfoProd where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {
            infoProduto.setNserie(rs.getString( "nserie"));
            infoProduto.setLoteProd(rs.getString(" loteProd"));
            infoProduto.setModelo(rs.getString( "modelo"));           
            infoProduto.setPatProd(rs.getString( "patProd"));                      
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return infoProduto;
    }
  
  
  public boolean editarProduto(String nserie,String loteProd, String modelo, String patProd){
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbInfoProd set loteProd=?, modelo=?,  patProd=? where nserie=?";
        try {
             conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, loteProd);
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
