/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.ComponenteBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class ComponenteDao {
 ComponenteBean componente; 
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 
 public boolean adicionarComponente(String nserie, String cod, String tipo, String modelo, String fabricante, String descricao, String custo, String datavenda)throws SQLException {
     boolean sucesso = false;   
     String sql = "insert into tbpcomponente(nserie, cod, tipo, modelo, fabricante, decricao, custo, datavenda) values(?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, cod);
            pst.setString(3, tipo);
            pst.setString(4, modelo);
            pst.setString(5,  fabricante); 
            pst.setString(6, descricao);
            pst.setString(7, custo);
            pst.setString(8,  datavenda);
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
 
  public ComponenteBean pesquisarProduto(String nserie)throws SQLException {        
       String sql = "select * from tbcomponente where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,nserie);
            rs = pst.executeQuery();
           while (rs.next()) {
            componente.setNserie(rs.getString("nserie"));
            componente.setCod(rs.getString("cod"));
            componente.setTipo(rs.getString("tipo"));
            componente.setModelo(rs.getString("modelo"));
            componente.setFabricante(rs.getString("fabricante"));
            componente.setDescricao(rs.getString("descricao"));
            componente.setCusto(rs.getString("custo"));
            componente.setDatavenda(rs.getString("datavenda"));            
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return componente;
    }
  
  public ComponenteBean pesquisarProduto()throws SQLException {        
       String sql = "select * from tbcomponente";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);           
            rs = pst.executeQuery();
            while (rs.next()) {
            componente.setNserie(rs.getString("nserie"));
            componente.setCod(rs.getString("cod"));
            componente.setTipo(rs.getString("tipo"));
            componente.setModelo(rs.getString("modelo"));
            componente.setFabricante(rs.getString("fabricante"));
            componente.setDescricao(rs.getString("descricao"));
            componente.setCusto(rs.getString("custo"));
            componente.setDatavenda(rs.getString("datavenda"));            
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return componente;
    }
  
  public boolean editarComponente(String nserie, String cod, String tipo, String modelo, String fabricante, String descricao, Double custo, Date datavenda)throws SQLException {
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbpcomponente set nserie=?, cod=?, tipo=?, modelo=?, fabricante=?, decricao=?, custo=?, datavenda=? where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, cod);
            pst.setString(3, tipo);
            pst.setString(4, modelo);
            pst.setString(5,  fabricante); 
            pst.setString(6, descricao);
            pst.setDouble(7, custo);
            pst.setDate(8, (java.sql.Date) datavenda);
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
