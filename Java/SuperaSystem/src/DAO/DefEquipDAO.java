/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.DefEquipBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class DefEquipDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public boolean novoDefEquip(String nserie, String placaMaeDef, String processadorDef, String memoriaDef, String picoDef, String fonteDef, String armazenamentoDef, String soDef,String testeDef) {
        boolean sucesso = false;
        String sql = "insert into tbequipDef (nserie, placaMaeDef, processadorDef, memoriaDef, picoDef, fonteDef, armazenamentoDef, soDef)values(?,?,?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, placaMaeDef);
            pst.setString(3, processadorDef);
            pst.setString(4, memoriaDef);
            pst.setString(5, picoDef);
            pst.setString(6, fonteDef);
            pst.setString(7, armazenamentoDef);
            pst.setString(8, soDef);
            pst.setString(9, testeDef);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Defeito/Solução: " + e);
        }

        return sucesso;
    }

    public boolean editDefEquip(String nserie, String placaMaeDef, String processadorDef, String memoriaDef, String picoDef, String fonteDef, String armazenamentoDef, String soDef, String testeDef ) {
        boolean sucesso = false;
        String sql = "update tbequipDef set placaMaeDef=?, processadorDef=?, memoriaDef=?, picoDef=?, fonteDef=?, armazenamentoDef=?, soDef=? where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, placaMaeDef);
            pst.setString(2, processadorDef);
            pst.setString(3, memoriaDef);
            pst.setString(4, picoDef);
            pst.setString(5, fonteDef);
            pst.setString(6, armazenamentoDef);
            pst.setString(7, soDef);
            pst.setString(8, testeDef);
            pst.setString(8, nserie);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar Defeito/Solução: ");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Defeito/Solução: " + e);
        }

        return sucesso;
    }

    public ArrayList<DefEquipBean> listaDefEquip() {
        ArrayList<DefEquipBean> listaDefeito = new ArrayList<>();
        String sql = "select * from tbequipDef";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                DefEquipBean defeito = new DefEquipBean();
                defeito.setArmazenamentoDef(rs.getString("armazenamentoDef"));
                defeito.setExpansaoDef(rs.getString("expansaoDef"));
                defeito.setMemoriaDef(rs.getString("memoriaDef"));
                defeito.setPicoDef(rs.getString("picoDef"));
                defeito.setPlacaMaeDef(rs.getString("placaMaeDef"));
                defeito.setProcessadorDef(rs.getString("processadorDef"));
                defeito.setSoDef(rs.getString("soDef"));
                defeito.setFonteDef(rs.getString("fonteDef"));
                defeito.setTesteDef(rs.getString("testeDef"));
                listaDefeito.add(defeito);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar Defeito / Solução: " + e);
        }
        return listaDefeito;
    }

    public ArrayList<DefEquipBean> listaDefEquip(String nserie) {
        ArrayList<DefEquipBean> listaDefeito = new ArrayList<>();
        String sql = "select * from tbequipDef where nserie =?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            rs = pst.executeQuery();
            while (rs.next()) {
                DefEquipBean defeito = new DefEquipBean();
                defeito.setArmazenamentoDef(rs.getString("armazenamentoDef"));
                defeito.setExpansaoDef(rs.getString("expansaoDef"));
                defeito.setMemoriaDef(rs.getString("memoriaDef"));
                defeito.setPicoDef(rs.getString("picoDef"));
                defeito.setPlacaMaeDef(rs.getString("placaMaeDef"));
                defeito.setProcessadorDef(rs.getString("processadorDef"));
                defeito.setSoDef(rs.getString("soDef"));
                defeito.setFonteDef(rs.getString("fonteDef"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar Defeito / Solução: " + e);
        }
        return listaDefeito;
    }

}
