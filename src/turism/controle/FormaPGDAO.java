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
import turism.modelo.Formapagamento;

/**
 *
 * @author matheus
 */
public class FormaPGDAO {

    public FormaPGDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Formapagamento f) {
        String sql = "INSERT INTO (nome) VALUES (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareCall(sql);
            stmt.setString(1, f.getNome());
            stmt.execute();
            f.setIdformapagamento(this.selecionaUltimo().getIdformapagamento());
        } catch (SQLException ex) {
            Logger.getLogger(FormaPGDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Formapagamento f) {
        String sql = "UPDATE formapagamento SET nome = ? WHERE formapagamento.idformapagamento = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, f.getNome());
            stmt.setInt(2, f.getIdformapagamento());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FormaPGDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Formapagamento f) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM formapagamento WHERE idformapagamento = '" + f.getIdformapagamento() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar forma de pagamento " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                Logger.getLogger(FormaPGDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ArrayList<Formapagamento> buscaFormas(String nome) {
        ArrayList<Formapagamento> list = new ArrayList<>();
        Formapagamento form;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM formapagamento WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                form = new Formapagamento();
                form.setIdformapagamento(result.getInt("idformapagamento"));
                form.setNome(result.getString("nome"));
                list.add(form);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no forma de pagamento " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return list;
    }

    public Formapagamento buscaForma(int id) {
        Formapagamento forma = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM formapagamento WHERE idformapagamento = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                forma = new Formapagamento();
                forma.setIdformapagamento(result.getInt("idformapagamento"));
                forma.setNome(result.getString("nome"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro na Forma Pagamento " + ex.getMessage());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return forma;
    }

    private Formapagamento selecionaUltimo() {
        Formapagamento forma = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idformapagamento) from formapagamento;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                forma = buscaForma(result.getInt("max(idformapagamento)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no forma de pagamento " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return forma;
    }

}
