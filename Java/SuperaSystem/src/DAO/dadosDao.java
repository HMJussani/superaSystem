/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import conectaBancoDados.ConexaoDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Rick
 */
public class dadosDao {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Connection  conexao = ConexaoDb.getConection();
    
    public boolean salvaBanco(){
       boolean sucesso = false;
       
       
       return sucesso;
    }
    
    public boolean leBanco(){
       boolean sucesso = false;
       
       
       return sucesso;
    }
}
