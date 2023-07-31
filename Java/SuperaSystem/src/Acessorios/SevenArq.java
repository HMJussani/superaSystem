/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acessorios;

/**
 *
 * @author hmjussani
 */
public class SevenArq {

    public String arqSevenPeca(String placaMae, String processador, String memoria, String pico, String armazenamento, String so, String expansao) {
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

    public String arqSevenTest(String placaMaeDef, String memoriaDef, String picoDef, String armazenamentoDef, String soDef, String expansaoDef) {
        String texto;
        texto = "2.TESTE " + "\n";
        texto += "- BURN IN TESTE: " + placaMaeDef + "\n";
        texto += "- CARREGAMENTO S.O: " + soDef + "\n";
        texto += "- FONTE ALIMENTAÇÂO: " + picoDef + "\n";
        texto += "- HD/SSD: " + armazenamentoDef + "\n";
        texto += "- MEMORIA RAM: " + memoriaDef + "\n";
        texto += "- REDE: " + expansaoDef + "\n";
        return texto;
    }

    public String arqSevenOrc(String placaMaeOrc, String processadorOrc, String memoriaOrc, String picoOrc, String armazenamentoOrc, String soOrc) {
        String texto = "3.ORÇAMENTO" + "\n";
        texto += "- PLACA MÃE: " + placaMaeOrc + "\n";
        texto += "- PROCESSADOR: " + processadorOrc + "\n";
        texto += "- MEMÓRIA: " + memoriaOrc + "\n";
        texto += "- PICO: " + picoOrc + "\n";
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
        texto += "Diagnóstico:" + "\n";
        return texto;
    }

    public String arqSevenDiag(String diag) {
        return diag;
    }

}
