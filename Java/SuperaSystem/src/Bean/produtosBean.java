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
public class produtosBean {
    
    private static String nserieProd = null;
    private static String tipoProd = null;
    private static String modeloProd = null;
    private static String descricaoProd = null;
    private static String patProd = null;
    
    public produtosBean() {
    }
    
     public static String getNserieProd() {
        return nserieProd;
    }

    public static void setNserieProd(String nserieProd) {
        produtosBean.nserieProd = nserieProd;
    }
    
     public static String getTipoProd() {
        return tipoProd;
    }

    public static void setTipoProd(String tipoProd) {
        produtosBean.tipoProd = tipoProd;
    }
    
    public static String getModeloProd() {
        return modeloProd;
    }

    public static void setModeloProd(String modeloProd) {
        produtosBean.modeloProd = modeloProd;
    }

    public static String getDescricaoProd() {
        return descricaoProd;
    }

    public static void setDescricaoProd(String descricaoProd) {
        produtosBean.descricaoProd = descricaoProd;
    }

    public static String getPatProd() {
        return patProd;
    }

    public static void setPatProd(String patProd) {
        produtosBean.patProd = patProd;
    }
}
