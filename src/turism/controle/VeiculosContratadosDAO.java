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
import turism.modelo.Veiculo;
import turism.modelo.Veiculoscontratados;
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class VeiculosContratadosDAO {

    public VeiculosContratadosDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Veiculoscontratados v) {
        String sql = "INSERT INTO veiculoscontratados(viagem_idviagem, veiculo_idveiculo, valor) VALUES (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, v.getViagem().getIdviagem());
            stmt.setInt(2, v.getVeiculo().getIdveiculo());
            stmt.setDouble(3, v.getValor());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculosContratadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Veiculoscontratados v) {
        String sql = "UPDATE veiculoscontratados SET valor = ? WHERE veiculoscontratados.veiculo_idveiculo = ? "
                + "AND veiculoscontratados.viagem_idviagem = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, v.getValor());
            stmt.setInt(3, v.getVeiculo().getIdveiculo());
            stmt.setInt(4, v.getViagem().getIdviagem());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Veiculoscontratados v) {
        String sql = "DELETE FROM veiculoscontratados WHERE veiculoscontratados.veiculo_idveiculo = ? AND veiculoscontratados.viagem_idviagem = ?;";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, v.getVeiculo().getIdveiculo());
            stmt.setInt(2, v.getViagem().getIdviagem());
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar veiculo contratado " + ex.getMessage());
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Veiculoscontratados> buscaVeiculosContratados(int idv, int idve) {
        ArrayList<Veiculoscontratados> list = new ArrayList<>();
        Veiculoscontratados vc;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM veiculoscontratados WHERE veiculoscontratados.viagem_idviagem = ? OR veiculoscontratados.veiculo_idveiculo = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idv);
            stmt.setInt(2, idve);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                vc = new Veiculoscontratados();
                vc.setValor(result.getDouble("valor"));
                ViagemDAO vDAO = new ViagemDAO();
                Viagem viagem = vDAO.buscaViagem(result.getInt("viagem_idviagem"));
                vc.setViagem(viagem);
                VeiculoDAO veDAO = new VeiculoDAO();
                Veiculo veiculo = veDAO.buscaVeiculo(result.getInt("veiculo_idveiculo"));
                vc.setVeiculo(veiculo);
                list.add(vc);
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

        return list;
    }

    public Veiculoscontratados buscaVeiculoContratado(int idv, int idve) {
        Veiculoscontratados vc = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM veiculoscontratados WHERE veiculoscontratados.viagem_idviagem = ? OR veiculoscontratados.veiculo_idveiculo = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idv);
            stmt.setInt(2, idve);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                vc.setValor(result.getDouble("valor"));
                ViagemDAO vDAO = new ViagemDAO();
                Viagem viagem = vDAO.buscaViagem(result.getInt("viagem_idviagem"));
                vc.setViagem(viagem);
                VeiculoDAO veDAO = new VeiculoDAO();
                Veiculo veiculo = veDAO.buscaVeiculo(result.getInt("veiculo_idveiculo"));
                vc.setVeiculo(veiculo);
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

        return vc;
    }
}
