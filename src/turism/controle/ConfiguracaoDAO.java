/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turism.Log.geraLog;
import turism.conexao.Conexao;
import turism.modelo.Configuracoes;

/**
 *
 * @author matheus
 */
public class ConfiguracaoDAO {

    public ConfiguracaoDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void atualizar(Configuracoes c) {
        PreparedStatement stmt;
        String sql = "UPDATE configuracoes SET idorigem = ?, databackup = ?, agendadostatusbackup = ? "
                + "WHERE configuracoes.idconfiguracao = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, c.getIdorigem());
            if (c.getDatabackup() != null) {
                stmt.setDate(2, new java.sql.Date(c.getDatabackup().getTime()));
            } else {
                stmt.setDate(2, null);
            }
            stmt.setBoolean(3, c.getAgendadostatusbackup());
            stmt.setInt(4, c.getIdconfiguracao());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            geraLog g = new geraLog();
            g.LogTxt("erro na configuracoes " + ex.getMessage(), "ConfiguracoesDAO");
        }
    }

    public Configuracoes seleciona() {
        Configuracoes c = null;
        ResultSet result;
        PreparedStatement stmt;
        String sql = "SELECT * FROM configuracoes WHERE idconfiguracao = 1";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                c = new Configuracoes();
                c.setAgendadostatusbackup(result.getBoolean("agendadostatusbackup"));
                c.setDatabackup(result.getDate("databackup"));
                c.setIdorigem(result.getInt("idorigem"));                
                c.setIdconfiguracao(result.getInt("idconfiguracao"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no parcela " + e.getMessage());
            geraLog g = new geraLog();
            g.LogTxt("erro no configuracoes " + e.getMessage(), "ConfiguracoesDAO");
        }
        return c;
    }

}
