/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;

import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import view.TelaMedicao;

/**
 *
 * @author Rick
 */
public class PortasSeriais {

    public SerialPort abrePorta(String porta) {
        auxliarGetSet auxiliar = new auxliarGetSet();
        SerialPort portaSerial = new SerialPort(porta);
        try {

            portaSerial.openPort();
            portaSerial.setParams(
                    SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            portaSerial.setEventsMask(MASK_RXCHAR);
           auxiliar.SetConectado(true);
            
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return portaSerial;
    }

    public void enviaDados(SerialPort porta, String dado) {
        if (porta.isOpened()) {
            try {
                porta.writeString(dado);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
//        if (!porta.isOpened()) {
//           // JOptionPane.showMessageDialog(null, "A porta não está aberta!");
//        }
    }

    public String recebeDados(SerialPort porta, int num) {
        String lido = null;
        try {
            lido = porta.readString(num);

        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return lido;
    }
    
    public void recebeDados(SerialPort porta) throws SerialPortException {
          porta.addEventListener((SerialPortEvent serialPortEvent) -> {
            if (serialPortEvent.isRXCHAR()) {
                
                String st = null;
                try {
                    st = porta.readString(12);
                    auxliarGetSet.setDados(st);
                    //TelaMedicao.manometro.setValue(Float.parseFloat(st));
                } catch (SerialPortException ex) {
                    System.out.println(" Erro :" + ex);
                }
            }
        });
        
           
    }
}
