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
public class ModelosBean {
    
    private int idModel;
    private String tipo;
    private String processador;
    private String gabinete;
    private String model;
    private String mem; 
    private String mBoard;
    private String fonteAlimenta;
    private String armazenaModel;
    private String armazenaTipo;
    private String sParalela;
    private String sSerial; 
    private String redeLan;
    private String wifi; 
    private String expansao;

    public ModelosBean() {
    }

    public ModelosBean(int idModel, String tipo, String processador, String gabinete, String model, String mem, String mBoard, String fonteAlimenta, String armazenaModel, String armazenaTipo, String sParalela, String sSerial, String redeLan, String wifi, String expansao) {
        this.idModel = idModel;
        this.tipo = tipo;
        this.processador = processador;
        this.gabinete = gabinete;
        this.model = model;
        this.mem = mem;
        this.mBoard = mBoard;
        this.fonteAlimenta = fonteAlimenta;
        this.armazenaModel = armazenaModel;
        this.armazenaTipo = armazenaTipo;
        this.sParalela = sParalela;
        this.sSerial = sSerial;
        this.redeLan = redeLan;
        this.wifi = wifi;
        this.expansao = expansao;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getGabinete() {
        return gabinete;
    }

    public void setGabinete(String gabinete) {
        this.gabinete = gabinete;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getmBoard() {
        return mBoard;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getFonteAlimenta() {
        return fonteAlimenta;
    }

    public void setFonteAlimenta(String fonteAlimenta) {
        this.fonteAlimenta = fonteAlimenta;
    }

    public String getArmazenaModel() {
        return armazenaModel;
    }

    public void setArmazenaModel(String armazenaModel) {
        this.armazenaModel = armazenaModel;
    }

    public String getArmazenaTipo() {
        return armazenaTipo;
    }

    public void setArmazenaTipo(String armazenaTipo) {
        this.armazenaTipo = armazenaTipo;
    }

    public String getsParalela() {
        return sParalela;
    }

    public void setsParalela(String sParalela) {
        this.sParalela = sParalela;
    }

    public String getsSerial() {
        return sSerial;
    }

    public void setsSerial(String sSerial) {
        this.sSerial = sSerial;
    }

    public String getRedeLan() {
        return redeLan;
    }

    public void setRedeLan(String redeLan) {
        this.redeLan = redeLan;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getExpansao() {
        return expansao;
    }

    public void setExpansao(String expansao) {
        this.expansao = expansao;
    }
    
    
    
}
