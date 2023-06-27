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
public class OrdemServicoBean {
    
    private String id_pedido;
    private Date data_os;
    private int qtde_equipamento;
    private String defeito;
    private String servico;
    private String tecnico;
    private String valor;
    private String idcli;
    private String situacao;
    private String orcamento; 
    
    public OrdemServicoBean() {
        
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getData_os() {
        return data_os;
    }

    public void setData_os(Date data_os) {
        this.data_os = data_os;
    }

    public int getQtde_equipamento() {
        return qtde_equipamento;
    }

    public void setQtde_equipamento(int qtde_equipamento) {
        this.qtde_equipamento = qtde_equipamento;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
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

    public String getIdcli() {
        return idcli;
    }

    public void setIdcli(String idcli) {
        this.idcli = idcli;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }

    
   
}
