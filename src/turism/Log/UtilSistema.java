/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class UtilSistema {

    Runtime run = Runtime.getRuntime();

    public void criaPasta() throws IOException {

        run = Runtime.getRuntime();
        File pBKP = new File("backup");
        if (!pBKP.exists()) {
            criaPasta(pBKP);
        }
        File pBKPA = new File("backup/arquivo");
        if (!pBKPA.exists()) {
            criaPasta(pBKPA);
        }
        File pLOg = new File("log");
        if (!pLOg.exists()) {
            criaPasta(pLOg);

        }
    }

    public void criaPasta(File caminho) {
        run = Runtime.getRuntime();
        if (!caminho.exists()) {
            try {
                run.exec("mkdir " + caminho);
            } catch (IOException ex) {
                Logger.getLogger(UtilSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void criaArquivo(File caminho) {
        run = Runtime.getRuntime();
        if (!caminho.exists()) {
            try {
                run.exec("touch " + caminho);
            } catch (IOException ex) {
                Logger.getLogger(UtilSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
