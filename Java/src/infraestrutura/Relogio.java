/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infraestrutura;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.TelaMedicao;

/**
 *
 * @author Rick
 */
public class Relogio implements Runnable{
    public int conta = 0;
    public boolean stop = false;
    
    @Override
    public void run(){
        while(stop){
        try {
            Thread.sleep(1000);           
            if(conta >60){
                TelaMedicao med = new TelaMedicao();                
                 med.conectar(auxliarGetSet.getPortaSerial());              
                conta =0;
            }
            conta++;
        } catch (InterruptedException ex) {
            Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }  
}
