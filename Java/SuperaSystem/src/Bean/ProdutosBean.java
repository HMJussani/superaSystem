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
    
    private String nserie;
    private String loteProd;
    private String patProd;
    private String model;
    private String mem; 
    private String mBoard;
    private String power;
    private String storage;            
    private String sParalela;
    private String sSerial; 
    private String redeLan;
    private String wifi; 

    public ProdutosBean(String nserie, String loteProd, String patProd, String model, String mem, String mBoard, String power, String storage, String sParalela, String sSerial, String redeLan, String wifi) {
        this.nserie = nserie;
        this.loteProd = loteProd;
        this.patProd = patProd;
        this.model = model;
        this.mem = mem;
        this.mBoard = mBoard;
        this.power = power;
        this.storage = storage;
        this.sParalela = sParalela;
        this.sSerial = sSerial;
        this.redeLan = redeLan;
        this.wifi = wifi;
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getLoteProd() {
        return loteProd;
    }

    public void setLoteProd(String loteProd) {
        this.loteProd = loteProd;
    }

    public String getPatProd() {
        return patProd;
    }

    public void setPatProd(String patProd) {
        this.patProd = patProd;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
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
  
    
}
