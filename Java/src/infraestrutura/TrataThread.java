/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;



import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortException;
import view.TelaMedicao;
import view.TelaPrincipal;


/**
 *
 * @author Rick
 *
 * Classe criada para gerenciar Threads que tem processos demorados, como o
 * processo de conectar na porta serial, o programa deve "ouvir" a porta serial
 * até que cheguem dados
 */
public class TrataThread implements Runnable {

    auxliarGetSet aux = new auxliarGetSet();
    PortasSeriais portaCom = new PortasSeriais();
    private volatile boolean stop = false;
    private static SerialPort comPort = null;
    private static String portaSer = null;

    @Override
    public void run() {
        portaSer = auxliarGetSet.getPorta();
        String rec = null;
        while (!stop) {
            comPort = portaCom.abrePorta(portaSer);
            if (comPort.isOpened()) {
                try {
                    comPort.writeString("?#");
               } catch (SerialPortException ex) {
                    System.out.println("Erro: "+ ex);
                }
                rec = portaCom.recebeDados(comPort, 14);
                if (rec.equals("#controlvac_V1")) {
                    JOptionPane.showMessageDialog(null, "Módulo " + rec + " encontrado!");
                    auxliarGetSet.setPortaSerial(comPort);
                    auxliarGetSet.setAchou(true);
                   TelaPrincipal.ledSerialCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led2.png")));
                   parar();
                }
                parar();
            }
        }
    }

    public void parar() {
        this.stop = true;
    }

}
