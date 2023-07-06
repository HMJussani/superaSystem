/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lefile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author hmjussani
 */
public class Lefile {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
      
      
       /* 
        String pagina = "";
        String path = "C://Users//hmjussani//Documents//WebTemp/index.html";
        try {
            FileInputStream stream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();

            while (linha != null) {
             System.out.println(linha);
                pagina = pagina + linha;
                linha = br.readLine();
            }
            garvarArquivo(pagina);
        } catch (Exception FileNotFoundException) {
            System.out.println("Arquivo não encontrado");
        }
*/
    }

    private static void garvarArquivo(String linha) throws IOException {
        FileWriter arq = new FileWriter("C:/Users/hmjussani/Documents/index.html");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(linha);
        arq.close();
    }

}
