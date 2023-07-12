/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acessorios;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author RMA
 */
public class Arquivos {
    
    
  
    public boolean criaDir(String dir) {
        boolean sucesso = false;
        if (!new File(dir).exists()) {
            new File(dir).mkdir();
            if (new File(dir).exists()) {
                sucesso = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pasta ou caminho já existe ...");
        }
        return sucesso;
    }
    
        public String setData() {
        String padrao = "yyyy-MM-dd";
        SimpleDateFormat dataPadrao = new SimpleDateFormat(padrao);
        return dataPadrao.format(new Date());
    }
    
        
     public String getPath() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String pathDir = null;
        int i = file.showSaveDialog(null);
        if (i == 0) {
            File arquivo = file.getSelectedFile();
            pathDir = arquivo.getPath()+ "\\";
        }
        return pathDir;
    }    
}
