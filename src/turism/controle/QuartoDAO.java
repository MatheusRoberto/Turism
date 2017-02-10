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
import turism.conexao.Conexao;
import turism.modelo.Hotel;
import turism.modelo.Quarto;

/**
 *
 * @author matheus
 */
public class QuartoDAO {

    public QuartoDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Quarto q) {
        String sql = "INSERT INTO quarto (idhotel, camacasal, camasolteiro, observacao) VALUES(?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, q.getIdhotel().getIdhotel());
            stmt.setInt(2, q.getCamacasal());
            stmt.setInt(3, q.getCamasolteiro());
            stmt.setString(4, q.getObservacao());
            stmt.execute();
            q.setIdquarto(this.selecionaUltimo().getIdquarto());
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualiza(Quarto q) {
        String sql = "UPDATE quarto SET idhotel = ?, camacasal = ?, camasolteiro = ?, observacao = ? WHERE quarto.idquarto = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, q.getIdhotel().getIdhotel());
            stmt.setInt(2, q.getCamacasal());
            stmt.setInt(3, q.getCamasolteiro());
            stmt.setString(4, q.getObservacao());
            stmt.setInt(5, q.getIdquarto());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuartoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Quarto q) {
        String sql = "DELETE FROM quarto WHERE idquarto = '" + q.getIdquarto() + "';";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar quarto" + ex.getMessage());
            Logger.getLogger(QuartoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuartoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Quarto buscaQuarto(int id) {
        Quarto quarto = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM quarto WHERE idquarto = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                quarto = new Quarto();
                quarto.setIdquarto(result.getInt("idquarto"));
                quarto.setCamacasal(result.getInt("camacasal"));
                quarto.setCamasolteiro(result.getInt("camasolteiro"));
                quarto.setObservacao("observacao");
                Hotel hotel;
                HotelDAO hDAO = new HotelDAO();
                hotel = hDAO.buscaHotel(result.getInt("idhotel"));
                quarto.setIdhotel(hotel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no quarto" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return quarto;
    }

    private Quarto selecionaUltimo() {
        Quarto quarto = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idquarto) from quarto;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                quarto = buscaQuarto(result.getInt("max(idquarto)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Quarto " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return quarto;
    }

}
