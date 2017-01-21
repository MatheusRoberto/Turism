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
import turism.modelo.Empresa;
import turism.modelo.Veiculo;

/**
 *
 * @author matheus
 */
public class VeiculoDAO {

    public VeiculoDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Veiculo v) {
        String sql = "INSERT INTO veiculo (nome, tipo, lotacao, idempresa) VALUES(?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, v.getNome());
            stmt.setString(2, v.getTipo());
            stmt.setInt(3, v.getLotacao());
            stmt.setInt(4, v.getIdempresa().getIdempresa());
            stmt.execute();
            v.setIdveiculo(this.selecionaUltimo().getIdveiculo());
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualiza(Veiculo v) {
        String sql = "UPDATE veiculo SET nome = ?, tipo = ?, lotacao = ?, idempresa = ? WHERE veiculo.idveiculo = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, v.getNome());
            stmt.setString(2, v.getTipo());
            stmt.setInt(3, v.getLotacao());
            stmt.setInt(4, v.getIdempresa().getIdempresa());
            stmt.setInt(5, v.getIdveiculo());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Veiculo v) {
        String sql = "DELETE FROM veiculo WHERE idveiculo = '" + v.getIdveiculo() + "';";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar veiculo " + ex.getMessage());
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Veiculo buscaVeiculo(int id) {
        Veiculo veiculo = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM veiculo WHERE idveiculo = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                veiculo = new Veiculo();
                veiculo.setIdveiculo(result.getInt("idveiculo"));
                veiculo.setNome(result.getString("nome"));
                veiculo.setTipo(result.getString("tipo"));
                veiculo.setLotacao(result.getInt("lotacao"));
                Empresa empresa;
                EmpresaDAO eDAO = new EmpresaDAO();
                empresa = eDAO.buscaEmpresa(result.getInt("idempresa"));
                veiculo.setIdempresa(empresa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro no veiculo " + ex.getMessage());
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return veiculo;
    }

    private Veiculo selecionaUltimo() {
        Veiculo veiculo = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idveiculo) from veiculo;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                veiculo = buscaVeiculo(result.getInt("max(idveiculo)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Veiculo " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return veiculo;
    }
}
