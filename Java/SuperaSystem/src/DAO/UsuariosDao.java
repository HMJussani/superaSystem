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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Rick
 */
public class UsuariosDao {

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
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        } finally {
            conexao.close();

        }
        return sucesso;
    }
  
    public boolean excluiUser(String login) throws SQLException {
        boolean sucesso = false;
        //String user = null;
        String sql = "delete from tbusuarios where login =?;";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
           int execute= stmt.executeUpdate();
            if (execute>0) {
                sucesso = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        } finally {
            conexao.close();

        }
        return sucesso;
    }

  public ArrayList<UsuariosBean> pesquisarUser(){ 
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
            usuario.add(user);
            }
           conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }         
        return usuario;
    }
    
    public String retornaUser(String login) throws SQLException {
        String user = null;
        String sql = "select * from tbusuarios where login=?;";
        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user += rs.getString("iduser") + "," + rs.getString("usuario") + "," + rs.getString("login") + "," + rs.getString("perfil"); 
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        } finally {
            conexao.close();

        }
        return user;
    }
 public String retornaPerfil(String login) throws SQLException {
        String user = null;
        String sql = "select * from tbusuarios where login=?;";
        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = rs.getString("perfil"); 
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        } finally {
            conexao.close();

        }
        return user;
    }
    
    public boolean editaUser(String user, String pass) throws SQLException {
        String sql = "update tbusuarios set senha=? where login =?;";
        boolean sucesso = false;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pass);
            stmt.setString(2, user);
            stmt.executeUpdate();
            sucesso = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar nova senha: " + e);

        } finally {
            conexao.close();

        }
        return sucesso;
    }

    public boolean criaUser(String nome, String login, String senha, String perfil) throws SQLException {
        String sql = "insert into tbusuarios (nome, usuario, senha, perfil) values (?,?,?,? );";
        boolean sucesso = false;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setString(4, perfil);
            stmt.executeUpdate();
            sucesso = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar novo usuário: " + e);

        } finally {
            conexao.close();

        }
        return sucesso;
    }
}
