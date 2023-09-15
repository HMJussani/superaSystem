/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ComponenteBean;
import conectaBancoDados.ConexaoDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hmjussani
 */
public class ComponenteDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public ArrayList<ComponenteBean> pesquisa() {
        ArrayList<ComponenteBean> compList = new ArrayList<>();
        String sql = "SELECT * FROM tbcomponentes;";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                ComponenteBean componente = new ComponenteBean();
                componente.setTipo(rs.getString("idcli"));
                componente.setModelo(rs.getString("modelo"));
                componente.setFabricante(rs.getString("fabricante"));
                componente.setCapacidade(rs.getString("capacidade"));
                componente.setDetalhe(rs.getString("detalhe"));
                componente.setIdComp(rs.getInt("idComp"));
                compList.add(componente);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return compList;
    }

    public boolean novoComponente(String tipo, String fabricante, String modelo, String capacidade, String detalhe) {
        boolean sucesso = false;
        String sql = "insert into tbcomponentes (tipo, fabricante, modelo, capacidade, detalhe)values(?,?,?,?,?) ;";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, fabricante);
            pst.setString(3, modelo);
            pst.setString(4, capacidade);
            pst.setString(5, detalhe);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return sucesso;
    }

    public ArrayList<ComponenteBean> pesquisarComp(String arg, String valor) {
        ArrayList<ComponenteBean> compList = new ArrayList<>();
        String sql = "SELECT * FROM tbcomponentes where " + arg + "=?;";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, valor);
            rs = pst.executeQuery();
            while(rs.next()) {
                ComponenteBean comp = new ComponenteBean();
                comp.setIdComp(rs.getInt("idComp"));
                comp.setTipo(rs.getString("tipo"));
                comp.setFabricante(rs.getString("fabricante"));
                comp.setModelo(rs.getString("modelo"));
                comp.setCapacidade(rs.getString("capacidade"));
                comp.setDetalhe(rs.getString("detalhe"));
                compList.add(comp);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return compList;
    }

        public ArrayList<ComponenteBean> pesquisarComp(String arg1, String valor1 ,String arg2, String valor2 ) {
        ArrayList<ComponenteBean> compList = new ArrayList<>();
        String sql = "SELECT * FROM tbcomponentes where " + arg1 + "=? AND "+ arg2 + "=? ;";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, valor1);
            pst.setString(2, valor2);
            rs = pst.executeQuery();
            while (rs.next()) {
                ComponenteBean comp = new ComponenteBean();
                comp.setIdComp(rs.getInt("idComp"));
                comp.setTipo(rs.getString("tipo"));
                comp.setFabricante(rs.getString("fabricante"));
                comp.setModelo(rs.getString("modelo"));
                comp.setCapacidade(rs.getString("capacidade"));
                comp.setDetalhe(rs.getString("detalhe"));
                compList.add(comp);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return compList;
    }


    public ArrayList<ComponenteBean> pesquisarComp() {
        ArrayList<ComponenteBean> compList = new ArrayList<>();
        String sql = "SELECT * FROM tbcomponentes;";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                ComponenteBean comp = new ComponenteBean();
                comp.setIdComp(rs.getInt("idComp"));
                comp.setTipo(rs.getString("tipo"));
                comp.setFabricante(rs.getString("fabricante"));
                comp.setModelo(rs.getString("modelo"));
                comp.setCapacidade(rs.getString("capacidade"));
                comp.setDetalhe(rs.getString("detalhe"));
                compList.add(comp);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return compList;
    }
}
