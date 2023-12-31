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
    

    /**
     * Método responsável por adicionar um novo cliente
     */
    public boolean adicionarCliente(String idcli, String nome, String contato, String endereco, String tel, String email, String cidade, String estado) {
        boolean sucesso = false;
        String sql = "insert into tbclientes(idcli,nomecli,contatocli,endcli,telcli,emailcli,cidadeCli, estadoCli) values(?,?,?,?,?,?,?,?)";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idcli);
            pst.setString(2, nome);
            pst.setString(3, contato);
            pst.setString(4, endereco);
            pst.setString(5, tel);
            pst.setString(6, email);
            pst.setString(7, cidade);
            pst.setString(8, estado);
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
   
    public ArrayList<ClientesBean> pesquisarCliente(String nomecli) {
        ArrayList<ClientesBean> clienteList = new ArrayList<>();
        String sql = "SELECT * FROM tbclientes where nomecli=?;";       
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomecli);
            rs = pst.executeQuery();
            if (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomecli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEndcli(rs.getString("endcli"));
                cliente.setTelcli(rs.getString("telcli"));
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

    public ArrayList<ClientesBean> pesquisarCliente(String arg, String valor) {
        ArrayList<ClientesBean> clienteList = new ArrayList<>();
        String sql = "SELECT * FROM tbclientes where " + arg +"=?;";       
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, valor);
            rs = pst.executeQuery();
            if (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomecli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEndcli(rs.getString("endcli"));
                cliente.setTelcli(rs.getString("telcli"));
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
    
    public ArrayList<ClientesBean> pesquisarCliente() {
        ArrayList<ClientesBean> clienteList = new ArrayList<>();
        String sql = "select * from tbclientes";
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClientesBean cliente = new ClientesBean();
                cliente.setIdcli(rs.getString("idcli"));
                cliente.setNomecli(rs.getString("nomecli"));
                cliente.setContatocli(rs.getString("contatocli"));
                cliente.setEndcli(rs.getString("endcli"));
                cliente.setTelcli(rs.getString("telcli"));
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
    public boolean editarCliente(String idcli, String nome, String contato, String endereco, String tel, String email, String cidade, String estado) {
        boolean sucesso = false;
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "update tbclientes set nomecli=?,contatocli=?,endcli=?,telcli=?,emailcli=?,cidadeCli=?, estadoCli=? where idcli=?";
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
                pst.setString(8, idcli);
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    conexao.close();
                    sucesso = true;
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Email não preenchido ou já existente.\nEscolha outro email.");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
        return sucesso;
    }

    public boolean excluirCliente(String login) {
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
                    sucesso = true;
                    conexao.close();
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui OS pendente.");
            } catch (HeadlessException | SQLException e2) {
                JOptionPane.showMessageDialog(null, e2);

            }
        }

        return sucesso;
    }
}
