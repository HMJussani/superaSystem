/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author RMA
 */
import Bean.ClientesBean;
import conectaBancoDados.ConexaoDb;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = ConexaoDb.getConection();
    ArrayList<ClientesBean> clienteList = new ArrayList<>();

    /**
     * Método responsável por adicionar um novo cliente
     */
    public boolean adicionarCliente(String nome, String contato, String endereco, String tel, String email, String cidade, String estado){
       boolean sucesso = false;
        String sql = "insert into tbclientes(nomecli,contatocli,endcli,telcli,emailcli,cidadeCli, estadoCli) values(?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, contato);
            pst.setString(3, endereco);
            pst.setString(4, tel);
            pst.setString(5, email);
            pst.setString(6, cidade);
            pst.setString(7, estado);
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                sucesso = true;                
                conexao.close();
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Email já existente.\nEscolha outro email.");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
        return sucesso;
    }

    /**
     * Método responsável pela pesquisa de clientes pelo nome com filtro
     */
    public ArrayList<ClientesBean> pesquisarCliente(String nomecli){
        String sql = "select * from tbclientes where nomecli=?";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomecli);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomeCli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEmailcli(rs.getString("emailcli"));
                cliente.setCidadecli(rs.getString("cidadecli"));
                cliente.setEstadocli(rs.getString("estadocli"));
                clienteList.add(cliente);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
         return clienteList;
    }

    public ArrayList<ClientesBean> pesquisarCliente(){
        String sql = "select * from tbclientes";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomeCli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEmailcli(rs.getString("emailcli"));
                cliente.setCidadecli(rs.getString("cidadecli"));
                cliente.setEstadocli(rs.getString("estadocli"));
                clienteList.add(cliente);
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return clienteList;
    }

    /**
     * Método responsável pela edição dos dados do cliente
     */
    public void editarCliente(String nome, String endereco, String fone, String contato) throws SQLException {
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, endereco);
                pst.setString(3, fone);
                pst.setString(4, contato);
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Email não preenchido ou já existente.\nEscolha outro email.");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    /**
     * Método responsável por excluir um cliente
     */
    public boolean excluirCliente(String login) throws SQLException {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where login=?";
            try {
                conexao = ConexaoDb.getConection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, login);
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                    sucesso = true;
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui OS pendente.");
            } catch (HeadlessException | SQLException e2) {
                JOptionPane.showMessageDialog(null, e2);

            } finally {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

        return sucesso;
    }
}
