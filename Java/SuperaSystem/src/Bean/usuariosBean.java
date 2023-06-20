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
public class usuariosBean {
    
    private static String user = null;
    private static String pass = null;
    private static String perfil = null;
    private static String nome = null;
    
     public static String getPerfil() {
        return perfil;
    }

    public static void setPerfil(String perfil) {
        usuariosBean.perfil = perfil;
    }
    
     public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        usuariosBean.nome = nome;
    }
    
    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        usuariosBean.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        usuariosBean.pass = pass;
    }
    
}
