/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Rick
 */
public class clientesBean {
    
    private static String user = null;
    private static String pass = null;
    private static String perfil = null;
    private static String nome = null;
    
     public static String getPerfil() {
        return perfil;
    }

    public static void setPerfil(String perfil) {
        clientesBean.perfil = perfil;
    }
    
     public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        clientesBean.nome = nome;
    }
    
    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        clientesBean.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        clientesBean.pass = pass;
    }
    
}
