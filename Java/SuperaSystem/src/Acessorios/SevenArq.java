/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acessorios;

import Bean.DefEquipBean;
import Bean.ModelosBean;
import DAO.DefEquipDAO;
import DAO.ModelosDAO;
import java.util.ArrayList;

/**
 *
 * @author hmjussani
 */
public class SevenArq {

    private String model;
    private String nserie;
    private String memoria;
    private String so;
    private String placaMae;
    private String expansao;
    private String armazenamento;
    private String pico;
    private String sParalela;
    private String sSerial;
    private String redeLan;
    private String wifi;
    private String tipo;
    private String processador;
    private String gabinete;
    private String painel;
    private String placaMaeDef;
    private String memoriaDef;
    private String picoDef;
    private String armazenamentoDef;
    private String soDef;
    private String expansaoDef;
    private String processadorDef;
    private String TesteDef;
    private String fonteDef;

    private void getDadosOs(String model) {
        ModelosDAO modeloDao = new ModelosDAO();
        ArrayList<ModelosBean> modelList = modeloDao.pesquisarModelo("model", model);
        placaMae = (modelList.get(0).getmBoard());
        pico = (modelList.get(0).getFonteAlimenta());
        armazenamento = (modelList.get(0).getArmazenaModel());
        gabinete = (modelList.get(0).getGabinete());
        redeLan = (modelList.get(0).getRedeLan());
        memoria = (modelList.get(0).getMem());
        painel = (modelList.get(0).getPainel());
        processador = (modelList.get(0).getProcessador());
        wifi = (modelList.get(0).getWifi());
        expansao = modelList.get(0).getExpansao();
        so = modelList.get(0).getSo();
    }

    private void getDadosDef(String nSerie) {

        DefEquipDAO defEquip= new DefEquipDAO();
        ArrayList<DefEquipBean> defList = defEquip.pesquisaDef("nserie", nserie);
        placaMaeDef = defList.get(0).getmBoard();
        picoDef = defList.get(0).getFonteAlimenta();
        armazenamentoDef = defList.get(0).getArmazenaModel();
        memoriaDef = defList.get(0).getMem();
        processadorDef = defList.get(0).getProcessador();
        expansaoDef =defList.get(0).getExpansao();
        soDef =defList.get(0).getSo();
    }

    public String arqSevenPeca(String model) {
        getDadosOs(model);
        String texto;
        texto = "1.DESCRIÇÕES DAS PEÇAS INTERNAS" + "\n";
        texto += "- PLACA MÃE: " + placaMae + "\n";
        texto += "- PROCESSADOR: " + processador + "\n";
        texto += "- MEMÓRIA: " + memoria + "\n";
        texto += "- PICO: " + pico + "\n";
        texto += "- ARMAZENAMENTO: " + armazenamento + "\n";
        texto += "- S.O: " + so + "\n";
        texto += "- EXPANSÕES: " + expansao + "\n";
        return texto;
    }

    public String arqSevenTest(String nSerie) {
        getDadosDef(nSerie);
        String texto;
        texto = "2.TESTE " + TesteDef + "\n";
        texto += "- BURN IN TESTE: " + placaMaeDef + "\n";
        texto += "- CARREGAMENTO S.O: " + soDef + "\n";
        texto += "- FONTE ALIMENTAÇÂO: " + picoDef + "\n";
        texto += "- HD/SSD: " + armazenamentoDef + "\n";
        texto += "- MEMORIA RAM: " + memoriaDef + "\n";
        texto += "- REDE: " + expansaoDef + "\n";
        return texto;
    }

    public String arqSevenOrc(String placaMaeOrc, String processadorOrc, String memoriaOrc, String picoOrc,String fonteDef, String armazenamentoOrc, String soOrc) {
        String texto = "3.ORÇAMENTO" + "\n";
        texto += "- PLACA MÃE: " + placaMaeOrc + "\n";
        texto += "- PROCESSADOR: " + processadorOrc + "\n";
        texto += "- MEMÓRIA: " + memoriaOrc + "\n";
        texto += "- PICO: " + picoOrc + "\n";
        texto += "- FONTE ALIMENTAÇÃO: " + fonteDef + "\n";
        texto += "- ARMAZENAMENTO: " + armazenamentoOrc + "\n";
        texto += "- S.O: " + soOrc + "\n";
        return texto;
    }

    public String arqSevenOrcExp(String expansaoOrc, String caboSerial, String caboPar, String kitPainel, String kitGab, String maoObra) {
        String texto = "- EXPANSÕES: " + expansaoOrc + "\n";
        texto += "- CABO SERIAL: " + caboSerial + "\n";
        texto += "- CABO PARALELO: " + caboPar + "\n";
        texto += "- KIT GABINETE: " + kitGab + "\n";
        texto += "- KIT PAINEL: " + kitPainel + "\n";
        texto += "- MANUTENÇÃO: " + maoObra + "\n";
        texto += "4.DIAGNOSTICO:" + "\n";
        return texto;
    }

    public String arqSevenDiag(String diag) {
        String texto = "4.DIAGNOSTICO:" + "\n";
        texto += diag + "\n";
        return texto;
    }

}
