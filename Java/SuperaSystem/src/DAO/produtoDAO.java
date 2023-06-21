/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author HMJussani
 */

import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import Bean.ProdutosBean;

public class ProdutoDAO {
 ProdutosBean produto; 
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 
 public boolean adicionarProduto(String nserieProd, String tipoProd, String modeloProd, String descricaoProd, String patProd)throws SQLException {
     boolean sucesso = false;   
     String sql = "insert into tbproduto(nserieProd, tipoProd, modeloProd, descricaoProd, patProd) values(?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserieProd);
            pst.setString(2, tipoProd);
            pst.setString(3, modeloProd);
            pst.setString(4, descricaoProd);
            pst.setString(5, patProd);           
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
 
  public ProdutosBean pesquisarProduto(String nserieProd)throws SQLException {        
       String sql = "select * from tbproduto where nserieProd=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserieProd);
            rs = pst.executeQuery();
           while (rs.next()) {
            ProdutosBean.setNserieProd(rs.getString("nserieProd"));
            ProdutosBean.setTipoProd(rs.getString("tipoProd"));
            ProdutosBean.setModeloProd(rs.getString("modeloProd"));
            ProdutosBean.setDescricaoProd(rs.getString("descricaoProd"));
            ProdutosBean.setPatProd(rs.getString("patProd"));
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return produto;
    }
  
   public void pesquisarProduto()throws SQLException {        
        String sql = "select * from tbproduto;";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);           
            rs = pst.executeQuery();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
  
  public boolean editarProduto(String nserieProd, String tipoProd, String modeloProd, String descricaoProd, String patProd)throws SQLException {
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbproduto set nserieProd=?,tipoProd=?,modeloProd=?,descricaoProd=?, patProd=? where nserie=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);               
                pst.setString(1, tipoProd);
                pst.setString(2, modeloProd);
                pst.setString(3, descricaoProd);
                pst.setString(4, patProd);                       
                int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Dados do produto alterados com sucesso");                        
                        sucesso = true; 
                        conexao.close();
                    }
                }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
        }
        return sucesso;
    }

    public boolean excluirProduto(String nserieProd)throws SQLException {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste Produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbcproduto where nserieProd=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nserieProd);
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
