/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author RMA
 */
public class OrdemServicoBean {
    
    private String tipo;
    private String situacao;
    private String equipamento; 
    private String defeito; 
    private String servico;
    private String tecnico;
    private String valor;
    private String idcli;
    private ArrayList<ProdutosBean> equipamentos;
    private Calendar data_os; 
    

    public OrdemServicoBean(String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, String valor, String idcli, ArrayList<ProdutosBean> equipamentos, Calendar data_os) {
        this.tipo = tipo;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.servico = servico;
        this.tecnico = tecnico;
        this.valor = valor;
        this.idcli = idcli;
        this.equipamentos = equipamentos;
        this.data_os = data_os;
    }

    public OrdemServicoBean() {
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
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

    public ArrayList<ProdutosBean> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(ArrayList<ProdutosBean> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Calendar getData_os() {
        return data_os;
    }

    public void setData_os(Calendar data_os) {
        this.data_os = data_os;
    }
    
    
    
    
    
}
