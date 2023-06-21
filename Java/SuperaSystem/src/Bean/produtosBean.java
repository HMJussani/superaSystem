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
public class ProdutosBean {
    
    private static String nserieProd = null;
    private static String tipoProd = null;
    private static String modeloProd = null;
    private static String descricaoProd = null;
    private static String patProd = null;
    
    public ProdutosBean() {
    }
    
     public static String getNserieProd() {
        return nserieProd;
    }

    public static void setNserieProd(String nserieProd) {
        ProdutosBean.nserieProd = nserieProd;
    }
    
     public static String getTipoProd() {
        return tipoProd;
    }

    public static void setTipoProd(String tipoProd) {
        ProdutosBean.tipoProd = tipoProd;
    }
    
    public static String getModeloProd() {
        return modeloProd;
    }

    public static void setModeloProd(String modeloProd) {
        ProdutosBean.modeloProd = modeloProd;
    }

    public static String getDescricaoProd() {
        return descricaoProd;
    }

    public static void setDescricaoProd(String descricaoProd) {
        ProdutosBean.descricaoProd = descricaoProd;
    }

    public static String getPatProd() {
        return patProd;
    }

    public static void setPatProd(String patProd) {
        ProdutosBean.patProd = patProd;
    }
}
