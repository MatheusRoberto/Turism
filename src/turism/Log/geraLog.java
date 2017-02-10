/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class geraLog {

    public void LogTxt(String nome, String classe) {
        File arquivoTxt = new File(getClass().getResource("/turism/Log/") + "log_programa.txt");

        if (!arquivoTxt.exists()) {
            try {
                //Cria o arquivo
                arquivoTxt.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Arquivo criado");

            //salva o arquivo
            FileWriter writer;
            try {
                writer = new FileWriter(arquivoTxt);
                writer.write("Problema:" + nome + "\n");
                writer.write("Classe:" + classe + "\n");
                writer.write("Data:" + new Date() + "\n");

                writer.close();
                System.out.println("Arquivo salvado");
            } catch (IOException ex) {
                Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                FileReader reader = new FileReader(arquivoTxt);
                BufferedReader br = new BufferedReader(reader);
                String linha = br.readLine();
                FileWriter writer = new FileWriter(arquivoTxt);

                while (linha != null) {
                    writer.write(linha + "\n");
                    br.readLine();
                    linha = br.readLine();
                }

                br.close();
                reader.close();

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
