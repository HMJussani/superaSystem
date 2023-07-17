/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

/**
 *
 * @author RMA
 */
public class EquipOSBean {
    
   private String nserie ;
   private String idCli ;
   private String idOrdServ;
   private String model;  
   private String patEquip;
   private Boolean garantia;
   private Boolean analizado;

    public EquipOSBean() {
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getidOrdServ() {
        return idOrdServ;
    }

    public void setIdOrdServ(String idOrdServ) {
        this.idOrdServ = idOrdServ;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPatEquip() {
        return patEquip;
    }

    public void setPatEquip(String patEquip) {
        this.patEquip = patEquip;
    }

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    public Boolean getGarantia() {
        return garantia;
    }

    public void setGarantia(Boolean garantia) {
        this.garantia = garantia;
    }

    public Boolean getAnalizado() {
        return analizado;
    }

    public void setAnalizado(Boolean analizado) {
        this.analizado = analizado;
    }
  
   
   
   
    
}
