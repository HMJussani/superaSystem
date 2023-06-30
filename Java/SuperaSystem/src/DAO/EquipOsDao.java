/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Bean.EquipOSBean;
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
 * table tbEquipOS
 * private String nserie ;
   private String idOrdServ;
   private String model;  
   private String patProd;
 */

public class EquipOsDao {
 PreparedStatement pst = null;
 ResultSet rs = null;
 Connection  conexao = ConexaoDb.getConection();
 ArrayList<EquipOSBean> equipamentoOS = new ArrayList<>();
 
 public boolean adicionarEquipamento(String idcli, String nserie,String idOrdServ, String idModel, String patProd){
     boolean sucesso = false; 
     String sql = "insert into tbEquipOS(idCli,nserie, idOrdServ, model, patProd) values(?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
             pst.setString(1, idcli);
            pst.setString(2, nserie);
            pst.setString(3, idOrdServ);
            pst.setString(4, idModel);
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
  public ArrayList<String> pesquisarProduto(String idCli){ 
       ArrayList<String> equipOS = new ArrayList<>();
        String  sql = "select * from tbEquipOS where idCli=?";         
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1,idCli);
            rs = pst.executeQuery();
           while (rs.next()) { 
            equipOS.add(rs.getString( "idCli"));   
            equipOS.add(rs.getString( "nserie"));
            equipOS.add(rs.getString("idOrdServ"));
            equipOS.add(rs.getString( "model"));           
            equipOS.add(rs.getString( "patProd"));
                                 
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return equipOS;
    }
  
    public ArrayList<EquipOSBean> pesquisarProduto(){ 
      String sql = "select * from tbEquipOS";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
           while (rs.next()) {
            EquipOSBean equipOS = new EquipOSBean();
            equipOS.setNserie(rs.getString( "nserie"));
            equipOS.setIdOrdServ(rs.getString("idOrdServ"));
            equipOS.setModel(rs.getString( "model"));           
            equipOS.setPatProd(rs.getString( "patProd"));
            equipamentoOS.add(equipOS);
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return equipamentoOS;
    }
  
  public boolean editarProduto(String nserie,String idOrdServ, String model, String patProd){
      boolean sucesso = false; 
      int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste produto?", "Atenção!", JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION) {             
        String sql = "update tbEquipOS set idOrdServ=?, model=?,  patProd=? where nserie=?";
        try {
             conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, idOrdServ);
            pst.setString(2, model);
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
            String sql = "delete from tbEquipOS where nserie=?";
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
