/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarioDAO;

import conectaBancoDados.ConexaoDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rick
 */
public class usuariosDao {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();

    public boolean checaUser(String login, String pass) throws SQLException {
        boolean sucesso = false;
        String sql = "select * from tbusuarios where usuario =? and senha=?";

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

    public boolean checaUser(String login) throws SQLException {
        boolean sucesso = false;
        String user = null;
        String sql = "select idtelegram, nome from tbusuarios where idtelegram =?;";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
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
        String user = null;
        String sql = "delete from tbusuarios where idtelegram =?;";

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

    public String retornaUser() throws SQLException {
        String user = null;
        String sql = "select * from tbusuarios;";
        try {

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user += rs.getString("nome") + "," + rs.getString("idtelegram") + ",";
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL usuários: " + e);
        } finally {
            conexao.close();

        }
        return user;
    }

    public boolean editaUser(String user, String pass) throws SQLException {
        String sql = "update usuarios set pass=? where login =?;";
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

    public boolean criaUser(String nome, String user, String senha, String telegram) throws SQLException {
        String sql = "insert into tbusuarios (nome, usuario, senha, idtelegram) values (?,?,?,? );";
        boolean sucesso = false;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, user);
            stmt.setString(3, senha);
            stmt.setString(4, telegram);
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
