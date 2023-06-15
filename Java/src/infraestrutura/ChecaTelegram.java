/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;

import java.awt.Color;
import javax.swing.JOptionPane;
import view.TelaPrincipal;
import static view.TelaPrincipal.lblCheckNet;
import static view.TelaPrincipal.lblNet;
import static view.TelaPrincipal.lblTelegram;

/**
 *
 * @author Rick
 */
public class ChecaTelegram implements Runnable {

    private volatile boolean stop = false;
    Telegram telegram = new Telegram();

    public void run() {

        while (!stop) {//faz algo
            try {
                Thread.sleep(2000);
                auxliarGetSet.SetConectado(telegram.conexaoExt(auxliarGetSet.isCheckNet()));
                if (auxliarGetSet.isConectado()) {
                    telegram.conversa(auxliarGetSet.isConectado());
                    TelaPrincipal.lblCheckTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led3.png")));
                    lblTelegram.setEnabled(true);
                    lblCheckNet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led2.png")));
                    lblNet.setForeground(Color.BLACK);
                    lblNet.setText("Internet: OK!");
                } else {
                    lblNet.setForeground(Color.red);
                    lblNet.setText("Internet: FALHA!");
                    lblTelegram.setEnabled(false);
                    lblCheckNet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led1.png")));
                }
            } catch (Exception ex) {                
                JOptionPane.showMessageDialog(null, "Falha no Telegram!");
                parar();

            }
            if (stop) {
                return;
            }

            try {
                Thread.sleep(1000); //
                TelaPrincipal.lblCheckTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/led0.png")));
            } catch (InterruptedException ex) {
            }
        }//while

    }

    public void parar() {
        this.stop = true;
    }

}
