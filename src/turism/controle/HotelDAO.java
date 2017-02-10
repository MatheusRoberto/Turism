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
import turism.modelo.Hotel;
import turism.modelo.Quarto;

/**
 *
 * @author matheus
 */
public class HotelDAO {

    public HotelDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Hotel h) {
        String sql = "INSERT INTO hotel(nome) VALUES(?,?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, h.getNome());
                stmt.execute();
                h.setIdhotel(this.selecionaUltimo().getIdhotel());
            }
            System.out.println("Hotel cadastrado com sucesso");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualiza(Hotel h) {
        String sql = "UPDATE hotel SET nome = ? WHERE hotel.idhotel = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, h.getNome());
            stmt.setInt(2, h.getIdhotel());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Hotel es) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM hotel WHERE idhotel = '" + es.getIdhotel() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar hotel" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
            }
        }
    }

    public ArrayList<Hotel> buscaHoteis(String nome) {
        ArrayList<Hotel> list = new ArrayList<>();
        Hotel hotel;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM hotel WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                hotel = new Hotel();
                hotel.setIdhotel(result.getInt("idhotel"));
                hotel.setNome(result.getString("nome"));
                list.add(hotel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no hotel" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return list;
    }

    public Hotel buscaHotel(int id) {
        Hotel hotel = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM hotel WHERE idhotel = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                hotel = new Hotel();
                hotel.setIdhotel(result.getInt("idhotel"));
                hotel.setNome(result.getString("nome"));
                hotel.setQuartoList(this.carregaQuarto(hotel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no hotel" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return hotel;
    }

    public ArrayList<Quarto> carregaQuarto(Hotel h) {
        ArrayList<Quarto> list = new ArrayList<>();
        Quarto quarto;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM quarto WHERE idHotel = '" + h.getIdhotel() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                quarto = new Quarto();
                quarto.setIdquarto(result.getInt("idquarto"));
                quarto.setCamacasal(result.getInt("camacasal"));
                quarto.setCamasolteiro(result.getInt("camasolteiro"));
                quarto.setObservacao(result.getString("observacao"));
                quarto.setIdhotel(h);
                list.add(quarto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no hotel_quarto " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return list;
    }

    private Hotel selecionaUltimo() {
        Hotel hotel = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idhotel) from hotel;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                hotel = buscaHotel(result.getInt("max(idhotel)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Hotel " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return hotel;
    }
}
