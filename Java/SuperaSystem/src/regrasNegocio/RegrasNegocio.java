/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package regrasNegocio;

import Bean.ModelosBean;
import Bean.OrdServBean;
import DAO.ModelosDAO;
import DAO.OrdServDAO;
import java.util.ArrayList;
import viewRMA.TelaOrcamento;

/**
 *
 * @author RMA
 */
public class RegrasNegocio {

   
    private String memoria;
    private String memTipo;
    private String placaMae;
    private String expansao;
    private String armazenaTipo;
    private String armazena;
    private String pico;
    private String sParalela;
    private String sSerial;
    private String redeLan;
    private String wifi;
    private String tipo;
    private String processador;
    private String gabinete;
    private String painel;

    public String[] getDadosPecas(String model) {         
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo("model", model);
        placaMae = modelList.get(0).getmBoard();
        pico = modelList.get(0).getFonteAlimenta();
        armazenaTipo = modelList.get(0).getArmazenaTipo();
        gabinete = modelList.get(0).getGabinete();
        redeLan = modelList.get(0).getRedeLan();
        memTipo = modelList.get(0).getMemTipo();
        painel = modelList.get(0).getPainel();
        processador = modelList.get(0).getProcessador();
        wifi = modelList.get(0).getWifi();
        expansao = modelList.get(0).getExpansao();
        String[] pecas = {placaMae,pico,armazenaTipo,gabinete,redeLan, memTipo, painel, processador, wifi,expansao};
        return pecas;
    }
    
    public String getOS(String idcli) {
        String ordemDeServico = "";
        OrdServDAO ordemSErv = new OrdServDAO();
        ArrayList<OrdServBean> os = ordemSErv.pesquisarOsBy("idcli", idcli);
        int conta = 0;
        for (int i = 0; i < os.size(); i++) {
            if (os.get(i).getAberta()) {
                ordemDeServico += os.get(i).getIdOrdServ();
                ordemDeServico += "\n";
                conta++;
            }
        }
        if (conta == 0) {
            ordemDeServico = "zero";
        }
        return ordemDeServico;
    }
    /*
     private void getChekEqip(String model){
    ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo("model", model);
        if(TelaOrcamento.chekMother.isSelected())placaMae = modelList.get(0).getmBoard();
        if(TelaOrcamento.checkFonte.isSelected())pico = modelList.get(0).getFonteAlimenta();
        if(TelaOrcamento.chekArm.isSelected())armazenaTipo = modelList.get(0).getArmazenaTipo();
        if(TelaOrcamento.chekGab.isSelected())gabinete = modelList.get(0).getGabinete();
        if(TelaOrcamento.chekLan.isSelected())redeLan = modelList.get(0).getRedeLan();
        if(chekMem.isSelected())memTipo = modelList.get(0).getMemTipo();
        if(chekPainel.isSelected())painel = modelList.get(0).getPainel();
        if(chekProc.isSelected()) processador = modelList.get(0).getProcessador();
        if(chekwifi.isSelected())wifi = modelList.get(0).getWifi();  
    }
    */

}
