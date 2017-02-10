/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import turism.gui.TelaMenuPrincipal;
import turism.shell.Backup_Turism;

/**
 *
 * @author matheus
 */
public class Turism {

    public static void main(String args[]) {
        JFrame janela = new TelaMenuPrincipal();
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);

        Backup_Turism bkp = new Backup_Turism();
        try {
            bkp.agendaBackupBD();
        } catch (IOException ex) {
            Logger.getLogger(Turism.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
