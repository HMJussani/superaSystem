/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Bean.DefSolBean;
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
public class DefSolDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public boolean novoDefeito(String nserie, String defeito, String solucao) {
        boolean sucesso = false;
        String sql = "insert into tbdefsol (nserie, defeito, solucao)values(?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            pst.setString(2, defeito);
            pst.setString(3, solucao);
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

    public boolean editaDefeito(String nserie, String defeito, String solucao) {
        boolean sucesso = false;
        String sql = "update tbdefsol set nserie=?, defeito=?, solucao=? where nserie=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);           
            pst.setString(1, defeito);
            pst.setString(2, solucao);
             pst.setString(3, nserie);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Defeito/Solução: " + e);
        }

        return sucesso;
    }

    public ArrayList<DefSolBean> listaDefeitos() {
        ArrayList<DefSolBean> listaDefeito = new ArrayList<>();
        String sql = "select * from tbdefsol";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                DefSolBean defeito = new DefSolBean();
                defeito.setDefeito(rs.getString("defeito"));
                defeito.setSolucao(rs.getString("solucao"));
                defeito.setNserie(rs.getString("nserie"));
                listaDefeito.add(defeito);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar Defeito / Solução: " + e);
        }
        return listaDefeito;
    }

    public ArrayList<DefSolBean> listaDefeitos(String nserie) {
        ArrayList<DefSolBean> listaDefeito = new ArrayList<>();
        String sql = "select * from tbdefsol where nserie =?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nserie);
            rs = pst.executeQuery();
            while (rs.next()) {
                DefSolBean defeito = new DefSolBean();
                defeito.setDefeito(rs.getString("defeito"));
                defeito.setSolucao(rs.getString("solucao"));
                defeito.setNserie(rs.getString("nserie"));
                listaDefeito.add(defeito);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar Defeito / Solução: " + e);
        }
        return listaDefeito;
    }
}
