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
import turism.modelo.Hoteiscontratados;
import turism.modelo.Hotel;
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class HoteisContratadosDAO {

    public HoteisContratadosDAO() {
        this.connection = Conexao.getConnection();
    }
    
    private final Connection connection;
    
    public void adicionar(Hoteiscontratados h){
        String sql = "INSERT INTO hoteiscontratados(hotel_idHotel, viagem_idviagem, valor) VALUES (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, h.getHotel().getIdhotel());
            stmt.setInt(2, h.getViagem().getIdviagem());
            stmt.setDouble(3, h.getValor());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HoteisContratadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void atualizar(Hoteiscontratados h) {
        String sql = "UPDATE hoteiscontratados SET valor = ? WHERE hoteiscontratados.hotel_idHotel = ? "
                + "AND hoteiscontratados.viagem_idviagem = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, h.getValor());
            stmt.setInt(3, h.getHotel().getIdhotel());
            stmt.setInt(4, h.getViagem().getIdviagem());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HoteisContratadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Hoteiscontratados h) {
        String sql = "DELETE FROM hoteiscontratados WHERE hoteiscontratados.hotel_idHotel = ? AND hoteiscontratados.viagem_idviagem = ?;";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, h.getHotel().getIdhotel());
            stmt.setInt(2, h.getViagem().getIdviagem());
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar Hotel contratado " + ex.getMessage());
            Logger.getLogger(HoteisContratadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(HoteisContratadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Hoteiscontratados> buscaVeiculosContratados(int idv, int idh) {
        ArrayList<Hoteiscontratados> list = new ArrayList<>();
        Hoteiscontratados hc;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM hoteiscontratados WHERE hoteiscontratados.viagem_idviagem = ? OR hoteiscontratados.hotel_idHotel = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idv);
            stmt.setInt(2, idh);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                hc = new Hoteiscontratados();
                hc.setValor(result.getDouble("valor"));
                ViagemDAO vDAO = new ViagemDAO();
                Viagem viagem = vDAO.buscaViagem(result.getInt("viagem_idviagem"));
                hc.setViagem(viagem);
                HotelDAO hDAO = new HotelDAO();
                Hotel hotel = hDAO.buscaHotel(result.getInt("hotel_idHotel"));
                hc.setHotel(hotel);
                list.add(hc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Hotel contratado" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }

        return list;
    }

    public Hoteiscontratados buscaVeiculoContratado(int idv, int idh) {
        Hoteiscontratados hc = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM hoteiscontratados WHERE hoteiscontratados.viagem_idviagem = ? OR hoteiscontratados.hotel_idHotel = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idv);
            stmt.setInt(2, idh);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                hc.setValor(result.getDouble("valor"));
                ViagemDAO vDAO = new ViagemDAO();
                Viagem viagem = vDAO.buscaViagem(result.getInt("viagem_idviagem"));
                hc.setViagem(viagem);
                HotelDAO hDAO = new HotelDAO();
                Hotel hotel = hDAO.buscaHotel(result.getInt("hotel_idHotel"));
                hc.setHotel(hotel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Veiculo contratado" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }

        return hc;
    }
}
