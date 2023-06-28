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

    /**
     * Método responsável pela pesquisa de clientes pelo nome com filtro
     */
    public ArrayList<String> pesquisarCliente(String nomecli) {
        String sql = "SELECT * FROM tbclientes where nomecli=?;";
        ArrayList<String> cliente = new ArrayList<>();
        try {
            conexao = ConexaoDb.getConection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomecli);
            rs = pst.executeQuery();
            if(rs.next()) {
               
                cliente.add(rs.getString("idcli"));
                cliente.add(rs.getString("nomecli"));
                cliente.add(rs.getString("contatocli"));
                cliente.add(rs.getString("endcli"));
                cliente.add(rs.getString("telcli"));
                cliente.add(rs.getString("emailcli"));
                cliente.add(rs.getString("cidadecli"));
                cliente.add(rs.getString("estadocli"));
            }
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return cliente;
    }

    public ArrayList<ClientesBean> pesquisarCliente() {
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
