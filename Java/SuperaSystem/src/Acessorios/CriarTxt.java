/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acessorios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class CriarTxt {

    public void criarTxt(String path, String arquivo) {
        if (criaDir(path)) {
            File arq = new File(path + "\\" + arquivo + ".txt");
            try {
                if ((arq.exists())) {
                    int existe = JOptionPane.showConfirmDialog(null, "O Arquivo já existe. Sobrescrever?",
                            "Arquivo existente", JOptionPane.YES_NO_OPTION);
                    if (existe == JOptionPane.YES_OPTION) {
                        if (arq.delete()) {
                            JOptionPane.showMessageDialog(null, "Arquivo deletado.");
                        }
                    }
                } else {
                    arq.createNewFile();
                    escreverq(path, arq);
                    JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!");

                }
            } catch (IOException e) {
                System.out.println("Erro" + e);
            }
        }
       
    }

    private boolean criaDir(String dir) {
        boolean sucesso = false;
        if (!new File(dir).exists()) {
            new File(dir).mkdir();
        }
        if (new File(dir).exists()) {
            sucesso = true;
        }
        return sucesso;
    }
    
    private void escreverq(String content, File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);       
        bw.write(content);
        bw.close();
    }
    


   
}
