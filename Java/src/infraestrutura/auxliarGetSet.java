/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;

import jssc.SerialPort;

/**
 *
 * @author Rick
 */
public class auxliarGetSet {

    private static String porta = null;
    private static String dados = null;
    private static String user = null;
    private static String nome = null;
    private static float pressao = 0f;
    private static float temp = 0f;
    private static float umid = 0f;
    private static String Hora = null;
    private static String data = null;
    private static boolean achou = false;
    private static boolean netConect = false;
    private static String idtelegram = null;
    private static boolean bomba = false;
    private static boolean valvula = false;
    private static boolean conectado = false;
    private static boolean checkNet = false;

    public static boolean isCheckNet() {
        return checkNet;
    }

    public static void setCheckNet(boolean checkNet) {
        auxliarGetSet.checkNet = checkNet;
    }
   

    public static boolean isAchou() {
        return achou;
    }

    public static void setAchou(boolean achou) {
        auxliarGetSet.achou = achou;
    }

    public static float getPressao() {
        return pressao;
    }

    public static void setPressao(float pressao) {
        auxliarGetSet.pressao = pressao;
    }

    public static float getTemp() {
        return temp;
    }

    public static void setTemp(float temp) {
        auxliarGetSet.temp = temp;
    }

    public static float getUmid() {
        return umid;
    }

    public static void setUmid(float umid) {
        auxliarGetSet.umid = umid;
    }

    public static String getHora() {
        return Hora;
    }

    public static void setHora(String Hora) {
        auxliarGetSet.Hora = Hora;
    }

    public static String getData() {
        return data;
    }

    public static void setData(String data) {
        auxliarGetSet.data = data;
    }

    public static boolean isConectado() {
        return conectado;
    }

    public static void SetConectado(boolean conectado) {
        auxliarGetSet.conectado = conectado;
    }

    public static boolean isBomba() {
        return bomba;
    }

    public static void setBomba(boolean bomba) {
        auxliarGetSet.bomba = bomba;
    }

    public static boolean isValvula() {
        return valvula;
    }

    public static void setValvula(boolean valvula) {
        auxliarGetSet.valvula = valvula;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        auxliarGetSet.user = user;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        auxliarGetSet.nome = nome;
    }

    public static String getIdtelegram() {
        return idtelegram;
    }

    public static void setIdtelegram(String idtelegram) {
        auxliarGetSet.idtelegram = idtelegram;
    }

    public static String getDados() {
        return dados;
    }

    public static void setDados(String dados) {
        auxliarGetSet.dados = dados;
    }
    private static SerialPort portaSerial = null;

    public static SerialPort getPortaSerial() {
        return portaSerial;
    }

    public static void setPortaSerial(SerialPort portaSerial) {
        auxliarGetSet.portaSerial = portaSerial;
    }

    public static String getPorta() {
        return porta;
    }

    public static void setPorta(String aPorta) {
        porta = aPorta;
    }

    public static boolean isNetConect() {
        return netConect;
    }

    public static void setNetConect(boolean aNetConect) {
        netConect = aNetConect;
    }

}
