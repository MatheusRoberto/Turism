/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import turism.Log.geraLog;
import turism.controle.ConfiguracaoDAO;
import turism.modelo.Configuracoes;

/**
 *
 * @author matheus
 */
public class Backup_Turism {
    

    private void trocaLinha(String caminho) throws IOException {
        String arquivo = caminho;
        String arquivoTmp = caminho.replace(".sh", "-tmp.sh");

        BufferedReader reader;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp))) {
            reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("LOCAL")) {
                    linha = linha.replace("LOCAL", "cd " + getClass().getResource("/turism/backup/").getFile());
                } else if (linha.contains("PASTA_BACKUP")) {
                    linha = linha.replace("PASTA_BACKUP", getClass().getResource("/turism/backup/").getFile() + "arquivo/");
                }
                writer.write(linha + "\n");
            }
        }

        reader.close();

        new File(arquivo).delete();
        new File(arquivoTmp).renameTo(new File(arquivo));

    }

    public void backupBD() throws IOException {

        URL local = getClass().getResource("/turism/backup/backup.sh");

        Runtime run = Runtime.getRuntime();

        run.exec("mkdir " + getClass().getResource("/turism/backup/").getFile() + "/arquivo/");

        this.trocaLinha(local.getFile());

        final LocalShell shell = new LocalShell();

        run.exec("chmod a+x " + local.getFile());

        shell.executeCommand(local.getFile());
    }

    public void agendaBackupBD() throws IOException {
        ConfiguracaoDAO cDAO = new ConfiguracaoDAO();
        Configuracoes conf = cDAO.seleciona();
        if (!conf.getAgendadostatusbackup()) {
            this.geraScript();
            Runtime run = Runtime.getRuntime();
            URL local = getClass().getResource("/turism/backup/backup.sh");

            run.exec("at 22:00");
            run.exec(local.getFile());

            criaArquivo("Backup agendado at 22:00");

            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Calendar.HOUR_OF_DAY, 22);
            gc.set(Calendar.MINUTE, 0);
            gc.set(Calendar.SECOND, 0);
            Date agora = new Date();
            if (agora.after(gc.getTime())) {
                gc.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH)+1);
            }
            conf.setDatabackup(gc.getTime());
            conf.setAgendadostatusbackup(true);
            cDAO.atualizar(conf);
        }
    }

    public void geraScript() {
        File backupsh = new File(getClass().getResource("/turism/backup/").getFile() + "backup.sh");

        if (!backupsh.exists()) {
            try {
                backupsh.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Backup_Turism.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Editando e Salvando arquivo.
            FileWriter writer;
            try {
                writer = new FileWriter(backupsh);
                writer.write("#!/bin/bash\n"
                        + "\n"
                        + "echo \"Salvando local:\"\n"
                        + "# backup Path\n"
                        + "BACKUP_PATH=PASTA_BACKUP\n"
                        + "\n"
                        + "# database settings\n"
                        + "DB_NAME=\"turism\"\n"
                        + "DB_USER=\"root\"\n"
                        + "DB_PASS=\"madsr1411\"\n"
                        + "\n"
                        + "# current timestamp\n"
                        + "TIMESTAMP=$(date +%s)\n"
                        + "\n"
                        + "FILENAME=${DB_NAME}_${TIMESTAMP}.sql\n"
                        + "\n"
                        + "# svn up the content\n"
                        + "cd ${BACKUP_PATH}\n"
                        + "\n"
                        + "# remove previous backups\n"
                        + "rm ${BACKUP_PATH}*.sql\n"
                        + "rm ${BACKUP_PATH}*.gz\n"
                        + "\n"
                        + "# dump the database using the mysql administrator - so we can see all dbs\n"
                        + "#mysqldump -u$DB_USER -p$DB_PASS --opt --routines --skip-extended-insert --compact --force \"${DB_NAME}\" > \"${FILENAME}\"\n"
                        + "mysqldump -u$DB_USER -p$DB_PASS --force \"${DB_NAME}\" > \"${FILENAME}\"\n"
                        + "\n"
                        + "# compress file\n"
                        + "tar -czvf bkp_turism_${TIMESTAMP}.tar.gz ${FILENAME}\n"
                        + "\n"
                        + "echo \"Salvo e compactado!\"\n"
                        + "\n"
                        + "echo \"Subindo para Google Drive\"\n"
                        + "\n"
                        + "LOCAL\n"
                        + "\n"
                        + "python backup_initiator.py --backup-file-name \"bkp_turism\"\n"
                        + "\n"
                        + "echo -e \"\\nBackup feito: \"${TIMESTAMP} >> ${BACKUP_PATH}log_banco.txt");

                writer.close();
                System.out.println("Script gerado");
            } catch (IOException ex) {
                Logger.getLogger(Backup_Turism.class.getName()).log(Level.SEVERE, null, ex);
            }
            URL local = getClass().getResource("/turism/backup/backup.sh");

            Runtime run = Runtime.getRuntime();

            try {
                run.exec("mkdir " + getClass().getResource("/turism/backup/").getFile() + "/arquivo/");
                this.trocaLinha(local.getFile());

                run.exec("chmod a+x " + local.getFile());
            } catch (IOException ex) {
                Logger.getLogger(Backup_Turism.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void criaArquivo(String acao) {
        File arquivoTxt = new File(getClass().getResource("/turism/backup/arquivo/").getFile() + "log_banco.txt");

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
                writer.write("Ação:" + acao + "\n");
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

                writer.write("Ação:" + acao + "\n");
                writer.write("Data:" + new Date());
                writer.close();
                System.out.println("Arquivo salvado");
            } catch (IOException err) {
                Logger.getLogger(geraLog.class.getName()).log(Level.SEVERE, null, err);
            }
        }
    }

}
