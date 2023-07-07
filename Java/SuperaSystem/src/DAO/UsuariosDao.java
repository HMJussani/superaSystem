/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.UsuariosBean;
import conectaBancoDados.ConexaoDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Rick
 */
public class UsuariosDAO {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();
    ArrayList<UsuariosBean> usuario = new ArrayList<>();

    public boolean checaUser(String login, String pass) throws SQLException {
        boolean sucesso = false;
        String sql = "select * from tbusuarios where login =? and senha=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs.next()) {
                sucesso = true;
                 conexao.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        }
        return sucesso;
    }

    public boolean excluiUser(String login) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where login =?;";
            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, login);
                int execute = stmt.executeUpdate();
                if (execute > 0) {
                    sucesso = true;
                    conexao.close();
                }else{
                     JOptionPane.showMessageDialog(null, "Erro deletando usuários: " + login);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro deletando usuários: " + e);
            }
        }
        return sucesso;
    }

    public ArrayList<UsuariosBean> pesquisarUser() {
        String sql = "select * from tbusuarios";
        try {
            conexao = ConexaoDb.getConection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                UsuariosBean user = new UsuariosBean();
                user.setIduser(rs.getString("iduser"));
                user.setNome(rs.getString("usuario"));
                user.setUser(rs.getString("login"));
                user.setPass(rs.getString("senha"));
                user.setPerfil(rs.getString("perfil"));
                user.setEmail(rs.getString("email"));
                usuario.add(user);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return usuario;
    }
    
        public ArrayList<UsuariosBean> pesquisarUser(String login) {
        String sql = "select * from tbusuarios where login=?";
        try {
            conexao = ConexaoDb.getConection();
            stmt = conexao.prepareStatement(sql);
             stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                UsuariosBean user = new UsuariosBean();
                user.setIduser(rs.getString("iduser"));
                user.setNome(rs.getString("usuario"));
                user.setUser(rs.getString("login"));
                user.setPass(rs.getString("senha"));
                user.setPerfil(rs.getString("perfil"));
                user.setEmail(rs.getString("email"));
                usuario.add(user);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return usuario;
    }

    public boolean editaUser(String user, String pass) throws SQLException {
        String sql = "update tbusuarios set senha=? where login =?;";
        boolean sucesso = false;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pass);
            stmt.setString(2, user);
            int executou = stmt.executeUpdate();
            if (executou > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar nova senha: " + e);
        }
        return sucesso;
    }

    public boolean criaUser(String nome, String login, String senha, String perfil, String email) {
        String sql = "insert into tbusuarios (usuario, login, senha, perfil, email) values (?,?,?,?,? );";
        boolean sucesso = false;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setString(4, perfil);
            stmt.setString(5, email);
            int executou = stmt.executeUpdate();
            if (executou > 0) {
                sucesso = true;
                conexao.close();
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar novo usuário: " + e);

        }
        return sucesso;
    }
}
