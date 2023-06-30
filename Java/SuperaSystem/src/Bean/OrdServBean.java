/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import java.sql.Date;



/**
 *
 * @author RMA
 */
public class OrdServBean {
    
    private String idOrdServ;
    private String idcli;
    private Date dataAbertura;
    private Date dataFechamento;
    private Boolean garantia;
    private String defeito;    
    private String tecnico;
    private String valor;
    
    
    public OrdServBean() {
        
    }

   

    public void setIdcli(String idcli) {
        this.idcli = idcli;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Boolean getGarantia() {
        return garantia;
    }

    public void setGarantia(Boolean garantia) {
        this.garantia = garantia;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdOrdServ() {
        return idOrdServ;
    }

    public void setIdOrdServ(String idOrdServ) {
        this.idOrdServ = idOrdServ;
    }

    
    
   
}
