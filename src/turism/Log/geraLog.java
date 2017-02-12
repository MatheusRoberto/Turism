/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import turism.shell.LocalShell;

/**
 *
 * @author matheus
 */
public class geraLog {

    public void LogTxt(String nome, String classe) {
        File arquivoTxt = new File(getClass().getResource("/log/").getFile() + "log_programa.txt");
        
         Formatter saida = new Formatter();
        try {
            //Cria o arquivo
            saida = new Formatter(arquivoTxt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (!arquivoTxt.exists()) {
            try {
                //Cria o arquivo
                System.err.println(arquivoTxt.getName());
                new LocalShell().executeCommand("touch " + arquivoTxt);
            } catch (IOException ex) {
                Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (arquivoTxt.exists()) {
            System.out.println("Arquivo criado");
            }else{
                System.out.println("Erro ao criar!");
            }
            //salva o arquivo
            
                saida.format("Problema:" + nome + "\n");
                saida.format("Classe:" + classe + "\n");
                saida.format("Data:" + new Date() + "\n");

                saida.close();
                System.out.println("Arquivo salvado");

        } else {
            try {
                FileWriter writer;
                try (FileReader reader = new FileReader(arquivoTxt); BufferedReader br = new BufferedReader(reader)) {
                    String linha = br.readLine();
                    writer = new FileWriter(arquivoTxt);
                    while (linha != null) {
                        writer.write(linha + "\n");
                        br.readLine();
                        linha = br.readLine();
                    }                  }

                writer.write("Problema:" + nome + "\n");
                writer.write("Classe:" + classe + "\n");
                writer.write("Data:" + new Date());
                writer.close();
                System.out.println("Arquivo salvado");
            } catch (IOException err) {
                Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, err);
            }
        }
    }
}
