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
public class ControlaThread {

    private TrataThread minhaThread;

    public void iniciathread() {
        minhaThread = new TrataThread();
        Thread minhathread = new Thread(minhaThread);
        minhathread.start();
    }

    public void parathread() {
        minhaThread.parar();

    }
    
}
