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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Cidade;
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class ViagemDAO {

    public ViagemDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Viagem v) {
        String sql = "INSERT INTO viagem (dataida, datavolta, horariosaida, descricao, idorigem, iddestino, vagasextras) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(v.getDataida().getTime()));
            stmt.setDate(2, new java.sql.Date(v.getDatavolta().getTime()));
            stmt.setTime(3, new java.sql.Time(v.getHorariosaida().getTime()));
            stmt.setString(4, v.getDescricao());
            stmt.setInt(5, v.getIdorigem().getIdcidade());
            stmt.setInt(6, v.getIddestino().getIdcidade());
            stmt.setInt(7, v.getVagasextras());
            stmt.execute();
            v.setIdviagem(this.selecionaUltimo().getIdviagem());
        } catch (SQLException ex) {
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Viagem v) {
        String sql = "UPDATE viagem SET dataida = ?, datavolta = ?, horariosaida = ?, descricao = ?, idorigem = ?, iddestino = ?, vagasextras = ?"
                + "WHERE viagem.idviagem = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(v.getDataida().getTime()));
            stmt.setDate(2, new java.sql.Date(v.getDatavolta().getTime()));
            stmt.setTime(3, new java.sql.Time(v.getHorariosaida().getTime()));
            stmt.setString(4, v.getDescricao());
            stmt.setInt(5, v.getIdorigem().getIdcidade());
            stmt.setInt(6, v.getIddestino().getIdcidade());
            stmt.setInt(7, v.getVagasextras());
            stmt.setInt(8, v.getIdviagem());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(Viagem v) {
        String sql = "DELETE FROM viagem WHERE idviagem = '" + v.getIdviagem() + "';";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar Viagem " + ex.getMessage());
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Viagem buscaViagem(int origem, int destino) {
        Viagem viagem = null;
        PreparedStatement stmt = null;
        ResultSet result;
        String sql = "SELECT * FROM viagem WHERE idorigem = '" + origem + "' AND iddestino = '" + destino + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                viagem = new Viagem();
                viagem.setDataida(result.getDate("dataida"));
                viagem.setDatavolta(result.getDate("datavolta"));
                viagem.setHorariosaida(result.getDate("horariosaida"));
                viagem.setDescricao(result.getString("descricao"));
                viagem.setVagasextras(result.getInt("vagasextras"));
                CidadeDAO cDAO = new CidadeDAO();
                Cidade cidade = cDAO.buscaCidade(result.getInt("idorigem"));
                viagem.setIdorigem(cidade);
                cidade = cDAO.buscaCidade(result.getInt("iddestino"));
                viagem.setIddestino(cidade);
                viagem.setIdviagem(result.getInt("idviagem"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viagem;
    }

    public ArrayList<Cidade> buscaOrigem() {
        ArrayList<Cidade> list = new ArrayList<>();
        Cidade cidade;
        PreparedStatement stmt;
        ResultSet result;
        String sql = "SELECT * FROM viagem ;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                CidadeDAO cDAO = new CidadeDAO();
                cidade = cDAO.buscaCidade(result.getInt("idorigem"));;
                list.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Cidade> buscaDestino(int origem) {
        ArrayList<Cidade> list = new ArrayList<>();
        Cidade cidade;
        PreparedStatement stmt;
        ResultSet result;
        String sql = "SELECT * FROM viagem WHERE idorigem = '" + origem + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                CidadeDAO cDAO = new CidadeDAO();
                cidade = cDAO.buscaCidade(result.getInt("iddestino"));
                list.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Viagem buscaViagem(int id) {
        Viagem viagem = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM viagem WHERE idviagem = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                viagem = new Viagem();
                viagem.setIdviagem(result.getInt("idviagem"));
                viagem.setDataida(result.getDate("dataida"));
                viagem.setDatavolta(result.getDate("datavolta"));
                viagem.setDescricao(result.getString("descricao"));
                viagem.setHorariosaida(result.getDate("horariosaida"));
                CidadeDAO cDAO = new CidadeDAO();
                Cidade cidade = cDAO.buscaCidade(result.getInt("idorigem"));
                viagem.setIdorigem(cidade);
                cidade = cDAO.buscaCidade(result.getInt("iddestino"));
                viagem.setIddestino(cidade);
                viagem.setVagasextras(result.getInt("vagasextras"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro na Viagem " + ex.getMessage());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return viagem;
    }

    private Viagem selecionaUltimo() {
        Viagem viagem = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idviagem) from viagem;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                viagem = buscaViagem(result.getInt("max(idviagem)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro na viagem " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return viagem;
    }
}
