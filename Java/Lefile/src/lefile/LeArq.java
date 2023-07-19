/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lefile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author RMA
 */
public class LeArq {

    public String leArq(String path) {        
        String linha;
        String lido ="";
        try {
            FileReader ler = new FileReader(path+"seven.txt");
            BufferedReader reader = new BufferedReader(ler);
            while ((linha = reader.readLine()) != null) {
                lido += linha+"\n";
            }
        } catch (IOException ex) {
            System.out.println("Erro: " +ex);
        }
        return lido;
    }

}
