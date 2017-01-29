/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.conexao;

import com.mysql.jdbc.Connection;
import java.io.InputStream;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;



/**
 *
 * @author matheus
 */
public class ConexaoReport {

    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection connection
    ) throws JRException {
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, connection);
        //Abre o JasperPrint em um JFrame
        viewReportFrame(titulo, print);
    }

    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource) throws JRException {
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, dataSource);

        //Abre o JasperPrint em um JFrame
        viewReportFrame(titulo, print);
    }

    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros
    ) throws JRException {
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros);
        //Abre o JasperPrint em um JFrame
        viewReportFrame(titulo, print);
    }

    /**
     * Cria um JFrame para exibir o relatório representado pelo JasperPrint.
     *
     * @param titulo Título do JFrame.
     * @param print JasperPrint do relatório.
     */
    private static void viewReportFrame(String titulo, JasperPrint print) {
        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer(print);

        // cria o JFrame
        JFrame framerelatorio = new JFrame(titulo);

        //Adiciona o JRViewer no JFrame
        framerelatorio.add(viewer, BorderLayout.CENTER);

        //Configura tamanho padrão do JFrame
        framerelatorio.setSize(500, 500);

        // maximiza o JFrame para ocupar a tela toda.
        framerelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // configura a operação padrão quando o JFrame for fechado.
        framerelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // exibe o JFrame
        framerelatorio.setVisible(true);
    }

}
