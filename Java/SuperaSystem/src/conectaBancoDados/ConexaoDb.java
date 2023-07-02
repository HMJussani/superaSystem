/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectaBancoDados;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rick
 */
public class ConexaoDb {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private static  String URL ="jdbc:mysql://192.168.1.15:3306/dbSupera";
   // private static  String URL ="jdbc:mysql://192.168.100.101:3306/dbSupera";
    private static  String USER = "desenvolvimento"; 
    private static  String PASS = "Desenvolvimento@1";

    public static Connection getConection() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USER, PASS);
        } catch(CommunicationsException ex){
         JOptionPane.showMessageDialog(null, "Erro de conexão com Banco de Dados. Verifique IP / Servidor MySQL.");
        
        }catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ex);
            System.out.println(ex);
            }
        
        return conexao;
    }
   
}
