/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author RMA
 */

import conectaBancoDados.ConexaoDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 

public class PedidoDAO {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Connection  conexao = ConexaoDb.getConection();

}
