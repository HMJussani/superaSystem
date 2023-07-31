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
public class CriarSevenTxt{  
    SevenArq seven = new SevenArq();

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

    public Boolean criarBurnTxt(String path, String idOrdServ, String model, String patEquip, String nserie, String tecnico) {
        Boolean sucesso = false;
        if (criaDir(path)) {
            File arq = new File(path + "\\" + patEquip + ".txt");
            try {
                if ((arq.exists())) {
                    int existe = JOptionPane.showConfirmDialog(null, "O Arquivo já existe. Sobrescrever?",
                            "Arquivo existente", JOptionPane.YES_NO_OPTION);
                    if (existe == JOptionPane.YES_OPTION) {
                        arq.delete();
                        arq.createNewFile();
                       // escreverq(arq, idOrdServ, model, patEquip, nserie, tecnico);
                        sucesso = true;
                    }
                    return false;
                } else {
                    arq.createNewFile();
                    //escreverq(arq, idOrdServ, model, patEquip, nserie, tecnico);
                    sucesso = true;
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar TXT: " + e);
            }
        }
        return sucesso;
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

    private void escreverqPeca(File file,String placaMae, String processador, String memoria, String pico, String armazenamento, String so, String expansao ) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(seven.arqSevenPeca(placaMae, processador, memoria, pico, armazenamento, so, expansao));
        bw.close();
    }

    private void escreverq(String arq, File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(arq);
        bw.close();
    }

}
