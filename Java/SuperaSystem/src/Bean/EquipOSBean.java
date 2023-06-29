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
   private String id_ordemServ;
   private String model;  
   private String patProd;

    public EquipOSBean() {
    }

    public String getNserie() {
        return nserie;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public String getId_ordemServ() {
        return id_ordemServ;
    }

    public void setId_ordemServ(String id_ordemServ) {
        this.id_ordemServ = id_ordemServ;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPatProd() {
        return patProd;
    }

    public void setPatProd(String patProd) {
        this.patProd = patProd;
    }

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }
  
   
   
   
    
}
