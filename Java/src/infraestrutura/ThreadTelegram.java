/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;

/**
 *
 * @author Rick
 */
public class ThreadTelegram {

    private ChecaTelegram meuRunableSempreemLoop;

    public void iniciarTelegram() {
        meuRunableSempreemLoop = new ChecaTelegram();
        Thread minhathread = new Thread(meuRunableSempreemLoop);
        minhathread.start();
    }

    public void pararTelegram() {
         meuRunableSempreemLoop.parar();

    }

}
