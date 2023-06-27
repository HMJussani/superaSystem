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
public class ClientesBean {

    private String idcli = null;
    private String nomecli = null;
    private String contatocli = null;
    private String endcli = null;
    private String telcli = null;
    private String emailcli = null;
    private String cidadecli = null;
    private String estadocli = null;

    public ClientesBean(String idcli, String nomecli, String contatocli, String endcli, String telcli, String emailcli, String cidadecli, String estadocli) {
        this.idcli = idcli;
        this.nomecli = nomecli;
        this.contatocli = contatocli;
        this.endcli = endcli;
        this.telcli = telcli;
        this.emailcli = emailcli;
        this.cidadecli = cidadecli;
        this.estadocli = estadocli;
    }

    public ClientesBean() {
    }
   
    public String getIdcli() {
        return idcli;
    }

    public void setIdcli(String idcli) {
        this.idcli = idcli;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getContatocli() {
        return contatocli;
    }

    public void setContatocli(String contatocli) {
        this.contatocli = contatocli;
    }

    public String getEndcli() {
        return endcli;
    }

    public void setEndcli(String endcli) {
        this.endcli = endcli;
    }

    public String getTelcli() {
        return telcli;
    }

    public void setTelcli(String telcli) {
        this.telcli = telcli;
    }

    public String getEmailcli() {
        return emailcli;
    }

    public void setEmailcli(String emailcli) {
        this.emailcli = emailcli;
    }

    public String getCidadecli() {
        return cidadecli;
    }

    public void setCidadecli(String cidadecli) {
        this.cidadecli = cidadecli;
    }

    public String getEstadocli() {
        return estadocli;
    }

    public void setEstadocli(String estadocli) {
        this.estadocli = estadocli;
    }

}
