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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Logs;
import turism.modelo.Usuario;

/**
 *
 * @author matheus
 */
public class LogsDAO {

    public LogsDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Logs l) {
        String sql = "INSERT INTO (acao, data, hora, idusuario) VALUES (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, l.getAcao());
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            stmt.setTime(3, new java.sql.Time(System.currentTimeMillis()));
            stmt.setInt(4, l.getIdusuario().getIdusuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LogsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Logs> buscaLogs(String acao, Date i, Date f, Usuario u) {
        ArrayList<Logs> list = new ArrayList<>();
        Logs logs;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM logs WHERE (acao LIKE '" + acao + "%' OR idusuario = '" + u.getIdusuario() + ")"
                + " AND (data >= '" + i + "AND data <= '" + f + ") ORDER BY idusuario;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                logs = new Logs();
                logs.setIdlogs(result.getInt("idlogs"));
                logs.setAcao(result.getString("acao"));
                logs.setData(result.getDate("data"));
                logs.setHora(result.getDate("hora"));
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario usuario = uDAO.buscaUsuario(result.getInt("idusuario"));
                logs.setIdusuario(usuario);
                list.add(logs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no logs " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return list;
    }

    public Logs buscaLog(int id) {
        Logs logs = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM logs WHERE idlogs = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                logs = new Logs();
                logs.setIdlogs(result.getInt("idlogs"));
                logs.setAcao(result.getString("acao"));
                logs.setData(result.getDate("data"));
                logs.setHora(result.getTime("hora"));
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario usuario = uDAO.buscaUsuario(result.getInt("idusuario"));
                logs.setIdusuario(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro no Logs " + ex.getMessage());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return logs;
    }

    private Logs selecionaUltimo() {
        Logs logs = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idlogs) from logs;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                logs = buscaLog(result.getInt("max(idlogs)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no forma de logs " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return logs;
    }

}
