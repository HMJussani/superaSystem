/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import java.util.Date;

/**
 *
 * @author RMA
 */
public class ComponenteBean {
    
    private String nserie;
    private String cod;
    private String tipo;
    private String modelo;
    private String fabricante;
    private String descricao;
    private String custo;
    private String datavenda;

    public ComponenteBean(String nserie, String cod, String tipo, String modelo, String fabricante, String descricao, String custo, String datavenda) {
        this.nserie = nserie;
        this.cod = cod;
        this.tipo = tipo;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.descricao = descricao;
        this.custo = custo;
        this.datavenda = datavenda;
    }
   
        

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(String datavenda) {
        this.datavenda = datavenda;
    }
    
    
    
}
