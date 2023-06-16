/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarioBean;

/**
 *
 * @author Rick
 */
public class usuariosBean {
    
    private static String user = null;
    private static String pass = null;

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
